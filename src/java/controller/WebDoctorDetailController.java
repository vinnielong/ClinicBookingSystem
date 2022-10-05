/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DoctorDAO;
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
import model.Doctor;
import model.DoctorFeedback;

/**
 *
 * @author Admin
 */
public class WebDoctorDetailController extends HttpServlet {

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
            out.println("<title>Servlet WebDoctorDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WebDoctorDetailController at " + request.getContextPath() + "</h1>");
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
        try {
            String option = request.getParameter("option");
             int sid = Integer.parseInt(request.getParameter("sid"));
            DoctorDAO doctor = new DoctorDAO();
            Doctor s = doctor.getDoctorByID(sid);
            ArrayList<DoctorFeedback> listFeedback = doctor.getFeedbackByDoctorID(sid);
            request.setAttribute("doctor", s);
            request.setAttribute("totalfeedback", listFeedback.size());
            request.setAttribute("avgrate", doctor.averageRate(s.getId()));
            if (option == null) {
                request.setAttribute("listF", listFeedback);
                request.getRequestDispatcher("Web-doctor-detail.jsp").forward(request, response);
            } else if (option.equalsIgnoreCase("delete")) {
                int fid = Integer.parseInt(request.getParameter("fid"));
                if (doctor.deleteComment(fid)) {
                    response.sendRedirect("dtinfo?sid=" + sid + "#doc_reviews");
                }
            } else if (option.equalsIgnoreCase("filter")) {
                int star = Integer.parseInt(request.getParameter("star"));
                if (star == 999) {
                    request.setAttribute("listF", listFeedback);
                } else {
                    ArrayList<DoctorFeedback> sortsf = doctor.filterFeedback(sid, star);
                    request.setAttribute("listF", sortsf);
                }
                request.getRequestDispatcher("Web-doctor-detail.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(WebDoctorDetailController.class.getName()).log(Level.SEVERE, null, ex);
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
           DoctorDAO doctor = new DoctorDAO();
            int choice = Integer.parseInt(request.getParameter("choice"));
            int sid = Integer.parseInt(request.getParameter("sid"));
            int patient = Integer.parseInt(request.getParameter("patientID"));
            int star = 0;
            int fid = 0;
            String comment = null;
            switch (choice) {
                case 2:
                    fid = Integer.parseInt(request.getParameter("fid"));
                    star = Integer.parseInt(request.getParameter("" + fid + ""));
                    comment = request.getParameter("comment");
                    if (doctor.editComment(fid, star, comment)) {
                        response.sendRedirect("dtinfo?sid=" + sid + "#doc_reviews");
                    }
                    break;
                case 3:
                    star = Integer.parseInt(request.getParameter("rate"));
                    comment = request.getParameter("comment");
                    if (doctor.addComment(sid, patient, star, comment)) {
                        response.sendRedirect("dtinfo?sid=" + sid + "#doc_reviews");
                    }
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(WebDoctorDetailController.class.getName()).log(Level.SEVERE, null, ex);
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
