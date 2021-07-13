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
import locdq.promotions.PromotionsDAO;
import locdq.promotions.PromotionsDTO;
import locdq.users.UsersDTO;

/**
 *
 * @author test
 */
public class LoadPromotionsForDate extends HttpServlet {

    private final String ERROR = "";
    private final String SUCCESS = "dayspromotion.jsp";

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
            String assignmentdate = request.getParameter("assignmentdate");
            if (assignmentdate == null) {
                assignmentdate = "";
            }
            HttpSession session = request.getSession();
            UsersDTO user = (UsersDTO) session.getAttribute("USER");
            PromotionsDAO dao = new PromotionsDAO();
            List<PromotionsDTO> list = dao.getListByDate(user, assignmentdate);
            if (list != null) {
                session.setAttribute("LIST_PROMOTIONBYDATE", list);
                url = SUCCESS;
            }
            if (list.size()==0 && !assignmentdate.equals("")){
                request.setAttribute("DATENULL", "User don't have Promotion in date");
            }
        } catch (Exception e) {
            log("Error in LoadPromotionsForDate" + e.getMessage());
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
