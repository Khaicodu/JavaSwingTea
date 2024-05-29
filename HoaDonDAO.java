/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.TKDAO;

import QuanLyTEA.connect.DBConnection;
import QuanLyTEA.model.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class HoaDonDAO {
    public static ArrayList<HoaDon> getALL() throws Exception{
        ArrayList<HoaDon> lst = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try{
            Statement stmt = connection.createStatement();
            String SQL = "SELECT TOP (1000) * FROM [QLChidori].[dbo].[HoaDon]";
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setSoHD(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setTenDN(rs.getString(3));
                hd.setThoiGianTao(rs.getString(4));
                hd.setTrangThai(rs.getString(5));
                hd.setTongTien(rs.getFloat(6));
                lst.add(hd);
            }
            rs.close();
            stmt.close();
            connection.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
//public static ArrayList<HoaDon> getALL() throws Exception {
//        ArrayList<HoaDon> lst = new ArrayList<>();
//        Connection connection = DBConnection.getConnection();
//        try {
//            Statement stmt = connection.createStatement();
//            String SQL = "SELECT * FROM HoaDon";  // Adjust table name and columns as per your database
//            ResultSet rs = stmt.executeQuery(SQL);
//            while (rs.next()) {
//                HoaDon hd = new HoaDon();
//                hd.setSoHD(rs.getInt("soHD"));
//                hd.setTenKH(rs.getString("tenKH"));
//                hd.setTenDN(rs.getString("tenDN"));
//                hd.setThoiGianTao(rs.getString("thoiGianTao"));
//                hd.setTrangThai(rs.getString("trangThai"));
//                hd.setTongTien(rs.getFloat("tongTien"));
//                lst.add(hd);
//            }
//            rs.close();
//            stmt.close();
//            connection.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return lst;
//    }    
//    public static boolean isSoHoaDonExist(String soHD) throws Exception {
//        Connection connection = null;
//        boolean exists = false;
//
//        try {
//            connection = DBConnection.getConnection();
//            String query = "SELECT COUNT(*) FROM HoaDon WHERE soHD = ?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, soHD);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                exists = rs.getInt(1) > 0;
//            }
//
//            rs.close();
//            ps.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            DBConnection.closeConnection(connection);
//        }
//
//        return exists;
//    }
//    
//    public class HoaDonHelper {
//    public static String generateUniqueSoHD() throws Exception {
//        Random random = new Random();
//        String soHD;
//
//        do {
//            soHD = String.valueOf(10000 + random.nextInt(90000)); // Sinh ngẫu nhiên số từ 10000 đến 99999
//        } while (HoaDonDAO.isSoHoaDonExist(soHD)); // Kiểm tra số này đã tồn tại chưa
//
//        return soHD;
//    }
//    }
    
    public static boolean isSoHoaDonExist(String soHD) throws Exception {
        Connection connection = null;
        boolean exists = false;

        try {
            connection = DBConnection.getConnection();
            String query = "SELECT COUNT(*) FROM HoaDon WHERE soHD = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, soHD);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection);
        }

        return exists;
    }

    // Lấy mã hóa đơn mới nhất
    public static String getLatestSoHD() throws Exception {
        Connection connection = null;
        String latestSoHD = null;

        try {
            connection = DBConnection.getConnection();
            String query = "SELECT MAX(soHD) FROM HoaDon";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                latestSoHD = rs.getString(1);
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection);
        }

        return latestSoHD;
    }
    
    public boolean addHoaDon(HoaDon hoaDon) throws Exception {
        String sql = "INSERT INTO HoaDon (soHD, tenKH, tenDN, thoiGianTao, trangThai, tongTien) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, hoaDon.getSoHD());
            pstmt.setString(2, hoaDon.getTenKH());
            pstmt.setString(3, hoaDon.getTenDN());
            pstmt.setString(4, hoaDon.getThoiGianTao());
            pstmt.setString(5, hoaDon.getTrangThai());
            pstmt.setFloat(6, hoaDon.getTongTien());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    //tìm kiếm
    public static ArrayList<HoaDon> timKiemHoaDon(int soHD) throws Exception {
    ArrayList<HoaDon> resultList1 = new ArrayList<>();
    Connection connection = DBConnection.getConnection();
    String query = "SELECT * FROM dbo.[HoaDon] WHERE soHD = ?";

    PreparedStatement ps = connection.prepareStatement(query);
    ps.setInt(1, soHD);
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        HoaDon hd = new HoaDon();
        hd.setSoHD(rs.getInt("soHD"));
        hd.setTenKH(rs.getString("tenKH"));
        hd.setTenDN(rs.getString("tenDN"));
        hd.setThoiGianTao(rs.getString("thoiGianTao"));
        hd.setTrangThai(rs.getString("trangThai"));
        hd.setTongTien(rs.getFloat("tongTien"));
        resultList1.add(hd);
    }

    rs.close();
    ps.close();
    DBConnection.closeConnection(connection);
    
    return resultList1;
}
}
