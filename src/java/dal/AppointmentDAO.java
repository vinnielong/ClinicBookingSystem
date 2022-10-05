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
import model.Appointment;

/**
 *
 * @author Vinnie Long
 */
public class AppointmentDAO extends dal.DbContext {

    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection connection = null;
    DoctorDAO daoDoctor = new DoctorDAO();
    PatientDAO daoPatient = new PatientDAO();
    SettingDAO dalSetting = new SettingDAO();
    AccountDAO dalAccount = new AccountDAO();

    public ArrayList<Appointment> getallAppointments() throws SQLException, IOException {
        ArrayList<Appointment> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM appointment order by date desc;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Appointment(rs.getInt("id"), daoPatient.getPatientByID(rs.getInt("patient_id")),
                        daoDoctor.getDoctorByID(rs.getInt("doctor_id")), rs.getDate("date"), rs.getInt("status"), rs.getString("description"),
                        dalSetting.getSettingByID(rs.getInt("slot")), dalAccount.getAccountById(rs.getInt("staff_id"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Appointment> getAppoinmentsByDoctorID(int id) throws SQLException, IOException {
        ArrayList<Appointment> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM clinicbooking.appointment WHERE doctor_id = ? AND status = 1 OR status = 3 order by date desc;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Appointment(rs.getInt("id"), daoPatient.getPatientByID(rs.getInt("patient_id")),
                        daoDoctor.getDoctorByID(rs.getInt("doctor_id")), rs.getDate("date"), rs.getInt("status"), rs.getString("description"),
                        dalSetting.getSettingByID(rs.getInt("slot")), dalAccount.getAccountById(rs.getInt("staff_id"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Appointment getAppoinmentsByID(int id) throws SQLException, IOException {
        Appointment a = null;
        try {
            String sql = "SELECT * FROM appointment where id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                a = new Appointment(rs.getInt("id"), daoPatient.getPatientByID(rs.getInt("patient_id")),
                        daoDoctor.getDoctorByID(rs.getInt("doctor_id")), rs.getDate("date"), rs.getInt("status"), rs.getString("description"),
                        dalSetting.getSettingByID(rs.getInt("slot")), dalAccount.getAccountById(rs.getInt("staff_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return a;
    }

    public boolean addAppointment(int doctor_id, int patient_id, String date, String description, int slot) throws SQLException {
        boolean isAdded = false;
        try {
            String sql = "INSERT INTO appointment (patient_id, doctor_id, date, status, description, slot) VALUES (?, ?, ?, 2, ?, ?) ;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, patient_id);
            ps.setInt(2, doctor_id);
            ps.setDate(3, Date.valueOf(date));
            ps.setString(4, description);
            ps.setInt(5, slot);
            isAdded = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return isAdded;
    }

    public boolean editAppointment(int id, int status, int staff_id) throws SQLException {
        boolean isEdited = false;
        try {
            String sql = "update appointment set staff_id = ?, status = ? where id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, staff_id);
            ps.setInt(2, status);
            ps.setInt(3, id);
            isEdited = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return isEdited;
    }

    public ArrayList<Appointment> searchAppointments(String text, Integer doctor, Integer status) throws SQLException, IOException {
        ArrayList<Appointment> list = new ArrayList<>();
        try {
            String sql = "SELECT appointment.id, patient_id, doctor_id, date, appointment.status, appointment.description, slot, staff_id\n"
                    + "FROM appointment join patient on appointment.patient_id = patient.id join account on patient.account_id = account.id\n"
                    + "join doctor on appointment.doctor_id = doctor.id where account.fullname like ?";
            if (doctor != 0 && status != null) {
                sql += " AND appointment.doctor_id = " + doctor + " AND appointment.status = " + status;
            } else if (doctor == 0 && status != null) {
                sql += " AND appointment.status = " + status;
            } else if (status == null && doctor != 0) {
                sql += " AND appointment.doctor_id = " + doctor;
            }
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + text + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Appointment(rs.getInt("id"), daoPatient.getPatientByID(rs.getInt("patient_id")),
                        daoDoctor.getDoctorByID(rs.getInt("doctor_id")), rs.getDate("date"), rs.getInt("status"), rs.getString("description"),
                        dalSetting.getSettingByID(rs.getInt("slot")), dalAccount.getAccountById(rs.getInt("staff_id"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean changeStatus(int id, int status) throws SQLException {
        boolean isChanged = false;
        try {
            String sql = "update appointment set status = ? where id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, id);
            isChanged = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return isChanged;
    }

    public ArrayList<Appointment> getAppoinmentsByPatientID(int id) throws SQLException, IOException {
        ArrayList<Appointment> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM appointment where patient_id = ? order by date desc;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Appointment(rs.getInt("id"), daoPatient.getPatientByID(rs.getInt("patient_id")),
                        daoDoctor.getDoctorByID(rs.getInt("doctor_id")), rs.getDate("date"), rs.getInt("status"), rs.getString("description"),
                        dalSetting.getSettingByID(rs.getInt("slot")), dalAccount.getAccountById(rs.getInt("staff_id"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Appointment> getAppoinmentsByPatientDoctorID(int id, int doctor_id) throws SQLException, IOException {
        ArrayList<Appointment> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM appointment where patient_id = ? and doctor_id = ? order by date desc;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, doctor_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Appointment(rs.getInt("id"), daoPatient.getPatientByID(rs.getInt("patient_id")),
                        daoDoctor.getDoctorByID(rs.getInt("doctor_id")), rs.getDate("date"), rs.getInt("status"), rs.getString("description"),
                        dalSetting.getSettingByID(rs.getInt("slot")), dalAccount.getAccountById(rs.getInt("staff_id"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Appointment> getAppoinmentsByStaffID(int id) throws IOException, SQLException {
        ArrayList<Appointment> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM appointment WHERE staff_id = ? AND status != 3 order by date desc;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Appointment(rs.getInt("id"), daoPatient.getPatientByID(rs.getInt("patient_id")),
                        daoDoctor.getDoctorByID(rs.getInt("doctor_id")), rs.getDate("date"), rs.getInt("status"), rs.getString("description"),
                        dalSetting.getSettingByID(rs.getInt("slot")), dalAccount.getAccountById(rs.getInt("staff_id"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Object countAppointment() throws SQLException {
        int count = 0;
        try {
            String sql = "SELECT count(*) FROM appointment";
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

    public String encode64(Blob blob) throws IOException, SQLException {
        String img = "";
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

    public ArrayList<Appointment> getLastAppointments() throws SQLException, IOException {
        ArrayList<Appointment> list = new ArrayList<>();
        try {
            String sql = "select * from appointment order by date desc limit 7;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Appointment(rs.getInt("id"), daoPatient.getPatientByID(rs.getInt("patient_id")),
                        daoDoctor.getDoctorByID(rs.getInt("doctor_id")), rs.getDate("date"), rs.getInt("status"), rs.getString("description"),
                        dalSetting.getSettingByID(rs.getInt("slot")), dalAccount.getAccountById(rs.getInt("staff_id"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
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

}
