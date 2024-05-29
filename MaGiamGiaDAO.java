/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.TKDAO;

import QuanLyTEA.connect.DBConnection;
import QuanLyTEA.model.MaGiamGia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class MaGiamGiaDAO {
    public static ArrayList<MaGiamGia> getALL() throws Exception{
        ArrayList<MaGiamGia> lst = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try{
            Statement stmt = connection.createStatement();
            String SQL = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[MaGiamGia]";
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()){
                MaGiamGia gg = new MaGiamGia();
                gg.setMaGG(rs.getString(1));
                gg.setTenGG(rs.getString(2));
                gg.setNgayBD(rs.getString(3));
                gg.setNgayKT(rs.getString(4));
                gg.setSoHD(rs.getInt(5));
                lst.add(gg);
            }
            rs.close();
            stmt.close();
            connection.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
}
