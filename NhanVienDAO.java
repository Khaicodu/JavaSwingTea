/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.TKDAO;

import QuanLyTEA.UI.GiaoDienTEA;
import QuanLyTEA.connect.DBConnection;
import QuanLyTEA.model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class NhanVienDAO  {
    public static ArrayList<NhanVien> getALL() throws Exception{
        ArrayList<NhanVien> lst = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try{
            Statement stmt = connection.createStatement();
            String SQL = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[nhanVien]";
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setHoVaTen(rs.getString(2));
                nv.setSoDT(rs.getString(3));
                nv.setNgaySinh(rs.getString(4));
                nv.setCccd(rs.getString(5));
                nv.setDiaChi(rs.getString(6));
                lst.add(nv);
            }
            rs.close();
            stmt.close();
            connection.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
    public boolean ThemNV(NhanVien nv) throws Exception{
        Connection connection = DBConnection.getConnection();
            String query = "INSERT INTO dbo.[NhanVien](maNV,hoVaTen,soDT,ngaySinh,cccd,diaChi)"
                    +"VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            {
            ps.setString(1,nv.getMaNV());
            ps.setString(2,nv.getHoVaTen());
            ps.setString(3,nv.getSoDT());
            ps.setString(4,nv.getNgaySinh());
            ps.setString(5,nv.getCccd());
            ps.setString(6,nv.getDiaChi());
            }
        return ps.executeUpdate()>0;
}
        public boolean checkTrungMaNV(NhanVien nv) throws Exception{
        try{
            Connection connection = DBConnection.getConnection();
            String query = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[nhanVien] WHERE maNV = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,nv.getMaNV());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return true;
            }
            DBConnection.closeConnection(connection);
        }catch(SQLException ex){
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
        public boolean checkTrungCCCD(NhanVien nv) throws Exception{
        try{
            Connection connection = DBConnection.getConnection();
            String query = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[nhanVien] WHERE cccd = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,nv.getCccd());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return true;
            }
            DBConnection.closeConnection(connection);
        }catch(SQLException ex){
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static boolean xoaNhanVien(String maNV) throws Exception {
        Connection connection = DBConnection.getConnection();
        String query = "DELETE FROM dbo.[nhanVien] WHERE maNV = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, maNV);
        int rowsAffected = ps.executeUpdate();
        ps.close();
        DBConnection.closeConnection(connection);
        return rowsAffected > 0;
    }
    
    public static boolean suaNhanVien(NhanVien nv) throws Exception {
        Connection connection = DBConnection.getConnection();
        String query = "UPDATE dbo.[NhanVien] SET hoVaTen = ?, soDT = ?, ngaySinh = ?, cccd = ?, diaChi = ? WHERE maNV = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, nv.getHoVaTen());
        ps.setString(2, nv.getSoDT());
        ps.setString(3, nv.getNgaySinh());
        ps.setString(4, nv.getCccd());
        ps.setString(5, nv.getDiaChi());
        ps.setString(6, nv.getMaNV());
        int rowsAffected = ps.executeUpdate();
        ps.close();
        DBConnection.closeConnection(connection);
        return rowsAffected > 0;
    }
    
    public static ArrayList<NhanVien> timKiemNhanVien(String maNV, String hoVaTen, String soDT, String ngaySinh, String cccd, String diaChi) throws Exception {
        ArrayList<NhanVien> resultList = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        StringBuilder query = new StringBuilder("SELECT * FROM dbo.[NhanVien] WHERE 1=1");

        if (!maNV.isEmpty() && !maNV.equals("Tìm kiếm bằng mã...")) {
            query.append(" AND maNV = ?");
        }
        if (!hoVaTen.isEmpty() && !hoVaTen.equals("Tìm kiếm bằng tên...")) {
            query.append(" AND hoVaTen LIKE ?");
        }
        if (!soDT.isEmpty() && !soDT.equals("Tìm kiếm bằng số điện thoại...")) {
            query.append(" AND soDT = ?");
        }
        if (!ngaySinh.isEmpty() && !ngaySinh.equals("Tìm kiếm bằng ngày sinh...")) {
            query.append(" AND ngaySinh = ?");
        }
        if (!cccd.isEmpty() && !cccd.equals("Tìm kiếm bằng căn cước...")) {
            query.append(" AND cccd = ?");
        }
        if (!diaChi.isEmpty() && !diaChi.equals("Tìm kiếm bằng địa chỉ...")) {
            query.append(" AND diaChi LIKE ?");
        }

        PreparedStatement ps = connection.prepareStatement(query.toString());
        int paramIndex = 1;

        if (!maNV.isEmpty() && !maNV.equals("Tìm kiếm bằng mã...")) {
            ps.setString(paramIndex++, maNV);
        }
        if (!hoVaTen.isEmpty() && !hoVaTen.equals("Tìm kiếm bằng tên...")) {
            ps.setString(paramIndex++, "%" + hoVaTen + "%");
        }
        if (!soDT.isEmpty() && !soDT.equals("Tìm kiếm bằng số điện thoại...")) {
            ps.setString(paramIndex++, soDT);
        }
        if (!ngaySinh.isEmpty() && !ngaySinh.equals("Tìm kiếm bằng ngày sinh...")) {
            ps.setString(paramIndex++, ngaySinh);
        }
        if (!cccd.isEmpty() && !cccd.equals("Tìm kiếm bằng căn cước...")) {
            ps.setString(paramIndex++, cccd);
        }
        if (!diaChi.isEmpty() && !diaChi.equals("Tìm kiếm bằng địa chỉ...")) {
            ps.setString(paramIndex++, "%" + diaChi + "%");
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            NhanVien nv = new NhanVien();
            nv.setMaNV(rs.getString("maNV"));
            nv.setHoVaTen(rs.getString("hoVaTen"));
            nv.setSoDT(rs.getString("soDT"));
            nv.setNgaySinh(rs.getString("ngaySinh"));
            nv.setCccd(rs.getString("cccd"));
            nv.setDiaChi(rs.getString("diaChi"));
            resultList.add(nv);
        }
        
        rs.close();
        ps.close();
        DBConnection.closeConnection(connection);
        
        return resultList;
    }
}
