/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locdq.promotions.PromotionsDTO;
import locdq.typepromotions.TypePromotionsDTO;
import locdq.users.UsersDAO;
import locdq.users.UsersDTO;

/**
 *
 * @author test
 */
public class AddPromotionServlet extends HttpServlet {

    private final String ERROR = "SearchServlet";
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
        try {
            HttpSession session = request.getSession();
            String userID = request.getParameter("userID");
            UsersDAO dao = new UsersDAO();
            UsersDTO user = dao.getUserByUserID(userID);
            int rank = 5;
            long millis = System.currentTimeMillis();
            java.sql.Date assignmentdate = new java.sql.Date(millis);
            PromotionsDTO pro = new PromotionsDTO(new TypePromotionsDTO(),user, rank, assignmentdate);

            List<PromotionsDTO> list=(List<PromotionsDTO>)session.getAttribute("PROMOTIONLIST");
            if (list==null){        
                list= new ArrayList<>();
            }                        
            boolean check=true;
            for (PromotionsDTO promotionsDTO : list) {
                if (promotionsDTO.getUsersDTO().getUserID().equals(userID)){
                    check=false;
                    request.setAttribute("ERRORADD", "User was added !!!");
                    break;
                }                
            }
            if (check){
                list.add(pro);
                request.setAttribute("SUCCESS", "Add Succesful!!!");
            }            
            session.setAttribute("PROMOTIONLIST", list);
            request.setAttribute("USERID", userID);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error in AddPromotionServlet" + e.getMessage());
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
