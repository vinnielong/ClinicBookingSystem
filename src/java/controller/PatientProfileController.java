/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.PatientDAO;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Account;
import model.Patient;

/**
 *
 * @author hunglx
 */
@MultipartConfig(maxFileSize = 104857600)
public class PatientProfileController extends HttpServlet {

    private static final long serialVersionUID = 9999L;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("utility/Message");

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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc != null && acc.getRole() == 1) {
            response.sendRedirect(request.getContextPath() + "/Error404.jsp");
        } else if (acc != null && acc.getRole() == 2) {
            response.sendRedirect(request.getContextPath() + "/Error404.jsp");
        } else if (acc != null && acc.getRole() == 3) {
            request.getRequestDispatcher("PatientProfile.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/Error404.jsp");
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
            AccountDAO account = new AccountDAO();
            PatientDAO dao = new PatientDAO();
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("acc");
            int accountID = acc.getId();
            int choice = Integer.parseInt(request.getParameter("choice"));
            switch (choice) {
                case 1:
                    String name = request.getParameter("name");
                    String gender_raw = request.getParameter("gender");
                    int gender = gender_raw.equalsIgnoreCase("Male") ? 1 : 0;
                    String dob = request.getParameter("dob");
                    String phone = request.getParameter("phone");
                    account.updateProfile(name, gender, dob, phone, "", 3, accountID);
                    request.setAttribute("success", resourceBundle.getString("profile_success"));
                    break;
                case 2:
                    String file = request.getParameter("avatar");
                    Part part = request.getPart("avatar");
                    InputStream is = part.getInputStream();
                    if (file == null) {
                        account.updateAvatar(is, accountID);
                        request.setAttribute("success", resourceBundle.getString("profile_success"));
                    } else {
                        request.setAttribute("fail", resourceBundle.getString("avatar_fail"));
                    }
                    break;
            }
            Patient p = dao.getPatientByAccountID(accountID);
            session.setAttribute("user", p);
                        request.getRequestDispatcher("PatientProfile.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PatientProfileController.class.getName()).log(Level.SEVERE, null, ex);
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
