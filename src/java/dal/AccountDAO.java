/*
 * Xuan Son
 * 
 * Jan 17, 2022
 *
 */
package dal;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Xuan Son
 */
public class AccountDAO extends DbContext {

    PreparedStatement ps = null;
    ResultSet rs = null;
    DbContext dbc = new DbContext();
    Connection connection = null;

    public void signup(Account ac) throws SQLException {
        try {
            String sql = "insert into account(fullname,gender,email,phonenumber,username,password,role,status)\n"
                    + "values(?,?,?,?,?,?,?,?)";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, ac.getFullName());
            ps.setInt(2, ac.getGender());
            ps.setString(3, ac.getEmail());
            ps.setString(4, ac.getPhoneNumber());
            ps.setString(5, ac.getUsername());
            ps.setString(6, ac.getPassword());
            ps.setInt(7, ac.getRole());
            ps.setInt(8, ac.getStatus());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Account> searchAccount(String search, String role, String status) throws SQLException {
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "";
        try {
            if (search == null && status == null && role != null) {
                int roleNum = Integer.parseInt(role);
                sql = "SELECT * FROM clinicbooking.account where role = " + roleNum + ";";
            } else if (search == null && status != null && role != null) {
                int roleNum = Integer.parseInt(role);
                int statusNum = Integer.parseInt(status);
                sql = "SELECT * FROM clinicbooking.account where role = " + roleNum + " and status = " + statusNum + ";";
            } else if (search != null && status == null && role != null) {
                int roleNum = Integer.parseInt(role);
                sql = "select * from (SELECT * FROM clinicbooking.account\n"
                        + "where fullname like '%" + search + "%' or email like '%" + search + "%' or phonenumber like '%" + search + "%' or username like '%" + search + "%') c where c.role = " + roleNum + ";";
            } else if (search == null && status != null && role == null) {
                int statusNum = Integer.parseInt(status);
                sql = "SELECT * FROM clinicbooking.account where status = " + statusNum + ";";
            } else if (search != null && status == null && role == null) {
                sql = "SELECT * FROM clinicbooking.account\n"
                        + "where fullname like '%" + search + "%' or email like '%" + search + "%' or phonenumber like '%" + search + "%' or username like '%" + search + "%';";
            } else if (search != null && status != null && role == null) {
                int statusNum = Integer.parseInt(status);
                sql = "select * from (SELECT * FROM clinicbooking.account\n"
                        + "where fullname like '%" + search + "%' or email like '%" + search + "%' or phonenumber like '%" + search + "%' or username like '%" + search + "%') c where c.status = " + statusNum + ";";
            } else {
                int roleNum = Integer.parseInt(role);
                int statusNum = Integer.parseInt(status);
                sql = "select * from (SELECT * FROM clinicbooking.account\n"
                        + "where fullname like '%" + search + "%' or email like '%" + search + "%' or phonenumber like '%" + search + "%' or username like '%" + search + "%') c where c.role = " + roleNum + " and c.status = " + statusNum + " ;";
            }
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                accounts.add(new Account(rs.getInt("id"), rs.getString("fullname"), rs.getInt("gender"), rs.getString("email"), rs.getString("phonenumber"), rs.getString("username"), rs.getString("password"), rs.getInt("role"), rs.getInt("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return accounts;
    }

    public void addAccount(Account ac, InputStream is) throws SQLException {
        try {
            String sql = "insert into clinicbooking.account(fullname,gender,email,phonenumber,username,role,status,avatar)\n"
                    + "values(?,?,?,?,?,?,?,?);";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, ac.getFullName());
            ps.setInt(2, ac.getGender());
            ps.setString(3, ac.getEmail());
            ps.setString(4, ac.getPhoneNumber());
            ps.setString(5, ac.getUsername());
            ps.setInt(6, ac.getRole());
            ps.setInt(7, ac.getStatus());
            ps.setBlob(8, is);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public void addAccountNoImg(Account ac) throws SQLException {
        try {
            String sql = "insert into clinicbooking.account(fullname,gender,email,phonenumber,username,role,status)\n"
                    + "values(?,?,?,?,?,?,?);";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, ac.getFullName());
            ps.setInt(2, ac.getGender());
            ps.setString(3, ac.getEmail());
            ps.setString(4, ac.getPhoneNumber());
            ps.setString(5, ac.getUsername());
            ps.setInt(6, ac.getRole());
            ps.setInt(7, ac.getStatus());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public void updateAccount(Account ac, int id, InputStream is) throws SQLException {
        try {
            String sql = "update clinicbooking.account\n"
                    + "set fullname=?, gender=?, phonenumber=?, status=?, role=?, avatar=?\n"
                    + "where id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, ac.getFullName());
            ps.setInt(2, ac.getGender());
            ps.setString(3, ac.getPhoneNumber());
            ps.setInt(4, ac.getStatus());
            ps.setInt(5, ac.getRole());
            ps.setBlob(6, is);
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public void updateAccountNoImg(Account ac, int id) throws SQLException {
        try {
            String sql = "update clinicbooking.account\n"
                    + "set fullname=?, gender=?, phonenumber=?, status=?, role=?\n"
                    + "where id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, ac.getFullName());
            ps.setInt(2, ac.getGender());
            ps.setString(3, ac.getPhoneNumber());
            ps.setInt(4, ac.getStatus());
            ps.setInt(5, ac.getRole());
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Account> getAllAccount() throws SQLException {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT * FROM clinicbooking.account;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                accounts.add(new Account(rs.getInt("id"), rs.getString("fullname"), rs.getInt("gender"), rs.getString("email"), rs.getString("phonenumber"), rs.getString("username"), rs.getString("password"), rs.getInt("role"), rs.getInt("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return accounts;
    }

    public void changeStatusRole(int status, int id, int role) throws SQLException {
        try {
            String sql = "UPDATE clinicbooking.account SET role = ?, status = ? WHERE id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, role);
            ps.setInt(2, status);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Account getAccountById(int id) throws SQLException {
        Account a = null;
        try {
            String sql = "SELECT * FROM clinicbooking.account where id = ?;";
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a = new Account(rs.getInt("id"), rs.getString("fullname"), rs.getInt("gender"), rs.getString("email"), rs.getString("phonenumber"), rs.getString("username"), rs.getString("password"), rs.getInt("role"), rs.getInt("status"), rs.getBlob("avatar"), null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Account getAccountByEmail(String email) throws SQLException {
        Account a = null;
        try {
            String sql = "SELECT * FROM clinicbooking.account where email = ?;";
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a = new Account(rs.getInt("id"), rs.getString("fullname"), rs.getInt("gender"), rs.getString("email"), rs.getString("phonenumber"), rs.getString("username"), rs.getString("password"), rs.getInt("role"), rs.getInt("status"), rs.getBlob("avatar"), null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public int checkAccountExist(String user) throws SQLException {
        try {
            String sql = "select * from account\n"
                    + "where username = ?\n";
            connection = getConnection();
            ps = connection.prepareStatement(sql);//truyen cau lenh len sql
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return 0;
    }

    public int checkEmailExist(String email) throws SQLException {
        try {
            String sql = "select * from account\n"
                    + "where email = ?\n";
            connection = getConnection();
            ps = connection.prepareStatement(sql);//truyen cau lenh len sql
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return 0;
    }

    public Account getAccountByUserAndPass(String user, String pass) throws SQLException {
        Account a = null;
        try {
            String sql = "select * from account where username=? and password=?";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                a = new Account(rs.getInt("id"), rs.getString("fullname"), rs.getInt("gender"), rs.getString("email"), rs.getString("phonenumber"), rs.getString("username"),
                        rs.getString("password"), rs.getInt("role"), rs.getInt("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public int changePassword(String password, int account_id) throws SQLException {
        String sql = "update clinicbooking.account set password=? where id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, password);
            ps.setInt(2, account_id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return 0;
    }

    public boolean updateProfile(String name, int gender, String dob, String phone, String description, int role, int accountID) throws SQLException {
        boolean p = false;
        String sql = "";
        try {
            if (role == 2) {
                sql = "UPDATE doctor JOIN account ON doctor.accountid = account.id set fullname = ?, gender = ?, dob = ?, phonenumber = ?, description=? where account.id = ?;";
                connection = getConnection();
                ps = connection.prepareStatement(sql);
                ps.setString(1, name);
                ps.setInt(2, gender);
                ps.setDate(3, Date.valueOf(dob));
                ps.setString(4, phone);
                ps.setString(5, description);
                ps.setInt(6, accountID);
            } else if (role == 3) {
                sql = "UPDATE account SET fullname = ?, gender = ?, dob = ?, phonenumber = ? WHERE id = ?;";
                connection = getConnection();
                ps = connection.prepareStatement(sql);
                ps.setString(1, name);
                ps.setInt(2, gender);
                ps.setDate(3, Date.valueOf(dob));
                ps.setString(4, phone);
                ps.setInt(5, accountID);
            }
            p = ps.executeUpdate() > 0;
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

    public boolean updateAvatar(InputStream avatar, int accountID) throws SQLException {
        boolean p = false;
        try {
            String sql = "UPDATE account SET avatar = ? WHERE id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setBlob(1, avatar);
            ps.setInt(2, accountID);
            p = ps.executeUpdate() > 0;
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

    public ArrayList<Account> getAccountByRole(int role_id) throws SQLException {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM clinicbooking.account where role = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, role_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt("id"), rs.getString("fullname"), rs.getInt("gender"), rs.getString("email"), rs.getString("phonenumber"), rs.getString("username"), rs.getString("password"), rs.getInt("role"), rs.getInt("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Account> getAccountBy2Role(int role1, int role2) throws SQLException {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM clinicbooking.account where role = ?\n"
                    + "union distinct\n"
                    + "SELECT * FROM clinicbooking.account where role = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, role1);
            ps.setInt(2, role2);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt("id"), rs.getString("fullname"), rs.getInt("gender"), rs.getString("email"), rs.getString("phonenumber"), rs.getString("username"), rs.getString("password"), rs.getInt("role"), rs.getInt("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
