/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.doctor;

import dal.AccountDAO;
import dal.DoctorDAO;
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
import model.Doctor;

/**
 *
 * @author hunglx
 */
@MultipartConfig(maxFileSize = 104857600)
public class DoctorProfileController extends HttpServlet {

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
        request.getRequestDispatcher("DoctorProfile.jsp").forward(request, response);
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
            DoctorDAO dao = new DoctorDAO();
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
                    String description = request.getParameter("description");
                    if (account.updateProfile(name, gender, dob, phone, description, 2, accountID)) {
                        request.setAttribute("success", resourceBundle.getString("profile_success"));
                    } else {
                        request.setAttribute("fail", resourceBundle.getString("profile_fail"));
                    }
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
            Doctor d = dao.getDoctorByAccountID(accountID);
            session.setAttribute("user", d);
            request.getRequestDispatcher("DoctorProfile.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DoctorProfileController.class.getName()).log(Level.SEVERE, null, ex);
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
