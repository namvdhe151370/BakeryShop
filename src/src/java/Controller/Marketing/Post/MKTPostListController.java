/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Marketing.Post;

import Controller.Common.BaseRequiredAuthorization;
import DAO.PostDAO;
import Model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class MKTPostListController extends BaseRequiredAuthorization {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDAO postDAO = new PostDAO();
        List<Post> listPosts = postDAO.getAllPosts();
        String raw_author = request.getParameter("author");
        String raw_title = request.getParameter("title");
        String raw_category = request.getParameter("category");
        String raw_status = request.getParameter("status");
        String raw_order = request.getParameter("orderby");
        String raw_direction = request.getParameter("direction");
        int category, status;
        String title = "", author = "", order = "", direction = "";
        try {
            category = Integer.parseInt(raw_category);
        } catch (Exception e) {
            category = -1;
        }
        try {
            status = Integer.parseInt(raw_status);
        } catch (Exception e) {
            status = -1;
        }
        if (raw_author == null) {
            author = "";
        } else {
            author = raw_author;
        }
        if (raw_status == null) {
            title = "";
        } else {
            title = raw_title;
        }
        if (raw_order == null) {
            order = "";
        } else {
            order = raw_order;
        }
        if (raw_direction == null) {
            direction = "";
        } else {
            direction = raw_direction;
        }
        //Paging
        int page_index;
        try {
            page_index = Integer.parseInt(request.getParameter("page_index"));
        } catch (Exception e) {
            page_index = 1;
        }
        int page_size = 5, totalpage = 0;
        int count = postDAO.getNumberOfRecords(author, title, category, status);
        if (count % page_size == 0) {
            totalpage = count / page_size;
        } else {
            totalpage = count / page_size + 1;
        }
        if (page_index < 1 || page_index > totalpage) {
            page_index = 1;
        }
        listPosts = postDAO.getListPostsByFilter(page_size, page_index, author, title, category, status, order, direction);
        String currentUrl = "/src"+request.getServletPath();
        if(request.getQueryString()!=null){
            currentUrl += "?" + request.getQueryString();     
            if(currentUrl.contains("&page_index")){
                currentUrl = currentUrl.replaceAll("&page_index="+page_index, "");             
            }        
            else if(currentUrl.contains("?page_index")){
                currentUrl = currentUrl.replaceAll("page_index="+page_index, "");
            }          
            if(currentUrl.endsWith("?"))  currentUrl = currentUrl + "page_index=";
            else currentUrl = currentUrl + "&page_index=";                     
        }else{
            currentUrl+="?page_index=";
        }

        String urlOrder = "/src" + request.getServletPath();
        if (request.getQueryString() != null) {
            urlOrder += "?" + request.getQueryString();
            urlOrder = urlOrder.replaceAll("&direction=" + direction, "");
            if (urlOrder.contains("&orderby")) {
                urlOrder = urlOrder.replaceAll("&orderby=" + order, "");
            }else if(urlOrder.contains("?orderby")){
                urlOrder = urlOrder.replaceAll("orderby=" + order, "");
            }
        }

        request.setAttribute("page_index", page_index);
        request.setAttribute("currentUrl", currentUrl);
        request.setAttribute("urlOrder", urlOrder);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("listPost_Categories", postDAO.getListPost_Categories());
        request.setAttribute("listPosts", listPosts);
        request.setAttribute("title", title);
        request.setAttribute("author", author);
        request.setAttribute("category", category);
        request.setAttribute("status", status);
        response.getWriter().print(listPosts);
        request.getRequestDispatcher("/View/Marketing/Post/PostList.jsp").forward(request, response);
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
