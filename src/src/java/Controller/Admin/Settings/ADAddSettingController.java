/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin.Settings;

import Controller.Common.BaseRequiredAuthorization;
import DAO.SettingDAO;
import Model.Setting_Status;
import Model.Setting_Type;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class ADAddSettingController extends BaseRequiredAuthorization {

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
            out.println("<title>Servlet ADAddSettingController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ADAddSettingController at " + request.getContextPath() + "</h1>");
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
        SettingDAO sedao = new SettingDAO();
        List<Setting_Status> listSeStatus = sedao.getListSettingStatus();
        List<Setting_Type> listSeType = sedao.getListSettingType();
        request.setAttribute("listSeStatus", listSeStatus);
        request.setAttribute("listSeType", listSeType);
        request.getRequestDispatcher("/View/Admin/Setting/AddSettings.jsp").forward(request, response);
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
        
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int value = Integer.parseInt(request.getParameter("value"));
        String order = request.getParameter("order");
        int statusId = Integer.parseInt(request.getParameter("statusId"));
        String description = request.getParameter("description");
        
        SettingDAO sedao = new SettingDAO();
        sedao.InsertSettingById(typeId, value, order, statusId, description);
        
        response.sendRedirect("/src/admin/settinglist");
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
