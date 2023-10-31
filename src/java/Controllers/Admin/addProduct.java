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
public class addProduct extends HttpServlet {

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
            out.println("<title>Servlet addProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addProduct at " + request.getContextPath() + "</h1>");
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
        CategoryDAO categoryDAO = new CategoryDAO();
        ArrayList<Category> categorys = categoryDAO.getAllCategory();

        request.setAttribute("categorys", categorys);
        request.getRequestDispatcher("addProduct.jsp").forward(request, response);

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

        String productName = request.getParameter("productName");
        String category = request.getParameter("category");
        String unitPrice = request.getParameter("unitPrice");
        String unitsInStock = request.getParameter("unitsInStock");
        String quantityPerUnit = request.getParameter("quantityPerUnit");
        String unitsOnOrder = request.getParameter("unitsOnOrder");
        String reorderLevel = request.getParameter("reorderLevel");

        Product product = new Product();
        product.setProductName(productName);
        product.setCategoryID(Integer.parseInt(category));
        product.setQuantityPerUnit(quantityPerUnit);
        product.setUnitPrice(Float.parseFloat(unitPrice));
        product.setUnitsOnOrder(Float.parseFloat(unitsOnOrder));
        product.setReorderLevel(Float.parseFloat(reorderLevel));

        try {
            int result = productDAO.createProduct(product);
            if (result > 0) {
                request.setAttribute("msg", "Create Product Successfull!");
            } else {
                request.setAttribute("msg", "Create Product Error!");
            }
        } catch (Exception ex) {
            Logger.getLogger(addProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("addProduct.jsp").forward(request, response);
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
