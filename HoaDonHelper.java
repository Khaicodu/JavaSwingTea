package QuanLyTEA.TKDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class HoaDonHelper {
    // Tạo mã hóa đơn mới dựa trên mã hóa đơn mới nhất
    public static String generateUniqueSoHD() throws Exception {
        String latestSoHD = HoaDonDAO.getLatestSoHD();
        int nextSoHD;

        if (latestSoHD == null) {
            nextSoHD = 1; // Giá trị khởi đầu nếu chưa có hóa đơn nào
        } else {
            nextSoHD = Integer.parseInt(latestSoHD) + 1;
        }

        return String.format("%05d", nextSoHD);
    }
}
