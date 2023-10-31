/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Home;

import DataAccess.CategoryDAO;
import DataAccess.ProductDAO;
import Models.Category;
import Models.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taisk
 */
public class indexController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet indexController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet indexController at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO productDao = new ProductDAO();
        CategoryDAO categoryDao = new CategoryDAO();
        String pramCategoryId = request.getParameter("selectedOption");
        String searchName = request.getParameter("search");
        String page = request.getParameter("page");
        int pageSize = 30;
        int Count = 0;

        if (page == null) {
            page = "1";
        }

        ArrayList<Product> listProducts = null;
        ArrayList<Product> listProductTop5 = null;
        ArrayList<Category> categories = null;
        List<Product> listProductPage = null;
        try {
            if (pramCategoryId != null && pramCategoryId != "") {
                listProducts = productDao.getProductByCategoryID(pramCategoryId);
            } else if (searchName != null) {
                listProducts = productDao.getProductByName(searchName);
            } else {
                listProducts = productDao.getAllProduct();
            }
            int start = (Integer.parseInt(page) - 1) * pageSize;
            int end = Math.min(start + pageSize, listProducts.size());

            if ((listProducts.size() % pageSize) == 0) {
                Count = listProducts.size() / pageSize;
            } else {
                Count = listProducts.size() / pageSize + 1;
            }
            
            listProductPage = listProducts.subList(start, end);
            listProductTop5 = productDao.getProductTopNew();
            categories = categoryDao.getAllCategory();
        } catch (Exception ex) {
            Logger.getLogger(indexController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("Count", Count);
        request.setAttribute("page", page);
        request.setAttribute("listProductPage", listProductPage);
        request.setAttribute("listProductTop5", listProductTop5);
        request.setAttribute("categories", categories);
        request.setAttribute("pramCategoryId", pramCategoryId);
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        processRequest(request, response);
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
