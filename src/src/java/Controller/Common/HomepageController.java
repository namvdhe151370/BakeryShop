/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Common;

import DAO.CartDAO;
import DAO.PostDAO;
import DAO.ProductDAO;
import DAO.SliderDAO;
import Model.Cart;
import Model.Post;
import Model.Product;
import Model.Slider;
import Model.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class HomepageController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {

            ProductDAO proDao = new ProductDAO();
            List<Product> lsProForHome = proDao.getProductforHomepage();

            PostDAO postDao = new PostDAO();
            List<Post> lsBlogForHome = postDao.getBlogforHomepage();

            SliderDAO slideDao = new SliderDAO();
            List<Slider> lsSliderForHome = slideDao.get3SliderforHomepage();

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            CartDAO cartdao = new CartDAO();
            Cart cartUser = cartdao.getCartByUserID(user);
            int a = cartUser.getCarts().size();
            
            session.setAttribute("cartUser", cartUser);
            session.setAttribute("backUrl", "/src/homepage");
            request.setAttribute("lsProduct", lsProForHome);
            request.setAttribute("lsBlog", lsBlogForHome);
            request.setAttribute("lsSlider", lsSliderForHome);
            
            request.getRequestDispatcher("/View/Homepage.jsp").forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
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
