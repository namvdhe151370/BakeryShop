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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HuyThai
 */
public class EditCustomer extends HttpServlet {

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
            out.println("<title>Servlet MKTCustomerEditController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MKTCustomerEditController at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        int Id = Integer.parseInt(request.getParameter("Id"));
        UserDAO ud = new UserDAO();
        User u = ud.GetUserById(Id);
        request.setAttribute("user", u);
        request.getRequestDispatcher("/View/Marketing/Customer/EditCustomer.jsp").forward(request, response);
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
        int Id = Integer.parseInt(request.getParameter("Id"));
        UserDAO ud = new UserDAO();
        User u = ud.GetUserById(Id);
        String name = request.getParameter("Name");
        String Gender = (String) request.getParameter("Gender");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("Address");
        boolean Gender1;
        if (Gender.equals("1")) {
            Gender1 = true;
        } else {
            Gender1 = false;
        }
        ud.UpdateCustomer(name, Gender1, email, mobile, address, Id);
        ud.InsertCustomerChangesHistory(u.getId(), email, name, Gender1, mobile, address);
        request.setAttribute("mess", "Edit Successfull!");
        response.sendRedirect("/src/marketing/customerview?Id=" +Id);

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