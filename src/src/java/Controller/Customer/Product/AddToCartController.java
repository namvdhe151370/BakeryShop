/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Product;

import DAO.CartDAO;
import DAO.Cart_DetailDAO;
import Model.Cart;
import Model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hellb
 */
public class AddToCartController extends HttpServlet {

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
       int productID = Integer.parseInt(request.getParameter("productID"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            session.setAttribute("mess", "LoginForCart");
            response.sendRedirect("/src/homepage");

        } else {
            CartDAO cartdao = new CartDAO();
            Cart cartUser = cartdao.getCartByUserID(user);
            
            if (cartUser.getCartID() == 0) {
                cartdao.InsertCart(user.getId());
                cartUser = cartdao.getCartByUserID(user);
            }

            
            if (cartUser.getCarts().containsKey(productID)) {
                new Cart_DetailDAO().UpdateCartDetail(productID, cartUser.getCartID(), cartUser.getCarts().get(productID).getQuantity()+1);
            } else {
                new Cart_DetailDAO().InsertCartDetail(productID, cartUser.getCartID(), 1);
            }
            String backUrl = (String) session.getAttribute("backUrl");
            
            if (backUrl == null) {
                backUrl = "productlist";
            }
            session.setAttribute("mess", "AddToCart");
            session.setAttribute("cartUser", cartUser);
            response.sendRedirect(backUrl);
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
