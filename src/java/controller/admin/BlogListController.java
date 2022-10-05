/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.AccountDAO;
import dal.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import dal.SettingDAO;
import model.Settings;
import model.Account;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Admin
 */
@MultipartConfig(maxFileSize = 104857600)
public class BlogListController extends HttpServlet {

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
            out.println("<title>Servlet BlogListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogListController at " + request.getContextPath() + "</h1>");
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
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String categorylist = request.getParameter("categorylist");
            String authorlist = request.getParameter("authorlist");
            String status = request.getParameter("status");
            String search = request.getParameter("search");
            if (!StringUtils.isEmpty(categorylist) && categorylist.equalsIgnoreCase("0")) {
                categorylist = null;
            }
            if (!StringUtils.isEmpty(authorlist) && authorlist.equalsIgnoreCase("0")) {
                authorlist = null;
            }
            if (search == "") {
                search = null;
            }
            BlogDAO blogDAO = new BlogDAO();
            SettingDAO settingDAO = new SettingDAO();
            AccountDAO accountDAO = new AccountDAO();
            if (categorylist == null && authorlist == null && status == null && search == null) {
                ArrayList<Blog> blogs = blogDAO.getBlogList();
                request.setAttribute("blogs", blogs);
            } else {
                ArrayList<Blog> blogs = blogDAO.searchBlog(search, authorlist, status, categorylist);
                request.setAttribute("blogs", blogs);
                request.setAttribute("search", search);
            }
            if (categorylist != null) {
                int category1 = Integer.parseInt(categorylist);
                request.setAttribute("category1", category1);
            }
            if (status != null) {
                int status1 = Integer.parseInt(status);
                request.setAttribute("status1", status1);
            }
            if (authorlist != null) {
                int author1 = Integer.parseInt(authorlist);
                request.setAttribute("author1", author1);
            }
            ArrayList<Settings> categories = settingDAO.getSettingsByType(2);
            ArrayList<Account> authors = accountDAO.getAccountBy2Role(1, 5);
            request.setAttribute("authors", authors);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/admin/BlogList.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BlogListController.class.getName()).log(Level.SEVERE, null, ex);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("blogid"));
            int status = (request.getParameter("blogstatus") == null ? 0 : 1);
            int featured1 = (request.getParameter("blogfeatured") == null ? 0 : 1);

            BlogDAO blogDAO = new BlogDAO();
            blogDAO.changeFeaturedStatus(id, featured1, status);

            response.sendRedirect("bloglist");
        } catch (Exception e) {
            e.printStackTrace();
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
