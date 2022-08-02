/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Marketing.Customer;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class CustomerList extends HttpServlet {

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
        UserDAO userDAO = new UserDAO();
        String raw_name = request.getParameter("name");
        String raw_email = request.getParameter("email");
        String raw_mobile = request.getParameter("mobile");
        String raw_status = request.getParameter("status");
        String raw_order = request.getParameter("orderby");
        String raw_direction = request.getParameter("direction");
        int status;
        String name="", email="", mobile="", order="", direction="";
        try {
            status = Integer.parseInt(raw_status);
        } catch (Exception e) {
            status = -1;
        }
        int page_index, page_size = 3, totalpage = 0, count;
        if(raw_name != null) name = raw_name;
        if(raw_email != null) email = raw_email;
        if(raw_mobile != null) mobile = raw_mobile;
        if(raw_order != null) order = raw_order;
        if(raw_direction != null) direction = raw_direction;      
        count = userDAO.getNumberOfRecords(name, email, mobile, status);
        try {
            page_index = Integer.parseInt(request.getParameter("page_index"));
        } catch (Exception e) {
            page_index = 1;
        }
        if (count % page_size == 0) {
            totalpage = count / page_size;
        } else {
            totalpage = count / page_size + 1;
        }
        if (page_index < 1 || page_index > totalpage) {
            page_index = 1;
        }
        List<User> listCustomers = new ArrayList<>();
//        response.getWriter().print(name + " "+email+ " "+mobile+" "+status+" "+page_index+ " "+order+" "+direction+ " "+page_size);
        listCustomers =  userDAO.getListCustomers(page_index, page_size, name, email, mobile, status, order, direction);
        request.setAttribute("page_index", page_index);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("listCustomers", listCustomers);
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("mobile", mobile);
        request.setAttribute("status", status);
        request.getRequestDispatcher("/View/Marketing/Customer/CustomerList.jsp").forward(request, response);

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