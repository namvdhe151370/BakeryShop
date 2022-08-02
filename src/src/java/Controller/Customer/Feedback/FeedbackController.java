/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Feedback;

import Controller.Common.ProcessingUploadFile;
import DAO.CategoryDAO;
import DAO.FeedbackDAO;
import DAO.ProductDAO;
import Model.Category;
import Model.Feedback;
import Model.Feedback_Images;
import Model.Order_Details;
import Model.Product;
import Model.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class FeedbackController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
//        response.getWriter().print(u.getEmail());
        if (u == null) {
            response.sendRedirect("/src/homepage");
        } else {
            int productId = 0, orderId = 0;
            try {
                productId = Integer.parseInt(request.getParameter("productId"));
                orderId = Integer.parseInt(request.getParameter("orderId"));
                ProductDAO productDAO = new ProductDAO();
                FeedbackDAO feedbackDAO = new FeedbackDAO();
//          Customer can feedback a product when product was bought and it is not given feedback
                if (feedbackDAO.isBought(productId, u.getId(), orderId) && !feedbackDAO.isGivenFeedback(u.getId(), productId, orderId)) {
                    CategoryDAO categoryDAO = new CategoryDAO();
                    Product product = productDAO.getProductbyID(productId);
                    List<Product> topProduct = productDAO.getTopProduct(true);
                    List<Category> listCategory = categoryDAO.getAllCategory();
                    int maxNumberOfImages = Integer.parseInt(getServletConfig().getInitParameter("maxNumberOfImages"));
                    request.setAttribute("maxNumberOfImages", maxNumberOfImages);
                    request.setAttribute(("listCategory"), listCategory);
                    request.setAttribute("topProduct", topProduct);
                    request.setAttribute("product", product);
                    request.setAttribute("orderId", orderId);
                    request.getRequestDispatcher("/View/Customer/Feedback/Feedback.jsp").forward(request, response);
                }
                response.sendRedirect("/src/comment/error");
            } catch (Exception e) {
                response.sendRedirect("/src/comment/error");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        User u = (User) request.getSession().getAttribute("user");
        String result = "";
        InputStream inputStream = null;
        OutputStream outputStream = null;
        ProcessingUploadFile processFile = new ProcessingUploadFile();
        ArrayList<String> listNameImages = new ArrayList<>();
        listNameImages = processFile.processAndGetImagesName("Test", request, feedbackDAO.getLastIndexOfFeedback_Images()+1);
        ArrayList<Feedback_Images> listFeedbackImages = new ArrayList<>();
        for (int i = 1; i <= listNameImages.size(); i++) {
            Feedback_Images fi = new Feedback_Images();
            fi.setImageName(listNameImages.get(i-1));
            listFeedbackImages.add(fi);
        }
        String note = request.getParameter("message");
        Feedback f = new Feedback();
        f.setListImages(listFeedbackImages);
        f.setRatedStar(Float.parseFloat(request.getParameter("rating")));
        f.setNote(note);
        f.setUserId(u);
        Product p = new Product();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        p.setProductID(Integer.parseInt(request.getParameter("productId")));
        Order_Details od = new Order_Details();
        f.setProductId(p);
        od.setOrderDetailID(feedbackDAO.getOrderDetailIdByOrderIdAndProductId(orderId, p.getProductID()));
        f.setOrderDetailsId(od);
        feedbackDAO.insertFeedback(f);
        response.sendRedirect("/src/order/orderinformation?OrderID="+orderId+"&userID="+u.getId());
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
