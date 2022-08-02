/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Sale.Dashboard;

import DAO.OrderDAO;
import DAO.UserDAO;
import Model.Order_Status;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class SaleDashboardController extends HttpServlet {

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
        LocalDate today = LocalDate.now();
        String saleid = request.getParameter("saleid");
        String then = request.getParameter("then");
        String before = request.getParameter("before");
        String status = request.getParameter("status");

        if (then == null) {
            then = today.toString();
        }
        if (before == null) {
            before = today.plusDays(-7).toString();
        }
        if (saleid == null) {
            saleid = "";
        }
        if (status == null) {
            status = "";
        }
        OrderDAO orderDAO = new OrderDAO();
        List<Order_Status> listStatus = orderDAO.getAllOrderStatus();
        UserDAO userDAO = new UserDAO();
        List<User> listSale = userDAO.getSaleList();

        //Range of days
        int range = LocalDate.parse(then).getDayOfMonth() - LocalDate.parse(before).getDayOfMonth();
        List<LocalDate> lsDate = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            lsDate.add(LocalDate.parse(then).plusDays(-i));
        }
        Collections.reverse(lsDate);
        //Chart Data
        double money = 0; int totalOrder = 0;int successOrder=0;
        HashMap<String, String> chartRevenue = new HashMap<>();
        HashMap<String, String> chartTotal = new HashMap<>();
        HashMap<String, String> chartSuccessTotal = new HashMap<>();
        for (LocalDate datez : lsDate) {
             response.getWriter().print(datez);
            money = orderDAO.getRevenuesbyDay(saleid, datez.toString(), status);
            chartRevenue.put(datez.toString(), String.valueOf(money));
            totalOrder = orderDAO.getOrderbyDay(saleid, datez.toString(), status);
            chartTotal.put(datez.toString(), String.valueOf(totalOrder));
            successOrder = orderDAO.getOrderbyDay(saleid, datez.toString(), "4");
            chartSuccessTotal.put(datez.toString(),String.valueOf(successOrder));
        }

         response.getWriter().print(chartSuccessTotal);
        request.setAttribute("chartRevenue", chartRevenue);
        request.setAttribute("chartSuccessTotal", chartSuccessTotal);
        request.setAttribute("chartTotal", chartTotal);
        request.setAttribute("then", then);
        request.setAttribute("before", before);
        request.setAttribute("saleid", saleid);
        request.setAttribute("status", status);
        request.setAttribute("listStatus", listStatus);
        request.setAttribute("listSale", listSale);
        request.getRequestDispatcher("/View/Sale/Dashboard/SaleDashboard.jsp").forward(request, response);
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