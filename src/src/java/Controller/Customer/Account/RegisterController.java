/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Account;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Huy Thai
 */
public class RegisterController extends HttpServlet {

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
        request.getRequestDispatcher("/View/Register.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        String Name = request.getParameter("Name");
        String Email = request.getParameter("Email");
        String Password = request.getParameter("Password");
        String Repassword = request.getParameter("Repassword");
        String Mobile = request.getParameter("Mobile");
        int Gender = Integer.parseInt(request.getParameter("Gender"));
        String Address = request.getParameter("Address");
        try {

            if (!Password.equals(Repassword)) {
                request.setAttribute("mess", "Password and Repassword different");
                request.getRequestDispatcher("../View/Homepage.jsp").forward(request, response);
            } else {
                UserDAO dao = new UserDAO();
                User a = dao.checkUserExist(Email);
                if (a == null) {

                    dao.Register(Email, Password, Name, Mobile, Gender, Address);
                    SendMailController sendmail = new SendMailController();
                    request.setAttribute("mess", "Check your Email");
                    request.getRequestDispatcher("../View/Homepage.jsp").forward(request, response);
                    String subject = "Register Successfull !!!";
                    String link = "http://localhost:8080/src/homepage";
                    String message = "<!DOCTYPE html>\n"
                            + "<html lang=\"en\">\n"
                            + "    <h3 style=\"color: blue;\">Resgister Successfull, Click link to access systerm !</h3>\n"
                            + "    <a href=\"" + link + "\">Click here</a>\n"
                            + "</html>";
                    sendmail.send(Email, subject, message, "hoangdxhe151343@fpt.edu.vn",
                            "nguoichoihedep123");
                } else {
                    request.setAttribute("mess", "Email Already Existed");
                    request.getRequestDispatcher("../View/Homepage.jsp").forward(request, response);
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