/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
import utility.PasswordEncrypt;
import utility.Validate;

/**
 *
 * @author Admin
 */
public class ForgotResetPasswordController extends HttpServlet {

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("ForgotResetPassword.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ResourceBundle resourceBundle = ResourceBundle.getBundle("utility/Message");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("authcode");
        AccountDAO accountDAO = new AccountDAO();
        String password = request.getParameter("psw1");
        String repass = request.getParameter("psw-repeat1");
        Validate validate = new Validate();
        if (!password.equals(repass)) {
            request.setAttribute("mess", resourceBundle.getString("pass_not_matched"));
            request.setAttribute("password", password);
            request.setAttribute("repass", repass);
            request.getRequestDispatcher("ForgotResetPassword.jsp").forward(request, response);
        } else if (validate.checkPassword(password) == false) {
            request.setAttribute("mess", resourceBundle.getString("password_requirement"));
            request.setAttribute("password", password);
            request.setAttribute("repass", repass);
            request.getRequestDispatcher("ForgotResetPassword.jsp").forward(request, response);
        } else {
            PasswordEncrypt encrypt = new PasswordEncrypt();
            try {
                accountDAO.changePassword(encrypt.generateEncryptedPassword(password), account.getId());
                System.out.println(encrypt.generateEncryptedPassword(password));
                System.out.println(account.getId());
                request.setAttribute("mess", resourceBundle.getString("change_pass"));
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ForgotResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
