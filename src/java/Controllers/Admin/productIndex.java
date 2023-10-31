/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Admin;

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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taisk
 */
public class productIndex extends HttpServlet {

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
            out.println("<title>Servlet productIndex</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productIndex at " + request.getContextPath() + "</h1>");
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

        List<Product> listProduct;
        List<Product> listProductPage;
        String page = request.getParameter("page");

        int pageSize = 30;
        int Count = 0;

        if (page == null) {
            page = "1";
        }
        try {

            listProduct = productDao.getAllProduct();
            int start = (Integer.parseInt(page) - 1) * pageSize;
            int end = Math.min(start + pageSize, listProduct.size());

            if ((listProduct.size() % pageSize) == 0) {
                Count = listProduct.size() / pageSize;
            } else {
                Count = listProduct.size() / pageSize + 1;
            }
            listProductPage = listProduct.subList(start, end);
            List<Category> listCategory = categoryDao.getAllCategory();
            request.setAttribute("listProductPage", listProductPage);
            request.setAttribute("listCategory", listCategory);
            request.setAttribute("Count", Count);
            request.setAttribute("page", page);
        } catch (Exception ex) {
            Logger.getLogger(productIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("crudProductAdmin.jsp").forward(request, response);
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
