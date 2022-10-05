/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.AccountDAO;
import dal.SettingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Settings;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Admin
 */
public class AccountListController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String role = request.getParameter("role");
            String status = request.getParameter("status");
            String search = request.getParameter("search");
            AccountDAO accountDAO = new AccountDAO();
            SettingDAO settingDAO = new SettingDAO();
            if (!StringUtils.isEmpty(role) && role.equalsIgnoreCase("0")) {
                role = null;
            }
            if (search == "") {
                search = null;
            }
            if (search == null && status == null && role == null) {
                ArrayList<Account> accounts = accountDAO.getAllAccount();
                request.setAttribute("accounts", accounts);

            } else {

                ArrayList<Account> accounts = accountDAO.searchAccount(search, role, status);
                request.setAttribute("accounts", accounts);
                request.setAttribute("search", search);
            }
            if (role != null) {
                int role1 = Integer.parseInt(role);
                request.setAttribute("role1", role1);
            }
            if (status != null) {
                int status1 = Integer.parseInt(status);
                request.setAttribute("status1", status1);
            }
            ArrayList<Settings> listRole = settingDAO.getSettingsByType(1);
            request.setAttribute("listRole", listRole);
            request.getRequestDispatcher("AccountList.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AccountListController.class.getName()).log(Level.SEVERE, null, ex);
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
            String status1 = request.getParameter("accountstatus");
            int id = Integer.parseInt(request.getParameter("accountid"));
            int role = Integer.parseInt(request.getParameter("role"));
            int status = 1;
            AccountDAO accountDAO = new AccountDAO();
            if (status1 == null) {
                status = 0;
                accountDAO.changeStatusRole(status, id, role);
            } else {
                accountDAO.changeStatusRole(status, id, role);
            }
            //Lay thong tin account, nhung chu yeu la lay status
            Account account = accountDAO.getAccountById(id);
            response.sendRedirect("accountlist");
        } catch (SQLException ex) {
            Logger.getLogger(AccountListController.class.getName()).log(Level.SEVERE, null, ex);
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
