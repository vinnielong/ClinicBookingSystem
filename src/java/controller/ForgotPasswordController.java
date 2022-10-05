/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import utility.SendMail;

/**
 *
 * @author Admin
 */
public class ForgotPasswordController extends HttpServlet {

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
    ResourceBundle resourceBundle = ResourceBundle.getBundle("utility/Message");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        AccountDAO accountDAO = new AccountDAO();
        try {
            if (accountDAO.checkEmailExist(email) == 0) {
                request.setAttribute("mess", resourceBundle.getString("not_existed_email"));
                request.setAttribute("email", email);
                request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
            } else {
                SendMail sendMail = new SendMail();
                String code = sendMail.getRandom();
                Account account = accountDAO.getAccountByEmail(email);
                account.setCode(code);
                String message = "<h1> Clinic Booking </h1>"
                        + "Enter email successfully.Please verify your account using this code: "
                        + "<h3> Verify Code: " + code + "</h3>";
                String subject = "Forgot Password : Confirmation";
                String to = email;
                String from = "hauzter57@gmail.com";

                //call the send email method
                boolean test = sendMail.sendAttach(message, subject, to, from);

                if (test) {
                    HttpSession session = request.getSession();
                    session.setAttribute("authcode", account);
                    response.sendRedirect("VerifyEmailResetPass.jsp");
                } else {
                    response.getWriter().println("Failed to send verification email");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("authcode");

        String code = request.getParameter("authcode");

        if (code.equals(account.getCode())) {
            response.sendRedirect("ForgotResetPassword.jsp");
            session.setAttribute("authcode", account);
        } else {
            response.getWriter().println("Incorrect verification code");
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

}
