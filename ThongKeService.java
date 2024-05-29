/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.TKDAO;

/**
 *
 * @author Admin
 */
import QuanLyTEA.model.HoaDon;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ThongKeService {
    public static double doanhThuHomNay() throws Exception {
        ArrayList<HoaDon> lst = HoaDonDAO.getALL();

        // Lấy ngày hiện tại và định dạng nó
        LocalDate today = LocalDate.now();
        String formattedToday = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        double doanhThu = 0;
        // Duyệt qua danh sách hóa đơn và tính tổng doanh thu của ngày hôm nay
        for (HoaDon hd : lst) {
           
            String dateFromDB = hd.getThoiGianTao().split(" ")[2]; 

            
//            System.out.println("Formatted Today: " + formattedToday);
//            System.out.println("Date from DB: " + dateFromDB);
//            
            if (dateFromDB.equals(formattedToday)) {
                doanhThu += hd.getTongTien();
            }
        }
        return doanhThu;
    }
    
     public static int soLuongHoaDonHomNay() throws Exception {
        ArrayList<HoaDon> lst = HoaDonDAO.getALL();

        // Lấy ngày hiện tại và định dạng nó
        LocalDate today = LocalDate.now();
        String formattedToday = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        int soLuongHoaDon = 0;

        // Duyệt qua danh sách hóa đơn và đếm số hóa đơn của ngày hôm nay
        for (HoaDon hd : lst) {
            //xuat ngày từ chuỗi thoiGianTao
            String dateFromDB = hd.getThoiGianTao().split(" ")[2]; 
            
            if (dateFromDB.equals(formattedToday)) {
                soLuongHoaDon++;
            }
        }

        return soLuongHoaDon;
    }
     
     public static double doanhThu7NgayGanNhat() throws Exception {
        ArrayList<HoaDon> lst = HoaDonDAO.getALL();

        // Lấy ngày hiện tại và ngày cách đây 7 ngày
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(6);

        double doanhThu = 0;

        // Duyệt qua danh sách hóa đơn và tính tổng doanh thu của 7 ngày gần nhất
        for (HoaDon hd : lst) {
            String dateFromDB = hd.getThoiGianTao().split(" ")[2]; 
            LocalDate date = LocalDate.parse(dateFromDB, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (!date.isBefore(sevenDaysAgo) && !date.isAfter(today)) {
                doanhThu += hd.getTongTien();
            }
        }
        return doanhThu;
    }

    public static int soLuongHoaDon7NgayGanNhat() throws Exception {
        ArrayList<HoaDon> lst = HoaDonDAO.getALL();

        // Lấy ngày hiện tại và ngày cách đây 7 ngày
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(6);

        int soLuongHoaDon = 0;

        // Duyệt qua danh sách hóa đơn và đếm số hóa đơn của 7 ngày gần nhất
        for (HoaDon hd : lst) {
            String dateFromDB = hd.getThoiGianTao().split(" ")[2]; 
            LocalDate date = LocalDate.parse(dateFromDB, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (!date.isBefore(sevenDaysAgo) && !date.isAfter(today)) {
                soLuongHoaDon++;
            }
        }
        return soLuongHoaDon;
    }
    
    //DOanh thu tháng
    public static double doanhThuThangNay() throws Exception {
        ArrayList<HoaDon> lst = HoaDonDAO.getALL();

        // Lấy tháng hiện tại và định dạng nó
        LocalDate today = LocalDate.now();
        String formattedMonth = today.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        double doanhThu = 0;
        // Duyệt qua danh sách hóa đơn và tính tổng doanh thu của tháng hiện tại
        for (HoaDon hd : lst) {
            String dateFromDB = hd.getThoiGianTao().split(" ")[2];
            if (dateFromDB.startsWith(formattedMonth)) {
                doanhThu += hd.getTongTien();
            }
        }
        return doanhThu;
    }

    public static int soLuongHoaDonThangNay() throws Exception {
        ArrayList<HoaDon> lst = HoaDonDAO.getALL();

        // Lấy tháng hiện tại và định dạng nó
        LocalDate today = LocalDate.now();
        String formattedMonth = today.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        int soLuongHoaDon = 0;
        // Duyệt qua danh sách hóa đơn và đếm số hóa đơn của tháng hiện tại
        for (HoaDon hd : lst) {
            String dateFromDB = hd.getThoiGianTao().split(" ")[2];
            if (dateFromDB.startsWith(formattedMonth)) {
                soLuongHoaDon++;
            }
        }
        return soLuongHoaDon;
    }
    //TỔng DThu
      public static double tongDoanhThu() throws Exception {
        ArrayList<HoaDon> lst = HoaDonDAO.getALL();

        double tongDoanhThu = 0;

        // Duyệt qua danh sách hóa đơn và tính tổng doanh thu
        for (HoaDon hd : lst) {
            tongDoanhThu += hd.getTongTien();
        }
        return tongDoanhThu;
    }
      
      public static int soLuongHoaDonTong() throws Exception {
        ArrayList<HoaDon> lst = HoaDonDAO.getALL();

        // Lấy tháng hiện tại và định dạng nó
        int soLuongHoaDon = 0;
        // Duyệt qua danh sách hóa đơn và đếm số hóa đơn của tháng hiện tại
        for (HoaDon hd : lst) {         
                soLuongHoaDon++;
            }        
        return soLuongHoaDon;
    }
}
