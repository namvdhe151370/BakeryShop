/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Product;

import DAO.CartDAO;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import DAO.SettingDAO;
import Model.Cart;
import Model.Category;
import Model.Product;
import Model.Setting;
import Model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hellb
 */
public class ProductListController extends HttpServlet {

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
//    private void getProducts() {
//
//    }
//
//    private void getProductByCategory() {
//
//    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ckeyword = request.getParameter("categorykeyword");
        String urlkeyword = request.getParameter("searchkeyword");
        ProductDAO Pdao = new ProductDAO();
        SettingDAO setdao = new SettingDAO();
        List<Product> listProduct = null;

        List<Product> topProduct = Pdao.getTopProduct(true);
        HttpSession session = request.getSession();
        CategoryDAO Cdao = new CategoryDAO();

        //take list category pagging
        String pageCate = request.getParameter("pageCate");
        if (pageCate == null) {
            pageCate = "1";
        }
        int pageCategory = Integer.parseInt(pageCate);

        List<Category> listCategory = null;
        Setting sizeCatePro = setdao.getSettingById(2);
        int CA_PAZE_SIZE = sizeCatePro.getValue();
        int cateStatus = sizeCatePro.getStatus().getStatusID();
        int totalCategory = Cdao.getTotalCategory(true);
        int endCaPage = totalCategory / CA_PAZE_SIZE;
        if (totalCategory % CA_PAZE_SIZE != 0) {
            endCaPage += 1;
        }
        if (cateStatus == 1) {

            listCategory = Cdao.getListCategoryPaging(true, pageCategory, CA_PAZE_SIZE);
        } else {
            listCategory = Cdao.getAll(true);
        }

        int page = 1;
        int endPage = 0;
        int totalProduct = 0;
        String pageStr = request.getParameter("page");
        //check data of page exist
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }

        Setting sizeProduct = setdao.getSettingById(1);
        int PAZE_SIZE = sizeProduct.getValue();
        int proStatus = sizeProduct.getStatus().getStatusID();
        if (proStatus == 1) {
            //check list category by id exist and pagging
            if (ckeyword != null) {
                int keywordInt = Integer.parseInt(ckeyword);
                totalProduct = Pdao.getTotalProductByCid(true, keywordInt);
                endPage = totalProduct / PAZE_SIZE;
                if (totalProduct % PAZE_SIZE != 0) {
                    endPage += 1;
                }
                listProduct = Pdao.getListProductByCidPaging(true, keywordInt, page, PAZE_SIZE);
                session.setAttribute("backUrl", "productlist?categorykeyword=" + ckeyword);
            } //check list search by name exist and pagging
            else if (urlkeyword != null) {
                totalProduct = Pdao.getTotalProductBySearchName(true, urlkeyword);
                endPage = totalProduct / PAZE_SIZE;
                if (totalProduct % PAZE_SIZE != 0) {
                    endPage += 1;
                }
                listProduct = Pdao.getListProductBySearchNamePaging(true, urlkeyword, page, PAZE_SIZE);
                session.setAttribute("backUrl", "productlist?searchkeyword=" + urlkeyword);
                //all product list and pagging
            } else {
                listProduct = Pdao.getListProductPaging(true, page, PAZE_SIZE);
                totalProduct = Pdao.getTotalProduct();
                endPage = totalProduct / PAZE_SIZE;
                int to = totalProduct % PAZE_SIZE;
                if (to != 0) {
                    endPage += 1;
                }
                session.setAttribute("backUrl", "productlist?page=" + page);
            }
        } else {
            //check list category by id exist and pagging
            if (ckeyword != null) {
                int keywordInt = Integer.parseInt(ckeyword);
                listProduct = Pdao.getListProductByCid(true, keywordInt);
                session.setAttribute("backUrl", "productlist?categorykeyword=" + ckeyword);

            } //check list search by name exist and pagging
            else if (urlkeyword != null) {
                listProduct = Pdao.getListProductBySearchName(true, urlkeyword);
                session.setAttribute("backUrl", "productlist?searchkeyword=" + urlkeyword);
                //all product list and pagging
            } else {
                listProduct = Pdao.getListProduct(true);
                session.setAttribute("backUrl", "productlist?page=" + page);
            }
        }

        User user = (User) session.getAttribute("user");
        CartDAO cartdao = new CartDAO();
        Cart cartUser = cartdao.getCartByUserID(user);
        session.setAttribute("cartUser", cartUser);
        request.setAttribute("categorykeyword", ckeyword);
        request.setAttribute("searchkeyword", urlkeyword);

        request.setAttribute("cateStatus", cateStatus);
        request.setAttribute("CA_PAZE_SIZE", CA_PAZE_SIZE);
        request.setAttribute("totalCategory", totalCategory);
        request.setAttribute("page", page);
        request.setAttribute("endPage", endPage);
        request.setAttribute("totalProduct", totalProduct);
        request.setAttribute("pageCate", pageCate);
        request.setAttribute("endCaPage", endCaPage);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("topProduct", topProduct);

        request.getRequestDispatcher("/View/Customer/Product/Productlist.jsp").forward(request, response);
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
