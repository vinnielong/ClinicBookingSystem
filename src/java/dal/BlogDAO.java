/*
 * Xuan Son
 * 
 * Mar 11, 2022
 *
 */
package dal;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;

/**
 *
 * @author Xuan Son
 */
public class BlogDAO extends DbContext {

    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection connection = null;
    SettingDAO settingDAO = new SettingDAO();
    ServiceDAO serviceDAO = new ServiceDAO();
    AccountDAO accountDAO = new AccountDAO();

    public ArrayList<Blog> getBlogList() throws SQLException {
        ArrayList<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM clinicbooking.blog;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("idblog"));
                blog.setThumbnailString(serviceDAO.encode64(rs.getBlob("thumbnail")));
                blog.setTitle(rs.getString("title"));
                blog.setAuthor(accountDAO.getAccountById(rs.getInt("author")));
                blog.setCategory(settingDAO.getSettingByID(rs.getInt("category")));
                blog.setFeatured(rs.getInt("featured"));
                blog.setStatus(rs.getInt("status"));
                blog.setContent(rs.getString("content"));
                blog.setBriefInfo(rs.getString("briefInfo"));
                blog.setPostDate(rs.getDate("postDate"));
                blogs.add(blog);
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
        return blogs;
    }
    
    public ArrayList<Blog> get4Blog1Page(int index) throws SQLException {
        ArrayList<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM clinicbooking.blog order by postDate desc limit ?,4;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (index * 4 - 4));
            rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("idblog"));
                blog.setThumbnailString(serviceDAO.encode64(rs.getBlob("thumbnail")));
                blog.setTitle(rs.getString("title"));
                blog.setAuthor(accountDAO.getAccountById(rs.getInt("author")));
                blog.setCategory(settingDAO.getSettingByID(rs.getInt("category")));
                blog.setFeatured(rs.getInt("featured"));
                blog.setStatus(rs.getInt("status"));
                blog.setContent(rs.getString("content"));
                blog.setBriefInfo(rs.getString("briefInfo"));
                blog.setPostDate(rs.getDate("postDate"));
                blogs.add(blog);
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
        return blogs;
    }
    
    public ArrayList<Blog> get4Blog1PageByCategory(int index,int categoryId) throws SQLException {
        ArrayList<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM clinicbooking.blog where category = ? limit ?,4;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ps.setInt(2, (index * 4 - 4));
            rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("idblog"));
                blog.setThumbnailString(serviceDAO.encode64(rs.getBlob("thumbnail")));
                blog.setTitle(rs.getString("title"));
                blog.setAuthor(accountDAO.getAccountById(rs.getInt("author")));
                blog.setCategory(settingDAO.getSettingByID(rs.getInt("category")));
                blog.setFeatured(rs.getInt("featured"));
                blog.setStatus(rs.getInt("status"));
                blog.setContent(rs.getString("content"));
                blog.setBriefInfo(rs.getString("briefInfo"));
                blog.setPostDate(rs.getDate("postDate"));
                blogs.add(blog);
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
        return blogs;
    }
    
    public ArrayList<Blog> top5FeaturedPost() throws SQLException {
        ArrayList<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM clinicbooking.blog where featured = 1 limit 5;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("idblog"));
                blog.setThumbnailString(serviceDAO.encode64(rs.getBlob("thumbnail")));
                blog.setTitle(rs.getString("title"));
                blog.setAuthor(accountDAO.getAccountById(rs.getInt("author")));
                blog.setCategory(settingDAO.getSettingByID(rs.getInt("category")));
                blog.setFeatured(rs.getInt("featured"));
                blog.setStatus(rs.getInt("status"));
                blog.setContent(rs.getString("content"));
                blog.setBriefInfo(rs.getString("briefInfo"));
                blog.setPostDate(rs.getDate("postDate"));
                blogs.add(blog);
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
        return blogs;
    }
    
    public ArrayList<Blog> search4Blog1Page(String search, int index) throws SQLException {
        ArrayList<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM clinicbooking.blog where title like '%"+search+"%' order by postDate desc limit ?,4;;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (index * 4 - 4));
            rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("idblog"));
                blog.setThumbnailString(serviceDAO.encode64(rs.getBlob("thumbnail")));
                blog.setTitle(rs.getString("title"));
                blog.setAuthor(accountDAO.getAccountById(rs.getInt("author")));
                blog.setCategory(settingDAO.getSettingByID(rs.getInt("category")));
                blog.setFeatured(rs.getInt("featured"));
                blog.setStatus(rs.getInt("status"));
                blog.setContent(rs.getString("content"));
                blog.setBriefInfo(rs.getString("briefInfo"));
                blog.setPostDate(rs.getDate("postDate"));
                blogs.add(blog);
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
        return blogs;
    }
    
    public int countBlog() throws SQLException {
        String sql = "SELECT count(*) FROM clinicbooking.blog;";
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
    
    public int countBlogByCategory(int category) throws SQLException {
        String sql = "SELECT count(*) FROM clinicbooking.blog where category = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, category);
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
    
    public int countSearchBlog(String search) throws SQLException {
        String sql = "SELECT count(*) FROM clinicbooking.blog where title like '%"+search+"%';";
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

    public ArrayList<Blog> searchBlog(String search, String authorlist, String status, String categorylist) throws SQLException {
        ArrayList<Blog> blogs = new ArrayList<>();
        try {
            String sql = "";
            if (search != null && authorlist == null && status == null && categorylist == null) {
                sql = "SELECT * FROM clinicbooking.blog where title like '%" + search + "%';";

            } else if (search != null && authorlist != null && status == null && categorylist == null) {
                int authorNum = Integer.parseInt(authorlist);
                sql = "SELECT * FROM clinicbooking.blog where title like '%" + search + "%' and author = " + authorNum + ";";
            } else if (search != null && authorlist == null && status != null && categorylist == null) {
                int statusNum = Integer.parseInt(status);
                sql = "SELECT * FROM clinicbooking.blog where title like '%" + search + "%' and status = " + statusNum + ";";
            } else if (search != null && authorlist == null && status == null && categorylist != null) {
                int categoryNum = Integer.parseInt(categorylist);
                sql = "SELECT * FROM clinicbooking.blog where title like '%" + search + "%' and category = " + categoryNum + ";";
            } else if (search == null && authorlist != null && status == null && categorylist == null) {
                int authorNum = Integer.parseInt(authorlist);
                sql = "SELECT * FROM clinicbooking.blog where author = " + authorNum + ";";
            } else if (search == null && authorlist != null && status != null && categorylist == null) {
                int authorNum = Integer.parseInt(authorlist);
                int statusNum = Integer.parseInt(status);
                sql = "SELECT * FROM clinicbooking.blog where author = " + authorNum + " and status = " + statusNum + ";";
            } else if (search == null && authorlist != null && status == null && categorylist != null) {
                int authorNum = Integer.parseInt(authorlist);
                int categoryNum = Integer.parseInt(categorylist);
                sql = "SELECT * FROM clinicbooking.blog where author = " + authorNum + " and category = " + categoryNum + ";";
            } else if (search == null && authorlist == null && status != null && categorylist == null) {
                int statusNum = Integer.parseInt(status);
                sql = "SELECT * FROM clinicbooking.blog where status = " + statusNum + ";";
            } else if (search == null && authorlist == null && status != null && categorylist != null) {
                int statusNum = Integer.parseInt(status);
                int categoryNum = Integer.parseInt(categorylist);
                sql = "SELECT * FROM clinicbooking.blog where status = " + statusNum + " and category = " + categoryNum + " ;";
            } else if (search == null && authorlist == null && status == null && categorylist != null) {
                int categoryNum = Integer.parseInt(categorylist);
                sql = "SELECT * FROM clinicbooking.blog where category = " + categoryNum + " ;";
            } else if (search != null && authorlist != null && status != null && categorylist == null) {
                int authorNum = Integer.parseInt(authorlist);
                int statusNum = Integer.parseInt(status);
                sql = "SELECT * FROM clinicbooking.blog where title like '%" + search + "%' and author = " + authorNum + " and status = " + statusNum + ";";
            } else if (search != null && authorlist != null && status == null && categorylist != null) {
                int authorNum = Integer.parseInt(authorlist);
                int categoryNum = Integer.parseInt(categorylist);
                sql = "SELECT * FROM clinicbooking.blog where title like '%" + search + "%' and author = " + authorNum + "and category = " + categoryNum + " ;";
            } else if (search != null && authorlist == null && status != null && categorylist != null) {
                int statusNum = Integer.parseInt(status);
                int categoryNum = Integer.parseInt(categorylist);
                sql = "SELECT * FROM clinicbooking.blog where title like '%" + search + "%' and status = " + statusNum + "and category = " + categoryNum + ";";
            } else if (search == null && authorlist != null && status != null && categorylist != null) {
                int authorNum = Integer.parseInt(authorlist);
                int statusNum = Integer.parseInt(status);
                int categoryNum = Integer.parseInt(categorylist);
                sql = "SELECT * FROM clinicbooking.blog where author = " + authorNum + " and status = " + statusNum + "and category = " + categoryNum + ";";
            } else {
                int authorNum = Integer.parseInt(authorlist);
                int statusNum = Integer.parseInt(status);
                int categoryNum = Integer.parseInt(categorylist);
                sql = "SELECT * FROM clinicbooking.blog where author = " + authorNum + " and status = " + statusNum + "and category = " + categoryNum + " and title like '%" + search + "%' ;";
            }
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("idblog"));
                blog.setThumbnailString(serviceDAO.encode64(rs.getBlob("thumbnail")));
                blog.setTitle(rs.getString("title"));
                blog.setAuthor(accountDAO.getAccountById(rs.getInt("author")));
                blog.setCategory(settingDAO.getSettingByID(rs.getInt("category")));
                blog.setFeatured(rs.getInt("featured"));
                blog.setStatus(rs.getInt("status"));
                blog.setContent(rs.getString("content"));
                blog.setBriefInfo(rs.getString("briefInfo"));
                blog.setPostDate(rs.getDate("postDate"));
                blogs.add(blog);
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
        return blogs;
    }
    
    public void addBlog(Blog bl, InputStream is) throws SQLException {
        try {
            String sql = "insert into clinicbooking.blog(thumbnail,title,author,category,featured,status,content,briefInfo,postDate)\n"
                    + "values(?,?,?,?,?,?,?,?,?);";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setBlob(1, is);
            ps.setString(2, bl.getTitle());
            ps.setInt(3, bl.getAuthor().getId());
            ps.setInt(4, bl.getCategory().getID());
            ps.setInt(5, bl.getFeatured());
            ps.setInt(6, bl.getStatus());
            ps.setString(7, bl.getContent());
            ps.setString(8, bl.getBriefInfo());
            ps.setDate(9, bl.getPostDate());
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
        }}
    
    public void addBlogNoImg(Blog bl) throws SQLException {
        try {
            String sql = "insert into clinicbooking.blog(title,author,category,featured,status,content,briefInfo,postDate)\n"
                    + "values(?,?,?,?,?,?,?,?);";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, bl.getTitle());
            ps.setInt(2, bl.getAuthor().getId());
            ps.setInt(3, bl.getCategory().getID());
            ps.setInt(4, bl.getFeatured());
            ps.setInt(5, bl.getStatus());
            ps.setString(6, bl.getContent());
            ps.setString(7, bl.getBriefInfo());
            ps.setDate(8, bl.getPostDate());
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
        }}

    public void changeFeaturedStatus(int id, int featured, int status) throws SQLException {
        try {
            String sql = "UPDATE clinicbooking.blog SET featured = ?, status = ? WHERE idblog = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, featured);
            ps.setInt(2, status);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public void changeStatus(int id, int status) throws SQLException {
        try {
            String sql = "UPDATE clinicbooking.blog SET status = ? WHERE idblog = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public void changeFeatured(int id, int featured) throws SQLException {
        try {
            String sql = "UPDATE clinicbooking.blog SET featured = ? WHERE idblog = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, featured);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void updateBlog(Blog bl, int id, InputStream is) throws SQLException {
        try {
            String sql = "update clinicbooking.blog set title=?, featured=?, category=?, status=?, briefInfo=?, content=?, thumbnail=? where idblog = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, bl.getTitle());
            ps.setInt(2, bl.getFeatured());
            ps.setInt(3, bl.getCategory().getID());
            ps.setInt(4,bl.getStatus());
            ps.setString(5, bl.getBriefInfo());
            ps.setString(6, bl.getContent());
            ps.setBlob(7, is);
            ps.setInt(8, id);
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
    
    public void updateBlogNoImg(Blog bl, int id) throws SQLException {
        try {
            String sql = "update clinicbooking.blog set title=?, featured=?, category=?, status=?, briefInfo=?, content=? where idblog = ?;";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, bl.getTitle());
            ps.setInt(2, bl.getFeatured());
            ps.setInt(3, bl.getCategory().getID());
            ps.setInt(4,bl.getStatus());
            ps.setString(5, bl.getBriefInfo());
            ps.setString(6, bl.getContent());
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

    public Blog getBlogById(int id) throws SQLException {
        String sql = "SELECT * FROM clinicbooking.blog where idblog = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("idblog"));
                blog.setThumbnailString(serviceDAO.encode64(rs.getBlob("thumbnail")));
                blog.setTitle(rs.getString("title"));
                blog.setAuthor(accountDAO.getAccountById(rs.getInt("author")));
                blog.setCategory(settingDAO.getSettingByID(rs.getInt("category")));
                blog.setFeatured(rs.getInt("featured"));
                blog.setStatus(rs.getInt("status"));
                blog.setContent(rs.getString("content"));
                blog.setBriefInfo(rs.getString("briefInfo"));
                blog.setPostDate(rs.getDate("postDate"));
                return blog;
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
        return null;
    }
}
