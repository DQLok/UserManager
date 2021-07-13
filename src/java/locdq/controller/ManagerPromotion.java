/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locdq.promotions.PromotionsDAO;
import locdq.promotions.PromotionsDTO;
import locdq.typepromotions.TypePromotionsDTO;
import locdq.users.UsersDAO;
import locdq.users.UsersDTO;

/**
 *
 * @author test
 */
public class ManagerPromotion extends HttpServlet {

    private final String ERROR = "promotion.jsp";
    private final String SUCCESS = "promotion.jsp";

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
            String button = request.getParameter("btAction");
            String index = request.getParameter("index");
            String userID = request.getParameter("userID");
            String rank = request.getParameter("rank");
            String assignmentdate = request.getParameter("assignmentdate");
            UsersDAO daouser = new UsersDAO();
            UsersDTO user = daouser.getUserByUserID(userID);            
            HttpSession session = request.getSession();
            List<PromotionsDTO> list = (List<PromotionsDTO>) session.getAttribute("PROMOTIONLIST");
            if (button.equals("Remove")) {
                list.remove(Integer.parseInt(index) - 1);
                session.setAttribute("PROMOTIONLIST", list);
                request.setAttribute("SUCCESS", "Delete Successful!!!");
                url = SUCCESS;
            }
            if (button.equals("UpdateRank")) {
                if (!rank.matches("[0-9]") || Integer.parseInt(rank) < 0 || Integer.parseInt(rank) > 10) {
                    request.setAttribute("ERROR", "Rank must be in [1..10]");
                } else {
                    PromotionsDTO dto = new PromotionsDTO(new TypePromotionsDTO(),user, Integer.parseInt(rank), Date.valueOf(assignmentdate));
                    list.set(Integer.parseInt(index) - 1, dto);
                    request.setAttribute("SUCCESS", "Update rank successful!!!");
                    session.setAttribute("PROMOTIONLIST", list);
                }                                
            }
            if (button.equals("Save")){
                String typepro=request.getParameter("typepro");
                PromotionsDAO dao=new PromotionsDAO();               
                dao.saveDatabase(list,typepro);
//                name=dao.saveDatabase2(list, typepro);
//                if(name!=null){
//                    request.setAttribute("ALO", name);
//                }                
                session.removeAttribute("PROMOTIONLIST");
                request.setAttribute("SUCCESS", "Save Successful!!!");
            }
            url = SUCCESS;
            request.setAttribute("INDEX", index);
        } catch (Exception e) {
            log("Error in ManagerPromotion" + e.getMessage());
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
