/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.se1625.tblaccount.TblAccountDTO;
import com.se1625.usergoogle.UserGoogleDTO;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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
}
