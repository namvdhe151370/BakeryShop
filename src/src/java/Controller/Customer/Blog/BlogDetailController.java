/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Blog;

import DAO.PostDAO;
import Model.Post;
import Model.Post_Category;
import java.io.IOException;
import java.io.PrintWriter;
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
public class BlogDetailController extends HttpServlet {

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
      int PostId = Integer.parseInt(request.getParameter("postId"));
        PostDAO dao = new PostDAO();
        Post p = dao.getPostbyID(PostId);
        int blogCatePage = dao.getBlogCategoryEndPage(4);
        List<Post_Category> lsActivePostCategory = dao.getActivePostCategory(true);
        List<Post> lsRecentPost = dao.getActiveRecentPostList(true);
        //get the current index of blog category list 
        String pageCate = request.getParameter("currentPageCate");
        //if post category list page index is not assigned, page is assigned to be 1
        if (pageCate == null) {
            pageCate = "1";
        }
        int currentPageCate = Integer.parseInt(pageCate);
        List<Post_Category> lsPagingBlogCategory = dao.getPagingBlogCategoryList(true, currentPageCate, 4);
        //
        
        
        ///
        request.setAttribute("blogCatePage", blogCatePage);
        request.setAttribute("currentPageCate", currentPageCate);
        request.setAttribute("lsPagingBlogCategory", lsPagingBlogCategory);
        request.setAttribute("lsRecentPost", lsRecentPost);
        request.setAttribute("lsActivePostCategory", lsActivePostCategory);
        request.setAttribute("post", p);
        request.setAttribute("postId", PostId);

        request.getRequestDispatcher("/View/Customer/Blog/BlogDetails.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);  }

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
