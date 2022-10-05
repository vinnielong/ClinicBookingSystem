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
import model.Settings;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Admin
 */
public class DoctorListController extends HttpServlet {

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
        try {
            String search = request.getParameter("search");
            String speciality = request.getParameter("speciality");
            String gender = request.getParameter("gender");
            DoctorDAO doctorDAO = new DoctorDAO();
            SettingDAO settingDAO = new SettingDAO();
            if (search == "") {
                search = null;
            }
            if (!StringUtils.isEmpty(speciality) && speciality.equalsIgnoreCase("0")) {
                speciality = null;
            }
            if (search==null && speciality==null && gender==null) {
                ArrayList<Doctor> doctors = doctorDAO.getAllDoctors();
                request.setAttribute("doctors", doctors);
            }else{
                ArrayList<Doctor> doctors = doctorDAO.searchDoctorAdminSite(search, speciality, gender);
                request.setAttribute("doctors", doctors);
                request.setAttribute("search", search);
            }
            if (speciality!=null) {
                int speciality1 = Integer.parseInt(speciality);
                request.setAttribute("speciality1", speciality1);
            }
            if (gender!=null) {
                int gender1 = Integer.parseInt(gender);
                request.setAttribute("gender1", gender1);
            }
            ArrayList<Settings> listSpeciality = settingDAO.getSettingsByType(2);
            request.setAttribute("listSpeciality", listSpeciality);
            request.getRequestDispatcher("DoctorList.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DoctorListController.class.getName()).log(Level.SEVERE, null, ex);
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
