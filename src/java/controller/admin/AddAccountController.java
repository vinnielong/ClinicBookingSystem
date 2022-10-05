/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.AccountDAO;
import dal.SettingDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Account;
import model.Settings;

/**
 *
 * @author Admin
 */
@MultipartConfig(maxFileSize = 104857600)
public class AddAccountController extends HttpServlet {

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
        try {
            SettingDAO settingDAO = new SettingDAO();
            ArrayList<Settings> listRole = settingDAO.getSettingsByType(1);
            request.setAttribute("listRole", listRole);
            request.getRequestDispatcher("AddAccount.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddAccountController.class.getName()).log(Level.SEVERE, null, ex);
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
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String fullName = request.getParameter("fullname");
            int gender = Integer.parseInt(request.getParameter("gender"));
            String phone = request.getParameter("phone");
            int role = Integer.parseInt(request.getParameter("role"));
            int status = (request.getParameter("status")==null?0:1);
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String file = request.getParameter("avatar");
            Part part = request.getPart("avatar");
            InputStream is = part.getInputStream();

            Account account = new Account();
            account.setFullName(fullName);
            account.setGender(gender);
            account.setPhoneNumber(phone);
            account.setRole(role);
            account.setStatus(status);
            account.setEmail(email);
            account.setUsername(username);

            AccountDAO accountDAO = new AccountDAO();
            if (file == null) {
                accountDAO.addAccount(account, is);
            } else {
                accountDAO.addAccountNoImg(account);
            }
            response.sendRedirect("accountlist");
        } catch (SQLException ex) {
            Logger.getLogger(AddAccountController.class.getName()).log(Level.SEVERE, null, ex);
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
