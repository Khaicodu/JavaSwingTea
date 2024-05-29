/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.TKDAO;

import QuanLyTEA.connect.DBConnection;
import QuanLyTEA.model.ChiTietHD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ChiTietDAO {
    public static ArrayList<ChiTietHD> getALL() throws Exception{
        ArrayList<ChiTietHD> lst = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try{
            Statement stmt = connection.createStatement();
            String SQL = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[ChiTietHD]";
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()){
                ChiTietHD ct = new ChiTietHD();
                ct.setSoHD(rs.getInt(1));
                ct.setMaTU(rs.getString(2));
                ct.setTenTU(rs.getString(3));
                ct.setGiaTU(rs.getInt(4));
                ct.setSoLuong(rs.getInt(5));
                ct.setSizeTU(rs.getString(6));
                ct.setTenKH(rs.getString(7));
                ct.setSoDTKH(rs.getString(8));
                ct.setThoiGianTao(rs.getString(9));
                ct.setTrangThai(rs.getString(10));
                ct.setLoaiThanhToan(rs.getString(11));
                ct.setTongTien(rs.getFloat(12));
                ct.setGhiChu(rs.getString(13));
                lst.add(ct);
            }
            rs.close();
            stmt.close();
            connection.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
public static ArrayList<ChiTietHD> getChiTietHDBySoHD(int soHD) throws Exception {
        ArrayList<ChiTietHD> chiTietList = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM ChiTietHD WHERE soHD = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, soHD);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ChiTietHD chiTiet = new ChiTietHD();
                chiTiet.setSoHD(rs.getInt("soHD"));
                chiTiet.setMaTU(rs.getString("maTU"));
                chiTiet.setTenTU(rs.getString("tenTU"));
                chiTiet.setGiaTU(rs.getInt("giaTU"));
                chiTiet.setSoLuong(rs.getInt("soLuong"));
                chiTiet.setSizeTU(rs.getString("sizeTU"));
                // add more fields if needed
                chiTietList.add(chiTiet);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Lỗi", ex);
        }
        return chiTietList;
    }

    public static String getGhiChuBySoHD(int soHD) throws Exception {
        String ghiChu = null;
        Connection connection = DBConnection.getConnection();
        
        try {
            String query = "SELECT ghiChu FROM ChiTietHD WHERE soHD = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, soHD);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ghiChu = rs.getString("ghiChu");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Lỗi", ex);
        }
        return ghiChu;
    }
    
    public static String getLoaiTTBySoHD(int soHD) throws Exception {
        String loaiThanhToan = null;
        Connection connection = DBConnection.getConnection();
        
        try {
            String query = "SELECT loaiThanhToan FROM ChiTietHD WHERE soHD = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, soHD);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                loaiThanhToan = rs.getString("loaiThanhToan");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Lỗi", ex);
        }
        return loaiThanhToan;
    }
    
    public boolean addCToaDon(ChiTietHD chiTiet) throws Exception {
        String sql = "INSERT INTO ChiTietHD (soHD, maTU, tenTU, giaTU, soLuong, sizeTU, tenKH, soDTKH, thoiGianTao, trangThai,loaiThanhToan,tongTien,ghiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, chiTiet.getSoHD());
            pstmt.setString(2, chiTiet.getMaTU());
            pstmt.setString(3, chiTiet.getTenTU());
            pstmt.setInt(4, chiTiet.getGiaTU());
            pstmt.setInt(5, chiTiet.getSoLuong());
            pstmt.setString(6, chiTiet.getSizeTU());
            pstmt.setString(7, chiTiet.getTenKH());
            pstmt.setString(8, chiTiet.getSoDTKH());
            pstmt.setString(9, chiTiet.getThoiGianTao());
            pstmt.setString(10, chiTiet.getTrangThai());
            pstmt.setString(11, chiTiet.getLoaiThanhToan());
            pstmt.setFloat(12, chiTiet.getTongTien());
            pstmt.setString(13,chiTiet.getGhiChu());
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
}
