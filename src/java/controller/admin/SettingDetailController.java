/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.SettingDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Settings;

/**
 *
 * @author Admin
 */
public class SettingDetailController extends HttpServlet {

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
            String option = request.getParameter("option");
            if (option.equalsIgnoreCase("edit")) {
                int id = Integer.parseInt(request.getParameter("id"));
                SettingDAO sd = new SettingDAO();
                Settings st = sd.getSettingByID(id);
                request.setAttribute("setting", st);
                request.setAttribute("option", option);
                request.getRequestDispatcher("AddEditSetting.jsp").forward(request, response);
            } else if (option.equalsIgnoreCase("add")) {
                request.setAttribute("option", option);
                request.getRequestDispatcher("AddEditSetting.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDetailController.class.getName()).log(Level.SEVERE, null, ex);
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
            SettingDAO sd = new SettingDAO();
            String option = request.getParameter("option");
            if (option.equalsIgnoreCase("add")) {
                int type = Integer.parseInt(request.getParameter("type"));
                String name = request.getParameter("name");
                int value = Integer.parseInt(request.getParameter("value"));
                String desc = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("status"));
                sd.addNewSetting(type, name, value, desc, status);
            } else if (option.equalsIgnoreCase("edit")) {
                int id = Integer.parseInt(request.getParameter("id"));
                int type = Integer.parseInt(request.getParameter("type"));
                String name = request.getParameter("name");
                int value = Integer.parseInt(request.getParameter("value"));
                String desc = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("status"));
                sd.editSetting(type, name, value, desc, status, id);
            }
            response.sendRedirect("settinglist");
        } catch (SQLException ex) {
            Logger.getLogger(SettingDetailController.class.getName()).log(Level.SEVERE, null, ex);
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
