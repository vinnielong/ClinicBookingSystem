/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.DoctorDAO;
import dal.SettingDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Doctor;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Admin
 */
public class DoctorListSearchController extends HttpServlet {

    private static final long serialVersionUID = 9999L;

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
//        try {
//            String search = request.getParameter("search");
//            String speciality = request.getParameter("speciality");
//            String gender = request.getParameter("gender");
//            DoctorDAO doctorDAO = new DoctorDAO();
//            SettingDAO settingDAO = new SettingDAO();
//            if (search == "") {
//                search = null;
//            }
//            if (!StringUtils.isEmpty(speciality) && speciality.equalsIgnoreCase("0")) {
//                speciality = null;
//            }
//            if (search==null && speciality==null && gender==null) {
//                
//            }
//            request.setAttribute("searchDoctor", searchDoctor);
//            request.setAttribute("search", search);
//            request.getRequestDispatcher("DoctorListSearch.jsp").forward(request, response);
//        } catch (Exception ex) {
//            Logger.getLogger(DoctorListSearchController.class.getName()).log(Level.SEVERE, null, ex);
//        }

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
