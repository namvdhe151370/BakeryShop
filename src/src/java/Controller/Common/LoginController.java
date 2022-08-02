/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Common;


import DAO.CartDAO;
import DAO.UserDAO;
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
 * @author admin
 */
public class LoginController extends HttpServlet {

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
        request.getRequestDispatcher("/View/Homepage.jsp").forward(request, response);

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
        PasswordProcessingController pp = new PasswordProcessingController();
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String Email = request.getParameter("Email");
        String Password = request.getParameter("Password");
        UserDAO udao = new UserDAO();
        User a = udao.checkLogin(Email, pp.encoding(Password));
        
        HomepageController h = new HomepageController();
        
        
        if (a == null) {
            request.setAttribute("mess", "Fail");
            
        } else {
            session.setAttribute("user", a);
           //add size cart to session
            CartDAO cartdao = new CartDAO();
            Cart cartUser = cartdao.getCartByUserID(a);
            session.setAttribute("cartUser", cartUser);
            
        }
        h.processRequest(request, response);
//        request.getRequestDispatcher("/View/Homepage.jsp").forward(request, response);
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
