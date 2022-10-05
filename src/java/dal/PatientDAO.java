/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Patient;

/**
 *
 * @author Vinnie Long
 */
public class PatientDAO extends dal.DbContext {

    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection connection = null;

    public Patient getPatientByID(int id) throws SQLException, IOException {
        Patient p = null;
        try {
            String sql = "select * from patient join account on patient.account_id = account.id where patient.id = ?";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                p = new Patient(rs.getInt("id"), rs.getString("fullname"), rs.getInt("gender"), rs.getDate("dob"), rs.getString("phonenumber"),
                        rs.getString("email"), rs.getInt("status"), rs.getString("address"), rs.getBlob("avatar"), encode64(rs.getBlob("avatar")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return p;
    }

    public Patient getPatientByAccountID(int id) throws SQLException, IOException {
        Patient p = null;
        try {
            String sql = "select * from patient join account on patient.account_id = account.id where account.id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                p = new Patient(rs.getInt("id"), rs.getString("fullname"), rs.getInt("gender"), rs.getDate("dob"), rs.getString("phonenumber"),
                        rs.getString("email"), rs.getInt("status"), rs.getString("address"), rs.getBlob("avatar"), encode64(rs.getBlob("avatar")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return p;
    }

    public ArrayList<Patient> getAllPatient() throws SQLException, IOException {
        ArrayList<Patient> list = new ArrayList<>();
        try {
            String sql = "select * from patient join account on patient.account_id = account.id;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Patient(rs.getInt("id"), rs.getString("fullname"), rs.getInt("gender"), rs.getDate("dob"),
                        rs.getString("phonenumber"), rs.getString("email"), rs.getInt("status"), rs.getString("address"), rs.getBlob("avatar"), encode64(rs.getBlob("avatar"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public ArrayList<Patient> getPatientByName(String name) throws SQLException, IOException {
        ArrayList<Patient> list = new ArrayList<>();
        try {
            String sql = "select * from patient join account on patient.account_id = account.id where fullname like '%" + name + "%';";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Patient(rs.getInt("id"), rs.getString("fullname"), rs.getInt("gender"), rs.getDate("dob"),
                        rs.getString("phonenumber"), rs.getString("email"), rs.getInt("status"), rs.getString("address"), rs.getBlob("avatar"), encode64(rs.getBlob("avatar"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public String encode64(Blob blob) throws IOException, SQLException {
        String img = null;
        if (blob != null) {
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = outputStream.toByteArray();
            img = Base64.getEncoder().encodeToString(imageBytes);
            inputStream.close();
            outputStream.close();
        } else {
            img = "";
        }
        return img;
    }
    public boolean updatePatientByPatientID(int id,String name,int gender,Date dob,String phone,String address) throws SQLException, IOException {
        boolean p =false;
        try {
            String sql = "update account join patient on patient.account_id = account.id set fullname=?,gender=?,dob=?,phonenumber=?,address=? where patient.id=?  ";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, gender);
            ps.setDate(3, dob);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setInt(6, id);
            p= ps.executeUpdate() > 0;    
        } catch (SQLException ex) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return p;
    }

    public Object countPatient() throws SQLException {
        int count = 0;
        try {
            String sql = "SELECT count(*) FROM patient";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return count;
    }

    public void changeStatus(int status, int id) throws SQLException {
        try {
            String sql = "UPDATE account join patient on patient.account_id = account.id  SET status = ? WHERE patient.id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

}
