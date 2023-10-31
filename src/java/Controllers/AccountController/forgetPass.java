/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.AccountController;

import DataAccess.LoginDAO;
import Models.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import Common.SendMail;
import javax.mail.MessagingException;

/**
 *
 * @author taisk
 */
public class forgetPass extends HttpServlet {

    static int otpGen = 0;
    static String emails;
    static String passwords;

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
            out.println("<title>Servlet forgetPass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet forgetPass at " + request.getContextPath() + "</h1>");
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
        boolean flag = false;
        request.setAttribute("flag", flag);
        request.getRequestDispatcher("view/account/forgetPass.jsp").forward(request, response);

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
        Account account = new Account();
        LoginDAO loginDAO = new LoginDAO();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cPassword = request.getParameter("cPassword");
        if (email != null && password != null ) {
            emails = email;
            passwords = password;
        }
        String msg = "";
        String otp = request.getParameter("otp");
        if (otp != null) {
            if (otpGen != Integer.parseInt(otp)) {
                msg = "OTP NOT matching!";
                request.setAttribute("msg", msg);
                request.setAttribute("flag", true);
                request.getRequestDispatcher("view/account/forgetPass.jsp").forward(request, response);
            } else {
                int result = loginDAO.updatePassword(emails, passwords);
                if (result > 0) {
                    msg = "Change Password success!";
                    request.setAttribute("msg", msg);
                    request.setAttribute("flag", false);
                    request.getRequestDispatcher("view/account/forgetPass.jsp").forward(request, response);
                    return;
                }
            }
        }

        if (loginDAO.isEmail(email)) {
            if (!password.equals(cPassword)) {
                msg = "Password not same!";
                request.setAttribute("msg", msg);
                request.setAttribute("flag", false);
                request.getRequestDispatcher("view/account/forgetPass.jsp").forward(request, response);
                return;
            }
            otpGen = GenOTP();
            SendMail.Send(email, otpGen);

            request.setAttribute("flag", true);
            request.getRequestDispatcher("view/account/forgetPass.jsp").forward(request, response);
            return;
        } else {
            msg = "Email not Exist!";
            request.setAttribute("msg", msg);
            request.setAttribute("flag", false);
            request.getRequestDispatcher("view/account/forgetPass.jsp").forward(request, response);
            return;
        }

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

    public int GenOTP() {
        int min = 10_000; // Số nguyên tối thiểu (bao gồm)
        int max = 99_999; // Số nguyên tối đa (bao gồm)
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;

        return randomNumber;
    }
}
