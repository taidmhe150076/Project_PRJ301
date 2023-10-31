/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Home.Cart;

import DataAccess.ProductDAO;
import Models.CartItem;
import Models.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taisk
 */
public class Cart extends HttpServlet {

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
            out.println("<title>Servlet Cart</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Cart at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") == null) {
            response.sendRedirect("Login");
            return;
        }
        ArrayList<CartItem> carts = (ArrayList<CartItem>)session.getAttribute("cart");
        float totalPrice = 0;
        for (CartItem cartItem : carts) {
            totalPrice += cartItem.getQuantity() * cartItem.getProduct().getUnitPrice();
        }
        request.setAttribute("cart", carts);
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("view/cart/cartIndex.jsp").forward(request, response);
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
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") == null) {
            response.sendRedirect("Login");
            return;
        }
 
        ProductDAO productDAO = new ProductDAO();
        String productId = request.getParameter("productid");
        Product product = null;
        ArrayList<CartItem> carts = null;
        try {
            product = productDAO.getProductById(Integer.parseInt(productId));
            carts = (ArrayList<CartItem>)session.getAttribute("cart");
            if (carts != null) {
                
                boolean isCarted = false;
                for (CartItem cart : carts) {
                    if (cart.getProduct().getProductID() == product.getProductID()) {
                        cart.setQuantity(cart.getQuantity() + 1);
                        isCarted = true;
                    }
                }
                if (!isCarted) {
                    CartItem item = new CartItem();
                    item.setCartId(carts.size() + 1);
                    item.setProduct(product);
                    item.setQuantity(1);
                    carts.add(item);
                }
            }else{
                
                carts = new ArrayList<CartItem>();
                carts.add(new CartItem(carts.size() + 1, 1, product));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
        int countCart = carts.size();
        session.setAttribute("cart", carts);
        session.setAttribute("count", countCart);
        response.sendRedirect("indexController");
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
