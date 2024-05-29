/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.TKDAO;

import QuanLyTEA.connect.DBConnection;
import QuanLyTEA.model.KhachHang;
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
public class KhachHangDAO {
    public static ArrayList<KhachHang> getAll() throws Exception {
        ArrayList<KhachHang> lst = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            String SQL = "SELECT TOP (1000) * FROM [QLChidori].[dbo].[KhachHang]";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("maKH"));
                kh.setTenKH(rs.getString("tenKH"));
                kh.setNgaySinhKH(rs.getString("ngaySinhKH"));
                kh.setSoDTKH(rs.getString("soDTKH"));
                kh.setTinhLuy(rs.getInt("tinhLuy"));
                lst.add(kh);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Lỗi khi lấy danh sách khách hàng: " + ex.getMessage());
        }
        return lst;
    }

    public static ArrayList<KhachHang> timKiemKhachHangTheoSDT(String soDTKH) throws Exception {
        ArrayList<KhachHang> khachHangList = new ArrayList<>();
        String sql = "SELECT maKH, tenKH, ngaySinhKH, soDTKH, tinhLuy FROM KhachHang WHERE soDTKH = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, soDTKH);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("maKH"));
                kh.setTenKH(rs.getString("tenKH"));
                kh.setNgaySinhKH(rs.getString("ngaySinhKH"));
                kh.setSoDTKH(rs.getString("soDTKH"));
                kh.setTinhLuy(rs.getInt("tinhLuy"));
                khachHangList.add(kh);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Lỗi khi tìm kiếm khách hàng: " + ex.getMessage());
        }
        return khachHangList;
    }

    public static boolean capNhatDiemTichLuy(KhachHang khachHang) throws Exception {
        String sql = "UPDATE KhachHang SET tinhLuy = ? WHERE soDTKH = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, khachHang.getTinhLuy());
            pstmt.setString(2, khachHang.getSoDTKH());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Lỗi khi cập nhật điểm tích lũy: " + ex.getMessage());
        }
    }
    
    public static String getSoDTKHByTenKH(String tenKH) throws Exception {
        String soDTKH = null;
        Connection connection = DBConnection.getConnection();
        String query = "SELECT soDTKH FROM KhachHang WHERE tenKH = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, tenKH);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                soDTKH = rs.getString("soDTKH");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching phone number from database", e);
        } finally {
            connection.close();
        }
        
        return soDTKH;
    }
    
    public static boolean suaKhachHang(KhachHang kh) throws Exception {
        Connection connection = DBConnection.getConnection();
        String query = "UPDATE dbo.[KhachHang] SET maKH = ?, ngaySinhKH = ?, soDTKH = ?, tinhLuy = ? WHERE tenKH = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, kh.getMaKH());
        ps.setString(2, kh.getNgaySinhKH());
        ps.setString(3, kh.getSoDTKH());
        ps.setInt(4, kh.getTinhLuy());
        ps.setString(5, kh.getTenKH());
        int rowsAffected = ps.executeUpdate();
        ps.close();
        DBConnection.closeConnection(connection);
        return rowsAffected > 0;
    }
    
public boolean checkTrungTenKH(String tenKH) throws Exception {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT TOP 1 * FROM dbo.[KhachHang] WHERE tenKH = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, tenKH);
            ResultSet rs = ps.executeQuery();
            boolean exists = rs.next();
            rs.close();
            ps.close();
            DBConnection.closeConnection(connection);
            return exists;
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean themKhachHang(KhachHang kh) throws Exception {
        // Kiểm tra xem các trường thông tin đã được nhập đầy đủ hay không
        if (kh.getMaKH().isEmpty() || kh.getTenKH().isEmpty() || kh.getNgaySinhKH().isEmpty() || kh.getSoDTKH().isEmpty() || String.valueOf(kh.getTinhLuy()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
            return false;
        }

        // Kiểm tra trùng tên khách hàng
        KhachHangDAO khDAO = new KhachHangDAO();
        if (khDAO.checkTrungTenKH(kh.getTenKH())) {
            JOptionPane.showMessageDialog(null, "Tên khách hàng đã tồn tại (thêm biệt danh)");
            return false;
        }

        try {
            // Thực hiện thêm vào cơ sở dữ liệu
            Connection connection = DBConnection.getConnection();
            String query = "INSERT INTO dbo.[KhachHang](maKH, tenKH, ngaySinhKH, soDTKH, tinhLuy) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getTenKH());
            ps.setString(3, kh.getNgaySinhKH());
            ps.setString(4, kh.getSoDTKH());
            ps.setInt(5, kh.getTinhLuy());
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
    
    
    
    
    
}
