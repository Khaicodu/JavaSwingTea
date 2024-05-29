/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.TKDAO;

import QuanLyTEA.connect.DBConnection;
import QuanLyTEA.model.LoaiThucUong;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class LoaiThucUongDAO {
    public static ArrayList<LoaiThucUong> getALL() throws Exception{
        ArrayList<LoaiThucUong> lst = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try{
            Statement stmt = connection.createStatement();
            String SQL = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[LoaiThucUong]";
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()){
                LoaiThucUong ltu = new LoaiThucUong();
                ltu.setMaLoai(rs.getString(1));
                ltu.setTenLoaiTU(rs.getString(2));
                
                lst.add(ltu);
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
