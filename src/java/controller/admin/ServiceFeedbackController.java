/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

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
import model.Service;
import model.ServiceFeedback;

/**
 *
 * @author vinnielong
 */
public class ServiceFeedbackController extends HttpServlet {

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
            String option = request.getParameter("option");
            int id = Integer.parseInt(request.getParameter("id"));
            ServiceDAO service = new ServiceDAO();
            Service s = service.getServiceByID(id);
            ArrayList<ServiceFeedback> listFeedback = service.getFeedbackByServiceID(id);
            request.setAttribute("service", s);
            if (option == null) {
                request.setAttribute("listF", listFeedback);
            } else if (option.equalsIgnoreCase("filter")) {
                int star = Integer.parseInt(request.getParameter("star"));
                if (star == 999) {
                    request.setAttribute("listF", listFeedback);
                } else {
                    ArrayList<ServiceFeedback> filter = service.filterFeedback(id, star);
                    request.setAttribute("listF", filter);
                }
            }
            request.getRequestDispatcher("ServiceFeedback.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
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
