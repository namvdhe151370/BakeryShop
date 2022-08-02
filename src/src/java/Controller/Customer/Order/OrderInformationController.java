/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Order;

import DAO.CartDAO;
import DAO.CategoryDAO;
import DAO.FeedbackDAO;
import DAO.OrderDAO;
import DAO.OrderDetailsDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import Model.Cart;
import Model.Category;
import Model.Order;
import Model.Order_Details;
import Model.Product;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Huy Thai
 */
public class OrderInformationController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderInformation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderInformation at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        User u = (User) request.getSession().getAttribute("user");
        int orderID = Integer.parseInt(request.getParameter("OrderID"));
        int userID = u.getId();
        String ckeyword = request.getParameter("categorykeyword");
        String urlkeyword = request.getParameter("searchkeyword");
        ProductDAO Pdao = new ProductDAO();
        List<Product> listProduct = null;
        List<Product> topProduct = Pdao.getTopProduct(true);
        HttpSession session = request.getSession();
        CategoryDAO Cdao = new CategoryDAO();

        //take list category pagging
        String pageCate = request.getParameter("pageCate");
        if (pageCate == null) {
            pageCate = "1";
        }
        int pageCategory = Integer.parseInt(pageCate);
        final int CA_PAZE_SIZE = 4;
        int totalCategory = Cdao.getTotalCategory(true);
        int endCaPage = totalCategory / CA_PAZE_SIZE;
        if (totalCategory % CA_PAZE_SIZE != 0) {
            endCaPage += 1;
        }
        List<Category> listCategory = Cdao.getListCategoryPaging(true, pageCategory, CA_PAZE_SIZE);

        int page = 1;
        int endPage = 0;
        int totalProduct = 0;
        String pageStr = request.getParameter("page");
        //check data of page exist
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        final int PAZE_SIZE = 2;
        //check list category by id exist and pagging
        if (ckeyword != null) {
            int keywordInt = Integer.parseInt(ckeyword);
            totalProduct = Pdao.getTotalProductByCid(true, keywordInt);
            endPage = totalProduct / PAZE_SIZE;
            if (totalProduct % PAZE_SIZE != 0) {
                endPage += 1;
            }
            listProduct = Pdao.getListProductByCidPaging(true, keywordInt, page, PAZE_SIZE);
            session.setAttribute("backUrl", "productlist?categorykeyword=" + ckeyword);
        } //check list search by name exist and pagging
        else if (urlkeyword != null) {
            totalProduct = Pdao.getTotalProductBySearchName(true, urlkeyword);
            endPage = totalProduct / PAZE_SIZE;
            if (totalProduct % PAZE_SIZE != 0) {
                endPage += 1;
            }
            listProduct = Pdao.getListProductBySearchNamePaging(true, urlkeyword, page, PAZE_SIZE);
            session.setAttribute("backUrl", "productlist?searchkeyword=" + urlkeyword);
            //all product list and pagging
        } else {
            listProduct = Pdao.getListProductPaging(true, page, PAZE_SIZE);
            totalProduct = Pdao.getTotalProduct();
            endPage = totalProduct / PAZE_SIZE;
            int to = totalProduct % PAZE_SIZE;
            if (to != 0) {
                endPage += 1;
            }
        }

        // get orderdetails by userid
        OrderDetailsDAO odd = new OrderDetailsDAO();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        ArrayList<Order_Details> order_details = odd.getOrder_DetailsByOrderId(orderID);
        for (Order_Details od : order_details) {
            if (!feedbackDAO.isGivenFeedback(userID, od.getProductId().getProductID(), orderID) && feedbackDAO.isBought(od.getProductId().getProductID(), userID, orderID)) {
                od.setStatusFeedback(true);
            } else {
                od.setStatusFeedback(false);
            }
        }
        request.setAttribute("order_details", order_details);

        // get order by userid
        OrderDAO od = new OrderDAO();
        Order order = od.getOrdersByOrderId(orderID);
        request.setAttribute("order", order);

        //get User Information 
//        UserDAO ud = new UserDAO();
//        User u = ud.GetUserProfileById(2);
        request.setAttribute("user", u);

        //get total money
        float totalMoney = 0;
        for (Order_Details orderdetail : order_details) {
            totalMoney += (orderdetail.getPrice() * (1 - orderdetail.getDiscount())
                    * orderdetail.getQuantity());
        }
        order.setTotalMoney(totalMoney);
        //

        User user = (User) session.getAttribute("user");
        CartDAO cartdao = new CartDAO();
        Cart cartUser = cartdao.getCartByUserID(user);
        int a = cartUser.getCarts().size();

        session.setAttribute("cartUser", cartUser);
        request.setAttribute("categorykeyword", ckeyword);
        request.setAttribute("searchkeyword", urlkeyword);
        request.setAttribute("page", page);
        request.setAttribute("endPage", endPage);
        request.setAttribute("totalProduct", totalProduct);
        request.setAttribute("pageCate", pageCate);
        request.setAttribute("endCaPage", endCaPage);
        session.setAttribute("listCategory", listCategory);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("topProduct", topProduct);
        //  response.getWriter().print(order_details);
        request.getRequestDispatcher("/View/Customer/Order/OrderInformation.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("OrderID"));
        OrderDAO od = new OrderDAO();
        od.CancelOrder(orderId);
        session.setAttribute("mess", "CanCel Order Successfull");
        response.sendRedirect("/src/order/orderinformation?OrderID=" + orderId);

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
