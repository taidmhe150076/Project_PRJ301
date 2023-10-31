/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Home;

import DataAccess.OrderDetailDAO;
import DataAccess.OrdersDAO;
import Models.Account;
import Models.OrderDetails;
import Models.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author taisk
 */
public class orders extends HttpServlet {

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
            out.println("<title>Servlet orders</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet orders at " + request.getContextPath() + "</h1>");
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
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        OrdersDAO orderdao = new OrdersDAO();
        ArrayList<OrderDetails> listOrderDetail = new ArrayList<>();
        HttpSession session = request.getSession(true);
        Account ac = (Account)session.getAttribute("login");
        if (ac == null) {
            response.sendRedirect("Login");
            return;
        }
        
        int count = 0;
        int pageSize = 20;
        int pageCount = 0;
        String yearParam = request.getParameter("year");
        String page = request.getParameter("page");

        if (page == null) {
            page = "1";
        }
        ArrayList<Orders> listOrderses = getOrders(ac.getCustomerID());
        int start = (Integer.parseInt(page) - 1) * pageSize;
        int end = Math.min(start + pageSize, listOrderses.size());
        if ((listOrderses.size() % pageSize) == 0) {
            pageCount = listOrderses.size() / pageSize;
        } else {
            pageCount = listOrderses.size() / pageSize + 1;
        }
        List<Orders> listOrderByPage = listOrderses.subList(start, end);
        request.setAttribute("page", page);
        request.setAttribute("listOrderByPage", listOrderByPage);
        request.setAttribute("pageCount", pageCount);
        request.getRequestDispatcher("orderUser.jsp").forward(request, response);

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

    public ArrayList<Orders> getOrders(String cusId) {
        
        OrdersDAO orderdao = new OrdersDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        ArrayList<Orders> orderses = new ArrayList<>();

        ArrayList<Orders> listOrders = orderdao.getOrder(cusId);

        for (Orders order : listOrders) {

            ArrayList<OrderDetails> orderDetail = orderDetailDAO.getOrderDetailByOrderID(order.getOrderID());
            if (orderDetail != null) {
                order.setOrderDetails(orderDetail);
            }
            orderses.add(order);
        }
        if (!orderses.isEmpty()) {
            return orderses;
        }
        return null;
    }
}
