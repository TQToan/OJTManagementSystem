/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.controller;

import com.se1625.tblapplication.ApplyCVStudentError;
import com.se1625.tblapplication.TblApplicationDAO;
import com.se1625.tblapplication.TblApplicationDTO;
import com.se1625.tblcompany_post.TblCompany_PostDAO;
import com.se1625.tblcompany_post.TblCompany_PostDTO;
import com.se1625.tblstudent.TblStudentDAO;
import com.se1625.tblstudent.TblStudentDTO;
import com.se1625.utils.MyApplicationConstants;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
public class ApplyCVStudentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext context = this.getServletContext();
        Properties properties = (Properties) context.getAttribute("SITE_MAPS");
        //check session và lấy attribute
        HttpSession session = request.getSession(false);
        String url = properties.getProperty(MyApplicationConstants.ApplyCVStudentFeature.HOME_AFTER_CLICK1_PAGE);
        try {
            if (session != null) {
              TblStudentDTO student = (TblStudentDTO) session.getAttribute("ACCOUNT");
//            TblStudentDAO dao = new TblStudentDAO();
//            TblStudentDTO student = dao.getStudent("SE151252");
            if (student != null) {
                // get parameters
                // Create a factory for disk-based file items
                DiskFileItemFactory factory = new DiskFileItemFactory();

                // Configure a repository (to ensure a secure temp location is used)
                ServletContext servletContext = this.getServletConfig().getServletContext();
                File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                factory.setRepository(repository);

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);

                // Parse the request
                List<FileItem> items = upload.parseRequest(request);

                Iterator<FileItem> iter = items.iterator();
                HashMap<String, String> params = new HashMap<>();
                String name = "";
                String value = "";
                String fileName = "";
                String cvName = "";
                long fileLength = 0;
                String filePath = "";
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (item.isFormField()) {
                        name = item.getFieldName();
                        value = item.getString("UTF-8");
                        params.put(name, value);
                    } else {
                        fileName = item.getName();
                        if (fileName == null || fileName.equals("")) {
                            break;
                        } else {
                            Path path = Paths.get(fileName);
                            String realPath = request.getServletContext().getRealPath("/CVs");
                            cvName = student.getStudentCode() + "_" + path.getFileName().toString();
                            File uploadFile = new File(realPath + "/" + cvName);
                            filePath = uploadFile.toString();
                            
                            if (Files.exists(Paths.get(realPath)) == false) {
                                Files.createDirectories(Paths.get(realPath));
                            }
                            item.write(uploadFile);
                            fileLength = Files.size(Paths.get(filePath));
                        }
                    }
                }
                String expectedJob = params.get("txtExpectedJob");
                String technology = params.get("txtTechnology");
                String experience = params.get("txtExperience");
                String foreignLanguage = params.get("txtForeignLanguage");
                String otherSkills = params.get("txtOtherSkills");
                int portID = Integer.parseInt(params.get("postID"));
                ApplyCVStudentError errors = new ApplyCVStudentError();
                boolean found = false;

                if (expectedJob.trim().length() < 6 || expectedJob.trim().length() > 50) {
                    found = true;
                    errors.setExpectedJobLengthError("Expected Job is required 6-50 characters");
                }

                if (technology.trim().length() < 6 || technology.trim().length() > 50) {
                    found = true;
                    errors.setTechnologyLengthError("Technology is required 6-50 characters");
                }

                if (foreignLanguage.trim().length() < 6 || foreignLanguage.trim().length() > 50) {
                    found = true;
                    errors.setForeignLanguageLengthError("Foreign Language is required 6-50 characters");
                }

                if (otherSkills.trim().length() < 6 || otherSkills.trim().length() > 50) {
                    found = true;
                    errors.setOtherSkillsLengthError("Other skills is required 6-50 characters");
                }

                if (cvName.trim().equals("")) {
                    found = true;
                    errors.setFileUploadError("File is not empty");
                } else {
                    System.out.println(cvName);
                    if (cvName.endsWith("doc") == false
                            && cvName.endsWith("docx") == false
                            && cvName.endsWith("pdf") == false) {
                        found = true;
                        errors.setFileUploadTypeError("File is required .doc, .docx, .pdf");
                    }
                    System.out.println(fileLength);
                    fileLength = (fileLength / (1024*1024));
                    long sizeMax =  1;
                    if (fileLength > sizeMax) {
                        found = true;
                        errors.setFileUploadLengthError("File's length is required less than 1MB");
                    }
                }

                TblStudentDAO studentDAO = new TblStudentDAO();
                TblStudentDTO studentInformation = studentDAO.
                        getStudentInformation(student.getStudentCode());
                request.setAttribute("STUDENT_INFORMATION", studentInformation);

                TblApplicationDTO application = new TblApplicationDTO();
                application.setAttachmentPath(cvName);
                application.setExpected_job(expectedJob);
                application.setExperience(experience);
                application.setForeign_Language(foreignLanguage);
                application.setOtherSkills(otherSkills);
                application.setTechnology(technology);
                application.setStudent(student);

                TblCompany_PostDAO companyPostDAO = new TblCompany_PostDAO();
                TblCompany_PostDTO companyPost = companyPostDAO.getCompanyPost(portID);
                application.setCompanyPost(companyPost);

                request.setAttribute("APPLICATION_INFORMATION", application);

                if (found) {
                    if (cvName.trim().isEmpty() == false) {
                        Files.deleteIfExists(Paths.get(filePath));
                    }
                    request.setAttribute("ERRORS", errors);
                } else {
                    TblApplicationDAO applicationDAO = new TblApplicationDAO();
                    boolean result = applicationDAO.addApplication(application);
                    if (result) {
                        url = properties.getProperty(MyApplicationConstants.ApplyCVStudentFeature.STUDENT_APPLIED_JOB_PAGE);
                    }
                }
            }
            }
        } catch (SQLException ex) {
            log("SQLException occurs ApplyCVStudentServlet " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException occurs ApplyCVStudentServlet " + ex.getMessage());
        } catch (Exception ex) {
            log("Exception occurs ApplyCVStudentServlet " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}