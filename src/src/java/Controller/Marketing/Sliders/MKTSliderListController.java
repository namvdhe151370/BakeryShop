/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Marketing.Sliders;

import Controller.Common.BaseRequiredAuthorization;
import DAO.SliderDAO;
import Model.Slider;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class MKTSliderListController extends BaseRequiredAuthorization {

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
            out.println("<title>Servlet SliderListController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SliderListController at " + request.getContextPath() + "</h1>");
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
      HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        SliderDAO sdao = new SliderDAO();
        String status = request.getParameter("status");
        if(status == null){
            status="";
        }
        HashMap<String,String> lsStatus = new HashMap<>();
        lsStatus.put("Active", "1");
        lsStatus.put("Deactive", "2");
        
        
//        if (user == null) {
//            request.setAttribute("mess", "LoginForSliderList");
//            request.getRequestDispatcher("/View/Homepage.jsp").forward(request, response);
//        } else {
            List<Slider> listslider = sdao.getAllSlider();
            request.setAttribute("listslider", listslider);
            request.setAttribute("lsStatus", lsStatus);
            request.getRequestDispatcher("/View/Marketing/Slider/SliderList.jsp").forward(request, response);
//        }
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        SliderDAO sdao = new SliderDAO();
        
        if (user == null) {
            response.sendRedirect("/src/comment/homepage");
        } else {
            List<Slider> listslider = sdao.getAllSliderByUserId(user);
            request.setAttribute("listslider", listslider);
            
            request.getRequestDispatcher("../View/Marketing/Slider/SliderList.jsp").forward(request, response);
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
