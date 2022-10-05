/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.ServiceDAO;
import dal.SettingDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Service;
import model.Settings;

/**
 *
 * @author Vinnie Long
 */
@MultipartConfig(maxFileSize = 104857600)
public class ServiceDetailController extends HttpServlet {

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
            ServiceDAO dao = new ServiceDAO();
            SettingDAO d = new SettingDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            Service s = dao.getServiceByID(id);
            ArrayList<Settings> list = d.getSettingsByType(2);
            request.setAttribute("service", s);
            request.setAttribute("listCategory", list);
            request.getRequestDispatcher("ServiceDetails.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDetailController.class.getName()).log(Level.SEVERE, null, ex);
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
            ServiceDAO dao = new ServiceDAO();
            SettingDAO d = new SettingDAO();
            PrintWriter out = response.getWriter();
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            int price = Integer.parseInt(request.getParameter("price"));
            int status = (request.getParameter("status") == null ? 0 : 1);
            int category = Integer.parseInt(request.getParameter("category"));
            String desc = request.getParameter("description");
            String file = request.getParameter("thumbnail");
            Part part = request.getPart("thumbnail");
            InputStream is = part.getInputStream();
            Service s = null;
            s = new Service(id, title, d.getSettingByID(category), price, status, desc);
            if (file == null) {
                dao.editService(s, id, is);
            } else {
                dao.editService2(s, id);
            }
            response.sendRedirect("servicelist");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDetailController.class.getName()).log(Level.SEVERE, null, ex);
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
