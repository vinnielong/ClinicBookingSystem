/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import dal.DoctorDAO;
import dal.PatientDAO;
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
import model.Doctor;
import model.Patient;
import utility.PasswordEncrypt;

/**
 *
 * @author the-Beast
 */
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 9999L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (acc != null && acc.getRole() != 2 && acc.getRole() != 3) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } else if (acc != null && acc.getRole() == 2) {
            response.sendRedirect(request.getContextPath() + "/doctor/myappointment");
        } else if (acc != null && acc.getRole() == 3) {
            response.sendRedirect("homepage");
        } else {
            if (username != null || password != null) {
                request.setAttribute("mess", resourceBundle.getString("wrong_username_or_pass"));
            }

            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            AccountDAO db = new AccountDAO();
            DoctorDAO dao = new DoctorDAO();
            PatientDAO pao = new PatientDAO();
            PasswordEncrypt encrypt = new PasswordEncrypt();
            Account acc = db.getAccountByUserAndPass(username, encrypt.generateEncryptedPassword(password));
            HttpSession session = request.getSession();

            if (acc != null && acc.getStatus() == 1) {
                session.setAttribute("acc", acc);
                switch (acc.getRole()) {
                    case 1:
                        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                        break;
                    case 2:
                        Doctor d = dao.getDoctorByAccountID(acc.getId());
                        session.setAttribute("user", d);
                        response.sendRedirect(request.getContextPath() + "/doctor/myappointment");
                        break;
                    case 3:
                        Patient p = pao.getPatientByAccountID(acc.getId());
                        session.setAttribute("user", p);
                        response.sendRedirect("homepage");
                        break;
                    case 4:
                        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                        break;
                    case 5:
                        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                        break;
                }
            } else {
                doGet(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
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
