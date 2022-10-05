/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.doctor;

import dal.AppointmentDAO;
import dal.PatientDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Appointment;
import model.Doctor;
import model.Patient;

/**
 *
 * @author hunglx
 */
public class MyPatientDetailController extends HttpServlet {

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
            AppointmentDAO appointment = new AppointmentDAO();
            PatientDAO patient = new PatientDAO();
            HttpSession session = request.getSession();
            Doctor d = (Doctor) session.getAttribute("user");
            int id = Integer.parseInt(request.getParameter("id"));
            Patient p = patient.getPatientByID(id);
            ArrayList<Appointment> a = appointment.getAppoinmentsByPatientDoctorID(id, d.getId());
            request.setAttribute("patient", p);
            request.setAttribute("listAppointments", a);
            request.getRequestDispatcher("MyPatientDetail.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MyPatientDetailController.class.getName()).log(Level.SEVERE, null, ex);
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
