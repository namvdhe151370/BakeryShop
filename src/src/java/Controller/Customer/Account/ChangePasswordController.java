/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Account;



import Controller.Common.PasswordProcessingController;
import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class ChangePasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/View/ChangePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        PasswordProcessingController pp = new PasswordProcessingController();
//        Check session before change password.
//        Change password and set new password for session.
        if (user != null) {
            String newPassword = request.getParameter("password");
            String currentPassword = request.getParameter("currentpassword");
            if (user.getPassword().equals(pp.encoding(currentPassword))) {
                UserDAO accountDAO = new UserDAO();
                accountDAO.changePassword(user, newPassword);
                user.setPassword(pp.encoding(newPassword));
                request.getSession().setAttribute("account", user);
//            Remove new password in session when password was changed successfully.
//                request.getSession().removeAttribute("newPassword");
                request.setAttribute("message", "Success");
            } else {
                request.setAttribute("message", "Fail");
            }
            request.getRequestDispatcher("../View/Customer/Account/UserProfile.jsp").forward(request, response);
        }else{
            response.sendRedirect("/src/comment/login");
        }

    }
}
