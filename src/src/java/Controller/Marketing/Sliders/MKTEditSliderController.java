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
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author admin
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15, // 15 MB
        location = "C:\\Users\\hellb\\OneDrive\\Desktop\\src\\web\\uploads"
)

public class MKTEditSliderController extends BaseRequiredAuthorization {

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
            out.println("<title>Servlet EditSliderController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditSliderController at " + request.getContextPath() + "</h1>");
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
       int sliderId = Integer.parseInt(request.getParameter("sliderId"));
        SliderDAO sdao = new SliderDAO();
        
        Slider slider = sdao.getSliderById(sliderId);
        request.setAttribute("slider", slider);
        request.getRequestDispatcher("/View/Marketing/Slider/EditSlider.jsp").forward(request, response);
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
       int sliderId = Integer.parseInt(request.getParameter("sliderId"));
        String title = request.getParameter("title");
        String backlink = request.getParameter("backlink");
        String image = request.getParameter("image");
        String note = request.getParameter("note");
        int statusSlider = Integer.parseInt(request.getParameter("statusSlider"));
        
        SliderDAO sdao = new SliderDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Slider s = new Slider();
        
        s.setUserID(user);
        s.setTitle(title);
        s.setBacklink(backlink);
        s.setNotes(note);
        s.setStatus(statusSlider);
        String sqlThumbnail = sdao.getSliderById(sliderId).getImage();
       
        Part imagePart = request.getPart("image");
                
        if (imagePart.getSubmittedFileName().equalsIgnoreCase("")) {
            s.setImage(sqlThumbnail);
        } else {
            if (imagePart.getSubmittedFileName() != null) {
                String filename = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
                imagePart.write(filename);
                s.setImage("/src/uploads/" + filename);

            }
        }
           
       sdao.editSliderById(sliderId, s);
        
        
        response.sendRedirect("sliderlist");
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
