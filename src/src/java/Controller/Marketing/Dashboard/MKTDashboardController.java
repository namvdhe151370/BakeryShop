/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Marketing.Dashboard;

import DAO.FeedbackDAO;
import DAO.PostDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class MKTDashboardController extends HttpServlet {

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

        String then = request.getParameter("then");
        String before = request.getParameter("before");

        if (then == null) {
            then = today.toString();
        }
        if (before == null) {
            before = today.plusDays(-7).toString();
        }

        //Post statics
        PostDAO postDAO = new PostDAO();
        int totalPost = postDAO.getCountPost();
        int totalPostInRange = postDAO.getCountPostInRange(before, then);
        int activePost = postDAO.getCountPostInRange("1", before, then);
        int deactivePost = postDAO.getCountPostInRange("0", before, then);
        float activePostPercentage = (float) (activePost * 1.0 / totalPost * 1.0) * 100;
        float deactivePostPercentage = (float) (deactivePost * 1.0 / totalPost * 1.0) * 100;

        //Product statics
        ProductDAO productDAO = new ProductDAO();
        int totalProduct = productDAO.getCountProduct();
        int totalProductInRange = productDAO.getCountProductInRange(before, then);
        int activeProduct = productDAO.getCountProductInRange("1", before, then);
        int deactiveProduct = productDAO.getCountProductInRange("0", before, then);
        float activeProductPercentage = (float) (activeProduct * 1.0 / totalProduct * 1.0) * 100;
        float deactiveProductPercentage = (float) (deactiveProduct * 1.0 / totalProduct * 1.0) * 100;

        //Customer statics
        UserDAO userDAO = new UserDAO();
        int totalCustomer = userDAO.getCountCustomers(4);
        int totalCustomerInRange = userDAO.getCountCustomersInRange("4", before, then);
        int activeCustomer = userDAO.getCountCustomerInRange("1", "4", before, then);
        int deactiveCustomer = userDAO.getCountCustomerInRange("0", "4", before, then);
        float activeCustomerPercentage = (float) (activeCustomer * 1.0 / totalCustomer * 1.0) * 100;
        float deactiveCustomerPercentage = (float) (deactiveCustomer * 1.0 / totalCustomer * 1.0) * 100;

        //Feedback statics
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        int totalFeedback = feedbackDAO.getCountFeedback();
        int totalFeedbackInRange = feedbackDAO.getCountFeedbackInRange(before, then);
        int activeFeedback = feedbackDAO.getCountFeedbackInRange("1", before, then);
        int deactiveFeedback = feedbackDAO.getCountFeedbackInRange("0", before, then);
        float activeFeedbackPercentage = (float) (activeFeedback * 1.0 / totalFeedback * 1.0) * 100;
        float deactiveFeedbackPercentage = (float) (deactiveFeedback * 1.0 / totalFeedback * 1.0) * 100;

        //Range of days
        int range = LocalDate.parse(then).getDayOfMonth() - LocalDate.parse(before).getDayOfMonth();
        List<LocalDate> lsDate = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            lsDate.add(LocalDate.parse(then).plusDays(-i));
        }
        Collections.reverse(lsDate);

        HashMap<String, Integer> chartData = new HashMap<>();
        for (LocalDate localDate : lsDate) {
            int count = userDAO.getCountCustomersByDate(localDate.toString(), "4");
            chartData.put(localDate.toString(), count);
        }

        request.setAttribute("then", then);
        request.setAttribute("before", before);
        request.setAttribute("chartData", chartData);

        request.setAttribute("deactivePostPercentage", deactivePostPercentage);
        request.setAttribute("activePostPercentage", activePostPercentage);
        request.setAttribute("deactivePost", deactivePost);
        request.setAttribute("activePost", activePost);
        request.setAttribute("totalPost", totalPost);
        request.setAttribute("totalPostInRange", totalPostInRange);

        request.setAttribute("deactiveProductPercentage", deactiveProductPercentage);
        request.setAttribute("activeProductPercentage", activeProductPercentage);
        request.setAttribute("deactiveProduct", deactiveProduct);
        request.setAttribute("activeProduct", activeProduct);
        request.setAttribute("totalProduct", totalProduct);
        request.setAttribute("totalProductInRange", totalProductInRange);

        request.setAttribute("deactiveCustomerPercentage", deactiveCustomerPercentage);
        request.setAttribute("activeCustomerPercentage", activeCustomerPercentage);
        request.setAttribute("deactiveCustomer", deactiveCustomer);
        request.setAttribute("activeCustomer", activeCustomer);
        request.setAttribute("totalCustomer", totalCustomer);
        request.setAttribute("totalCustomerInRange", totalCustomerInRange);

        request.setAttribute("deactiveFeedbackPercentage", deactiveFeedbackPercentage);
        request.setAttribute("activeFeedbackPercentage", activeFeedbackPercentage);
        request.setAttribute("deactiveFeedback", deactiveFeedback);
        request.setAttribute("activeFeedback", activeFeedback);
        request.setAttribute("totalFeedback", totalFeedback);
        request.setAttribute("totalFeedbackInRange", totalFeedbackInRange);

        //
        response.getWriter().print(activeProductPercentage);
        request.getRequestDispatcher("/View/Marketing/Dashboard/MKTDashboard.jsp").forward(request, response);
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
