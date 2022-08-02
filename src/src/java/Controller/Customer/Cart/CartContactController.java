/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Cart;

import DAO.CartDAO;
import DAO.Cart_DetailDAO;
import DAO.CategoryDAO;
import DAO.OrderDAO;
import DAO.OrderDetailsDAO;
import DAO.ProductDAO;
import DAO.ShipInformationDAO;
import Model.Cart;
import Model.Cart_Detail;
import Model.Category;
import Model.Order;
import Model.Product;
import Model.ShipInformation;
import Model.User;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author hellb
 */
public class CartContactController extends HttpServlet {

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

    private static final DecimalFormat df = new DecimalFormat("#.##");

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
        response.setContentType("text/html;charset=UTF-8");
        String ckeyword = request.getParameter("categorykeyword");
        String skeyword = request.getParameter("searchkeyword");

        ProductDAO Pdao = new ProductDAO();
        //get top 3 lastest product 
        List<Product> topProduct = Pdao.getTopProduct(true);
        List<Product> listProduct = null;
        HttpSession session = request.getSession();
        CategoryDAO Cdao = new CategoryDAO();

        String pageCate = request.getParameter("pageCate");

        User user = (User) session.getAttribute("user");
        CartDAO cartdao = new CartDAO();
        Cart cartUser = cartdao.getCartByUserID(user);
        //check account null
        if (user == null) {
            request.setAttribute("mess", "LoginForCart");
            request.getRequestDispatcher("../View/Homepage.jsp").forward(request, response);

        } else {
            Cart cart = null;
            //get page category = 1 when page cate null
            if (pageCate == null) {
                pageCate = "1";
            }
            int pageCategory = Integer.parseInt(pageCate);
            final int CA_PAZE_SIZE = 3;
            //get total category and list category page by cart Id
            int totalCategory = Cdao.getTotalCategoryByCartId(true, cartUser.getCartID());
            int endCaPage = totalCategory / CA_PAZE_SIZE;
            if (totalCategory % CA_PAZE_SIZE != 0) {
                endCaPage += 1;
            }
            List<Category> listCategoryCart = Cdao.getListCategoryPagingByCartId(true, cartUser.getCartID(), pageCategory, CA_PAZE_SIZE);

            int page = 1;
            int endPage = 0;
            int totalProduct = 0;
            String pageStr = request.getParameter("page");
            //check data of page exist
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }
            final int PAZE_SIZE = 10;
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
            request.getRequestDispatcher("/View/Customer/Cart/CartContact.jsp").forward(request, response);
        }
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
        String fullname = request.getParameter("fullname");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String note = request.getParameter("note");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CartDAO cartdao = new CartDAO();

        Cart cartUser = cartdao.getCartByUserID(user);

        for (Map.Entry<Integer, Cart_Detail> entry : cartUser.getCarts().entrySet()) {
            Integer key = entry.getKey();
            Cart_Detail value = entry.getValue();
            Product product = value.getProduct();
            if (product.getQuantity() == 0) {
                response.sendRedirect("/src/View/404.jsp");
                return;
            }
        }

        ShipInformation shipInfor = new ShipInformation(address, fullname, gender, phone, email, note);
        ShipInformationDAO sidao = new ShipInformationDAO();
        int shipInforId = sidao.createReturnId(shipInfor);

        double totalPrice = 0;
        for (Map.Entry<Integer, Cart_Detail> entry : cartUser.getCarts().entrySet()) {
            Integer key = entry.getKey();
            Cart_Detail value = entry.getValue();
            Product product = value.getProduct();
            totalPrice += product.getPrice() * (1 - product.getDiscount()) * value.getQuantity();
        }

        Double roudingTotalPrice = Double.valueOf(df.format(totalPrice));
        OrderDAO orderDAO = new OrderDAO();
        Order order = new Order(user, 2, roudingTotalPrice, shipInforId);
        order.setTotalMoney(roudingTotalPrice);
        int orderId = orderDAO.createReturnId(order);
        OrderDetailsDAO cdedao = new OrderDetailsDAO();
        ProductDAO pdao = new ProductDAO();

        for (Map.Entry<Integer, Cart_Detail> entry : cartUser.getCarts().entrySet()) {
            Integer key = entry.getKey();
            Cart_Detail value = entry.getValue();
            Product product = value.getProduct();
            ProductDAO productDAO = new ProductDAO();
            Product currentProduct = productDAO.getProductbyID(product.getProductID());
            int currentQuantity = currentProduct.getQuantity() - value.getQuantity();

            cdedao.saveOrderDetalsByProduct(orderId, value);
            pdao.updateQuantityProduct(currentQuantity, product.getProductID());
            if (currentQuantity == 0) {
                pdao.updateOutOfStockPro(product.getProductID());
            }
        }
        new Cart_DetailDAO().removeAllByCartId(cartUser.getCartID());
//        cartdao.removeCartById(cartUser.getCartID());
        session.setAttribute("mess", "Check Your Email To Confirm Your Order");
        response.sendRedirect("/src/customer/cartcompletion");

//        
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
