/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.TKDAO;

import QuanLyTEA.UI.GiaoDienTEA;
import QuanLyTEA.connect.DBConnection;
import QuanLyTEA.model.ThucUong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ThucUongDAO {
    public static ArrayList<ThucUong> getALL() throws Exception{
        ArrayList<ThucUong> lst = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try{
            Statement stmt = connection.createStatement();
            String SQL = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[ThucUong]";
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()){
                ThucUong tu = new ThucUong();
                tu.setMaTU(rs.getString(1));
                tu.setTenTU(rs.getString(2));
                tu.setSizeTU(rs.getString(3));
                tu.setGiaTU(rs.getString(4));
                tu.setMaLoai(rs.getString(5));
                tu.setTrangThai(rs.getString(6));
                lst.add(tu);
            }
            rs.close();
            stmt.close();
            connection.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
public boolean checkTrungMaTU(String maTU) throws Exception {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT TOP 1 * FROM dbo.[ThucUong] WHERE maTU = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, maTU);
            ResultSet rs = ps.executeQuery();
            boolean exists = rs.next();
            rs.close();
            ps.close();
            DBConnection.closeConnection(connection);
            return exists;
        } catch (SQLException ex) {
            Logger.getLogger(ThucUongDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean checkTrungSizeTU(String sizeTU) throws Exception {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT TOP 1 * FROM dbo.[ThucUong] WHERE sizeTU = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, sizeTU);
            ResultSet rs = ps.executeQuery();
            boolean exists = rs.next();
            rs.close();
            ps.close();
            DBConnection.closeConnection(connection);
            return exists;
        } catch (SQLException ex) {
            Logger.getLogger(ThucUongDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean themThucUong(String maTU, String tenTU, String giaTU, String sizeTU, String maLoai, String trangThai) throws Exception {
        // Kiểm tra xem các trường thông tin đã được nhập đầy đủ hay không
        if (maTU.isEmpty() || tenTU.isEmpty() || giaTU.isEmpty() || sizeTU.isEmpty() || maLoai.isEmpty() || trangThai.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
            return false;
        }

        // Kiểm tra độ dài của mã thức uống
        if (maTU.length() != 4) {
            JOptionPane.showMessageDialog(null, "Mã thức uống phải có 4 kí tự");
            return false;
        }

        // Kiểm tra kiểu ký tự của mã thức uống
        boolean b1 = Character.isLetter(maTU.charAt(0));
        boolean b2 = Character.isLetter(maTU.charAt(1));
        boolean b3 = Character.isDigit(maTU.charAt(2));
        boolean b4 = Character.isDigit(maTU.charAt(3));
        if (!(b1 && b2 && b3 && b4)) {
            JOptionPane.showMessageDialog(null, "Mã thức uống không đúng định dạng. Mẫu TR01");
            return false;
        }

        // Kiểm tra trùng mã thức uống
        ThucUongDAO tuDAO = new ThucUongDAO();
        if (tuDAO.checkTrungMaTU(maTU)) {
            JOptionPane.showMessageDialog(null, "Mã thức uống đã tồn tại");
            return false;
        }

        // Kiểm tra trùng size thức uống
        if (tuDAO.checkTrungSizeTU(sizeTU)) {
            JOptionPane.showMessageDialog(null, "Size thức uống đã tồn tại");
            return false;
        }

        try {
            // Thực hiện thêm thức uống vào cơ sở dữ liệu
            Connection connection = DBConnection.getConnection();
            String query = "INSERT INTO dbo.[ThucUong](maTU, tenTU, giaTU, sizeTU, maLoai, trangThai) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, maTU);
            ps.setString(2, tenTU);
            ps.setString(3, giaTU);
            ps.setString(4, sizeTU);
            ps.setString(5, maLoai);
            ps.setString(6, trangThai);
            ps.executeUpdate();
            ps.close();
            DBConnection.closeConnection(connection);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Thêm không thành công: " + ex.getMessage());
            return false;
        }
    }
    
    public static boolean capNhatThucUong(String maTU, String tenTU, String giaTU, String sizeTU, String maLoai, String trangThai) throws Exception {
    if (maTU.isEmpty() || tenTU.isEmpty() || giaTU.isEmpty() || sizeTU.isEmpty() || maLoai.isEmpty() || trangThai.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        return false;
    }

    // Kiểm tra độ dài của mã thức uống
    if (maTU.length() != 4) {
        JOptionPane.showMessageDialog(null, "Mã thức uống phải có 4 kí tự");
        return false;
    }

    // Kiểm tra kiểu ký tự của mã thức uống
    boolean b1 = Character.isLetter(maTU.charAt(0));
    boolean b2 = Character.isLetter(maTU.charAt(1));
    boolean b3 = Character.isDigit(maTU.charAt(2));
    boolean b4 = Character.isDigit(maTU.charAt(3));
    if (!(b1 && b2 && b3 && b4)) {
        JOptionPane.showMessageDialog(null, "Mã thức uống không đúng định dạng. Mẫu TR01");
        return false;
    }


//    ThucUongDAO tuDAO = new ThucUongDAO();
//
//        // Kiểm tra trùng size thức uống
//        if (tuDAO.checkTrungSizeTU(sizeTU)) {
//            JOptionPane.showMessageDialog(null, "Size thức uống đã tồn tại");
//            return false;
//        }

    try {
        // Thực hiện cập nhật thông tin thức uống vào cơ sở dữ liệu
        Connection connection = DBConnection.getConnection();
        String query = "UPDATE dbo.[ThucUong] SET tenTU = ?, giaTU = ?, sizeTU = ?, maLoai = ?, trangThai = ? WHERE maTU = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, tenTU);
        ps.setString(2, giaTU);
        ps.setString(3, sizeTU);
        ps.setString(4, maLoai);
        ps.setString(5, trangThai);
        ps.setString(6, maTU);
        ps.executeUpdate();
        DBConnection.closeConnection(connection);
        return true;
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Sửa không thành công: " + ex.getMessage());
        return false;
    }
}

public static ArrayList<ThucUong> timKiemThucUongTheoTenTU(String tenTU) throws Exception {
    ArrayList<ThucUong> resultList = new ArrayList<>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        connection = DBConnection.getConnection();
        String query = "SELECT * FROM dbo.[ThucUong] WHERE tenTU LIKE ?";
        ps = connection.prepareStatement(query);
        ps.setString(1, "%" + tenTU + "%");
        rs = ps.executeQuery();

        while (rs.next()) {
            ThucUong tu = new ThucUong();
            tu.setMaTU(rs.getString("maTU"));
            tu.setTenTU(rs.getString("tenTU"));
            tu.setGiaTU(rs.getString("giaTU"));
            tu.setSizeTU(rs.getString("sizeTU"));
            tu.setMaLoai(rs.getString("maLoai"));
            tu.setTrangThai(rs.getString("trangThai"));
            resultList.add(tu);
        }
    } finally {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    return resultList;
}
public static ArrayList<ThucUong> timKiemThucUongTheoTrangThai(String trangThai) throws Exception {
    ArrayList<ThucUong> resultList = new ArrayList<>();
    Connection connection = DBConnection.getConnection();
    String query = "SELECT * FROM dbo.[ThucUong] WHERE trangThai = ?";

    PreparedStatement ps = connection.prepareStatement(query);
    ps.setString(1, trangThai);
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        ThucUong tu = new ThucUong();
        tu.setMaTU(rs.getString("maTU"));
        tu.setTenTU(rs.getString("tenTU"));
        tu.setGiaTU(rs.getString("giaTU"));
        tu.setSizeTU(rs.getString("sizeTU"));
        tu.setMaLoai(rs.getString("maLoai"));
        tu.setTrangThai(rs.getString("trangThai"));
        resultList.add(tu);
    }

    rs.close();
    ps.close();
    DBConnection.closeConnection(connection);
    
    return resultList;
}

public static ArrayList<ThucUong> timKiemThucUongTheoMaLoai(String maLoai) throws Exception {
    ArrayList<ThucUong> resultList = new ArrayList<>();
    Connection connection = DBConnection.getConnection();
    String query = "SELECT * FROM dbo.[ThucUong] WHERE maLoai = ?";

    PreparedStatement ps = connection.prepareStatement(query);
    ps.setString(1, maLoai);
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        ThucUong tu = new ThucUong();
        tu.setMaTU(rs.getString("maTU"));
        tu.setTenTU(rs.getString("tenTU"));
        tu.setGiaTU(rs.getString("giaTU"));
        tu.setSizeTU(rs.getString("sizeTU"));
        tu.setMaLoai(rs.getString("maLoai"));
        tu.setTrangThai(rs.getString("trangThai"));
        resultList.add(tu);
    }

    rs.close();
    ps.close();
    DBConnection.closeConnection(connection);
    
    return resultList;
}


public static ArrayList<ThucUong> layTatCaThucUong() throws Exception {
    ArrayList<ThucUong> thucUongList = new ArrayList<>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        connection = DBConnection.getConnection();
        String query = "SELECT * FROM ThucUong"; // Query để lấy tất cả thức uống
        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();

        // Lặp qua các dòng kết quả từ truy vấn và thêm vào danh sách thức uống
        while (rs.next()) {
            String maTU = rs.getString("maTU");
            String tenTU = rs.getString("tenTU");
            String giaTU = rs.getString("giaTU");
            String sizeTU = rs.getString("sizeTU");
            String maLoai = rs.getString("maLoai");
            String trangThai = rs.getString("trangThai");

            ThucUong tu = new ThucUong(maTU, tenTU, giaTU, sizeTU, maLoai, trangThai);
            thucUongList.add(tu);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        throw new Exception("Lỗi khi lấy danh sách thức uống: " + ex.getMessage());
    } finally {
        // Đóng kết nối, statement và result set
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    return thucUongList;
}
 
public static ThucUong getThucUongFromDB(String maTU) throws Exception {
        ThucUong thucUong = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();
            String query = "SELECT * FROM dbo.[ThucUong] WHERE maTU = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, maTU);
            rs = ps.executeQuery();

            if (rs.next()) {
                String tenTU = rs.getString("tenTU");
                String giaTU = rs.getString("giaTU");
                String sizeTU = rs.getString("sizeTU");
                String maLoai = rs.getString("maLoai");
                String trangThai = rs.getString("trangThai");
                thucUong = new ThucUong(maTU, tenTU, giaTU, sizeTU, maLoai, trangThai);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Lỗi khi lấy thông tin từ cơ sở dữ liệu: " + ex.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) DBConnection.closeConnection(connection);
        }

        return thucUong;
    }

}
