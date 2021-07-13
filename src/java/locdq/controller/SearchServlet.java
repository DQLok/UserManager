/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locdq.users.UsersDAO;
import locdq.users.UsersDTO;

/**
 *
 * @author test
 */
public class SearchServlet extends HttpServlet {

    private final String ERROR = "LoadDataServlet";
    private final String SUCCESS = "search.jsp";
    private final String SUCCESS_PROFILE="LoadDataServlet";

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
            String txtSearchValue = request.getParameter("txtSearchValue");
            String rolename=request.getParameter("rolename");
            if (rolename.equals("profile")){
                    url=SUCCESS_PROFILE;
            }else{
            if (rolename.equals("All")){
                rolename="";
            }
            UsersDAO dao = new UsersDAO();
            List<UsersDTO> list = dao.getListUser(txtSearchValue,rolename);
            if (list != null) {
                HttpSession session = request.getSession();
                session.setAttribute("LIST_USERS", list);
                request.setAttribute("ROLE", rolename);
                //-----
                request.setAttribute("SEARCH_VALUE", txtSearchValue);
                url=SUCCESS;
            }
            }
        } catch (Exception e) {
            log("Error in SearchServlet" + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            //response.sendRedirect(url);
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
