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

/**
 *
 * @author Admin
 */
public class EmailVerifyController extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mess", resourceBundle.getString("send_code_to_mail"));
        req.getRequestDispatcher("VerifyEmailRegister.jsp").forward(req, resp);
        
    }

    
    ResourceBundle resourceBundle = ResourceBundle.getBundle("utility/Message");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("authcode");
            String code = request.getParameter("authcode");
            if (code.equals(account.getCode())) {
                AccountDAO accountDAO = new AccountDAO();
                accountDAO.signup(account);
                request.setAttribute("mess", resourceBundle.getString("verify_success"));
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                request.setAttribute("mess", resourceBundle.getString("verify_fail"));
                request.getRequestDispatcher("VerifyEmailRegister.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmailVerifyController.class.getName()).log(Level.SEVERE, null, ex);
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
