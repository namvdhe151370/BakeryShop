/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Order;

import DAO.OrderDAO;
import DAO.OrderDetailsDAO;
import DAO.UserDAO;
import Model.Order;
import Model.Order_Details;
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
public class OrderDetailsController extends HttpServlet {

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
            out.println("<title>Servlet OrderDetailsController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderDetailsController at " + request.getContextPath() + "</h1>");
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
        int orderid = Integer.parseInt(request.getParameter("OrderID"));
        OrderDAO od = new OrderDAO();
        Order order = od.getOrdersByOrderId(orderid);
        request.setAttribute("order", order);
        //get order note
        String OrderNote = od.getOrderNoteByShipID(order.getShipInfoID());
        request.setAttribute("OrderNote", OrderNote);
        //get list order status
        List<String> orderstatus = od.getOrderStatusList();
        request.setAttribute("orderstatus", orderstatus);
        //get list sale
        List<String> salelist = od.getSaleList();
        request.setAttribute("salelist", salelist);
        // get orderdetails by userid
        OrderDetailsDAO odd = new OrderDetailsDAO();
        ArrayList<Order_Details> order_details = odd.getOrder_DetailsByOrderId(order.getOderID());
        request.setAttribute("order_details", order_details);
        //get User Information 
        UserDAO ud = new UserDAO();
        User u = ud.GetUserById(order.getUserId().getId());
        request.setAttribute("user", u);
        //get Sale Information
        User sale = ud.GetUserById(order.getSaleId());
        request.setAttribute("sale", sale);
        //get total money
        float totalMoney = 0;
        for (Order_Details orderdetail : order_details) {
            totalMoney += (orderdetail.getPrice() * (1-orderdetail.getDiscount())
                    * orderdetail.getQuantity());
        }
        order.setTotalMoney(totalMoney);
        //  response.getWriter().print(order_details);
        request.getRequestDispatcher("/View/Customer/Order/OrderDetails.jsp").forward(request, response);
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
        int status = Integer.parseInt(request.getParameter("Statusxyz"));
        String note = request.getParameter("note");
        int sale = Integer.parseInt(request.getParameter("sale"));
        int orderid = Integer.parseInt(request.getParameter("OrderID"));
        OrderDAO od = new OrderDAO();
        Order order = od.getOrdersByOrderId(orderid);
        od.UpdateOrderStatusAndSaleId(sale, status, order.getOderID());
        od.UpdateOrderNote(note, order.getShipInfoID());
        session.setAttribute("mess", "Update Order Successfully");
        response.sendRedirect("/src/order/orderdetails?OrderID="+orderid);
//    response.getWriter().print(request.getParameter("Statusxyz"));
//    response.getWriter().print(request.getParameter("sale"));
//    response.getWriter().print(request.getParameter("note"));
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