/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import utility.PasswordEncrypt;
import utility.SendMail;
import utility.Validate;

/**
 *
 * @author Admin
 */
public class RegistrationController extends HttpServlet {

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
        String role = request.getParameter("role");
        request.setAttribute("role", role);
        request.getRequestDispatcher("Register.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ResourceBundle resourceBundle = ResourceBundle.getBundle("utility/Message");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String fullname = request.getParameter("fullname1");
        String gender = request.getParameter("gender1");
        int gender1 = 0;
        if (gender.equalsIgnoreCase("male")) {
            gender1 = 1;
        } else if (gender.equalsIgnoreCase("female")) {
            gender1 = 0;
        } else {
            gender1 = 2;
        }
        String email = request.getParameter("email1");
        String phone = request.getParameter("phonenumber1");
        String username = request.getParameter("username1");
        String password = request.getParameter("psw1");
        String repass = request.getParameter("psw-repeat1");
        int role = Integer.parseInt(request.getParameter("role"));
        int status = 1;
        //System.out.println(fullname + "\t" + gender1 + "\t" + email + "\t" + phone + "\t" + username + "\t" + password);

        Validate validate = new Validate();
        AccountDAO accountDAO = new AccountDAO();
        try {
            if (validate.checkPhone(phone) == false) {
                // check validate phone number
                request.setAttribute("mess", resourceBundle.getString("invalid_phone"));
                request.setAttribute("fullname", fullname);
                request.setAttribute("gender", gender1);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("repass", repass);
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else if (accountDAO.checkAccountExist(username) == 1) {
                //check account existed
                request.setAttribute("mess", resourceBundle.getString("existed_account"));
                request.setAttribute("fullname", fullname);
                request.setAttribute("gender", gender1);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("repass", repass);
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else if (accountDAO.checkEmailExist(email) == 1) {
                //check mail existed
                request.setAttribute("mess", resourceBundle.getString("existed_email"));
                request.setAttribute("fullname", fullname);
                request.setAttribute("gender", gender1);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("repass", repass);
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else if (!password.equals(repass)) {
                //check pass equal confirmpass or not
                request.setAttribute("mess", resourceBundle.getString("pass_not_matched"));
                request.setAttribute("fullname", fullname);
                request.setAttribute("gender", gender1);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("repass", repass);
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else if (validate.checkPassword(password) == false) {
                // validate password
                request.setAttribute("mess", resourceBundle.getString("password_requirement"));
                request.setAttribute("fullname", fullname);
                request.setAttribute("gender", gender1);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("repass", repass);
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else if (validate.checkEmail(email) == false) {
                request.setAttribute("mess", resourceBundle.getString("invalid_email"));
                request.setAttribute("fullname", fullname);
                request.setAttribute("gender", gender1);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("repass", repass);
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else {
                //craete new user using all information
                SendMail sendMail = new SendMail();
                PasswordEncrypt encrypt = new PasswordEncrypt();
                Account account = new Account();
                account.setFullName(fullname);
                account.setGender(gender1);
                account.setEmail(email);
                account.setPhoneNumber(phone);
                account.setUsername(username);
                account.setPassword(encrypt.generateEncryptedPassword(password));
                account.setRole(role);
                account.setStatus(status);

                //get the 6-digit code
                String code = sendMail.getRandom();
                account.setCode(code);

                String message = "<h1> Clinic Booking </h1>"
                        + "Registered successfully.Please verify your account using this code: "
                        + "<h3> Verify Code: " + code + "</h3>";
                String subject = "CodersArea : Confirmation";
                String to = email;
                String from = "hauzter57@gmail.com";

                //call the send email method
                boolean test = sendMail.sendAttach(message, subject, to, from);

                //check if the email send successfully
                if (test) {
                    HttpSession session = request.getSession();
                    session.setAttribute("authcode", account);
                    response.sendRedirect("emailverify");
                } else {
                    response.getWriter().println("Failed to send verification email");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*try {
            AccountDAO accountDAO = new AccountDAO();
            accountDAO.signup(account);
            response.getWriter().println("Đăng kí thành công");

        } catch (Exception e) {
            e.printStackTrace();
        }*/
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
