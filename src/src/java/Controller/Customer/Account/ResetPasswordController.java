/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Account;


import Controller.Common.MailerController;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class ResetPasswordController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/View/Customer/Account/ResetPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDBContext = new UserDAO();
        String toEmail = request.getParameter("email");
        if (userDBContext.getUserByEmail(toEmail) == null) {
            request.setAttribute("message", "Fail");
        } else {
            MailerController mail = new MailerController();
            StringBuilder content = new StringBuilder();
            content.append("http://localhost:8080/src/account/processresetpassword?token=");  
            String token = generateString(9, userDBContext.getUserByEmail(toEmail).getId());
            content.append(token);
            mail.sendEmail(toEmail, content.toString(), "Reset Your Password");
            userDBContext.setTokenResetPassword(token, toEmail);
            request.setAttribute("message", "Success");
        }
        request.getRequestDispatcher("/View/Customer/Account/ResetPassword.jsp").forward(request, response);
    }

    public String generateString(int length, int userID){
        Random rand = new Random();
        String AlphaNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
//            Random to get index from 0 to AlphaNumber.length()-1.
            int index = rand.nextInt(AlphaNumber.length());
            result.append(AlphaNumber.charAt(index));
        }
        result.append(Integer.toString(userID));
        return result.toString();
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
