/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Settings;

/**
 *
 * @author Vinnie Long
 */
public class SettingDAO extends dal.DbContext {

    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection connection = null;

    public ArrayList<Settings> getallSettings() throws SQLException {
        ArrayList<Settings> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM setting;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Settings(rs.getInt("idsetting"), rs.getString("typename"), rs.getString("settingname"), rs.getInt("settingvalue"), rs.getInt("status"), rs.getString("description")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Settings> getSettingsByType(int type) throws SQLException {
        ArrayList<Settings> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM clinicbooking.setting WHERE typename = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, type);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Settings(rs.getInt("idsetting"), rs.getString("typename"), rs.getString("settingname"), rs.getInt("settingvalue"), rs.getInt("status"), rs.getString("description")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Settings> searchSettings(String text, Integer type, Integer status) throws SQLException {
        ArrayList<Settings> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM setting where (settingname like ? or settingvalue like ?)";
            if (type != 0 && status != null) {
                sql += "AND (typename = " + type + " AND status = " + status + ")";
            } else if (type == 0 && status != null) {
                sql += "AND (status = " + status + ")";
            } else if (status == null && type != 0) {
                sql += "AND (typename = " + type + ")";
            }
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + text + "%");
            ps.setString(2, "%" + text + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Settings(rs.getInt("idsetting"), rs.getString("typename"), rs.getString("settingname"), rs.getInt("settingvalue"), rs.getInt("status"), rs.getString("description")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public void addNewSetting(int type, String name, int value, String desc, int status) throws SQLException {
        try {
            String sql = "insert into clinicbooking.setting (typename,settingname,settingvalue,status,description) values(?,?,?,?,?);";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, type);
            ps.setString(2, name);
            ps.setInt(3, value);
            ps.setInt(4, status);
            ps.setString(5, desc);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Settings getSettingByID(int id) throws SQLException {
        Settings a = null;
        try {
            String sql = "SELECT * FROM setting where idsetting = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                a = new Settings(rs.getInt("idsetting"), rs.getString("typename"), rs.getString("settingname"), rs.getInt("settingvalue"), rs.getInt("status"), rs.getString("description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Settings getSettingByValueAndTypeName(int value, int typename) throws SQLException {
        Settings a = null;
        try {
            String sql = "SELECT * FROM setting where settingvalue = ? and typename = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, value);
            ps.setInt(2, typename);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a = new Settings(rs.getInt("idsetting"), rs.getString("typename"), rs.getString("settingname"), rs.getInt("settingvalue"), rs.getInt("status"), rs.getString("description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public void editSetting(int type, String name, int value, String desc, int status, int id) throws SQLException {
        try {
            String sql = "update clinicbooking.setting set typename =? ,settingname = ?,settingvalue = ? ,status = ?,description=?  where idsetting=?";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, type);
            ps.setString(2, name);
            ps.setInt(3, value);
            ps.setInt(4, status);
            ps.setString(5, desc);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public void changeStatus(int status, int id) throws SQLException {
        try {
            String sql = "UPDATE setting SET status = ? WHERE idsetting = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
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
