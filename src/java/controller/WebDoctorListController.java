/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DoctorDAO;
import dal.SettingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Doctor;
import model.Settings;

/**
 *
 * @author Admin
 */
public class WebDoctorListController extends HttpServlet {

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
            out.println("<title>Servlet WebDoctorListController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WebDoctorListController at " + request.getContextPath() + "</h1>");
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
            SettingDAO setting = new SettingDAO();
            DoctorDAO doctor = new DoctorDAO();
            String option = request.getParameter("option");
            ArrayList<Settings> listSpec = setting.getSettingsByType(2);
            request.setAttribute("listSpecialities", listSpec);
            String page = request.getParameter("page");
            if (page == null || page.trim().isEmpty()) {
                page = "1";
            }
            int pageIndex = Integer.parseInt(page);
            int pageSize = 6;

            if (option == null) {
                int totalRecords =doctor.countDoctor();
                int totalPages = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
                ArrayList<Doctor> listDoctor= doctor.getallDoctorPaginate(pageIndex, pageSize);
                request.setAttribute("listDoctor", listDoctor);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("pageIndex", pageIndex);
                request.getRequestDispatcher("Web-doctor-list.jsp").forward(request, response);


            } else if (option.equalsIgnoreCase("sort")) {
                int choice = Integer.parseInt(request.getParameter("choice"));
                int totalRecords = doctor.countDoctor();
                int totalPages = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
                //ArrayList<Service> sort = doctor.sortDoctor(choice, pageIndex, pageSize);
               // request.setAttribute("listServices", sort);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("optionval", "sort");
                request.setAttribute("choiceval", choice);
                request.getRequestDispatcher("Web-doctor-list.jsp").forward(request, response);


            } else if (option.equalsIgnoreCase("search")) {
                String valuesearch = "";
                Integer gender = null;
                if(request.getParameter("gender_type")!=null){
                        gender = request.getParameter("gender_type").equalsIgnoreCase("male")?1:0;    
                }                       
                String[] arraySpec = request.getParameterValues("speciality");
                List<String> category = arraySpec == null ? new ArrayList<>() : Arrays.asList(arraySpec);
                ArrayList<Doctor> search = doctor.searchDoctor(gender, category, pageIndex, pageSize);
                int totalRecords = doctor.countSearchResults(gender, category);
                int totalPages = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
                for (String i : category) {
                    valuesearch += String.valueOf(i);
                    if (category.indexOf(i) < category.size() - 1) {
                        valuesearch += ",";
                    }
                }
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("listDoctor", search);
                request.setAttribute("value", gender);
                request.setAttribute("optionval", "search");
                request.setAttribute("listSpecSearch", valuesearch);
                request.getRequestDispatcher("Web-doctor-list.jsp").forward(request, response);

            }
        } catch (SQLException ex) {
            Logger.getLogger(WebDoctorListController.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
