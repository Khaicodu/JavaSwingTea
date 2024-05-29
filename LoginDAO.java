/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.TKDAO;
import QuanLyTEA.model.UserAD;
import QuanLyTEA.connect.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    public UserAD login(String tenDN, String matKhau) throws Exception {
        UserAD user = null;
        Connection connection = null;

        try {
            connection = DBConnection.getConnection();
            String query = "SELECT * FROM NguoiDung WHERE tenDN = ? AND matKhau = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, tenDN);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if ("QuangkhaiAD".equals(tenDN)) {
                    user = new UserAD(tenDN, matKhau, true, true, true, true, true);
                } else if ("ThaonguyenTN".equals(tenDN)) {
                    user = new UserAD(tenDN, matKhau, false, false, false, false, true);
                }
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection);
        }

        return user;
    }
}