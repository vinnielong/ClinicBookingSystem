/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ServiceDAO;
import dal.SettingDAO;
import java.io.IOException;
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
import javax.servlet.http.HttpSession;
import model.Account;
import model.Service;
import model.Settings;

/**
 *
 * @author vinnielong
 */
public class WebServiceListController extends HttpServlet {

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
                SettingDAO setting = new SettingDAO();
                ServiceDAO service = new ServiceDAO();
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
                    int totalRecords = service.countServices(1);
                    int totalPages = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
                    ArrayList<Service> listService = service.getallServicesPaginate(pageIndex, pageSize);
                    request.setAttribute("listServices", listService);
                    request.setAttribute("totalPages", totalPages);
                    request.setAttribute("pageIndex", pageIndex);
                    request.getRequestDispatcher("Services.jsp").forward(request, response);

                } else if (option.equalsIgnoreCase("sort")) {
                    int choice = Integer.parseInt(request.getParameter("choice"));
                    int totalRecords = service.countServices(1);
                    int totalPages = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
                    ArrayList<Service> sort = service.sortServices(choice, pageIndex, pageSize);
                    request.setAttribute("listServices", sort);
                    request.setAttribute("totalPages", totalPages);
                    request.setAttribute("pageIndex", pageIndex);
                    request.setAttribute("optionval", "sort");
                    request.setAttribute("choiceval", choice);
                    request.getRequestDispatcher("Services.jsp").forward(request, response);

                } else if (option.equalsIgnoreCase("search")) {
                    String valuesearch = "";
                    String text = request.getParameter("txt");
                    String[] arraySpec = request.getParameterValues("speciality");
                    List<String> category = arraySpec == null ? new ArrayList<>() : Arrays.asList(arraySpec);
                    ArrayList<Service> search = service.searchServices(text, category, pageIndex, pageSize);
                    int totalRecords = service.countSearchResults(text, category);
                    int totalPages = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
                    for (String i : category) {
                        valuesearch += String.valueOf(i);
                        if (category.indexOf(i) < category.size() - 1) {
                            valuesearch += ",";
                        }
                    }
                    request.setAttribute("totalPages", totalPages);
                    request.setAttribute("pageIndex", pageIndex);
                    request.setAttribute("listServices", search);
                    request.setAttribute("value", text);
                    request.setAttribute("optionval", "search");
                    request.setAttribute("listSpecSearch", valuesearch);
                    request.getRequestDispatcher("Services.jsp").forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(WebServiceListController.class.getName()).log(Level.SEVERE, null, ex);
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
