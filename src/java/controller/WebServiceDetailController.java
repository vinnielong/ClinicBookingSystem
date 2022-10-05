/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ServiceDAO;
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
import model.Service;
import model.ServiceFeedback;

/**
 *
 * @author vinnielong
 */
public class WebServiceDetailController extends HttpServlet {

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
            response.sendRedirect(request.getContextPath() + "/Error404.jsp");
        } else if (acc != null && acc.getRole() == 2) {
            response.sendRedirect(request.getContextPath() + "/Error404.jsp");
        } else if (acc != null && acc.getRole() == 3 || acc == null) {
            try {
                String option = request.getParameter("option");
                int sid = Integer.parseInt(request.getParameter("sid"));
                ServiceDAO service = new ServiceDAO();
                Service s = service.getServiceByID(sid);
                ArrayList<ServiceFeedback> listFeedback = service.getFeedbackByServiceID(sid);
                ArrayList<Service> listService = service.getRelatedServices(s.getCategory().getID());
                request.setAttribute("service", s);
                request.setAttribute("listService", listService);
                request.setAttribute("totalfeedback", listFeedback.size());
                request.setAttribute("thumbnail", service.encode64(s.getThumbnail()));
                request.setAttribute("avgrate", service.averageRate(s.getID()));
                if (option == null) {
                    request.setAttribute("listF", listFeedback);
                    request.getRequestDispatcher("ServiceDetail.jsp").forward(request, response);
                } else if (option.equalsIgnoreCase("delete")) {
                    int fid = Integer.parseInt(request.getParameter("fid"));
                    if (service.deleteComment(fid)) {
                        response.sendRedirect("serviceinfo?sid=" + sid + "#doc_reviews");
                    }
                } else if (option.equalsIgnoreCase("filter")) {
                    int star = Integer.parseInt(request.getParameter("star"));
                    if (star == 999) {
                        request.setAttribute("listF", listFeedback);
                    } else {
                        ArrayList<ServiceFeedback> sortsf = service.filterFeedback(sid, star);
                        request.setAttribute("listF", sortsf);
                    }
                    request.getRequestDispatcher("ServiceDetail.jsp").forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(WebServiceDetailController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            ServiceDAO service = new ServiceDAO();
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
                    if (service.editComment(fid, star, comment)) {
                        response.sendRedirect("serviceinfo?sid=" + sid + "#doc_reviews");
                    }
                    break;
                case 3:
                    star = Integer.parseInt(request.getParameter("rate"));
                    comment = request.getParameter("comment");
                    if (service.addComment(sid, patient, star, comment)) {
                        response.sendRedirect("serviceinfo?sid=" + sid + "#doc_reviews");
                    }
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(WebServiceDetailController.class.getName()).log(Level.SEVERE, null, ex);
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
