/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.AccountDAO;
import dal.BlogDAO;
import dal.SettingDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Account;
import model.Blog;
import model.Settings;

/**
 *
 * @author Admin
 */
@MultipartConfig(maxFileSize = 104857600)
public class AddBlogController extends HttpServlet {

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
            out.println("<title>Servlet AddBlogController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddBlogController at " + request.getContextPath() + "</h1>");
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
            SettingDAO settingDAO = new SettingDAO();
            ArrayList<Settings> categories = settingDAO.getSettingsByType(2);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/admin/AddBlog.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddBlogController.class.getName()).log(Level.SEVERE, null, ex);
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
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String title = request.getParameter("title");
            int flag = (request.getParameter("flag") == null ? 0 : 1);
            int category = Integer.parseInt(request.getParameter("categorylist"));
            int status = (request.getParameter("status") == null ? 0 : 1);
            String file = request.getParameter("thumbnail");
            Part part = request.getPart("thumbnail");
            InputStream is = part.getInputStream();
            String brief = request.getParameter("brief");
            String content = request.getParameter("content");

            Date date = Date.valueOf(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("acc");
            SettingDAO settingDAO = new SettingDAO();
            AccountDAO accountDAO = new AccountDAO();
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setFeatured(flag);
            blog.setCategory(settingDAO.getSettingByID(category));
            blog.setStatus(status);
            blog.setAuthor(accountDAO.getAccountById(acc.getId()));
            blog.setBriefInfo(brief);
            blog.setContent(content);
            blog.setPostDate(date);

            BlogDAO blogDAO = new BlogDAO();
            if (file == null) {
                blogDAO.addBlog(blog, is);
            } else {
                blogDAO.addBlogNoImg(blog);
            }
            response.sendRedirect("bloglist");
        } catch (Exception ex) {
            Logger.getLogger(AddBlogController.class.getName()).log(Level.SEVERE, null, ex);
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
