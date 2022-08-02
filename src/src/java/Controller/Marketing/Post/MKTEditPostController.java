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
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
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

public class MKTEditPostController extends BaseRequiredAuthorization {

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
            out.println("<title>Servlet EditPostController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditPostController at " + request.getContextPath() + "</h1>");
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
        int id = Integer.parseInt(request.getParameter("postID"));

        PostDAO postDB = new PostDAO();
        List<Post_Category> lsPostCategory = postDB.getPostCategoryList();
        Post post = postDB.getPostbyID(id);
        request.setAttribute("post", post);
        request.setAttribute("lsPostCategory", lsPostCategory);
        request.getRequestDispatcher("/View/Marketing/Post/EditPost.jsp").forward(request, response);

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
        int id = Integer.parseInt(request.getParameter("postID"));
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

        String updateDate = java.time.LocalDate.now().toString();
        User user = (User) session.getAttribute("user");

        PostDAO postDAO = new PostDAO();

        Post p = new Post();
        p.setUserID(new User(2));
        p.setPostTitle(title);
        p.setPostDescription(description);
        p.setBriefInformation(briefInformation);
        p.setPostCategoryID(new Post_Category(categoryID));
        p.setUpdateDate(updateDate);

        String sqlThumbnail = postDAO.getPostbyID(id).getThumbnail();

        Part imagePart = request.getPart("thumbnail");

        if (imagePart.getSubmittedFileName().equalsIgnoreCase("")) {
            p.setThumbnail(sqlThumbnail);
        } else {
            if (imagePart.getSubmittedFileName() != null) {
                String filename = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
                imagePart.write(filename);
                p.setThumbnail("/src/uploads/" + filename);

            }
        }
  
        if (request.getParameter("post") != null) {
            p.setPostStatus(true);
            postDAO.updatePostByID(id, p);
           
        } else if (request.getParameter("save") != null) {
            p.setPostStatus(false);
            postDAO.updatePostByID(id, p);

        }
        response.sendRedirect("/src/marketing/viewpost?postID="+id);
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
