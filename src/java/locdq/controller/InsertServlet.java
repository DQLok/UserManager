/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import locdq.roles.RolesDAO;
import locdq.roles.RolesDTO;
import locdq.status.StatusDAO;
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
public class InsertServlet extends HttpServlet {

    private final String ERROR = "";
    private final String SUCCESS = "insert.jsp";

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
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String roleID = request.getParameter("roleID");
            String statusID = "s1";
            String confirm = request.getParameter("confirm");

            Part filePart = request.getPart("photo");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();            
            int lastdoc=fileName.lastIndexOf(".");
            String photosql="";
            if (fileName.substring(lastdoc).equalsIgnoreCase(".png") || fileName.substring(lastdoc).equalsIgnoreCase(".jpg")){
                photosql=InsertPicture.writeImage(request, userID, filePart, fileName);
            }else{
                check=false;
                error.setPhotoER("We only accept file .png or jpg");
            }

            if (!userID.matches("se[0-9]{1,}")){
                check=false;
                error.setUserIDER("UserID has format se__ (se + number)");
            }
            if (!username.matches("^[\\p{L} .'-]+$") || username.length() > 50 || username.length() < 2) {
                check = false;
                error.setUsernameER("UserName in alphabet and [2..50]");
            }
            if (password.length() < 1 || password.length() > 20) {
                check = false;
                error.setPasswordER("Password must be in [1..20]");
            }
            if (!email.matches("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$")) {
                check = false;
                error.setEmailER("Emails must be written in the format __@___.__ ");
            }
            if (!phone.matches("^[0-9]{10}")) {
                check = false;
                error.setPhoneER("Phone must have 10 numbers ");
            }
            RolesDAO daoro = new RolesDAO();
            List<RolesDTO> role = daoro.getListRole(roleID);

            StatusDAO daosta = new StatusDAO();
            List<StatusDTO> status = daosta.getListStatus(statusID);
            
            String changePassword=ChangeDataTypes.bytesToHex(password);
            UsersDTO dto = new UsersDTO(userID, username, changePassword, email, phone, photosql, role.get(0), status.get(0));

            UsersDAO dao = new UsersDAO();
            if (!confirm.equals(password)) {
                check = false;
                request.setAttribute("CONFIRM", "Confirm not match!!!");
            }
            if (dao.checkUserID(userID)) {
                check = false;
                error.setUserIDER("UserID is exist!!!");
            }
            if (!check) {
                request.setAttribute("ERROR", error);
            } else {
                dao.InsertUser(dto);
                request.setAttribute("SUCCESS", "Insert Successfully!!!");
            }
            url = SUCCESS;
        } catch (Exception e) {
            log("Error in InsertServlet" + e.getMessage());
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
