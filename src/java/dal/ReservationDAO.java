/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Reservation;

/**
 *
 * @author Admin
 */
public class ReservationDAO extends DbContext {

    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection connection = null;
    AccountDAO dalAccount = new AccountDAO();
    PatientDAO dalPatient = new PatientDAO();
    ServiceDAO dalService = new ServiceDAO();
    SettingDAO dalSetting = new SettingDAO();

    public ArrayList<Reservation> getAllReservation() throws SQLException, IOException {
        ArrayList<Reservation> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reservation;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt("id"), dalPatient.getPatientByID(rs.getInt("patient_id")),
                        dalService.getServiceByID(rs.getInt("service_id")), rs.getDate("date"), rs.getInt("status"),
                        dalAccount.getAccountById(rs.getInt("staff_id")), rs.getString("description"), dalSetting.getSettingByID(rs.getInt("slot"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Reservation> getAllReservationById(String servicename) throws SQLException, IOException {
        ArrayList<Reservation> list = new ArrayList<>();
        try {
            String sql = "select * from reservation inner join service on reservation.service_id=service.id where service.title like ? ;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + servicename + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt("id"), dalPatient.getPatientByID(rs.getInt("patient_id")),
                        dalService.getServiceByID(rs.getInt("service_id")), rs.getDate("date"), rs.getInt("status"),
                        dalAccount.getAccountById(rs.getInt("staff_id")), rs.getString("description"), dalSetting.getSettingByID(rs.getInt("slot"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Reservation getReservationById(int id) throws SQLException, IOException {
        Reservation r = null;
        try {
            String sql = "SELECT * FROM reservation where id=?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                r = new Reservation(rs.getInt("id"), dalPatient.getPatientByID(rs.getInt("patient_id")),
                        dalService.getServiceByID(rs.getInt("service_id")), rs.getDate("date"), rs.getInt("status"),
                        dalAccount.getAccountById(rs.getInt("staff_id")), rs.getString("description"), dalSetting.getSettingByID(rs.getInt("slot")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return r;
    }

    public ArrayList<Reservation> getReservationByPatientId(int patient_id) throws SQLException, IOException {
        ArrayList<Reservation> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reservation where patient_id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, patient_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt("id"), dalPatient.getPatientByID(rs.getInt("patient_id")),
                        dalService.getServiceByID(rs.getInt("service_id")), rs.getDate("date"), rs.getInt("status"),
                        dalAccount.getAccountById(rs.getInt("staff_id")), rs.getString("description"), dalSetting.getSettingByID(rs.getInt("slot"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Reservation> getReservationByStaffId(int staff_id) throws SQLException, IOException {
        ArrayList<Reservation> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reservation where staff_id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, staff_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt("id"), dalPatient.getPatientByID(rs.getInt("patient_id")),
                        dalService.getServiceByID(rs.getInt("service_id")), rs.getDate("date"), rs.getInt("status"),
                        dalAccount.getAccountById(rs.getInt("staff_id")), rs.getString("description"), dalSetting.getSettingByID(rs.getInt("slot"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean editReservation(int id, int staff_id) throws SQLException {
        boolean isEdited = false;
        try {
            String sql = "update reservation set staff_id = ? where id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, staff_id);
            ps.setInt(2, id);
            isEdited = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean addReservation(int patient_id, int service_id, String description, String date, int slot) throws SQLException {
        boolean e = false;
        try {
            String sql = "insert into reservation (patient_id,service_id,date,description,status,slot) values (?,?,?,?,2,?)";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, patient_id);
            ps.setInt(2, service_id);
            ps.setDate(3, Date.valueOf(date));
            ps.setString(4, description);
            ps.setInt(5, slot);
            e = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return e;
    }

    public ArrayList<Reservation> getLastReservation() throws IOException, SQLException {
        ArrayList<Reservation> list = new ArrayList<>();
        try {
            String sql = "select * from reservation order by date desc limit 7;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt("id"), dalPatient.getPatientByID(rs.getInt("patient_id")),
                        dalService.getServiceByID(rs.getInt("service_id")), rs.getDate("date"), rs.getInt("status"),
                        dalAccount.getAccountById(rs.getInt("staff_id")), rs.getString("description"), dalSetting.getSettingByID(rs.getInt("slot"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
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
