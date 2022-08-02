
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Account;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class ProcessResetPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        request.setAttribute("token", token);
        UserDAO userDBContext = new UserDAO();
        User u = userDBContext.getUserByToken(token);
        if (u != null) {
            try {
                long duration = Long.parseLong(getServletConfig().getInitParameter("ExpirationTime"));
                java.util.Date expirationToken = new java.util.Date();
                SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                expirationToken = format.parse(u.getExpirationToken());
                java.util.Date currentDate = new java.util.Date();
//                Caculate the different time between current date and expiration date.
                long diff = TimeUnit.MILLISECONDS.toMinutes(currentDate.getTime() - expirationToken.getTime());
                if (diff > duration) {
                    response.sendRedirect("/src/404error");
                } else {
                    request.getRequestDispatcher("/View/Customer/Account/ProcessResetPassword.jsp").forward(request, response);
                }
            } catch (ParseException ex) {
                Logger.getLogger(ProcessResetPassword.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("/src/View/404.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDBContext = new UserDAO();
        String token = request.getParameter("token");
//        Get user by token.
        User u = userDBContext.getUserByToken(token);
        if (u != null) {
            try {
//                Get the duration time of password reset link.
                long duration = Long.parseLong(getServletConfig().getInitParameter("ExpirationTime"));
                java.util.Date expirationToken = new java.util.Date();
                SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                expirationToken = format.parse(u.getExpirationToken());
                java.util.Date currentDate = new java.util.Date();
//                Caculate the different time between current date and expiration date.
                long diff = TimeUnit.MILLISECONDS.toMinutes(currentDate.getTime() - expirationToken.getTime());
                if (diff <= duration) {
                    String newPassword = request.getParameter("password");
                    userDBContext.changePassword(u, newPassword);
                    userDBContext.destroyToken(u);
                    request.setAttribute("message", "Success");
                } else {
                    request.setAttribute("message", "Fail");
                    userDBContext.destroyToken(u);
                }
            } catch (ParseException ex) {
                Logger.getLogger(ProcessResetPassword.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("/View/Customer/Account/ProcessResetPassword.jsp").forward(request, response);
        } else {
            response.sendRedirect("/src/View/404.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}