/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.AppointmentDAO;
import dal.DoctorDAO;
import dal.SettingDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Appointment;
import model.Doctor;
import model.DoctorFeedback;
import model.Settings;

/**
 *
 * @author Admin
 */
public class DoctorDetailEditController extends HttpServlet {

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
            int id = Integer.parseInt(request.getParameter("id"));
            SettingDAO settingDAO = new SettingDAO();
            DoctorDAO doctorDAO = new DoctorDAO();
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            Doctor doctorById = doctorDAO.getDoctorByID(id);
            ArrayList<Settings> settingses = settingDAO.getSettingsByType(2);
            ArrayList<Appointment> appointments = appointmentDAO.getAppoinmentsByDoctorID(id);
            ArrayList<DoctorFeedback> listFeedback = doctorDAO.getFeedbackByDoctorID(id);
            request.setAttribute("listFeedback", listFeedback);
            request.setAttribute("appointments", appointments);
            request.setAttribute("doctorById", doctorById);
            request.setAttribute("settingses", settingses);
            request.getRequestDispatcher("DoctorDetail.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDetailEditController.class.getName()).log(Level.SEVERE, null, ex);
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
            int id = Integer.parseInt(request.getParameter("id"));
            String doctorName = request.getParameter("doctorname");
            String role = request.getParameter("role");
            int gender = Integer.parseInt(request.getParameter("gender"));
            Date dob = Date.valueOf(request.getParameter("dob"));
            String phone = request.getParameter("phone");
            int speciality = Integer.parseInt(request.getParameter("speciality"));
            int status = (request.getParameter("status")==null?0:1);
            String description = request.getParameter("description");
            
            Doctor doctor = new Doctor();
            doctor.setDoctorName(doctorName);
            doctor.setRole(role);
            doctor.setGender(gender);
            doctor.setDob(dob);
            doctor.setPhone(phone);
            Settings s = new Settings();
            s.setID(speciality);
            doctor.setSpeciality(s);
            doctor.setStatus(status);
            doctor.setDescription(description);
            
            DoctorDAO doctorDAO = new DoctorDAO();
            doctorDAO.updateDoctor(doctor, id);
            response.sendRedirect("doctorlist");
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDetailEditController.class.getName()).log(Level.SEVERE, null, ex);
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
