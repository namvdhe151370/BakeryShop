/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Cart;

import DAO.CartDAO;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import DAO.SettingDAO;
import Model.Cart;
import Model.Cart_Detail;
import Model.Category;
import Model.Product;
import Model.Setting;
import Model.User;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class CartDetailsController extends HttpServlet {

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
        String ckeyword = request.getParameter("categorykeyword");
        String skeyword = request.getParameter("searchkeyword");

        ProductDAO Pdao = new ProductDAO();
        List<Product> topProduct = Pdao.getTopProduct(true);
        List<Product> listProduct = null;

        CategoryDAO Cdao = new CategoryDAO();
        SettingDAO setdao = new SettingDAO();
        String pageCate = request.getParameter("pageCate");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CartDAO cartdao = new CartDAO();
        Cart cartUser = cartdao.getCartByUserID(user);

        if (user == null) {
            request.setAttribute("mess", "LoginForCart");
            request.getRequestDispatcher("../View/Homepage.jsp").forward(request, response);

        } else {
            Cart cart = null;
            if (pageCate == null) {
                pageCate = "1";
            }
            int pageCategory = Integer.parseInt(pageCate);
            Setting sizeCateCart = setdao.getSettingById(4);
            int CA_PAZE_SIZE = sizeCateCart.getValue();
            int cateCartStatus = sizeCateCart.getStatus().getStatusID();
            int totalCategory = Cdao.getTotalCategoryByCartId(true, cartUser.getCartID());
            int endCaPage = totalCategory / CA_PAZE_SIZE;
            if (totalCategory % CA_PAZE_SIZE != 0) {
                endCaPage += 1;
            }

            List<Category> listCategoryCart = null;
            if (cateCartStatus == 1) {
                listCategoryCart = Cdao.getListCategoryPagingByCartId(true, cartUser.getCartID(), pageCategory, CA_PAZE_SIZE);
            } else {
                listCategoryCart = Cdao.getListCategoryByCartId(true, cartUser.getCartID());
            }

            int page = 1;
            int endPage = 0;
            int totalProduct = 0;
            String pageStr = request.getParameter("page");
            //check data of page exist
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }
            Setting sizeProduct = setdao.getSettingById(3);
            int PAZE_SIZE = sizeProduct.getValue();
            int proCartStatus = sizeProduct.getStatus().getStatusID();
            if (proCartStatus == 1) {
                //check list category by id exist and pagging
                if (ckeyword != null) {
                    int keywordInt = Integer.parseInt(ckeyword);
                    totalProduct = Pdao.getTotalProductByCidAndCartId(true, keywordInt, cartUser.getCartID());
                    endPage = totalProduct / PAZE_SIZE;
                    if (totalProduct % PAZE_SIZE != 0) {
                        endPage += 1;
                    }
                    cart = cartdao.getCartByCidAndCartIdPaging(user, keywordInt, page, PAZE_SIZE);
                } //check list search by name exist and pagging
                else if (skeyword != null) {
                    totalProduct = Pdao.getTotalProductBySearchNameAndCartId(skeyword, cartUser.getCartID());
                    endPage = totalProduct / PAZE_SIZE;
                    if (totalProduct % PAZE_SIZE != 0) {
                        endPage += 1;
                    }
                    cart = cartdao.getCartBySearchNamAndCartIdPaging(user, skeyword, page, PAZE_SIZE);
                } else {//all product list and pagging
                    cart = cartdao.getCartByUserIdPaging(user, page, PAZE_SIZE);
                    totalProduct = Pdao.getTotalProductByCartId(cartUser.getCartID());
                    endPage = totalProduct / PAZE_SIZE;
                    int to = totalProduct % PAZE_SIZE;
                    if (to != 0) {
                        endPage += 1;
                    }
                }
            } else {
                //check list category by id exist and pagging
                if (ckeyword != null) {
                    int keywordInt = Integer.parseInt(ckeyword);
                    
                    cart = cartdao.getCartByCidAndCartId(user, keywordInt);
                } //check list search by name exist and pagging
                else if (skeyword != null) {
                    cart = cartdao.getCartBySearchNamAndCartId(user, skeyword);
                }else {//all product list and pagging
                    cart = cartdao.getCartByUserId(user);
                }
            }

            double totalPrice = 0;
            for (Map.Entry<Integer, Cart_Detail> entry : cartUser.getCarts().entrySet()) {
                Integer key = entry.getKey();
                Cart_Detail value = entry.getValue();
                
                Product product = value.getProduct();
                
                totalPrice += product.getPrice() * (1 - product.getDiscount()) * value.getQuantity();

            }

            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("categorykeyword", ckeyword);
            request.setAttribute("searchkeyword", skeyword);
            request.setAttribute("PAZE_SIZE", PAZE_SIZE);
            request.setAttribute("totalCategory", totalCategory);
            request.setAttribute("cateCartStatus", cateCartStatus);
            request.setAttribute("CA_PAZE_SIZE", CA_PAZE_SIZE);
            request.setAttribute("totalProduct", totalProduct);
            request.setAttribute("page", page);
            request.setAttribute("endPage", endPage);
            request.setAttribute("pageCate", pageCate);
            request.setAttribute("endCaPage", endCaPage);
            request.setAttribute("listCategoryCart", listCategoryCart);
            request.setAttribute("listProduct", listProduct);
            session.setAttribute("cartUser", cartUser);
            request.setAttribute("cart", cart);
            request.setAttribute("topProduct", topProduct);
            request.getRequestDispatcher("/View/Customer/Cart/CartDetails.jsp").forward(request, response);
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
