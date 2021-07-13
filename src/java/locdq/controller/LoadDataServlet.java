/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locdq.roles.RolesDAO;
import locdq.roles.RolesDTO;
import locdq.typepromotions.TypePromotionsDAO;
import locdq.typepromotions.TypePromotionsDTO;
import locdq.users.UsersDAO;
import locdq.users.UsersDTO;

/**
 *
 * @author test
 */
@WebServlet(name = "LoadDataServlet", urlPatterns = {"/LoadDataServlet"})
public class LoadDataServlet extends HttpServlet {

    private final String ERROR = "";
    private final String SUCCESS = "search.jsp";
    private final String SUCCESS_USER= "profile.jsp";
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
            HttpSession session = request.getSession();
            RolesDAO daoroles = new RolesDAO();
            List<RolesDTO> listroles = daoroles.getListRole("");
            session.setAttribute("LIST_ROLES", listroles);

            TypePromotionsDAO daotp=new TypePromotionsDAO();
            List<TypePromotionsDTO> listtp=daotp.getTypePromotions();
            session.setAttribute("LIST_TYPEPROMOTIONS", listtp);
            
            UsersDTO user = (UsersDTO) session.getAttribute("USER");
            
            String roleID = user.getRoleDTO().getRoleID();
            UsersDAO dao = new UsersDAO();
            switch (roleID) {
                case "r1":                    
                    List<UsersDTO> list = dao.getListUser("", "");
                    session.setAttribute("LIST_USERS", list);
                    url = SUCCESS;
                    break;
                case "r2":
                    UsersDTO dto=dao.getUserByUserID(user.getUserID());
                    session.setAttribute("USER", dto);
                    url=SUCCESS_USER;
                    break;
            }

            
        } catch (Exception e) {
            log("Error in LoadDAtaServlet"+e.getMessage());
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
