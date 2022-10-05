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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Service;
import model.ServiceFeedback;

/**
 *
 * @author Vinnie Long
 */
public class ServiceDAO extends DbContext {

    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection connection = null;
    SettingDAO dao = new SettingDAO();
    PatientDAO patientDAO = new PatientDAO();

    public ArrayList<Service> getallServices() throws SQLException, IOException {
        ArrayList<Service> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM service;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Service(rs.getInt("id"), rs.getString("title"), dao.getSettingByID(rs.getInt("category")),
                        rs.getInt("price"), encode64(rs.getBlob("thumbnail")), rs.getInt("status"), rs.getString("description")));
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
        return list;
    }

    public ArrayList<Service> getallServicesPaginate(int pageIndex, int pageSize) throws IOException, SQLException {
        ArrayList<Service> list = new ArrayList<>();
        try {
            String sql = "WITH r AS (SELECT ROW_NUMBER() OVER (ORDER BY id) rownum, id, title, category, price, status, description, thumbnail FROM service WHERE status = 1) SELECT * FROM r WHERE r.rownum >= (? - 1) * ? + 1 AND r.rownum <= ? * ?";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pageIndex);
            ps.setInt(2, pageSize);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageIndex);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Service(rs.getInt("id"), rs.getString("title"), dao.getSettingByID(rs.getInt("category")),
                        rs.getInt("price"), encode64(rs.getBlob("thumbnail")), rs.getInt("status"), rs.getString("description")));
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
        return list;
    }

    public ArrayList<Service> sortServices(int choice, int pageIndex, int pageSize) throws SQLException, IOException {
        ArrayList<Service> list = new ArrayList<>();
        try {
            String sql = null;
            if (choice == 1) {
                sql = "WITH r AS (SELECT ROW_NUMBER() OVER (ORDER BY price DESC) rownum, id, title, category, price, status, description, thumbnail FROM service WHERE status = 1) SELECT * FROM r WHERE r.rownum >= (? - 1) * ? + 1 AND r.rownum <= ? * ?";
            } else if (choice == 2) {
                sql = "WITH r AS (SELECT ROW_NUMBER() OVER (ORDER BY price ASC) rownum, id, title, category, price, status, description, thumbnail FROM service WHERE status = 1) SELECT * FROM r WHERE r.rownum >= (? - 1) * ? + 1 AND r.rownum <= ? * ?";
            } else if (choice == 3) {
                sql = "WITH r AS (SELECT ROW_NUMBER() OVER (ORDER BY title ASC) rownum, id, title, category, price, status, description, thumbnail FROM service WHERE status = 1) SELECT * FROM r WHERE r.rownum >= (? - 1) * ? + 1 AND r.rownum <= ? * ?";
            }
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pageIndex);
            ps.setInt(2, pageSize);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageIndex);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Service(rs.getInt("id"), rs.getString("title"), dao.getSettingByID(rs.getInt("category")),
                        rs.getInt("price"), encode64(rs.getBlob("thumbnail")), rs.getInt("status"), rs.getString("description")));
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
        return list;
    }

    public int countServices(int i) throws SQLException {
        int count = 0;
        try {
            String sql = "SELECT count(*) FROM service";
            if (i == 1) {
                sql += " WHERE status = 1";
            }
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

    public ArrayList<Service> searchServices(String text, List<String> category, int pageIndex, int pageSize) throws SQLException, IOException {
        ArrayList<Service> list = new ArrayList<>();
        String sql = "WITH r AS (SELECT ROW_NUMBER() OVER (ORDER BY id DESC) rownum, id, title, category, service.status, price, service.description, thumbnail, settingname FROM service join setting on service.category = setting.idsetting WHERE (service.status = 1";
        if (!category.isEmpty() && !text.equals("")) {
            sql += " and title like '%" + text + "%')";
            sql += " and (";
            for (String c : category) {
                sql += " settingname = '" + c + "'";
                if (category.indexOf(c) < category.size() - 1) {
                    sql += " or";
                }
            }
            sql += ")";
        } else if (category.isEmpty() && !text.equals("")) {
            sql += " and title like '%" + text + "%')";
        } else if (!category.isEmpty() && text.equals("")) {
            sql += ") and (";
            for (String c : category) {
                sql += " settingname = '" + c + "'";
                if (category.indexOf(c) < category.size() - 1) {
                    sql += " or";
                }
            }
            sql += ")";
        }
        sql += ")SELECT * FROM r WHERE r.rownum >= (? - 1) * ? + 1 AND r.rownum <= ? * ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pageIndex);
            ps.setInt(2, pageSize);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageIndex);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Service(rs.getInt("id"), rs.getString("title"), dao.getSettingByID(rs.getInt("category")),
                        rs.getInt("price"), encode64(rs.getBlob("thumbnail")), rs.getInt("status"), rs.getString("description")));
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
        return list;
    }

    public int countSearchResults(String text, List<String> category) throws SQLException {
        int count = 0;
        try {
            String sql = "WITH r AS (SELECT ROW_NUMBER() OVER (ORDER BY id DESC) rownum, id, title, category, service.status, price, service.description, thumbnail, settingname FROM service join setting on service.category = setting.idsetting WHERE (service.status = 1";
            if (!category.isEmpty() && !text.equals("")) {
                sql += " and title like '%" + text + "%')";
                sql += " and (";
                for (String c : category) {
                    sql += " settingname = '" + c + "'";
                    if (category.indexOf(c) < category.size() - 1) {
                        sql += " or";
                    }
                }
                sql += ")";
            } else if (category.isEmpty() && !text.equals("")) {
                sql += " and title like '%" + text + "%')";
            } else if (!category.isEmpty() && text.equals("")) {
                sql += ") and (";
                for (String c : category) {
                    sql += " settingname = '" + c + "'";
                    if (category.indexOf(c) < category.size() - 1) {
                        sql += " or";
                    }
                }
                sql += ")";
            }
            sql += ") SELECT count(*) FROM r";
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

    public ArrayList<Service> searchServicesNoPaginate(String text, Integer category) throws SQLException {
        ArrayList<Service> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM service ";
            if (category != 0 && text != null) {
                sql += "where title like ? and category = ?";
                connection = getConnection();
                ps = connection.prepareStatement(sql);
                ps.setString(1, "%" + text + "%");
                ps.setInt(2, category);
            } else if (category == 0) {
                sql += "where title like ?";
                connection = getConnection();
                ps = connection.prepareStatement(sql);
                ps.setString(1, "%" + text + "%");
            } else {
                sql += "where category like ?";
                connection = getConnection();
                ps = connection.prepareStatement(sql);
                ps.setInt(1, category);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Service(rs.getInt("id"), rs.getString("title"), dao.getSettingByID(rs.getInt("category")),
                        rs.getInt("price"), rs.getBlob("thumbnail"), rs.getInt("status"), rs.getString("description")));
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
        return list;
    }

    public ArrayList<Service> getTop10BestService() throws IOException {
        ArrayList<Service> list = new ArrayList<>();
        try {
            String sql = "SELECT service.id, service.title,(Select avg(star) from clinicbooking.service_feedback where service.id = service_feedback.service_id)as rate,service.thumbnail\n"
                    + "FROM clinicbooking.service join clinicbooking.service_feedback on service.id = service_feedback.service_id\n"
                    + "group by service.id\n"
                    + "order by rate desc limit 10;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Service service = new Service();
                service.setID(rs.getInt("id"));
                service.setTitle(rs.getString("title"));
                service.setThumbnailString(encode64(rs.getBlob("thumbnail")));
                list.add(service);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean addNewService(Service s, InputStream is) throws SQLException {
        boolean isAdded = false;
        try {
            String sql = "INSERT INTO service (title, category, price, status, description, thumbnail) VALUES (?,?,?,?,?,?);";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, s.getTitle());
            ps.setInt(2, s.getCategory().getID());
            ps.setInt(3, s.getPrice());
            ps.setInt(4, s.getStatus());
            ps.setString(5, s.getDescription());
            ps.setBlob(6, is);
            isAdded = ps.executeUpdate() > 0;
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
        return isAdded;
    }

    public Service getServiceByID(int id) throws SQLException, IOException {
        Service a = null;
        try {
            String sql = "SELECT * FROM service WHERE id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                a = new Service(rs.getInt("id"), rs.getString("title"), dao.getSettingByID(rs.getInt("category")),
                        rs.getInt("price"), encode64(rs.getBlob("thumbnail")), rs.getInt("status"), rs.getString("description"));
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
        return a;

    }

    public boolean editService(Service s, int id, InputStream is) throws SQLException {
        boolean isEdited = false;
        try {
            String sql = "update service SET title = ?, category = ?, price = ?, status = ?, description = ?, thumbnail = ? WHERE id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, s.getTitle());
            ps.setInt(2, s.getCategory().getID());
            ps.setInt(3, s.getPrice());
            ps.setInt(4, s.getStatus());
            ps.setString(5, s.getDescription());
            ps.setBlob(6, is);
            ps.setInt(7, s.getID());
            isEdited = ps.executeUpdate() > 0;
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
        return isEdited;
    }

    public boolean editService2(Service s, int id) throws SQLException {
        boolean isEdited = false;
        try {
            String sql = "update service SET title = ?, category = ?, price = ?, status = ?, description = ? WHERE id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, s.getTitle());
            ps.setInt(2, s.getCategory().getID());
            ps.setInt(3, s.getPrice());
            ps.setInt(4, s.getStatus());
            ps.setString(5, s.getDescription());
            ps.setInt(6, s.getID());
            isEdited = ps.executeUpdate() > 0;
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
        return isEdited;
    }

    public void changeStatus(int status, int id) throws SQLException {
        try {
            String sql = "UPDATE service SET status = ? WHERE id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
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
    }

    public ArrayList<ServiceFeedback> getFeedbackByServiceID(int id) throws SQLException, IOException {
        ArrayList<ServiceFeedback> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM service_feedback WHERE service_id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ServiceFeedback(rs.getInt("id"), patientDAO.getPatientByID(rs.getInt("patient_id")), getServiceByID(rs.getInt("service_id")), rs.getInt("star"), rs.getString("comment")));
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
        return list;
    }

    public ArrayList<ServiceFeedback> filterFeedback(int id, int star) throws SQLException, IOException {
        ArrayList<ServiceFeedback> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM service_feedback WHERE service_id = ? AND star = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, star);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ServiceFeedback(rs.getInt("id"), patientDAO.getPatientByID(rs.getInt("patient_id")), getServiceByID(rs.getInt("service_id")), rs.getInt("star"), rs.getString("comment")));
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
        return list;
    }

    public boolean addComment(int sid, int patient, int star, String comment) throws SQLException {
        boolean isAdded = false;
        try {
            String sql = "INSERT INTO service_feedback (patient_id, service_id, star, comment) VALUES (?, ?, ?, ?);";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, patient);
            ps.setInt(2, sid);
            ps.setInt(3, star);
            ps.setString(4, comment);
            isAdded = ps.executeUpdate() > 0;
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
        return isAdded;
    }

    public boolean deleteComment(int id) throws SQLException {
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM service_feedback WHERE id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            isDeleted = ps.executeUpdate() > 0;
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
        return isDeleted;
    }

    public boolean editComment(int id, int star, String text) throws SQLException {
        boolean isEdited = false;
        try {
            String sql = "UPDATE service_feedback SET comment = ?, star = ? WHERE id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, text);
            ps.setInt(2, star);
            ps.setInt(3, id);
            isEdited = ps.executeUpdate() > 0;
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
        return isEdited;
    }

    public int averageRate(int id) throws SQLException {
        int rate = 0;
        try {
            String sql = "SELECT ROUND(AVG(star),0) FROM service_feedback where service_id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rate = rs.getInt(1);
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
        return rate;
    }

    public ArrayList<Service> getRelatedServices(int category) throws SQLException {
        ArrayList<Service> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM service where category = ?";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, category);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Service(rs.getInt("id"), rs.getString("title"), dao.getSettingByID(rs.getInt("category")),
                        rs.getInt("price"), rs.getBlob("thumbnail"), rs.getInt("status"), rs.getString("description")));
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

}
