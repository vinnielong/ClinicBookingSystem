/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import utility.PasswordEncrypt;
import utility.Validate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author the-Beast
 */
public class ChangePasswordController extends HttpServlet {

    private static final long serialVersionUID = 9999L;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("utility/Message");

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
        request.getRequestDispatcher("ChangePasswordPatient.jsp").forward(request, response);
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
        try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("acc");
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            Validate validate = new Validate();
            PasswordEncrypt encrypt = new PasswordEncrypt();

            if (!acc.getPassword().equals(encrypt.generateEncryptedPassword(oldPassword))) {
                request.setAttribute("mess", resourceBundle.getString("invalid_pass"));
                request.setAttribute("oldPassword", oldPassword);
                request.setAttribute("newPassword", newPassword);
                request.setAttribute("confirmPassword", confirmPassword);
                request.getRequestDispatcher("ChangePasswordPatient.jsp").forward(request, response);

            } else if (validate.checkPassword(newPassword) == false) {
                request.setAttribute("mess", resourceBundle.getString("password_requirement"));
                request.setAttribute("oldPassword", oldPassword);
                request.setAttribute("newPassword", newPassword);
                request.setAttribute("confirmPassword", confirmPassword);
                request.getRequestDispatcher("ChangePasswordPatient.jsp").forward(request, response);

            } else if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("mess", resourceBundle.getString("pass_not_matched"));
                request.setAttribute("oldPassword", oldPassword);
                request.setAttribute("newPassword", newPassword);
                request.setAttribute("confirmPassword", confirmPassword);
                request.getRequestDispatcher("ChangePasswordPatient.jsp").forward(request, response);

            } else {
                AccountDAO dao = new AccountDAO();
                dao.changePassword(encrypt.generateEncryptedPassword(newPassword), acc.getId());
                request.setAttribute("success", resourceBundle.getString("change_pass"));
                request.getRequestDispatcher("ChangePasswordPatient.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
