/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.doctor;

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
import javax.servlet.http.HttpSession;
import model.Doctor;
import model.DoctorFeedback;

/**
 *
 * @author hunglx
 */
public class DoctorFeedbackController extends HttpServlet {

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
            HttpSession session = request.getSession();
            Doctor d = (Doctor) session.getAttribute("user");
            String option = request.getParameter("option");
            DoctorDAO doctor = new DoctorDAO();
            ArrayList<DoctorFeedback> listFeedback = doctor.getFeedbackByDoctorID(d.getId());
            request.setAttribute("doctor", d);
            if (option == null) {
                request.setAttribute("listF", listFeedback);
            } else if (option.equalsIgnoreCase("filter")) {
                int star = Integer.parseInt(request.getParameter("star"));
                if (star == 999) {
                    request.setAttribute("listF", listFeedback);
                } else {
                    ArrayList<DoctorFeedback> filter = doctor.filterFeedback(d.getId(), star);
                    request.setAttribute("listF", filter);
                }
            }
            request.getRequestDispatcher("DoctorFeedback.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DoctorFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
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
