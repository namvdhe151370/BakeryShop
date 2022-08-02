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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author admin
 */
public class BlogListController extends HttpServlet {

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
        PostDAO postDB = new PostDAO();
        List<Post> lsRecentPost = postDB.getActiveRecentPostList(true);
        List<Post> lsPagingBlog = null;
        int endPage;
        int blogCatePage = postDB.getBlogCategoryEndPage(4);  
        String page = request.getParameter("currentPage");
        
        //if blog list page index is not assigned, page is assigned to be 1
        if (page == null) {
            page = "1";
        }
        int currentPage = Integer.parseInt(page);
        
        
        //get the current index of blog category list 
        String pageCate = request.getParameter("currentPageCate");
        
        //if post category list page index is not assigned, page is assigned to be 1
        if (pageCate == null) {
            pageCate = "1";
        }
        int currentPageCate = Integer.parseInt(pageCate);

        
        List<Post_Category> lsPagingBlogCategory = postDB.getPagingBlogCategoryList(true, currentPageCate, 4);

        String category = request.getParameter("categoryID");
        //if post categoryID is not assigned, page is assigned to be 0 to get all category posts
        if (category == null) {
            category = "0";
        }
        int categoryID = Integer.parseInt(category);

        //if categoryID = 0, show all Blog list which 2 records per pages 
        if (categoryID == 0) {
            lsPagingBlog = postDB.getPagingBlogList(true, currentPage, 2);
            endPage = postDB.getBlogEndpage("bloglist", "", 2);
        } 
        //if category !=0, show all blog list ,which has 2 records per pages, is searched by categoryID  
        else { 
            lsPagingBlog = postDB.getPagingBlogByCategory(true, categoryID, currentPage);
            endPage = postDB.getBlogListByCategoryEndPage(categoryID, 2);   
        }
      

        request.setAttribute("currentPage", currentPage);
        request.setAttribute("currentPageCate", currentPageCate);
        request.setAttribute("categoryID", categoryID);
        request.setAttribute("lsPagingBlogCategory", lsPagingBlogCategory);
        request.setAttribute("lsPagingBlog", lsPagingBlog);
        request.setAttribute("lsRecentPost", lsRecentPost);

        request.setAttribute("endPage", endPage);
        request.setAttribute("blogCatePage", blogCatePage);
        request.getRequestDispatcher("/View/Customer/Blog/BlogList.jsp").forward(request, response);
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
        PostDAO postDB = new PostDAO();
        String keyword = request.getParameter("keyword");
        List<Post> lsPagingBlog;
        List<Post> lsRecentPost = postDB.getActiveRecentPostList(true);

        int blogCatePage = postDB.getBlogCategoryEndPage(4);
        int endPage;
        String page = request.getParameter("currentPage");
        //if the parameter @currentPage is not assigned, it is assigned to be 1 to get all blog list
        if (page == null) {
            page = "1";
        }
        int currentPage = Integer.parseInt(page);
        
        //if the user input a keyword to search, show a blog list which has 2 records per page, searched by keyword
        if (keyword != null) {
            endPage = postDB.getBlogEndpage("searchBlog", keyword, 2);
            lsPagingBlog = postDB.getPagingBlogListByText(true, keyword, currentPage);
        //if the user does not input the keyword, show a blog list which has 2 records per page
        } else {
            endPage = postDB.getBlogEndpage("blogList", keyword, 2);
            lsPagingBlog = postDB.getPagingBlogList(true, currentPage, 2);
        }
      
        
        String pageCate = request.getParameter("currentPageCate");
        //if the parameter @currentPage is not assigned, it is assigned to be 1 to get all blog category list
        if (pageCate == null) {
            pageCate = "1";
        }
        int currentPageCate = Integer.parseInt(pageCate);
        List<Post_Category> lsPagingBlogCategory = postDB.getPagingBlogCategoryList(true, currentPageCate, 4);
        
        
        
        request.setAttribute("blogCatePage", blogCatePage);
        request.setAttribute("currentPageCate", currentPageCate);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("lsPagingBlog", lsPagingBlog);
        request.setAttribute("lsRecentPost", lsRecentPost);
        request.setAttribute("lsPagingBlogCategory", lsPagingBlogCategory);
        request.getRequestDispatcher("/View/Customer/Blog/BlogList.jsp").forward(request, response);
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
