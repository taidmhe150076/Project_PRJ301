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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taisk
 */
public class updateProduct extends HttpServlet {

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
            out.println("<title>Servlet updateProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateProduct at " + request.getContextPath() + "</h1>");
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
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        String productid = request.getParameter("productId");
        try {
            Product product = productDAO.getProductById(Integer.parseInt(productid));
            ArrayList<Category> category = categoryDAO.getAllCategory();
            request.setAttribute("product", product);
            request.setAttribute("category", category);
        } catch (Exception ex) {
            Logger.getLogger(updateProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
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
        ProductDAO productDAO = new ProductDAO();
        String productID = request.getParameter("productID");
        String productName = request.getParameter("productname");
        String categoryID = request.getParameter("categoryID");
        String unitPrice = request.getParameter("unitPrice");
        String unitsInStock = request.getParameter("unitsInStock");

        try {
            Product product = productDAO.getProductById(Integer.parseInt(productID));
            if (product != null) {
                product.setProductName(productName);
                product.setCategoryID(Integer.parseInt(categoryID));
                product.setUnitPrice(Float.parseFloat(unitPrice));
                product.setUnitsInStock(Float.parseFloat(unitsInStock));
            }
            int result = productDAO.updateProduct(product);
            if (result > 0) {
                request.setAttribute("msg", "Update Thành Công !");
            } else {
                request.setAttribute("msg", "Update Error !");
            }
        } catch (Exception ex) {
            Logger.getLogger(updateProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
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
