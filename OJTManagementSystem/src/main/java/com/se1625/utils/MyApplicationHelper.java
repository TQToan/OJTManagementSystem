/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.tblapplication.TblApplicationDTO;
import com.se1625.tblstudent.TblStudentDTO;
import com.se1625.usergoogle.UserGoogleDTO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
public class MyApplicationHelper {

    public static void getSiteMaps(ServletContext context) throws IOException {
        //1. get siteMaps file
        String siteMapsFile = context.getInitParameter("SITE_MAPS_FILE_PATH");
        //2. load properties from context and siteMapsFile to getResourceAsStream
        InputStream is = null;
        is = context.getResourceAsStream(siteMapsFile);
        Properties properties = new Properties();
        properties.load(is);

        //3. tạo attribute trong contextScope
        context.setAttribute("SITE_MAPS", properties);
    }

    public static String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public static boolean sendEmail(TblAccountDTO company, TblAccountDTO school)
            throws AddressException, MessagingException {
        boolean test = false;

        String toEmail = company.getEmail();
        final String fromEmail = school.getEmail();
        final String password = school.getPassword();
        Properties pr = new Properties();
        pr.setProperty("mail.smtp.host", "smtp.gmail.com");
        pr.setProperty("mail.smtp.port", "587");
        pr.setProperty("mail.smtp.auth", "true");
        pr.setProperty("mail.smtp.starttls.enable", "true");
        pr.put("mail.smtp.socketFactory.port", "587");
        pr.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        //get session
        Session session = Session.getInstance(pr, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }

        });

        Message mess = new MimeMessage(session);
        mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        mess.setFrom(new InternetAddress(fromEmail));

        mess.setSubject("User Email Verification");
        mess.setText("Registered successfully. Please verify your account using this code: " + company.getVerifyCode());
        Transport.send(mess);
        test = true;

        return test;
    }

    public static String createIdCompany(String lastID) {
        if (lastID == null) {
            lastID = "FPT001";
            return lastID;
        } else {
            // tách chuỗi ID và số riêng
            String prefix = lastID.substring(0, 3);
            String subnumber = lastID.substring(lastID.indexOf("T") + 1);
            // convert chuỗi gồm 4 số về số 
            int number = Integer.parseInt(subnumber);
            // tăng 1 đơn vị rồi convert lại thành chuỗi  
            String nextNumber = "" + (number + 1);
            // nối với chuỗi prefix ban đầu
            int length = subnumber.length() - nextNumber.length();
            String newId = prefix;
            for (int i = 0; i < length; i++) {
                newId = newId + "0";
            }
            newId = newId + nextNumber;
            return newId;
        }
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(MyApplicationConstants.Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", MyApplicationConstants.Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", MyApplicationConstants.Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", MyApplicationConstants.Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", MyApplicationConstants.Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = MyApplicationConstants.Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDTO googlePojo = new Gson().fromJson(response, UserGoogleDTO.class);

        return googlePojo;
    }

    public static String getStudentCode(String username) {
        int end = username.indexOf("@");
        int begin = end - 8;
        String StudentCode = username.substring(begin, end).toUpperCase();
        return StudentCode;
    }
    
    public static List<TblStudentDTO> readExcel(String excelFilePath) throws FileNotFoundException, IOException, IllegalArgumentException {
        List<TblStudentDTO> studentList = new ArrayList<>();

        //get File
        InputStream inputStream = new FileInputStream(excelFilePath);

        //get wordbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        //get sheet
        Sheet sheet = workbook.getSheetAt(0);

        //get all rows
        Iterator<Row> interator = sheet.iterator();
        while (interator.hasNext()) {
            Row nextRow = interator.next();
            if (nextRow.getRowNum() == 0) {
                //ignore header
                continue;
            }

            //get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            //read cells and set value for tblStudentDTO object
            TblStudentDTO student = new TblStudentDTO();
            TblAccountDTO account = new TblAccountDTO();
            while (cellIterator.hasNext()) {
                //read cell
                Cell cell = cellIterator.next();
                
                //set value for tblaccountDTO object
                int columnIndex = cell.getColumnIndex();
                switch(columnIndex) {
                    case 0:
                        String studentCode = cell.toString();
                        student.setStudentCode(studentCode);
                        break;
                    case 1:
                        String fullName = cell.toString();
                        account.setName(fullName);
                        break;
                    case 2:
                        String major = cell.toString();
                        student.setMajor(major);
                        break;
                    case 3:
                        String email = cell.toString();
                        account.setEmail(email);
                    case 4:
                        String phone = cell.toString();
                        student.setPhone(phone);
                        break;
                    case 5:
                        int credit = (int) cell.getNumericCellValue();
                        student.setNumberOfCredits(credit);
                        break;
                    default:
                        break;
                }
            }
            student.setAccount(account);
            studentList.add(student);
        }
        workbook.close();
        inputStream.close();
        return studentList;
    }
    
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workbook;
    }
    
    public static void writeHeaderLine(Sheet sheet, int rowIndex) {
        //create cellstyle 
        CellStyle cellStyle = createStyleForHeader(sheet);

        //create row 
        Row row = sheet.createRow(rowIndex);
         
        //create cells
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Stdudent Code");
        

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Major");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Company Name");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Job");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Grade");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Evaluation");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Status");
    }
    
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
    
    public static void writeStudentEvaluation(TblApplicationDTO application, Row row) {
        short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
        // DataFormat df = workbook.createDataFormat();
        // short format = df.getFormat("#,##0");
        CellStyle cellStyleFormatNumber = null;
        //Create CellStyle
        Workbook workbook = row.getSheet().getWorkbook();
        cellStyleFormatNumber = workbook.createCellStyle();
        cellStyleFormatNumber.setDataFormat(format);
        
        Cell cell = row.createCell(0);
        cell.setCellValue(application.getStudent().getStudentCode());
        
        cell = row.createCell(1);
        cell.setCellValue(application.getStudent().getMajor());
        
        
        cell = row.createCell(2);
        cell.setCellValue(application.getCompanyPost().getCompany()
                .getAccount().getName());
        
        
        cell = row.createCell(3);
        cell.setCellValue(application.getCompanyPost().
                getMajor().getMajorName());
        
        
        cell = row.createCell(4);
        cell.setCellValue(application.getGrade());
        
        
        cell = row.createCell(5);
        cell.setCellValue(application.getEvaluation());
        
        
        if (application.isIsPass() == true) {
            cell = row.createCell(6);
            cell.setCellValue("Passed");
        } else {
            cell = row.createCell(6);
            cell.setCellValue("Not Pass");
        }
        
    }
    
    public static void createExcelFile(Workbook workbook, String excelPath) throws FileNotFoundException, IOException {
        FileOutputStream outputStream = new FileOutputStream(excelPath);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
