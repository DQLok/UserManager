/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javafx.scene.shape.Path;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import locdq.roles.RolesDTO;
import locdq.status.StatusDTO;
import locdq.users.UserErrorDTO;
import locdq.users.UsersDAO;
import locdq.users.UsersDTO;
import locdq.utils.ChangeDataTypes;
import locdq.utils.InsertPicture;

/**
 *
 * @author test
 */
@MultipartConfig
public class UpdateServlet extends HttpServlet {

    private final String ERROR = "";
    private final String SUCCESS = "SearchServlet";

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
        String url = ERROR;
        UserErrorDTO error = new UserErrorDTO("", "", "", "", "", "", new RolesDTO("", ""), new StatusDTO("", ""));
        boolean check = true;
        try {
            String userID = request.getParameter("userID");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String passwordsql = request.getParameter("passwordsql");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String photoinsql = request.getParameter("photoinsql");
            String roleID = request.getParameter("roleID");
            String rolenameOfuser = request.getParameter("rolenameOfuser");
            String statusID = request.getParameter("statusID");
            String statusname = request.getParameter("statusname");

            Part filePart = request.getPart("photo");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            int lastdoc = fileName.lastIndexOf(".");
            String photosql = "";
            if (!fileName.equals("")) {
                if (fileName.substring(lastdoc).equalsIgnoreCase(".png") || fileName.substring(lastdoc).equalsIgnoreCase(".jpg")) {
                    photosql = InsertPicture.writeImage(request, userID, filePart, fileName.substring(lastdoc));
                } else {
                    check = false;
                    error.setPhotoER("We only accept file .png or jpg");
                }
            } else {
                photosql = photoinsql;
            }

            if (!username.matches("^[\\p{L} .'-]+$") || username.length() > 50 || username.length() < 1) {
                check = false;
                error.setUsernameER(username.toString() + ": UserName in alphabet and [2..50]");
            }
            
            String changePassword="";
            if (password.equals("")) {
                changePassword = passwordsql;
            } else {
                if (password.length() < 1 || password.length() > 20) {
                    check = false;
                    error.setPasswordER(password.toString() + ": Password must be in [1..20]");
                }
               changePassword  = ChangeDataTypes.bytesToHex(password);
            }
            
            if (!email.matches("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$")) {
                check = false;
                error.setEmailER(email.toString() + ": Emails must be written in the format __@___.__ ");
            }
            if (!phone.matches("^[0-9]{10}")) {
                check = false;
                error.setPhoneER(phone.toString() + ": Phone must have 10 numbers ");
            }

            
            UsersDTO dto = new UsersDTO(userID, username, changePassword, email, phone, photosql, new RolesDTO(roleID, rolenameOfuser), new StatusDTO(statusID, statusname));
            UsersDAO dao = new UsersDAO();
            if (!check) {
                request.setAttribute("ERROR", error);
                request.setAttribute("USERID", userID);
            } else {
                dao.UpdateUser(dto);
                request.setAttribute("SUCCESS", "UPDATE SUCCESSFUL!!!");
                request.setAttribute("USERID", userID);
            }
            url = SUCCESS;
        } catch (Exception e) {
            log("Error in UpdateServlet" + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
