/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Marketing.Feedback;

import Controller.Common.BaseRequiredAuthorization;
import DAO.FeedbackDAO;
import Model.Feedback;
import Model.Feedback_Images;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class FeedbackDetailController extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int feedbackId = 1;
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        try {
            feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        } catch (Exception e) {
            feedbackId = 1;
        }
        Feedback feedback = feedbackDAO.getFeedbackById(feedbackId);     
        request.setAttribute("feedback", feedback);
        request.getRequestDispatcher("/View/Marketing/Feedback/FeedbackDetail.jsp").forward(request, response);    
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String raw_status = request.getParameter("status");
        String raw_id = request.getParameter("imageId");
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        int id = Integer.parseInt(raw_id);
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        Boolean status = Boolean.parseBoolean(raw_status);
        if(id==0){
            feedbackDAO.updateStatusFeedback(feedbackId, status);
        }else{
            feedbackDAO.updateStatusImages(id, status);
        }
        response.getWriter().print("Id: "+id + "feedbackId"+feedbackId+ "status: "+status );
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
