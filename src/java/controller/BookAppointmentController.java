/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AppointmentDAO;
import dal.DoctorDAO;
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
import javax.servlet.http.HttpSession;
import model.Account;
import model.Doctor;
import model.DoctorFeedback;
import model.Settings;

/**
 *
 * @author vinnielong
 */
public class BookAppointmentController extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc != null && acc.getRole() == 1) {
            response.sendRedirect(request.getContextPath() + "/admin/settinglist");
        } else if (acc != null && acc.getRole() == 2) {
            response.sendRedirect(request.getContextPath() + "/doctor/myappointment");
        } else if (acc != null && acc.getRole() == 3) {
            try {
                DoctorDAO doctor = new DoctorDAO();
                SettingDAO setting = new SettingDAO();
                int id = Integer.parseInt(request.getParameter("id"));
                Doctor d = doctor.getDoctorByID(id);
                ArrayList<DoctorFeedback> list = doctor.getFeedbackByDoctorID(id);
                ArrayList<Settings> slot = setting.getSettingsByType(3);
                request.setAttribute("doctor", d);
                request.setAttribute("cboSlot", slot);
                request.setAttribute("totalFeedback", list.size());
                request.getRequestDispatcher("BookAppointment.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(BookAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acc == null) {
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
            AppointmentDAO dao = new AppointmentDAO();
            int doctor_id = Integer.parseInt(request.getParameter("doctor_id"));
            int patient_id = Integer.parseInt(request.getParameter("patient_id"));
            String date = request.getParameter("date");
            String description = request.getParameter("description");
            int slot = Integer.parseInt(request.getParameter("slot"));
            if (dao.addAppointment(doctor_id, patient_id, date, description, slot)) {
                response.sendRedirect("dtinfo?sid=" + doctor_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
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
