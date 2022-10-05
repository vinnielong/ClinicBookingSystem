/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.AppointmentDAO;
import dal.DoctorDAO;
import dal.PatientDAO;
import dal.ReservationDAO;
import dal.ServiceDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hunglx
 */
public class DashboardController extends HttpServlet {

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
            DoctorDAO doctor = new DoctorDAO();
            ServiceDAO service = new ServiceDAO();
            AppointmentDAO appointment = new AppointmentDAO();
            ReservationDAO res = new ReservationDAO();
            PatientDAO patient = new PatientDAO();
            request.setAttribute("doctor", doctor.countDoctor());
            request.setAttribute("service", service.countServices(0));
            request.setAttribute("appointment", appointment.countAppointment());
            request.setAttribute("patient", patient.countPatient());
            request.setAttribute("listAppointments", appointment.getLastAppointments());
            request.setAttribute("res", res.getLastReservation());
            request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BlogListController.class.getName()).log(Level.SEVERE, null, ex);
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
