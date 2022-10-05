/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.SettingDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Settings;

/**
 *
 * @author Admin
 */
@MultipartConfig(maxFileSize = 104857600)
public class WebBlogListController extends HttpServlet {

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
            String indexPage = request.getParameter("index");
            String search = request.getParameter("txtSearch");
            String categoryId = request.getParameter("categoryId");
            BlogDAO blogDAO = new BlogDAO();
            SettingDAO settingDAO = new SettingDAO();
            //If pageIndex null, set it to default (begin is 1)
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            //Count the number of records with content similar text search
            int count = 0;
            if (search != null) {
                if (!search.equals("")) {
                    count = blogDAO.countSearchBlog(search);
                }
            } else if (categoryId != null) {
                count = blogDAO.countBlogByCategory(Integer.parseInt(categoryId));
            } else {
                count = blogDAO.countBlog();
            }

            //Number post each page
            int numberPost = 4;
            int endPage = count / numberPost;

            if (count % numberPost != 0) {
                endPage++;
            }
            if (search != null) {
                if (!search.equals("")) {
                    ArrayList<Blog> blogs = blogDAO.search4Blog1Page(search, index);
                    request.setAttribute("blogs", blogs);
                    request.setAttribute("search", search);
                }
            } else if (categoryId != null) {
                ArrayList<Blog> blogs = blogDAO.get4Blog1PageByCategory(index, Integer.parseInt(categoryId));
                request.setAttribute("blogs", blogs);
            } else {
                ArrayList<Blog> blogs = blogDAO.get4Blog1Page(index);
                request.setAttribute("blogs", blogs);
            }
            ArrayList<Blog> top5Featured = blogDAO.top5FeaturedPost();
            ArrayList<Settings> categories = settingDAO.getSettingsByType(2);
            request.setAttribute("categories", categories);
            request.setAttribute("top5Featured", top5Featured);
            request.setAttribute("endPage", endPage);
            request.setAttribute("index", index);
            request.setAttribute("count", count);
            request.getRequestDispatcher("WebBlogList.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(WebBlogListController.class.getName()).log(Level.SEVERE, null, ex);
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
