/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Cart;

import Controller.Customer.Account.SendMailController;
import DAO.CategoryDAO;
import DAO.OrderDAO;
import DAO.OrderDetailsDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
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
 * @author HuyThai
 */
public class CartCompletionController extends HttpServlet {

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
            out.println("<title>Servlet CartCompletionController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartCompletionController at " + request.getContextPath() + "</h1>");
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
        User user = (User) request.getSession().getAttribute("user");
        String ckeyword = request.getParameter("categorykeyword");
        String urlkeyword = request.getParameter("searchkeyword");
        ProductDAO Pdao = new ProductDAO();
        List<Product> listProduct = null;
        List<Product> topProduct = Pdao.getTopProduct(true);
        HttpSession session = request.getSession();
        CategoryDAO Cdao = new CategoryDAO();
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
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        final int PAZE_SIZE = 2;
        if (ckeyword != null) {
            int keywordInt = Integer.parseInt(ckeyword);
            totalProduct = Pdao.getTotalProductByCid(true, keywordInt);
            endPage = totalProduct / PAZE_SIZE;
            if (totalProduct % PAZE_SIZE != 0) {
                endPage += 1;
            }
            listProduct = Pdao.getListProductByCidPaging(true, keywordInt, page, PAZE_SIZE);
            session.setAttribute("backUrl", "productlist?categorykeyword=" + ckeyword);
            System.out.println("ckeyword!!!=null");
        } else if (urlkeyword != null) {
            totalProduct = Pdao.getTotalProductBySearchName(true, urlkeyword);
            endPage = totalProduct / PAZE_SIZE;
            if (totalProduct % PAZE_SIZE != 0) {
                endPage += 1;
            }
            listProduct = Pdao.getListProductBySearchNamePaging(true, urlkeyword, page, PAZE_SIZE);
            session.setAttribute("backUrl", "productlist?searchkeyword=" + urlkeyword);
            System.out.println("keyword!=null");
        } else {
            listProduct = Pdao.getListProductPaging(true, page, PAZE_SIZE);
            totalProduct = Pdao.getTotalProduct();
            endPage = totalProduct / PAZE_SIZE;
            int to = totalProduct % PAZE_SIZE;
            if (to != 0) {
                endPage += 1;
            }
            session.setAttribute("backUrl", "productlist?page=" + page);
            System.out.println("keyword==null");
        }
        OrderDetailsDAO odd = new OrderDetailsDAO();
        OrderDAO od = new OrderDAO();
        int orderid = od.getLastestOrder(user.getId());
        ArrayList<Order_Details> order_details = odd.getOrder_DetailsByOrderId(orderid);
        request.setAttribute("order_details", order_details);
        Order order = od.getOrdersByOrderId(orderid);
        request.setAttribute("order", order);
        String AddressShip = od.getAddressShip();
        String OrderNote = od.getOrderNote();
        request.setAttribute("OrderNote", OrderNote);
        UserDAO ud = new UserDAO();
        User u = ud.GetUserById(user.getId());
        request.setAttribute("user", u);
        float totalMoney = 0;
        for (Order_Details orderdetail : order_details) {
            totalMoney += (orderdetail.getPrice() * (1-orderdetail.getDiscount())
                    * orderdetail.getQuantity());
        }
        order.setTotalMoney(totalMoney);
        int saleid = ud.GetLastestSaler();
        od.UpdateSaleID(saleid);
        String gender;
        if (u.isGender() == true) {
            gender = "Male";
        } else {
            gender = "Female";
        }
        SendMailController sendmail = new SendMailController();
        String subject = "Order Confirm Information";
        String submessage1 = "            <!DOCTYPE html>\n"
                + "            <html>\n"
                + "                <style>\n"
                + "                    table, th, td {\n"
                + "                        border:1px solid black;\n"
                + "                    }\n"
                + "                </style>\n"
                + "                <h4>Thank You For Your Interest And Order At Our Store</h4>\n"
                + "                </br>\n"
                + "                <div style=\"border:1px solid black; width: 60%; padding: 0 10px\">\n"
                + "                    <div class=\"row\">\n"
                + "                        <div class=\"col-xl-12 col-lg-12 col-md-12 col-sm-18 col-18 \">\n"
                + "                            <h3>Receiver Information</h3>\n"
                + "                            <p>Name: <span>" + u.getName() + "</span></p>\n"
                + "                            <p>Email: <span>" + u.getEmail() + "</span></p>\n"
                + "                            <p>Mobile: <span>" + u.getMobile() + "</span></p>\n"
                + "                            <p>Gender: <span>" + gender + "</span></p>\n"
                + "                            <p>Address: <span>" + AddressShip + "</span></p>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                    <hr>\n"
                + "                    <h3>Order Information</h3>\n"
                + "\n"
                + "<table style=\"width:100%; text-align: left\">\n"
                + "<tr>\n"
                + "<th>ID</th>\n"
                + "<th>Product Name</th>\n"
                + "<th>Price</th>\n"
                + "<th>Quantity</th>\n"
                + "<th>Subtotal</th>\n"
                + "</tr>\n";
        String submessage2 = "";
        for (Order_Details orderdetail : order_details) {
            submessage2
                    += "<tr>\n"
                    + "<td>" + orderdetail.getProductId().getProductID() + "</td>\n"
                    + "<td>" + orderdetail.getProductId().getProductName() + "</td>\n"
                    + "<td>$" + orderdetail.getPrice() + "</td>\n"
                    + "<td>" + orderdetail.getQuantity() + "</td>\n"
                    + "<td><fmt:formatNumber type = \"CURRENCY\" maxIntegerDigits = \"5\" value =/>$" + String.format("%.1f", orderdetail.getQuantity() * (orderdetail.getPrice() - (orderdetail.getDiscount() * orderdetail.getPrice()))) + "</td>\n"
                    + "</tr>\n";
        }
        String submessage3
                = "</table>\n"
                + "<hr>";
        String submessage4 = "                    <h3>Payment Guide</h3></br>\n"
                + "                    <h4>Ship CODE</h4></br>\n"
                + "                    <p>Payment When Receiving Product With The Delivery Staff</p></br>\n"
                + "                    <h4>Banking Account</h4>\n"
                + "                    <p> Customer Pay For Order Through The Account Number Below<br/>\n"
                + "                        The Order Will Be Active When Receiving The Money<br/>\n"
                + "                        Bank System: MBBANK<br/>\n"
                + "                        Account Name: HA HUY THAI<br/>\n"
                + "                        Bank Account Number: 0987654321</p>\n"
                + "                    <hr>\n"
                + "                    <div>\n"
                + "                        <!--<div class=\"ps-block__divider\"></div>-->\n"
                + "                        <div class=\"ps-block__detail\">\n"
                + "                            <h3 style=\"color: black\">Sub Total:  <span>$ <fmt:formatNumber type = \"CURRENCY\" maxIntegerDigits = \"5\" value =/> "  + String.format( "%.1f", order.getTotalMoney()) + "</span></h3>\n"
                + "                            <div class=\"ps-block__divider\"></div>\n"
                + "                        </div>\n"
                + "                        <div class=\"ps-block__detail\">\n"
                + "                            <h3 style=\"color: black\">Shopping Fee:  <span>$0</span></h3>\n"
                + "                            <div class=\"ps-block__divider\"></div>\n"
                + "                        </div>\n"
                + "                        <div class=\"ps-block__detail\">\n"
                + "                            <h3 style=\"color: black\">ToTal Cost:  <span>$  <fmt:formatNumber type = \"CURRENCY\" maxIntegerDigits = \"5\" value =/> "  + String.format( "%.1f", order.getTotalMoney()) + "</span></h3>\n"
                + "                            <div class=\"ps-block__divider\"></div>\n"
                + "                        </div>\n"
                + "                        </br>\n"
                + "                        <div class=\"ps-block__footer\" style=\"padding-bottom: 20px\">\n"
                + "                            <button type=\"button\" class=\"btn btn-primary\"><a href=\"http://localhost:8080/src/customer/cartcompletion\">View Details</a></button>\n"
                + "                        </div>\n"
                + "                        </br>\n"
                + "                    </div>"
                + "            </html>";
        String message = submessage1 + submessage2 + submessage3 + submessage4;
        sendmail.send(u.getEmail(), subject, message, "hoangdxhe151343@fpt.edu.vn", "nguoichoihedep123");
        request.setAttribute("page", page);
        request.setAttribute("endPage", endPage);
        request.setAttribute("totalProduct", totalProduct);
        request.setAttribute("pageCate", pageCate);
        request.setAttribute("endCaPage", endCaPage);
        session.setAttribute("listCategory", listCategory);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("topProduct", topProduct);
        request.getRequestDispatcher("/View/Customer/Cart/CartCompletion.jsp").forward(request, response);
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
        String payment = request.getParameter("payment");
        User user = (User) request.getSession().getAttribute("user");
         ProductDAO Pdao = new ProductDAO();
        List<Product> listProduct = null;
        List<Product> topProduct = Pdao.getTopProduct(true);
        HttpSession session = request.getSession();
        CategoryDAO Cdao = new CategoryDAO();
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
        OrderDAO od = new OrderDAO();
        int orderid = od.getLastestOrder(user.getId());
        String OrderNote = od.getOrderNote();
        request.setAttribute("pageCate", pageCate);
        request.setAttribute("endCaPage", endCaPage);
        session.setAttribute("listCategory", listCategory);
           request.setAttribute("topProduct", topProduct);
        request.setAttribute("OrderNote", OrderNote);
        if (payment == null) {
            response.sendRedirect("/src/customer/cartcompletion");
        } else {
            session.setAttribute("mess", "Order Has Been Confirmed");
            od.UpdateOrderStatus(orderid);
            response.sendRedirect("/src/customer/cartcompletion");
        }
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