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
import model.Doctor;
import model.Patient;
import model.DoctorFeedback;
import model.Service;

/**
 *
 * @author Vinnie Long
 */
public class DoctorDAO extends dal.DbContext {

    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection connection = null;
    SettingDAO dalSpec = new SettingDAO();
    PatientDAO patientDAO = new PatientDAO();

    public ArrayList<Doctor> getallDoctorPaginate(int pageIndex, int pageSize) throws SQLException, IOException {
        ArrayList<Doctor> list = new ArrayList<>();
        try {
            String sql = "WITH r AS (SELECT ROW_NUMBER() OVER (ORDER BY id) rownum, specialityId, description, doctor.id, fullname, gender, address, email, dob, phonenumber, doctor.role, status, avatar from doctor join account on doctor.accountid = account.id) \n"
                    + "SELECT * FROM r WHERE r.rownum >= (? - 1) * ? + 1 AND r.rownum <= ? * ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pageIndex);
            ps.setInt(2, pageSize);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageIndex);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Doctor(rs.getInt("id"), rs.getString("fullname"), rs.getString("role"), rs.getInt("gender"), rs.getDate("dob"),
                        rs.getString("phonenumber"), rs.getString("email"), rs.getInt("status"), dalSpec.getSettingByID(rs.getInt("specialityId")),
                        rs.getString("description"), rs.getBlob("avatar"), encode64(rs.getBlob("avatar"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public int averageRate(int id) throws SQLException {
        int rate = 0;
        try {
            String sql = "SELECT ROUND(AVG(star),0) FROM doctor_feedback where doctor_id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rate = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean addComment(int sid, int patient, int rate, String content) throws SQLException {
        boolean isAdded = false;
        try {
            String sql = "INSERT INTO doctor_feedback (patient_id, doctor_id, star, comment) VALUES (?, ?, ?, ?);";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, patient);
            ps.setInt(2, sid);
            ps.setInt(3, rate);
            ps.setString(4, content);
            isAdded = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            String sql = "DELETE FROM doctor_feedback WHERE id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            isDeleted = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean editComment(int id, int rate, String text) throws SQLException {
        boolean isEdited = false;
        try {
            String sql = "UPDATE doctor_feedback SET content = ?, rate = ? WHERE id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, text);
            ps.setInt(2, rate);
            ps.setInt(3, id);
            isEdited = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Doctor getDoctorByID(int id) throws SQLException, IOException {
        Doctor d = null;
        try {
            String sql = "select * from doctor join account on doctor.accountid = account.id where doctor.id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                d = new Doctor(rs.getInt("id"), rs.getString("fullname"), rs.getString("doctor.role"), rs.getInt("gender"), rs.getDate("dob"),
                        rs.getString("phonenumber"), rs.getString("email"), rs.getInt("status"), dalSpec.getSettingByID(rs.getInt("specialityId")),
                        rs.getString("description"), rs.getBlob("avatar"), encode64(rs.getBlob("avatar")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return d;
    }

    public Doctor getDoctorByAccountID(int id) throws SQLException, IOException {
        Doctor d = null;
        try {
            String sql = "select * from doctor join account on doctor.accountid = account.id where account.id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                d = new Doctor(rs.getInt("id"), rs.getString("fullname"), rs.getString("doctor.role"), rs.getInt("gender"), rs.getDate("dob"),
                        rs.getString("phonenumber"), rs.getString("email"), rs.getInt("status"), dalSpec.getSettingByID(rs.getInt("specialityId")),
                        rs.getString("description"), rs.getBlob("avatar"), encode64(rs.getBlob("avatar")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return d;
    }

    public ArrayList<Doctor> getTop10BestDoctor() throws IOException, SQLException {
        ArrayList<Doctor> list = new ArrayList<>();
        try {
            String sql = "select doctor.id, account.fullname, (select avg(star) from clinicbooking.doctor_feedback where doctor.id = doctor_feedback.doctor_id) as rate, doctor.role, account.address, account.avatar\n"
                    + "from clinicbooking.doctor join clinicbooking.account on doctor.accountid = account.id\n"
                    + "join clinicbooking.doctor_feedback on doctor.id = doctor_feedback.doctor_id\n"
                    + "group by doctor.id\n"
                    + "order by rate desc limit 10";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setDoctorName(rs.getString("fullname"));
                doctor.setRate(rs.getDouble("rate"));
                doctor.setRole(rs.getString("role"));
                doctor.setAddress(rs.getString("address"));
                doctor.setImageString(encode64(rs.getBlob("avatar")));
                list.add(doctor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Doctor> getAllDoctors() throws SQLException, IOException {
        ArrayList<Doctor> list = new ArrayList<>();
        try {
            String sql = "select * from doctor join account on doctor.accountid = account.id;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Doctor(rs.getInt("id"), rs.getString("fullname"), rs.getString("doctor.role"), rs.getInt("gender"), rs.getDate("dob"),
                        rs.getString("phonenumber"), rs.getString("email"), rs.getInt("status"), dalSpec.getSettingByID(rs.getInt("specialityId")),
                        rs.getString("description"), rs.getBlob("avatar"), encode64(rs.getBlob("avatar"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Doctor> searchDoctorAdminSite(String search, String speciality, String gender) throws SQLException {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try {
            String sql = "";
            if (search != null && speciality == null && gender == null) {
                sql = "SELECT * FROM clinicbooking.doctor join clinicbooking.account on doctor.accountid = account.id where fullname like '%" + search + "%' ;";
            } else if (search == null && speciality != null && gender == null) {
                int specNum = Integer.parseInt(speciality);
                sql = "SELECT * FROM clinicbooking.doctor join clinicbooking.account on doctor.accountid = account.id where specialityId = " + specNum + " ;";
            } else if (search == null && speciality == null && gender != null) {
                int genderNum = Integer.parseInt(gender);
                sql = "SELECT * FROM clinicbooking.doctor join clinicbooking.account on doctor.accountid = account.id where gender = " + genderNum + " ;";
            } else if (search != null && speciality != null && gender == null) {
                int specNum = Integer.parseInt(speciality);
                sql = "SELECT * FROM clinicbooking.doctor join clinicbooking.account on doctor.accountid = account.id where fullname like '%" + search + "%' and specialityId = " + specNum + " ;";
            } else if (search != null && speciality == null && gender != null) {
                int genderNum = Integer.parseInt(gender);
                sql = "SELECT * FROM clinicbooking.doctor join clinicbooking.account on doctor.accountid = account.id where fullname like '%" + search + "%' and gender = " + genderNum + " ;";
            } else if (search == null && speciality != null && gender != null) {
                int specNum = Integer.parseInt(speciality);
                int genderNum = Integer.parseInt(gender);
                sql = "SELECT * FROM clinicbooking.doctor join clinicbooking.account on doctor.accountid = account.id where specialityId = " + specNum + " and gender = " + genderNum + " ;";
            } else {
                int specNum = Integer.parseInt(speciality);
                int genderNum = Integer.parseInt(gender);
                sql = "SELECT * FROM clinicbooking.doctor join clinicbooking.account on doctor.accountid = account.id where fullname like '%" + search + "%' and specialityId = " + specNum + " and gender = " + genderNum + " ;";
            }
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setDoctorName(rs.getString("fullname"));
                d.setGender(rs.getInt("gender"));
                d.setSpeciality(dalSpec.getSettingByID(rs.getInt("specialityId")));
                d.setStatus(rs.getInt("status"));
                doctors.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return doctors;
    }

    public ArrayList<Doctor> getDoctorsByName(String name, int index) throws SQLException {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try {
            String sql = "SELECT d.id, d.doctorname, d.gender, d.specialityid , s.specialityname, d.status\n"
                    + "FROM clinicbooking.doctor d\n"
                    + "inner join speciality s\n"
                    + "on d.specialityid = s.id\n"
                    + "where doctorname like '%" + name + "%'"
                    + "limit ?,3;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (index * 3 - 3));
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setDoctorName(rs.getString("doctorname"));
                doctor.setGender(rs.getInt("gender"));
                doctor.setSpeciality(dalSpec.getSettingByID(rs.getInt("specialityID")));
                doctor.setStatus(rs.getInt("status"));
                doctors.add(doctor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return doctors;
    }

    public ArrayList<Doctor> get3Doctor1Page(int index) throws SQLException {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try {
            String sql = "select d.id, d.doctorname, d.gender, d.specialityid , s.specialityname, d.status  \n"
                    + "from doctor d \n"
                    + "inner join speciality s \n"
                    + "on d.specialityid = s.id\n"
                    + "limit ?,3;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (index * 3 - 3));
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setDoctorName(rs.getString("doctorname"));
                doctor.setGender(rs.getInt("gender"));
                doctor.setSpeciality(dalSpec.getSettingByID(rs.getInt("specialityID")));
                doctor.setStatus(rs.getInt("status"));
                doctors.add(doctor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return doctors;
    }

    public void updateDoctor(Doctor d, int id) throws SQLException {
        try {
            String sql = "update clinicbooking.doctor join account on doctor.accountid = account.id\n"
                    + "set account.fullname=?, gender=?, specialityId=?, status=?, doctor.role=?, dob=?, account.phonenumber=?, description=?\n"
                    + "where doctor.id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, d.getDoctorName());
            ps.setInt(2, d.getGender());
            ps.setInt(3, d.getSpeciality().getID());
            ps.setInt(4, d.getStatus());
            ps.setString(5, d.getRole());
            ps.setDate(6, d.getDob());
            ps.setString(7, d.getPhone());
            ps.setString(8, d.getDescription());
            ps.setInt(9, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public int countDoctorByName(String name) throws SQLException {
        try {
            String sql = "SELECT count(*) \n"
                    + "FROM clinicbooking.doctor\n"
                    + "Where doctorname like '%" + name + "%';";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public int countDoctor() throws SQLException {
        String sql = "SELECT count(*) FROM clinicbooking.doctor;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Patient> getAllPatientByDoctorId(int id) throws SQLException, IOException {
        ArrayList<Patient> list = new ArrayList<>();
        try {
            String sql = "SELECT patient.id, fullname, gender, email, dob, phonenumber, avatar, MaxDate FROM account JOIN patient ON account.id = patient.account_id\n"
                    + "INNER JOIN (SELECT patient_id, max(date) AS MaxDate FROM appointment WHERE doctor_id = ? GROUP BY patient_id) a ON patient.id = a.patient_id";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Patient(rs.getInt("id"), rs.getString("fullname"), rs.getInt("gender"), rs.getDate("dob"), rs.getDate("MaxDate"), rs.getString("phonenumber"), rs.getString("email"), encode64(rs.getBlob("avatar"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public int countPatient(String doctorName) throws SQLException {
        String sql = "SELECT count(*) FROM (SELECT * FROM patient WHERE id IN ( SELECT patient_id FROM appointment WHERE doctor_id IN ( SELECT id FROM doctor WHERE doctorname = ? ))) AS COUNT";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, doctorName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, e);
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

    public ArrayList<DoctorFeedback> getFeedbackByDoctorID(int id) throws SQLException, IOException {
        ArrayList<DoctorFeedback> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM doctor_feedback WHERE doctor_id = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DoctorFeedback(rs.getInt("id"), patientDAO.getPatientByID(rs.getInt("patient_id")), getDoctorByID(rs.getInt("doctor_id")), rs.getInt("star"), rs.getDate("date"), rs.getString("comment")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<DoctorFeedback> filterFeedback(int id, int star) throws SQLException, IOException {
        ArrayList<DoctorFeedback> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM doctor_feedback WHERE doctor_id = ? AND star = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, star);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DoctorFeedback(rs.getInt("id"), patientDAO.getPatientByID(rs.getInt("patient_id")), getDoctorByID(id), rs.getInt("star"), rs.getDate("date"), rs.getString("comment")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    /*public ArrayList<Service> sortDoctor(int choice, int pageIndex, int pageSize) throws SQLException, IOException {
        ArrayList<Service> list = new ArrayList<>();
        try {
            String sql = null;
            if (choice == 1) {
                sql = "WITH r AS (SELECT ROW_NUMBER() OVER (ORDER BY price DESC) rownum,specialityId, description, doctor.id, fullname, gender, address, email, dob, phonenumber, doctor.role, status, avatar from doctor join account on doctor.accountid = account.id) SELECT * FROM r WHERE r.rownum >= (? - 1) * ? + 1 AND r.rownum <= ? * ?";
            } else if (choice == 2) {
                sql = "WITH r AS (SELECT ROW_NUMBER() OVER (ORDER BY price ASC) rownum, specialityId, description, doctor.id, fullname, gender, address, email, dob, phonenumber, doctor.role, status, avatar from doctor join account on doctor.accountid = account.id) SELECT * FROM r WHERE r.rownum >= (? - 1) * ? + 1 AND r.rownum <= ? * ?";
            } else if (choice == 3) {
                sql = "WITH r AS (SELECT ROW_NUMBER() OVER (ORDER BY title ASC) rownum, specialityId, description, doctor.id, fullname, gender, address, email, dob, phonenumber, doctor.role, status, avatar from doctor join account on doctor.accountid = account.id) SELECT * FROM r WHERE r.rownum >= (? - 1) * ? + 1 AND r.rownum <= ? * ?";
            } else if (choice == 4) {
                sql = "WITH r AS (SELECT ROW_NUMBER() OVER (ORDER BY title ASC) rownum, specialityId, description, doctor.id, fullname, gender, address, email, dob, phonenumber, doctor.role, status, avatar from doctor join account on doctor.accountid = account.id) SELECT * FROM r WHERE r.rownum >= (? - 1) * ? + 1 AND r.rownum <= ? * ?";
                
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
    }*/
    public ArrayList<Doctor> searchDoctor(Integer gender, List<String> category, int pageIndex, int pageSize) throws SQLException, IOException {
        ArrayList<Doctor> list = new ArrayList<>();
        String sql = "WITH r AS (SELECT ROW_NUMBER() OVER (ORDER BY id DESC) rownum, specialityId, doctor.description, \n"
                + "doctor.id, fullname, gender, address, email, dob, phonenumber, doctor.role, account.status, avatar \n"
                + "from doctor join account on doctor.accountid = account.id \n"
                + "                join setting on idsetting=specialityId WHERE (1=1 ";
        if (!category.isEmpty() && gender != null) {
            sql += "and gender = " + gender + ")";
            sql += "and (";
            for (String c : category) {
                sql += " settingname = '" + c + "'";
                if (category.indexOf(c) < category.size() - 1) {
                    sql += " or";
                }
            }
            sql += ")";
        } else if (category.isEmpty() && gender != null) {
            sql += " and gender = " + gender + ")";
        } else if (!category.isEmpty() && gender == null) {
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
            System.out.println(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Doctor(rs.getInt("id"), rs.getString("fullname"), rs.getString("role"), rs.getInt("gender"), rs.getDate("dob"),
                        rs.getString("phonenumber"), rs.getString("email"), rs.getInt("status"), dalSpec.getSettingByID(rs.getInt("specialityId")),
                        rs.getString("description"), rs.getBlob("avatar"), encode64(rs.getBlob("avatar"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public int countSearchResults(Integer gender, List<String> category) throws SQLException {
        int count = 0;
        String sql = "WITH r AS (SELECT ROW_NUMBER() OVER (ORDER BY id DESC) rownum, specialityId, doctor.description, \n"
                + "doctor.id, fullname, gender, address, email, dob, phonenumber, doctor.role, account.status, avatar \n"
                + "from doctor join account on doctor.accountid = account.id \n"
                + "                join setting on idsetting=specialityId WHERE (1=1 ";
        if (!category.isEmpty() && gender != null) {
            sql += "and gender = " + gender + ")";
            sql += "and (";
            for (String c : category) {
                sql += " settingname = '" + c + "'";
                if (category.indexOf(c) < category.size() - 1) {
                    sql += " or";
                }
            }
            sql += ")";
        } else if (category.isEmpty() && gender != null) {
            sql += " and gender = " + gender + ")";
        } else if (!category.isEmpty() && gender == null) {
            sql += ") and (";
            for (String c : category) {
                sql += " settingname = '" + c + "'";
                if (category.indexOf(c) < category.size() - 1) {
                    sql += " or";
                }
            }
            sql += ")";
        }
        sql += ")SELECT count(*) FROM r";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

}
