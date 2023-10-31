/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Admin;

import DataAccess.OrderDetailDAO;
import DataAccess.OrdersDAO;
import Models.OrderDetails;
import Models.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author taisk
 */
public class IndexAdmin extends HttpServlet {

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
            out.println("<title>Servlet IndexAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IndexAdmin at " + request.getContextPath() + "</h1>");
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
        ArrayList monthOfRevenue = new ArrayList();
        ArrayList listYear = new ArrayList();
        int count = 0;
        int pageSize = 20;
        int pageCount = 0;
        String yearParam = request.getParameter("year");
        String page = request.getParameter("page");
        
        if (page == null) {
            page = "1";
        }
        
        // get currentYear
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentYears = currentDate.getYear();
        int currentMonth = currentDate.getMonth().getValue();
        int currentDay = currentDate.getDayOfMonth();

        if (yearParam != null) {
            currentYear = Integer.parseInt(yearParam);
        }

        while (count <= 30) {
            listYear.add(currentYears - count);
            count++;
        }

        for (int i = 1; i <= 12; i++) {
            float revenueMonth = 0;
            monthOfRevenue.add(getRevenueMonth(currentYear, i, revenueMonth));
        }

        float currentMonthOfRevenue = getRevenueMonth(currentYears, currentMonth, 0);
        float todayRevenue = getRevenueToday(currentYears, currentMonth, currentDay, 0);
        float totalRevenue = getRevenueTotal();
        int countOrders = getCountOrders();
        
        ArrayList<Orders> listOrderses = getOrders();
        
        int start = (Integer.parseInt(page) - 1) * pageSize;
        int end = Math.min(start + pageSize, listOrderses.size());
        if ((listOrderses.size() % pageSize) == 0) {
            pageCount = listOrderses.size() / pageSize;
        }else{
            pageCount = listOrderses.size() / pageSize + 1;
        }
        
        List<Orders> listOrderByPage =listOrderses.subList(start, end);
        String requestJson = convertToStringArray(monthOfRevenue).toString();

        request.setAttribute("currentMonthOfRevenue", currentMonthOfRevenue);
        request.setAttribute("todayRevenue", todayRevenue);
        request.setAttribute("totalRevenue", totalRevenue);
        request.setAttribute("countOrders", countOrders);
        request.setAttribute("page", page);
        request.setAttribute("listOrderByPage", listOrderByPage);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("listYear", listYear);
        request.setAttribute("currentYear", currentYear);
        request.setAttribute("monthOfRevenue", requestJson);
        request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
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
        // processRequest(request, response);
        String selectYear = request.getParameter("year");

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

    private StringBuilder convertToStringArray(ArrayList a) {
        // Chuyển đổi mảng Java thành chuỗi JSON thủ công
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < a.size(); i++) {
            json.append(a.get(i));
            if (i < a.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        if (json != null) {
            return json;
        }
        return null;
    }

    public float[] addElement(float[] arr, float element) {
        if (arr == null) {
            return new float[]{element};
        } else {
            int newArrLength = arr.length + 1;
            float[] newArr = new float[newArrLength];

            // Sao chép tất cả phần tử từ arr sang newArr
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = arr[i];
            }

            // Gán giá trị mới vào vị trí cuối cùng
            newArr[newArrLength - 1] = element;

            return newArr;
        }
    }

    public float getRevenueMonth(int year, int month, float revenueMonth) {
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

        List<OrderDetails> listOrderDetail = orderDetailDAO.getlistOrderDetailses(year, month);
        for (OrderDetails orderDetails : listOrderDetail) {
            revenueMonth += orderDetails.getQuantity() * orderDetails.getUnitPrice();
        }
        return revenueMonth;
    }

    public float getRevenueToday(int year, int month, int day, float revenueMonth) {
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

        List<OrderDetails> listOrderDetail = orderDetailDAO.getlistOrderDetailsesToday(year, month, day);
        for (OrderDetails orderDetails : listOrderDetail) {
            revenueMonth += orderDetails.getQuantity() * orderDetails.getUnitPrice();
        }
        return revenueMonth;
    }

    public float getRevenueTotal() {
        float revenueMonth = 0;
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        ArrayList<OrderDetails> listOrderDetail = new ArrayList<>();

        listOrderDetail = orderDetailDAO.getlistOrderDetailsesALL();
        for (OrderDetails orderDetails : listOrderDetail) {
            revenueMonth += orderDetails.getQuantity() * orderDetails.getUnitPrice();
        }
        return revenueMonth;
    }

    public int getCountOrders() {
        OrdersDAO orderdao = new OrdersDAO();
        return orderdao.getCountOrder();
    }
    public ArrayList<Orders> getOrders() {
        OrdersDAO orderdao = new OrdersDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        ArrayList<Orders> orderses = new ArrayList<>();
        
        ArrayList<Orders> listOrders = orderdao.getOrder();
        
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
