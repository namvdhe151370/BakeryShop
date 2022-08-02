/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;


import Controller.Common.BaseRequiredAuthorization;
import Controller.Customer.Account.SendMailController;
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

/**
 *
 * @author Huy Thai
 */
public class ADInsertUserController extends BaseRequiredAuthorization {

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
            out.println("<title>Servlet AddNewUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewUser at " + request.getContextPath() + "</h1>");
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
        UserDAO udao = new UserDAO();
        List<Role> listRole = udao.getRoleNameList();
        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("../View/Admin/User/AddNewUser.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        String Name = request.getParameter("Name");
        String Email = request.getParameter("Email");
        String Password = request.getParameter("Password");
        String Mobile = request.getParameter("Mobile");
        int Gender = Integer.parseInt(request.getParameter("Gender"));
        String Address = request.getParameter("Address");
        int RoleId = Integer.parseInt(request.getParameter("Role"));
        UserDAO ud = new UserDAO();
        User u = ud.getUserByEmail(Email);
        User u1 = ud.getUserByPhone(Mobile);
//        response.getWriter().print(Name+ " "+ Email+" "+Password+ " "+Mobile+" "+Gender+" "+Address+" " +RoleId );
        try {
            if (u1 != null) {
                request.setAttribute("mess", "Phone Was Used!!");
            } else {
                if (u == null) {
                    ud.AddNewUser(Email, Password, RoleId, Name, Mobile, Gender, Address);
                    SendMailController sendmail = new SendMailController();
                    request.setAttribute("mess", "Add successfull");
                    response.sendRedirect("/src/homepage");
                    String subject = "You was be added in systerm";
                    String link = "http://localhost:8080/src/homepage";
                    String message = "<!DOCTYPE html>\n"
                            + "<html lang=\"en\">\n"
                            + "    <h3 style=\"color: blue;\">Your Password Is Bellow</h3>\n"
                            + "    <h2 style=\"color: blue;\">"+Password+"</h2>\n"
                            + "    <a href=\"" + link + "\">Click here to Login</a>\n"
                            + "</html>";
                    sendmail.send(Email, subject, message, "hoangdxhe151343@fpt.edu.vn",
                            "nguoichoihedep123");
                } else {
                    request.setAttribute("mess", "Email Already Existed");
                    response.sendRedirect("/src/homepage");
                }
            }
        } catch (Exception e) {
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