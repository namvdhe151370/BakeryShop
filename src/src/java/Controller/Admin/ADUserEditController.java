/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Controller.Common.BaseRequiredAuthorization;
import DAO.UserDAO;
import Model.Role;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ADUserEditController extends BaseRequiredAuthorization {

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
            out.println("<title>Servlet EditUserDetails</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditUserDetails at " + request.getContextPath() + "</h1>");
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int Id = Integer.parseInt(request.getParameter("id"));
        UserDAO ud = new UserDAO();
        User u = ud.GetUserById(Id);
        
        List<Role> listRole = ud.getRoleNameList();
        List<String> status = ud.getUserStatusList();
        request.setAttribute("lsRole", listRole);
        request.setAttribute("lsStatus", status);
        request.setAttribute("user", u);
//        response.getWriter().print(listRole);
        request.getRequestDispatcher("/View/Admin/User/EditUser.jsp").forward(request, response);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Id = Integer.parseInt(request.getParameter("id"));
        int RoleId = Integer.parseInt(request.getParameter("role"));
        int Status = Integer.parseInt(request.getParameter("status"));
        UserDAO ud = new UserDAO();
    
        ud.EditUserDtails(RoleId, Status, Id);
        request.setAttribute("mess", "Edit Successfull!");
       response.sendRedirect("/src/user/details?id="+Id);

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