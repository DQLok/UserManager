/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locdq.users.UsersDAO;
import locdq.users.UsersDTO;
import locdq.utils.ChangeDataTypes;
import org.apache.log4j.Logger;

/**
 *
 * @author test
 */
public class LoginServlet extends HttpServlet {
    
      static final Logger LOGGER=Logger.getLogger(LoginServlet.class);//ghi file log    

    private final String ERROR = "login.jsp";
    private final String SUCCESS = "LoadDataServlet";

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
        try {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            UsersDAO dao = new UsersDAO();
            String changePassword = ChangeDataTypes.bytesToHex(password);
            UsersDTO user = dao.checkLogin(userID, changePassword);
            if (user != null) {
                if (user.getStatusDTO().getStatusID().equals("s2")) {
                    request.setAttribute("ERRORLOGIN", "Admin has issued a ban on you!!!");
                     LOGGER.warn("Admin baned account!");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                    LOGGER.info("User: "+user.getUsername()+" Login Success");
                    url = SUCCESS;
                }
            }else {
                request.setAttribute("ERRORLOGIN", "UserID or Password invalid!!!");
                LOGGER.warn("Account Invalid!");
            }
        } catch (Exception e) {            
            //log("Error in LoginServlet" + e.getMessage());
            LOGGER.error(e);
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
