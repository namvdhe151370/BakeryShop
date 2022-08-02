/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Marketing.Post;

import Controller.Common.BaseRequiredAuthorization;
import DAO.PostDAO;
import Model.Post;
import Model.Post_Category;
import Model.User;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15, // 15 MB
        location = "C:\\Users\\hellb\\OneDrive\\Desktop\\src\\web\\uploads"
)

/**
 *
 * @author admin
 */
public class MKTInsertPostController extends BaseRequiredAuthorization {

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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDAO post = new PostDAO();
        List<Post_Category> lsPostCategory = post.getPostCategoryList();
        request.setAttribute("lsPostCategory", lsPostCategory);
        request.getRequestDispatcher("/View/Marketing/Post/InsertPost.jsp").forward(request, response);

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
        String title = request.getParameter("title");
        String briefInformation = request.getParameter("briefInformation");
        String description = request.getParameter("Description");
        int categoryID = Integer.parseInt(request.getParameter("postCategoryID"));

        String raw_status = request.getParameter("status");

        boolean status = false;
        if (raw_status.equalsIgnoreCase("1")) {
            status = true;
        } else {
            status = false;
        }

        HttpSession session = request.getSession();

        String createDate = java.time.LocalDate.now().toString();
        User user = (User) session.getAttribute("user");

        PostDAO postDAO = new PostDAO();
        Post p = new Post();
        p.setUserID(user);
        p.setPostTitle(title);
        p.setPostDescription(description);
        p.setBriefInformation(briefInformation);
        p.setPostCategoryID(new Post_Category(categoryID));
        p.setPostDate(createDate);
        Part imagePart = request.getPart("thumbnail");

        if (imagePart.getSubmittedFileName() != null) {
            String filename = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            imagePart.write(filename);
            p.setThumbnail("/src/uploads/" + filename);
        }

        postDAO.addPost(p);

        response.sendRedirect("/src/marketing/viewpost?postID=" + postDAO.getLatestPostID());

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
