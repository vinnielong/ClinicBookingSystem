/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ReservationDAO;
import dal.ServiceDAO;
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
import model.Service;
import model.ServiceFeedback;
import model.Settings;

/**
 *
 * @author Admin
 */
public class BookingServiceController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookingServiceController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingServiceController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc != null && acc.getRole() == 1) {
            response.sendRedirect(request.getContextPath() + "/admin/settinglist");
        } else if (acc != null && acc.getRole() == 2) {
            response.sendRedirect(request.getContextPath() + "/doctor/myappointment");
        } else if (acc != null && acc.getRole() == 3) {
            try {
                ServiceDAO service = new ServiceDAO();
                SettingDAO setting = new SettingDAO();
                int id = Integer.parseInt(request.getParameter("id"));
                Service d = service.getServiceByID(id);
                ArrayList<ServiceFeedback> list = service.getFeedbackByServiceID(id);
                ArrayList<Settings> slot = setting.getSettingsByType(3);
                request.setAttribute("cboSlot", slot);
                request.setAttribute("service", d);
                request.setAttribute("totalFeedback", list.size());
                request.getRequestDispatcher("BookService.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(BookingServiceController.class.getName()).log(Level.SEVERE, null, ex);
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
            ReservationDAO dao = new ReservationDAO();
            int service_id = Integer.parseInt(request.getParameter("service_id"));
            int patient_id = Integer.parseInt(request.getParameter("patient_id"));
            String date = request.getParameter("date");
            String description = request.getParameter("description");
            int slot = Integer.parseInt(request.getParameter("slot"));
            if (dao.addReservation(patient_id, service_id, description, date, slot)) {
                response.sendRedirect("serviceinfo?sid=" + service_id);
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
