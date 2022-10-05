/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ReservationDAO;
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
import model.Account;
import model.Reservation;
import model.Patient;

/**
 *
 * @author vinnielong
 */
public class ReservationHistoryController extends HttpServlet {

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
            response.sendRedirect(request.getContextPath() + "/Error404.jsp");
        } else if (acc != null && acc.getRole() == 2) {
            response.sendRedirect(request.getContextPath() + "/Error404.jsp");
        } else if (acc != null && acc.getRole() == 3) {
            try {
                ReservationDAO reservation = new ReservationDAO();
                Patient p = (Patient) session.getAttribute("user");
                ArrayList<Reservation> res = reservation.getReservationByPatientId(p.getID());
                request.setAttribute("resList", res);
                request.getRequestDispatcher("ReservationHistory.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ReservationHistoryController.class.getName()).log(Level.SEVERE, null, ex);
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
