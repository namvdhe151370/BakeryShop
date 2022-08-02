
package Controller.Marketing.Product;

import Controller.Common.BaseRequiredAuthorization;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.Category;
import Model.Product;
import Model.Product_Status;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author long4
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15, // 15 MB
        location = "C:\\Users\\hellb\\OneDrive\\Desktop\\src\\web\\uploads"
)
public class MKTInsertProductController extends BaseRequiredAuthorization {

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
            out.println("<title>Servlet MKTInsertProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MKTInsertProduct at " + request.getContextPath() + "</h1>");
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
        CategoryDAO cdao = new CategoryDAO();
        ProductDAO pdao = new ProductDAO();
        List<Category> lsCategory = cdao.getAllCategory();
        List<Product_Status> lsStatus = pdao.getAllProductStatus();
        
        request.setAttribute("lsStatus", lsStatus);
        request.setAttribute("lsCategory", lsCategory);
        request.getRequestDispatcher("/View/Marketing/Product/InsertProduct.jsp").forward(request, response);
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
        String name = request.getParameter("productName");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float discount = Float.parseFloat(request.getParameter("discount"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        int degree = Integer.parseInt(request.getParameter("degree"));
        int time = Integer.parseInt(request.getParameter("time"));
        String createdate = request.getParameter("createdate");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        String status = request.getParameter("status");
        Part thumbnail = request.getPart("thumbnail");

        Product p = new Product();
        ProductDAO dao = new ProductDAO();

        String filename = Paths.get(thumbnail.getSubmittedFileName()).getFileName().toString();
        thumbnail.write(filename);
        p.setThumbnail("/src/uploads/" + filename);

        dao.addProduct(name, title, description, status, categoryID, price, quantity, p.getThumbnail(), weight, degree, time, createdate, discount);
        response.sendRedirect("/src/marketing/productlist");
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