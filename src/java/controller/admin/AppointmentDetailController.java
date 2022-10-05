/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.AccountDAO;
import dal.AppointmentDAO;
import dal.DoctorDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Appointment;
import model.Doctor;

/**
 *
 * @author Vinnie Long
 */
public class AppointmentDetailController extends HttpServlet {

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
            AppointmentDAO dao = new AppointmentDAO();
            DoctorDAO d = new DoctorDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            Appointment a = dao.getAppoinmentsByID(id);
            ArrayList<Doctor> list = d.getAllDoctors();
            String patientimg = dao.encode64(a.getPatient().getImage());
            String doctorimg = dao.encode64(a.getDoctor().getImage());
            AccountDAO account = new AccountDAO();
            ArrayList<Account> staff = account.getAccountByRole(4);
            request.setAttribute("cboStaff", staff);
            request.setAttribute("appointment", a);
            request.setAttribute("cboDoctor", list);
            request.setAttribute("patientimg", patientimg);
            request.setAttribute("doctorimg", doctorimg);
            request.getRequestDispatcher("AdminAppointmentDetails.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDetailController.class.getName()).log(Level.SEVERE, null, ex);
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
            boolean isEdited = false;
            AppointmentDAO dao = new AppointmentDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            Appointment a = dao.getAppoinmentsByID(id);
            String option = request.getParameter("option");
            if (option.equalsIgnoreCase("staff")) {
                int staff_id = Integer.parseInt(request.getParameter("cboStaff"));
                isEdited = dao.editAppointment(id, a.getStatus(), staff_id);
            } else if (option.equalsIgnoreCase("status")) {
                int status = Integer.parseInt(request.getParameter("status"));
                isEdited = dao.editAppointment(id, status, a.getStaff().getId());
            }
            if (isEdited) {
                response.sendRedirect("appointment");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDetailController.class.getName()).log(Level.SEVERE, null, ex);
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
