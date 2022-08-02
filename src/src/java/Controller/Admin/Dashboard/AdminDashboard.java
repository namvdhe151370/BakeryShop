/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin.Dashboard;

import DAO.FeedbackDAO;
import DAO.OrderDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.search.DateTerm;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hellb
 */
public class AdminDashboard extends HttpServlet {

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
        try {
            OrderDAO odao = new OrderDAO();
            UserDAO udao = new UserDAO();
            FeedbackDAO fdao = new FeedbackDAO();

            String from = request.getParameter("from");
            String to = request.getParameter("to");
            if (from == null && to == null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                Date date = cal.getTime();
                String[] days = new String[7];
                days[0] = sdf.format(date);

                for (int i = 1; i < 7; i++) {
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                    date = cal.getTime();
                    days[i] = sdf.format(date);
                }
                from = days[6];
                to = days[0];

            }
            //new order
            int countAllOrderByDate = odao.getCountAllOrderByDate(from, to);
            int countOrderByDateCancel = odao.getCountOrderByDateAndStatus(from, to, 1);
            int countOrderByDatePending = odao.getCountOrderByDateAndStatus(from, to, 2);
            int countOrderByDateProcessing = odao.getCountOrderByDateAndStatus(from, to, 3);
            int countOrderByDateShipped = odao.getCountOrderByDateAndStatus(from, to, 4);
            // number of Customer
            int totalCus = udao.getTotalCustomer();
            int countCusByDate = udao.getCountCusByDate(from, to);
            int countCusBoughtByDate = odao.getCountCusBoughtByDate(from, to);
            //total and product categoty revenues
            double countAllRevenues = odao.getCountAllRevenuesByDate(from, to);
            double countRevenuesByCate5 = odao.getCountRevenuesByDateAndCateId(from, to, 5);
            double countRevenuesByCate6 = odao.getCountRevenuesByDateAndCateId(from, to, 6);
            double countRevenuesByCate7 = odao.getCountRevenuesByDateAndCateId(from, to, 7);
            double countRevenuesByCate8 = odao.getCountRevenuesByDateAndCateId(from, to, 8);
            double countRevenuesByCate9 = odao.getCountRevenuesByDateAndCateId(from, to, 9);
            //total and product categoty average star of feedbacks
            int countAllStar = fdao.getCountAllStarByDate(from, to);
            int totalAllFeeback = fdao.getTotalAllFeebackByDate(from, to);
            double averageStar;
            if (totalAllFeeback == 0) {
                averageStar = 0;
            } else {
                averageStar = (double) countAllStar / (totalAllFeeback);
            }

            int countStarByCate5 = fdao.getCountStarByDateAndCate(from, to, 5), totalFeebackByCate5 = fdao.getTotalFeebackByDateAndCate(from, to, 5);
            double averageStarByCate5, averageStarByCate6, averageStarByCate7, averageStarByCate8, averageStarByCate9;
            if (totalFeebackByCate5 == 0) {
                averageStarByCate5 = 0;
            } else {
                averageStarByCate5 = (double) countStarByCate5 / (totalFeebackByCate5);
            }
            int countStarByCate6 = fdao.getCountStarByDateAndCate(from, to, 6), totalFeebackByCate6 = fdao.getTotalFeebackByDateAndCate(from, to, 6);
            if (totalFeebackByCate6 == 0) {
                averageStarByCate6 = 0;
            } else {
                averageStarByCate6 = (double) countStarByCate6 / (totalFeebackByCate6);
            }
            int countStarByCate7 = fdao.getCountStarByDateAndCate(from, to, 7), totalFeebackByCate7 = fdao.getTotalFeebackByDateAndCate(from, to, 7);
            if (totalFeebackByCate7 == 0) {
                averageStarByCate7 = 0;
            } else {
                averageStarByCate7 = (double) countStarByCate7 / (totalFeebackByCate7);
            }
            int countStarByCate8 = fdao.getCountStarByDateAndCate(from, to, 8), totalFeebackByCate8 = fdao.getTotalFeebackByDateAndCate(from, to, 8);
            if (totalFeebackByCate8 == 0) {
                averageStarByCate8 = 0;
            } else {
                averageStarByCate8 = (double) countStarByCate8 / (totalFeebackByCate8);
            }
            int countStarByCate9 = fdao.getCountStarByDateAndCate(from, to, 9), totalFeebackByCate9 = fdao.getTotalFeebackByDateAndCate(from, to, 9);
            if (totalFeebackByCate9 == 0) {
                averageStarByCate9 = 0;
            } else {
                averageStarByCate9 = (double) countStarByCate9 / (totalFeebackByCate9);
            }

            //Range of days
            int range = LocalDate.parse(to).getDayOfMonth() - LocalDate.parse(from).getDayOfMonth();

            System.out.println(range);
            List<Date> lsDate = new ArrayList<>();
//        for (int i = 0; i < range; i++) {
//            lsDate.add(LocalDate.parse(to).plusDays(-i));
//            response.getWriter().print(lsDate);
//        }

            Calendar calendar = new GregorianCalendar();
            Date date1 = null, enddate;
            try {
                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(from);
            } catch (ParseException ex) {
                Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            calendar.setTime(date1);
            enddate = new SimpleDateFormat("yyyy-MM-dd").parse(to);
            while (calendar.getTime().before(enddate)) {
                Date result = calendar.getTime();
                lsDate.add(result);
                calendar.add(Calendar.DATE, 1);
            }

            Collections.reverse(lsDate);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            HashMap<String, Integer> chartData = new HashMap<>();
            for (Date localDate : lsDate) {
                String tempdate = formatter.format(localDate);
                int count = odao.getCountOrderByDate(tempdate);
                chartData.put(tempdate, count);

            }
            for (Map.Entry<String, Integer> entry : chartData.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                response.getWriter().println(key + "," + value);
            }
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("chartData", chartData);
        
        request.setAttribute("countAllOrderByDate", countAllOrderByDate);
        request.setAttribute("countOrderByDateCancel", countOrderByDateCancel);
        request.setAttribute("countOrderByDatePending", countOrderByDatePending);
        request.setAttribute("countOrderByDateProcessing", countOrderByDateProcessing);
        request.setAttribute("countOrderByDateShipped", countOrderByDateShipped);
        request.setAttribute("totalCus", totalCus);
        request.setAttribute("countCusByDate", countCusByDate);
        request.setAttribute("countCusBoughtByDate", countCusBoughtByDate);
        request.setAttribute("countAllRevenues", countAllRevenues);
        request.setAttribute("countRevenuesByCate5", countRevenuesByCate5);
        request.setAttribute("countRevenuesByCate6", countRevenuesByCate6);
        request.setAttribute("countRevenuesByCate7", countRevenuesByCate7);
        request.setAttribute("countRevenuesByCate8", countRevenuesByCate8);
        request.setAttribute("countRevenuesByCate9", countRevenuesByCate9);
        request.setAttribute("averageStar", averageStar);
        request.setAttribute("averageStarByCate5", averageStarByCate5);
        request.setAttribute("averageStarByCate6", averageStarByCate6);
        request.setAttribute("averageStarByCate7", averageStarByCate7);
        request.setAttribute("averageStarByCate8", averageStarByCate8);
        request.setAttribute("averageStarByCate9", averageStarByCate9);

        } catch (ParseException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
      request.getRequestDispatcher("/View/Admin/Dashboard/Dashboard.jsp").forward(request, response);
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
