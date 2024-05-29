/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QuanLyTEA.UI;

import QuanLyTEA.TKDAO.HoaDonHelper;
import QuanLyTEA.TKDAO.HoaDonDAO;
import QuanLyTEA.TKDAO.HoaDonHelper;
import QuanLyTEA.TKDAO.KhachHangDAO;
import QuanLyTEA.TKDAO.NhanVienDAO;
import QuanLyTEA.TKDAO.ThucUongDAO;
import QuanLyTEA.TKDAO.ChiTietDAO;
import QuanLyTEA.TKDAO.ExcelExporter;
import QuanLyTEA.TKDAO.ThongKeService;
import QuanLyTEA.model.NhanVien;
import QuanLyTEA.model.ThucUong;
import QuanLyTEA.connect.DBConnection;
import QuanLyTEA.model.ChiTietHD;
import QuanLyTEA.model.HoaDon;
import QuanLyTEA.model.KhachHang;
import QuanLyTEA.model.UserAD;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
//ads
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import javax.swing.*;
import java.util.Properties;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import org.jdatepicker.impl.*;
//aaa
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//lấy ngày hiện tại
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Admin
 */
public class GiaoDienTEA extends javax.swing.JFrame {

    /**
     * Creates new form GiaoDienTEA
     */
    private NhanVienDAO nhanVienDAO;
    private ThucUongDAO thucUongDAO;
    private DefaultTableModel tableModel;
    private String tenDN;
    public GiaoDienTEA() {       
        initComponents();
        updateDateTime();
        configureTableOrder();
        nhanVienDAO = new NhanVienDAO();
        thucUongDAO = new ThucUongDAO();
        updateDoanhThuToday();
        updateSoLuongHoaDonToday();
        updateThongKe();
    }
    public GiaoDienTEA(String tenDN) {
        initComponents();
        this.tenDN = tenDN;
        txtDangHoatDong.setText(tenDN);
        updateDateTime();
        configureTableOrder();
        nhanVienDAO = new NhanVienDAO();
        thucUongDAO = new ThucUongDAO();
        updateDoanhThuToday();
        updateSoLuongHoaDonToday();
        updateThongKe();
    }
    
    private void updateThongKe() {
    try {
        double doanhThuToday = ThongKeService.doanhThuHomNay();
        int soLuongHoaDonToday = ThongKeService.soLuongHoaDonHomNay();
        double doanhThu7Ngay = ThongKeService.doanhThu7NgayGanNhat();
        int soLuongHoaDon7Ngay = ThongKeService.soLuongHoaDon7NgayGanNhat();
        double doanhThuThangNay = ThongKeService.doanhThuThangNay();
        int soLuongHoaDonThangNay = ThongKeService.soLuongHoaDonThangNay();
        double tongDoanhThu = ThongKeService.tongDoanhThu();
        int soLuongHoaDonTong = ThongKeService.soLuongHoaDonTong();
        
        
        txtDoanhThuToday.setText(String.valueOf(doanhThuToday));
        txtSoLuongHoaDonToday.setText(String.valueOf(soLuongHoaDonToday));
        txtDoanhThu7Ngay.setText(String.valueOf(doanhThu7Ngay));
        txtSoLuongHoaDon7Ngay.setText(String.valueOf(soLuongHoaDon7Ngay));
        txtDoanhThuThangNay.setText(String.valueOf(doanhThuThangNay));
        txtSoLuongHoaDonThangNay.setText(String.valueOf(soLuongHoaDonThangNay));
        txtTongDoanhThu.setText(String.valueOf(tongDoanhThu));
        txtSoLuongTongDT.setText(String.valueOf(soLuongHoaDonTong));
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tính toán thống kê: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    private void updateDoanhThuToday() {
        try {
            double doanhThuToday = ThongKeService.doanhThuHomNay();
            txtDoanhThuToday.setText(String.valueOf(doanhThuToday));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tính toán doanh thu hôm nay: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updateSoLuongHoaDonToday() {
        try {
            int soLuongHoaDonToday = ThongKeService.soLuongHoaDonHomNay();
            txtSoLuongHoaDonToday.setText(String.valueOf(soLuongHoaDonToday));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tính toán số lượng hóa đơn hôm nay: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setAdminRights() {
        // Cho phép tất cả quyền
        btnThemNV.setEnabled(true);
        btnXoaNV.setEnabled(true);
        btnTimKiemNV.setEnabled(true);
        btnSuaNV.setEnabled(true);
        tableNhanVien.setEnabled(true);
        tableThucUong.setEnabled(true);
        // ... bật tất cả các nút và thành phần liên quan đến quyền
    }

    public void setUserRights(UserAD user) {
        
        if (!user.canDelete()) {
            // Vô hiệu hóa các chức năng DELETE
            btnXoaNV.setEnabled(false);
        }
        if (!user.canUpdate()) {
            // Vô hiệu hóa các chức năng UPDATE
            btnSuaNV.setEnabled(false);
        }
        if (!user.canSearch()) {
            // Vô hiệu hóa các chức năng SEARCH
            btnTimKiemNV.setEnabled(false);
        }
        // Các chức năng thêm mới (INSERT) nếu có
        if (!user.isAdmin()) {
            btnThemNV.setEnabled(false);
        }
    }
    
private void configureTableOrder() {
    tableOder.setRowHeight(30);

    DefaultTableModel model = (DefaultTableModel) tableOder.getModel();
    
    if (model.getColumnCount() < 7) {
        model.addColumn("Tổng tiền");
    }

    TableColumnModel columnModel = tableOder.getColumnModel();
    columnModel.getColumn(4).setCellEditor(new SpinnerEditor());
    columnModel.getColumn(4).setCellRenderer(new SpinnerRenderer());

    tableOder.getModel().addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent e) {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int col = e.getColumn();
                if (col == 2 || col == 4) { 
                    updateTotalPrice(row);
                }
                updateTotalAmount(); // Cập nhật tổng tiền mỗi khi bảng thay đổi
            }
        }
    });
}

private void updateTotalPrice(int row) {
    DefaultTableModel model = (DefaultTableModel) tableOder.getModel();
    double price = Double.parseDouble(model.getValueAt(row, 2).toString()); 
    int quantity = (int) model.getValueAt(row, 4); 
    double totalPrice = price * quantity; 

    // Cập nhật giá trị trong cột "Tổng tiền"
    model.setValueAt(totalPrice, row, 5);
}

private void updateTotalAmount() {
    DefaultTableModel model = (DefaultTableModel) tableOder.getModel();
    double totalAmount = 0.0;

    for (int i = 0; i < model.getRowCount(); i++) {
        double rowTotal = Double.parseDouble(model.getValueAt(i, 5).toString()); // Lấy giá trị từ cột "Tổng tiền"
        totalAmount += rowTotal;
    }
    txtTongTienHD.setText(String.valueOf(totalAmount));
    txtTongTienSP.setText(String.valueOf(totalAmount));
}

// Các lớp Renderer và Editor
class SpinnerRenderer extends JSpinner implements TableCellRenderer {
    public SpinnerRenderer() {
        super(new SpinnerNumberModel(1, 1, 100, 1));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setValue(value);
        return this;
    }
}

class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
    private JSpinner spinner;
    private JTable table;

    public SpinnerEditor() {
        spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinner.addChangeListener(e -> {
            if (table != null) {
                int row = table.getEditingRow();
                if (row != -1) {
                    updateTotalPrice(row);
                    
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.fireTableCellUpdated(row, 5); 
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        spinner.setValue(value);
        return spinner;
    }

    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }
}
//ads
public class DatePickerExample extends JFrame {
    public DatePickerExample() {
        // Tạo một SqlDateModel
        SqlDateModel model = new SqlDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Thêm JDatePicker vào JPanel
        JPanel panel = new JPanel();
        panel.add(datePicker);

        // Cấu hình JFrame
        this.add(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
public class DateLabelFormatter extends AbstractFormatter {
    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCard = new javax.swing.JPanel();
        pnlTrangChu = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        pnlHoaDon = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableHoaDon = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        tableChiTietHD = new javax.swing.JTable();
        jLabel53 = new javax.swing.JLabel();
        txtSOHD = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txtKHACHHANG = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txtSDTKH = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        txtTGTAO = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        txtLOAITT = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtGHICHU = new javax.swing.JTextArea();
        jLabel66 = new javax.swing.JLabel();
        txtTRANGTHAI = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        txtTONGTIENSP = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        txtTONGTIENHD = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtDIACHIGIAO = new javax.swing.JTextArea();
        btnXACNHAN = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tableLichSuHD = new javax.swing.JTable();
        jLabel97 = new javax.swing.JLabel();
        txtTimKiemHD = new javax.swing.JTextField();
        btnTimKiemHD = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        txtTUNGAY = new javax.swing.JLabel();
        btnTuNgay = new javax.swing.JButton();
        jLabel69 = new javax.swing.JLabel();
        txtDENNGAY = new javax.swing.JLabel();
        btnDenNgay = new javax.swing.JButton();
        btnLoc = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        txtSOHD1 = new javax.swing.JLabel();
        txtKHACHHANG1 = new javax.swing.JLabel();
        txtSODTKH1 = new javax.swing.JLabel();
        txtTGTAO1 = new javax.swing.JLabel();
        txtLOAITT1 = new javax.swing.JLabel();
        txtGHICHU1 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        txtTRANGTHAI1 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        txtTONGTIENSP1 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        txtTONGTIENHD1 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tableOderCT = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        btnXuatFileEXCEL = new javax.swing.JButton();
        btnResetLSHD = new javax.swing.JButton();
        pnlKhuyenMai = new javax.swing.JPanel();
        pnlThongKe = new javax.swing.JPanel();
        btnDoanhThu = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        txtTuNgay = new javax.swing.JTextField();
        txtDenNgay = new javax.swing.JTextField();
        btnTUDAY = new javax.swing.JButton();
        btnDENDAY = new javax.swing.JButton();
        btnXuatFileExcel = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        txtDoanhThuToday = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        txtSoLuongHoaDonToday = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        x = new javax.swing.JLabel();
        txtDoanhThu7Ngay = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        txtSoLuongHoaDon7Ngay = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        txtDoanhThuThangNay = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        txtSoLuongHoaDonThangNay = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        txtTongDoanhThu = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        txtSoLuongTongDT = new javax.swing.JLabel();
        pnlNhanVien = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableNhanVien = new javax.swing.JTable();
        jpChucNang = new javax.swing.JPanel();
        coboBoxNV = new javax.swing.JComboBox<>();
        txtTimKiemMNV = new javax.swing.JTextField();
        txtTimKiemTenNV = new javax.swing.JTextField();
        txtTimKiemSTDNV = new javax.swing.JTextField();
        txtTimKiemNgaySNV = new javax.swing.JTextField();
        txtTimKiemCCCD = new javax.swing.JTextField();
        txtTimKiemDiaChi = new javax.swing.JTextField();
        btnTimKiemNV = new javax.swing.JButton();
        txtResetTimKiem = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMANV = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtHOTEN = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtSODT = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtNGAYSINH = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtCanCuoc = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtDIACHI = new javax.swing.JTextField();
        btnThemNV = new javax.swing.JButton();
        btnSuaNV = new javax.swing.JButton();
        btnXoaNV = new javax.swing.JButton();
        btnLamMoiNV = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        pnlBanHang = new javax.swing.JPanel();
        jpMenuTU = new javax.swing.JPanel();
        txtTensp = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        txtLoaiSP = new javax.swing.JLabel();
        cbbLoaiSP = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnTraSuaDau = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnTraSuaVQ = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnTRxoai = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnCFsua = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnTSDuongDen = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnTSsoCola = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnTRdua = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btnTRnhiet = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btnTRdao = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnCFden = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        btnCFdua = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableHDcho = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableGiaoHang = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableOder = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        txtTimSoDTKH = new javax.swing.JTextField();
        btnTimSDTKH = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        txtTenKHOder = new javax.swing.JTextField();
        btnThemKHNEW = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        txtDiemTL = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtGhiChuHD = new javax.swing.JTextArea();
        btnHuyHD = new javax.swing.JButton();
        btnGiaoHang = new javax.swing.JButton();
        btnDoiDiemTL = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        txtTongTienSP = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtTongTienHD = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtTienKhachTra = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        coboLoaiTT = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtDiaChiGiao = new javax.swing.JTextArea();
        btnThanhToan = new javax.swing.JButton();
        btnTSP = new javax.swing.JButton();
        txtTrangThai = new javax.swing.JComboBox<>();
        txtSoHD = new javax.swing.JTextField();
        btnTaoSoHD = new javax.swing.JButton();
        btnXoaTrongTableOder = new javax.swing.JButton();
        btnResetOder = new javax.swing.JButton();
        pnlSanPham = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        txtMATU = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtTENTU = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtGIATU = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtSIZETU = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        coboMALOAI = new javax.swing.JComboBox<>();
        btnThemTU = new javax.swing.JButton();
        btnCapNhatTU = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        coboTRANGTHAI = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        txtTimKiemTU = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        coboBoxLoaiTU = new javax.swing.JComboBox<>();
        jLabel50 = new javax.swing.JLabel();
        coboBoxTrangThai = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableThucUong = new javax.swing.JTable();
        btnTimTU = new javax.swing.JButton();
        pnlKhachHang = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tableKhachHang = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        txtTimKH = new javax.swing.JTextField();
        btnTimKH = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        txtSoDTKH = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        txtDiem = new javax.swing.JTextField();
        btnCapNhatKH = new javax.swing.JButton();
        btnLamMoiKH = new javax.swing.JButton();
        jpMenu2 = new javax.swing.JPanel();
        btnGiaoCa = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        txtHoatDong = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        txtDangHoatDong = new javax.swing.JLabel();
        jpMenu = new javax.swing.JPanel();
        txtLogo = new javax.swing.JLabel();
        btnTrangChu = new javax.swing.JButton();
        btnBanHang = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnKhuyenMai = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlCard.setLayout(new java.awt.CardLayout());

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/yiqiTrangChu.png"))); // NOI18N

        javax.swing.GroupLayout pnlTrangChuLayout = new javax.swing.GroupLayout(pnlTrangChu);
        pnlTrangChu.setLayout(pnlTrangChuLayout);
        pnlTrangChuLayout.setHorizontalGroup(
            pnlTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel73)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        pnlTrangChuLayout.setVerticalGroup(
            pnlTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTrangChuLayout.createSequentialGroup()
                .addComponent(jLabel73)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        pnlCard.add(pnlTrangChu, "card3");

        jLabel51.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel51.setText("HÓA ĐƠN");

        jLabel52.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel52.setText("CHI TIẾT HÓA ĐƠN");

        tableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số HĐ", "Người tạo", "Khách hàng", "TG tạo", "Trạng thái", "Tổng tiền"
            }
        ));
        tableHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHoaDonMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tableHoaDon);
        if (tableHoaDon.getColumnModel().getColumnCount() > 0) {
            tableHoaDon.getColumnModel().getColumn(3).setResizable(false);
        }

        tableChiTietHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã TU", "Tên TU", "Giá TU", "Size TU", "Số lượng"
            }
        ));
        jScrollPane11.setViewportView(tableChiTietHD);
        if (tableChiTietHD.getColumnModel().getColumnCount() > 0) {
            tableChiTietHD.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableChiTietHD.getColumnModel().getColumn(1).setPreferredWidth(130);
            tableChiTietHD.getColumnModel().getColumn(2).setPreferredWidth(60);
            tableChiTietHD.getColumnModel().getColumn(3).setPreferredWidth(10);
            tableChiTietHD.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        jLabel53.setText("Số HĐ:");

        txtSOHD.setText("xxxxx");

        jLabel55.setText("Khách hàng:");

        txtKHACHHANG.setText("xxxxx");

        jLabel57.setText("SĐT:");

        txtSDTKH.setText("xxxxx");

        jLabel59.setText("TG tạo:");

        txtTGTAO.setText("xxxxx");

        jLabel63.setText("Loại TT:");

        txtLOAITT.setText("xxxxx");

        jLabel65.setText("Ghi chú");

        txtGHICHU.setColumns(20);
        txtGHICHU.setRows(5);
        jScrollPane12.setViewportView(txtGHICHU);

        jLabel66.setText("Trạng thái");

        txtTRANGTHAI.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTRANGTHAI.setText("XXXXXX");

        jLabel68.setText("Tổng tiền SP:");

        txtTONGTIENSP.setText("xxxxx");

        jLabel70.setText("Tổng tiền HĐ:");

        txtTONGTIENHD.setText("xxxxx");

        jLabel72.setText("Địa chỉ");

        txtDIACHIGIAO.setColumns(20);
        txtDIACHIGIAO.setRows(5);
        jScrollPane13.setViewportView(txtDIACHIGIAO);

        btnXACNHAN.setBackground(new java.awt.Color(255, 105, 68));
        btnXACNHAN.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnXACNHAN.setForeground(new java.awt.Color(255, 255, 255));
        btnXACNHAN.setText("XUẤT HÓA ĐƠN");
        btnXACNHAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXACNHANActionPerformed(evt);
            }
        });

        jLabel54.setText("vnđ");

        jLabel56.setText("vnđ");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel52)
                .addGap(193, 193, 193))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(btnXACNHAN)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel53)
                                    .addComponent(jLabel55)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel59)
                                    .addComponent(jLabel63)
                                    .addComponent(jLabel65))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSOHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtKHACHHANG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSDTKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTGTAO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtLOAITT, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))))
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(txtTRANGTHAI)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addComponent(jLabel72)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addComponent(jLabel68)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTONGTIENSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addComponent(jLabel70)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtTONGTIENHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel56)
                                            .addComponent(jLabel54))
                                        .addGap(21, 21, 21))
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel66))
                                        .addGap(0, 0, Short.MAX_VALUE))))))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXACNHAN, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(txtSOHD)
                            .addComponent(jLabel66))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55)
                            .addComponent(txtKHACHHANG)
                            .addComponent(txtTRANGTHAI))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(txtSDTKH))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(txtTGTAO)
                            .addComponent(jLabel68)
                            .addComponent(txtTONGTIENSP)
                            .addComponent(jLabel54))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70)
                            .addComponent(txtTONGTIENHD)
                            .addComponent(jLabel56))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel63)
                                    .addComponent(txtLOAITT))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel65)
                                    .addComponent(jLabel72))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa đơn", jPanel18);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        jLabel58.setForeground(new java.awt.Color(0, 0, 153));
        jLabel58.setText("+HD trực tiếp");

        jLabel60.setForeground(new java.awt.Color(255, 0, 0));
        jLabel60.setText("+HD giao đi");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jLabel60)
                .addGap(71, 71, 71))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel60))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        tableLichSuHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số HD", "Tên khách hàng", "Người tạo", "Thời gian tạo", "Trạng thái", "Tổng tiền"
            }
        ));
        tableLichSuHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLichSuHDMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tableLichSuHD);

        jLabel97.setText("Mã HD:");

        btnTimKiemHD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiemHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        btnTimKiemHD.setText("Tìm");
        btnTimKiemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemHDActionPerformed(evt);
            }
        });

        jLabel64.setText("Từ ngày:");

        txtTUNGAY.setText("xxxx");

        btnTuNgay.setBackground(new java.awt.Color(0, 204, 204));
        btnTuNgay.setForeground(new java.awt.Color(255, 255, 255));
        btnTuNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/calendar.png"))); // NOI18N
        btnTuNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTuNgayActionPerformed(evt);
            }
        });

        jLabel69.setText("Đến ngày:");

        txtDENNGAY.setText("xxxxx");

        btnDenNgay.setBackground(new java.awt.Color(0, 204, 204));
        btnDenNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/calendar.png"))); // NOI18N
        btnDenNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDenNgayActionPerformed(evt);
            }
        });

        btnLoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/filter.png"))); // NOI18N
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        jLabel98.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel98.setText("Chi tiết hóa đơn");

        jLabel99.setText("Số HD:");

        jLabel100.setText("Khách hàng:");

        jLabel101.setText("SĐT:");

        jLabel102.setText("TG tạo:");

        jLabel103.setText("Loại TT:");

        jLabel104.setText("Ghi chú:");

        txtSOHD1.setText("x");

        txtKHACHHANG1.setText("x");

        txtSODTKH1.setText("x");

        txtTGTAO1.setText("x");

        txtLOAITT1.setText("x");

        txtGHICHU1.setText("x");

        jLabel111.setText("Trạng thái");

        txtTRANGTHAI1.setText("x");

        jLabel113.setText("Tổng tiền SP:");

        txtTONGTIENSP1.setText("x");

        jLabel115.setText("vnđ");

        jLabel116.setText("Tổng tiền HD:");

        txtTONGTIENHD1.setText("x");

        jLabel118.setText("vnđ");

        jLabel119.setText("Địa chỉ:");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel104)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGHICHU1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel103)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtLOAITT1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel102)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTGTAO1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel101)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSODTKH1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel100)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtKHACHHANG1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel99)
                        .addGap(82, 82, 82)
                        .addComponent(txtSOHD1)))
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(txtTRANGTHAI1))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(jLabel113)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTONGTIENSP1))
                            .addComponent(jLabel111)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(jLabel116)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTONGTIENHD1))
                            .addComponent(jLabel119))))
                .addGap(53, 53, 53)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel115)
                    .addComponent(jLabel98)
                    .addComponent(jLabel118))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel98)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99)
                    .addComponent(txtSOHD1)
                    .addComponent(jLabel111))
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel100)
                            .addComponent(txtKHACHHANG1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel101)
                            .addComponent(txtSODTKH1)))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(txtTRANGTHAI1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102)
                    .addComponent(txtTGTAO1)
                    .addComponent(jLabel113)
                    .addComponent(txtTONGTIENSP1)
                    .addComponent(jLabel115))
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103)
                    .addComponent(txtLOAITT1)
                    .addComponent(jLabel116)
                    .addComponent(txtTONGTIENHD1)
                    .addComponent(jLabel118))
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104)
                    .addComponent(txtGHICHU1)
                    .addComponent(jLabel119))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableOderCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã TU", "Tên TU", "Giá TU", "Size TU", "Số lượng"
            }
        ));
        jScrollPane16.setViewportView(tableOderCT);

        jButton7.setText("Khôi phục HD");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnXuatFileEXCEL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXuatFileEXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/export-file.png"))); // NOI18N
        btnXuatFileEXCEL.setText("Xuất File");
        btnXuatFileEXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileEXCELActionPerformed(evt);
            }
        });

        btnResetLSHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btnResetLSHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetLSHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(jButton7)))
                .addGap(34, 34, 34))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(txtTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTimKiemHD)
                                .addGap(15, 15, 15)
                                .addComponent(btnResetLSHD))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel97)
                                .addGap(114, 114, 114)
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110)
                                .addComponent(jLabel64)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTUNGAY, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTuNgay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDENNGAY, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDenNgay)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXuatFileEXCEL)))
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 1178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel97)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel64)
                                .addComponent(txtTUNGAY))
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel69)
                                .addComponent(txtDENNGAY)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiemHD)
                            .addComponent(btnResetLSHD)))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(btnLoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXuatFileEXCEL))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTuNgay)
                            .addComponent(btnDenNgay))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(433, 433, 433))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Lịch sử hđ", jPanel19);

        javax.swing.GroupLayout pnlHoaDonLayout = new javax.swing.GroupLayout(pnlHoaDon);
        pnlHoaDon.setLayout(pnlHoaDonLayout);
        pnlHoaDonLayout.setHorizontalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );

        pnlCard.add(pnlHoaDon, "card4");

        javax.swing.GroupLayout pnlKhuyenMaiLayout = new javax.swing.GroupLayout(pnlKhuyenMai);
        pnlKhuyenMai.setLayout(pnlKhuyenMaiLayout);
        pnlKhuyenMaiLayout.setHorizontalGroup(
            pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1220, Short.MAX_VALUE)
        );
        pnlKhuyenMaiLayout.setVerticalGroup(
            pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        pnlCard.add(pnlKhuyenMai, "card6");

        btnDoanhThu.setBackground(new java.awt.Color(205, 133, 63));
        btnDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        btnDoanhThu.setText("Doanh thu - Sản phẩm");
        btnDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoanhThuActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(205, 133, 63));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Giao ca");

        jLabel67.setText("Từ:");

        jLabel71.setText("Đến:");

        txtTuNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTuNgayActionPerformed(evt);
            }
        });

        btnTUDAY.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/calendar.png"))); // NOI18N
        btnTUDAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTUDAYActionPerformed(evt);
            }
        });

        btnDENDAY.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/calendar.png"))); // NOI18N
        btnDENDAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDENDAYActionPerformed(evt);
            }
        });

        btnXuatFileExcel.setBackground(new java.awt.Color(205, 133, 63));
        btnXuatFileExcel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXuatFileExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatFileExcel.setText("Xuất EX");
        btnXuatFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcelActionPerformed(evt);
            }
        });

        jPanel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel24.setBackground(new java.awt.Color(205, 133, 63));

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("Hôm nay");

        txtDoanhThuToday.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtDoanhThuToday.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuToday.setText("xxxxx");

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Hóa đơn bán:");

        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Hóa đơn giao:");

        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Hóa đơn hủy:");

        txtSoLuongHoaDonToday.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSoLuongHoaDonToday.setForeground(new java.awt.Color(255, 255, 255));
        txtSoLuongHoaDonToday.setText("x");

        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("x");

        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("x");

        jLabel79.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("VNĐ");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel78)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel81))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel76)
                                    .addComponent(jLabel77))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSoLuongHoaDonToday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDoanhThuToday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jLabel79)))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel74)
                .addGap(42, 42, 42)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDoanhThuToday)
                    .addComponent(jLabel79))
                .addGap(38, 38, 38)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(txtSoLuongHoaDonToday))
                .addGap(28, 28, 28)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(jLabel80))
                .addGap(28, 28, 28)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(jLabel81))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel25.setBackground(new java.awt.Color(205, 133, 63));

        x.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        x.setForeground(new java.awt.Color(255, 255, 255));
        x.setText("7 ngày");

        txtDoanhThu7Ngay.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtDoanhThu7Ngay.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThu7Ngay.setText("xxxxxx");

        jLabel84.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setText("Hóa đơn bán:");

        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setText("Hóa đơn giao:");

        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Hóa đơn hủy:");

        txtSoLuongHoaDon7Ngay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSoLuongHoaDon7Ngay.setForeground(new java.awt.Color(255, 255, 255));
        txtSoLuongHoaDon7Ngay.setText("x");

        jLabel82.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setText("VNĐ");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel86)
                    .addComponent(jLabel85)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSoLuongHoaDon7Ngay)))
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDoanhThu7Ngay)
                    .addComponent(x))
                .addGap(46, 46, 46)
                .addComponent(jLabel82)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(x)
                .addGap(45, 45, 45)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDoanhThu7Ngay)
                    .addComponent(jLabel82))
                .addGap(31, 31, 31)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(txtSoLuongHoaDon7Ngay))
                .addGap(27, 27, 27)
                .addComponent(jLabel85)
                .addGap(29, 29, 29)
                .addComponent(jLabel86)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel26.setBackground(new java.awt.Color(205, 133, 63));

        jLabel87.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Tháng này");

        txtDoanhThuThangNay.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtDoanhThuThangNay.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuThangNay.setText("xxxxxxxx");

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Hóa đơn bán:");

        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("Hóa đơn giao:");

        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Hóa đơn hủy:");

        jLabel83.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("VNĐ");

        txtSoLuongHoaDonThangNay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSoLuongHoaDonThangNay.setForeground(new java.awt.Color(255, 255, 255));
        txtSoLuongHoaDonThangNay.setText("x");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel91)
                            .addComponent(jLabel90)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(jLabel89)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSoLuongHoaDonThangNay)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(txtDoanhThuThangNay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jLabel83)
                        .addGap(19, 19, 19))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel87)
                .addGap(77, 77, 77))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel87)
                .addGap(37, 37, 37)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDoanhThuThangNay)
                    .addComponent(jLabel83))
                .addGap(32, 32, 32)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(txtSoLuongHoaDonThangNay))
                .addGap(27, 27, 27)
                .addComponent(jLabel90)
                .addGap(28, 28, 28)
                .addComponent(jLabel91)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel27.setBackground(new java.awt.Color(205, 133, 63));

        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Tổng doanh thu");

        txtTongDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtTongDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        txtTongDoanhThu.setText("xxxxxx");

        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setText("VNĐ");

        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText("Hóa đơn bán:");

        txtSoLuongTongDT.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSoLuongTongDT.setForeground(new java.awt.Color(255, 255, 255));
        txtSoLuongTongDT.setText("x");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel96)
                .addGap(18, 18, 18)
                .addComponent(txtSoLuongTongDT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel93)
                        .addGap(41, 41, 41))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(txtTongDoanhThu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel95)
                        .addGap(19, 19, 19))))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel93)
                .addGap(33, 33, 33)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongDoanhThu)
                    .addComponent(jLabel95))
                .addGap(35, 35, 35)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96)
                    .addComponent(txtSoLuongTongDT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlThongKeLayout.createSequentialGroup()
                        .addComponent(btnDoanhThu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel67)
                            .addComponent(jLabel71))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongKeLayout.createSequentialGroup()
                                .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDENDAY))
                            .addGroup(pnlThongKeLayout.createSequentialGroup()
                                .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTUDAY)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXuatFileExcel)))
                .addGap(43, 43, 43))
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnXuatFileExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlThongKeLayout.createSequentialGroup()
                            .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnTUDAY)
                                .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel67)
                                    .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnDENDAY)
                                .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel71))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
        );

        pnlCard.add(pnlThongKe, "card7");

        tableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ và tên", "Số điện thoại", "Ngày sinh", "Căn cước công dân", "Địa chỉ"
            }
        ));
        tableNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhanVienMouseClicked(evt);
            }
        });
        tableNhanVien.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tableNhanVienComponentShown(evt);
            }
        });
        jScrollPane4.setViewportView(tableNhanVien);

        jpChucNang.setBackground(new java.awt.Color(255, 255, 240));

        coboBoxNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả nhân viên", "NV01", "NV02", "NV03" }));
        coboBoxNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coboBoxNVActionPerformed(evt);
            }
        });

        txtTimKiemMNV.setForeground(new java.awt.Color(204, 204, 204));
        txtTimKiemMNV.setText("Tìm kiếm bằng mã...");
        txtTimKiemMNV.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemMNVFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemMNVFocusLost(evt);
            }
        });

        txtTimKiemTenNV.setForeground(new java.awt.Color(204, 204, 204));
        txtTimKiemTenNV.setText("Tìm kiếm bằng tên...");
        txtTimKiemTenNV.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemTenNVCaretUpdate(evt);
            }
        });
        txtTimKiemTenNV.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemTenNVFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemTenNVFocusLost(evt);
            }
        });

        txtTimKiemSTDNV.setForeground(new java.awt.Color(204, 204, 204));
        txtTimKiemSTDNV.setText("Tìm kiếm bằng số điện thoại...");
        txtTimKiemSTDNV.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemSTDNVFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemSTDNVFocusLost(evt);
            }
        });

        txtTimKiemNgaySNV.setForeground(new java.awt.Color(204, 204, 204));
        txtTimKiemNgaySNV.setText("Tìm kiếm bằng ngày sinh...");
        txtTimKiemNgaySNV.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemNgaySNVFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemNgaySNVFocusLost(evt);
            }
        });

        txtTimKiemCCCD.setForeground(new java.awt.Color(204, 204, 204));
        txtTimKiemCCCD.setText("Tìm kiếm bằng căn cước...");
        txtTimKiemCCCD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemCCCDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemCCCDFocusLost(evt);
            }
        });

        txtTimKiemDiaChi.setForeground(new java.awt.Color(204, 204, 204));
        txtTimKiemDiaChi.setText("Tìm kiếm bằng địa chỉ...");
        txtTimKiemDiaChi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemDiaChiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemDiaChiFocusLost(evt);
            }
        });

        btnTimKiemNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        btnTimKiemNV.setText("Tìm");
        btnTimKiemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemNVActionPerformed(evt);
            }
        });

        txtResetTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        txtResetTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResetTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpChucNangLayout = new javax.swing.GroupLayout(jpChucNang);
        jpChucNang.setLayout(jpChucNangLayout);
        jpChucNangLayout.setHorizontalGroup(
            jpChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpChucNangLayout.createSequentialGroup()
                .addGroup(jpChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpChucNangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimKiemTenNV, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTimKiemMNV)))
                    .addGroup(jpChucNangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTimKiemSTDNV, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                    .addGroup(jpChucNangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTimKiemNgaySNV))
                    .addGroup(jpChucNangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTimKiemCCCD))
                    .addGroup(jpChucNangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTimKiemDiaChi))
                    .addGroup(jpChucNangLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(coboBoxNV, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpChucNangLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtResetTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jpChucNangLayout.setVerticalGroup(
            jpChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(coboBoxNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txtTimKiemMNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemSTDNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemNgaySNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTimKiemNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtResetTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 240));

        jLabel1.setText("Mã NV");

        jLabel2.setText("Họ và tên");

        jLabel27.setText("Số điện thoại");

        txtSODT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSODTActionPerformed(evt);
            }
        });

        jLabel28.setText("Ngày sinh");

        jLabel29.setText("Căn cước công dân");

        jLabel30.setText("Địa chỉ");

        btnThemNV.setBackground(new java.awt.Color(205, 133, 63));
        btnThemNV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThemNV.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plus.png"))); // NOI18N
        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnSuaNV.setBackground(new java.awt.Color(205, 133, 63));
        btnSuaNV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSuaNV.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit.png"))); // NOI18N
        btnSuaNV.setText("Sửa");
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });

        btnXoaNV.setBackground(new java.awt.Color(205, 133, 63));
        btnXoaNV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoaNV.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N
        btnXoaNV.setText("Xóa");
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });

        btnLamMoiNV.setBackground(new java.awt.Color(205, 133, 63));
        btnLamMoiNV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLamMoiNV.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoiNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btnLamMoiNV.setText("Làm mới");
        btnLamMoiNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtMANV, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtHOTEN, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(txtSODT, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel28)
                                .addComponent(txtNGAYSINH, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(txtCanCuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDIACHI, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addGap(85, 85, 85))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnSuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(btnXoaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(btnLamMoiNV)
                        .addContainerGap(65, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel27)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMANV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSODT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCanCuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel28)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHOTEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNGAYSINH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDIACHI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addComponent(jpChucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCard.add(pnlNhanVien, "card8");

        jpMenuTU.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtTensp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTensp.setText("Tên sản phẩm");

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        btnTimKiem.setText("Tìm");

        txtLoaiSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtLoaiSP.setText("Loại sản phẩm");

        cbbLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Trà trái cây", "Trà sữa", "Cà phê" }));

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBackground(new java.awt.Color(205, 179, 139));

        btnTraSuaDau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tsDau.jpg"))); // NOI18N
        btnTraSuaDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraSuaDauActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("35.000 đ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Trà sữa dâu");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(8, Short.MAX_VALUE)
                        .addComponent(btnTraSuaDau, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTraSuaDau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(205, 179, 139));

        btnTraSuaVQ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tsVietquat.jpg"))); // NOI18N
        btnTraSuaVQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraSuaVQActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Trà sữa việt quất");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("35.000 đ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel6))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5))
                            .addComponent(btnTraSuaVQ, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTraSuaVQ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(205, 179, 139));

        btnTRxoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trDaoKem.jpg"))); // NOI18N
        btnTRxoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTRxoaiActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Trà xoài kem");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("25.000 đ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnTRxoai, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8))
                            .addComponent(jLabel7))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTRxoai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(205, 179, 139));

        btnCFsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cfSua.jpg"))); // NOI18N
        btnCFsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCFsuaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Cà phê sữa");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("20.000 đ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCFsua, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel10))
                            .addComponent(jLabel9))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCFsua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(205, 179, 139));

        btnTSDuongDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tsDuongDen.jpg"))); // NOI18N
        btnTSDuongDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTSDuongDenActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Trà sữa đường đen");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("35.000 đ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(btnTSDuongDen, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel12)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTSDuongDen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(205, 179, 139));

        btnTSsoCola.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/socola.jpg"))); // NOI18N
        btnTSsoCola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTSsoColaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Trà sữa socola");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("35.000 đ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(23, 23, 23))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnTSsoCola, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTSsoCola)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(205, 179, 139));

        btnTRdua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trDuaLuoi.jpg"))); // NOI18N
        btnTRdua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTRduaActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Trà dưa lưới");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("25.000 đ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnTRdua, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel16)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTRdua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(205, 179, 139));

        btnTRnhiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trNhietDoi.jpg"))); // NOI18N
        btnTRnhiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTRnhietActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Trà nhiệt đới");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("25.000 đ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(27, 27, 27))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnTRnhiet, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel18)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTRnhiet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(205, 179, 139));

        btnTRdao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trXoai.jpg"))); // NOI18N
        btnTRdao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTRdaoActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Trà đào");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("25.000 đ");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnTRdao, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(25, 25, 25))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTRdao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(205, 179, 139));

        btnCFden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cfDen.jpg"))); // NOI18N
        btnCFden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCFdenActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Cà phê đen đá");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("20.000 đ");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCFden, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel21))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel22)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCFden)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(205, 179, 139));

        btnCFdua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/epenso.jpg"))); // NOI18N
        btnCFdua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCFduaActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Cà phê cốt dừa");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("20.000 đ");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addGap(22, 22, 22))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCFdua, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel24)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCFdua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel13);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("Hóa đơn chờ");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("Hóa đơn giao");

        tableHDcho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số HD", "Người tạo", "Khách hàng", "TG tạo", "Trạng thái", "Tổng tiền"
            }
        ));
        jScrollPane2.setViewportView(tableHDcho);
        if (tableHDcho.getColumnModel().getColumnCount() > 0) {
            tableHDcho.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableHDcho.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        tableGiaoHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Số HĐ", "Người tạo", "Khách hàng", "TG tạo", "Trạng thái", "Ghi chú"
            }
        ));
        jScrollPane3.setViewportView(tableGiaoHang);

        javax.swing.GroupLayout jpMenuTULayout = new javax.swing.GroupLayout(jpMenuTU);
        jpMenuTU.setLayout(jpMenuTULayout);
        jpMenuTULayout.setHorizontalGroup(
            jpMenuTULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
            .addGroup(jpMenuTULayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMenuTULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMenuTULayout.createSequentialGroup()
                        .addGroup(jpMenuTULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)
                    .addGroup(jpMenuTULayout.createSequentialGroup()
                        .addGroup(jpMenuTULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpMenuTULayout.createSequentialGroup()
                                .addGroup(jpMenuTULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTensp)
                                    .addGroup(jpMenuTULayout.createSequentialGroup()
                                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnTimKiem)))
                                .addGap(6, 6, 6)
                                .addGroup(jpMenuTULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpMenuTULayout.createSequentialGroup()
                                        .addComponent(txtLoaiSP)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cbbLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        jpMenuTULayout.setVerticalGroup(
            jpMenuTULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMenuTULayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMenuTULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTensp)
                    .addComponent(txtLoaiSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMenuTULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem)
                    .addComponent(cbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tableOder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã TU", "Tên TU", "Giá TU", "Size TU", "Số lượng"
            }
        ));
        jScrollPane6.setViewportView(tableOder);
        if (tableOder.getColumnModel().getColumnCount() > 0) {
            tableOder.getColumnModel().getColumn(4).setPreferredWidth(35);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 240));

        jLabel37.setText("SĐT");

        btnTimSDTKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        btnTimSDTKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSDTKHActionPerformed(evt);
            }
        });

        jLabel31.setText("Họ tên");

        txtTenKHOder.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTenKHOderCaretUpdate(evt);
            }
        });

        btnThemKHNEW.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plus.png"))); // NOI18N
        btnThemKHNEW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHNEWActionPerformed(evt);
            }
        });

        jLabel32.setText("Điểm tích lũy");

        jLabel33.setText("Ghi chú");

        txtGhiChuHD.setColumns(20);
        txtGhiChuHD.setRows(5);
        txtGhiChuHD.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGhiChuHDCaretUpdate(evt);
            }
        });
        jScrollPane5.setViewportView(txtGhiChuHD);

        btnHuyHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuyHD.setText("HỦY");

        btnGiaoHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGiaoHang.setText("GIAO HÀNG");
        btnGiaoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaoHangActionPerformed(evt);
            }
        });

        btnDoiDiemTL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDoiDiemTL.setText("ĐỔI ĐIỂM");
        btnDoiDiemTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiDiemTLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel37)
                        .addComponent(jLabel31)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTenKHOder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(txtTimSoDTKH, javax.swing.GroupLayout.Alignment.LEADING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnTimSDTKH, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                .addComponent(btnThemKHNEW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel32)
                        .addComponent(txtDiemTL)
                        .addComponent(jLabel33)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnHuyHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGiaoHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDoiDiemTL)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTimSDTKH)
                    .addComponent(txtTimSoDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenKHOder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemKHNEW))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiemTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGiaoHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHuyHD)
                    .addComponent(btnDoiDiemTL))
                .addGap(20, 20, 20))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 240));

        jLabel34.setText("Tổng tiền sản phẩm");

        txtTongTienSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienSPActionPerformed(evt);
            }
        });

        jLabel35.setText("Tổng tiền hóa đơn");

        txtTongTienHD.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTongTienHDCaretUpdate(evt);
            }
        });
        txtTongTienHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienHDActionPerformed(evt);
            }
        });

        jLabel36.setText("Tiền khách trả");

        jLabel38.setText("Tiền thừa");

        jLabel39.setText("Loại thanh toán");

        coboLoaiTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản" }));

        jLabel40.setText("Trạng thái");

        jLabel42.setText("Địa chỉ");

        txtDiaChiGiao.setColumns(20);
        txtDiaChiGiao.setRows(5);
        jScrollPane7.setViewportView(txtDiaChiGiao);

        btnThanhToan.setBackground(new java.awt.Color(205, 133, 63));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnTSP.setBackground(new java.awt.Color(229, 160, 74));
        btnTSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dollar.png"))); // NOI18N
        btnTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTSPActionPerformed(evt);
            }
        });

        txtTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ", "Hoàn thành" }));
        txtTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(btnThanhToan))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(txtTongTienHD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTongTienSP)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel39)
                                    .addComponent(coboLoaiTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel40)
                                            .addComponent(jLabel38)
                                            .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongTienSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTongTienHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTSP))
                .addGap(12, 12, 12)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(coboLoaiTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTaoSoHD.setText("Tạo HD");
        btnTaoSoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoSoHDActionPerformed(evt);
            }
        });

        btnXoaTrongTableOder.setText("Xóa TU");
        btnXoaTrongTableOder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrongTableOderActionPerformed(evt);
            }
        });

        btnResetOder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btnResetOder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetOderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBanHangLayout = new javax.swing.GroupLayout(pnlBanHang);
        pnlBanHang.setLayout(pnlBanHangLayout);
        pnlBanHangLayout.setHorizontalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBanHangLayout.createSequentialGroup()
                .addComponent(jpMenuTU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBanHangLayout.createSequentialGroup()
                        .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6)
                            .addGroup(pnlBanHangLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(33, Short.MAX_VALUE))
                    .addGroup(pnlBanHangLayout.createSequentialGroup()
                        .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTaoSoHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSoHD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnResetOder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoaTrongTableOder)
                        .addGap(42, 42, 42))))
        );
        pnlBanHangLayout.setVerticalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMenuTU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBanHangLayout.createSequentialGroup()
                        .addComponent(btnTaoSoHD)
                        .addGap(2, 2, 2)
                        .addComponent(txtSoHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBanHangLayout.createSequentialGroup()
                        .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaTrongTableOder)
                            .addComponent(btnResetOder))
                        .addGap(15, 15, 15)))
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCard.add(pnlBanHang, "card5");

        jPanel16.setBackground(new java.awt.Color(255, 255, 240));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trDaoKem.jpg"))); // NOI18N

        jLabel41.setText("Mã TU");

        jLabel43.setText("Tên TU");

        jLabel44.setText("GiaTU");

        jLabel45.setText("Size TU");

        jLabel46.setText("Mã Loại");

        coboMALOAI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TR", "CF", "TS" }));

        btnThemTU.setBackground(new java.awt.Color(205, 133, 63));
        btnThemTU.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemTU.setForeground(new java.awt.Color(255, 255, 255));
        btnThemTU.setText("THÊM");
        btnThemTU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTUActionPerformed(evt);
            }
        });

        btnCapNhatTU.setBackground(new java.awt.Color(205, 133, 63));
        btnCapNhatTU.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCapNhatTU.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhatTU.setText("CẬP NHẬT");
        btnCapNhatTU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatTUActionPerformed(evt);
            }
        });

        jLabel47.setText("Trạng thái");

        coboTRANGTHAI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang bán", "Ngừng bán" }));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel43)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTENTU)
                            .addComponent(txtGIATU)
                            .addComponent(txtSIZETU)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(txtMATU, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(coboTRANGTHAI, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(btnThemTU)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCapNhatTU)
                                .addGap(59, 59, 59))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(18, 18, 18)
                        .addComponent(coboMALOAI, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(139, 139, 139))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtMATU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(txtTENTU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtGIATU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txtSIZETU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addComponent(coboMALOAI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(coboTRANGTHAI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemTU)
                    .addComponent(btnCapNhatTU))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 240));

        jLabel48.setText("Tên sản phẩm");

        jLabel49.setText("Loại TU");

        coboBoxLoaiTU.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "TR", "TS", "CF" }));
        coboBoxLoaiTU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coboBoxLoaiTUActionPerformed(evt);
            }
        });

        jLabel50.setText("Trạng Thái");

        coboBoxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang bán", "Ngừng bán" }));
        coboBoxTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coboBoxTrangThaiActionPerformed(evt);
            }
        });

        tableThucUong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã TU", "Tên TU", "Giá TU", "Size TU", "Mã Loại", "Trạng thái"
            }
        ));
        tableThucUong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableThucUongMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tableThucUong);

        jScrollPane9.setViewportView(jScrollPane8);

        btnTimTU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        btnTimTU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimTUActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(txtTimKiemTU, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTimTU))
                            .addComponent(jLabel48))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(coboBoxLoaiTU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(coboBoxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(jLabel50))
                    .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiemTU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(coboBoxLoaiTU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(coboBoxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTimTU))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCard.add(pnlSanPham, "card8");

        tableKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Họ và Tên", "Ngày sinh", "Số điện thoại", "Điểm tích lũy"
            }
        ));
        tableKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKhachHangMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tableKhachHang);

        jPanel20.setBackground(new java.awt.Color(255, 255, 240));

        jLabel107.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel107.setText("Tìm kiếm khách hàng");

        btnTimKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        btnTimKH.setText("Tìm");
        btnTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTimKH)))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(btnTimKH)))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel107)
                .addGap(29, 29, 29)
                .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnTimKH)
                .addContainerGap(536, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 240));

        jLabel61.setText("Mã KH");

        jLabel62.setText("Tên khách hàng");

        jLabel75.setText("Ngày sinh");

        jLabel105.setText("Số điện thoại");

        jLabel106.setText("Điểm tích lũy");

        btnCapNhatKH.setBackground(new java.awt.Color(205, 133, 63));
        btnCapNhatKH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCapNhatKH.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhatKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit.png"))); // NOI18N
        btnCapNhatKH.setText("Cập nhật");
        btnCapNhatKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatKHActionPerformed(evt);
            }
        });

        btnLamMoiKH.setBackground(new java.awt.Color(205, 133, 63));
        btnLamMoiKH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLamMoiKH.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoiKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btnLamMoiKH.setText("Làm mới");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75))
                .addGap(56, 56, 56)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel105)
                    .addComponent(txtSoDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel106)
                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(btnCapNhatKH, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(346, 346, 346)
                .addComponent(btnLamMoiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jLabel62)
                    .addComponent(jLabel75)
                    .addComponent(jLabel105)
                    .addComponent(jLabel106))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnCapNhatKH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnLamMoiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pnlCard.add(pnlKhachHang, "card9");

        getContentPane().add(pnlCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 41, 1220, 700));

        jpMenu2.setBackground(new java.awt.Color(255, 255, 240));

        btnGiaoCa.setText("Giao ca");

        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        txtHoatDong.setText("Đang hoạt động bởi:");

        dateLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtDangHoatDong.setText("xxxxxx");
        txtDangHoatDong.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtDangHoatDongAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jpMenu2Layout = new javax.swing.GroupLayout(jpMenu2);
        jpMenu2.setLayout(jpMenu2Layout);
        jpMenu2Layout.setHorizontalGroup(
            jpMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMenu2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGiaoCa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDangXuat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHoatDong)
                .addGap(18, 18, 18)
                .addComponent(txtDangHoatDong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 639, Short.MAX_VALUE)
                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(578, 578, 578))
        );
        jpMenu2Layout.setVerticalGroup(
            jpMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMenu2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpMenu2Layout.createSequentialGroup()
                        .addGroup(jpMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGiaoCa)
                            .addComponent(btnDangXuat)
                            .addComponent(txtHoatDong)
                            .addComponent(txtDangHoatDong))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(jpMenu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 1760, -1));

        jpMenu.setBackground(new java.awt.Color(255, 255, 240));

        txtLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/yiqi.png"))); // NOI18N

        btnTrangChu.setBackground(new java.awt.Color(139, 119, 101));
        btnTrangChu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        btnTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.png"))); // NOI18N
        btnTrangChu.setText("Trang chủ");
        btnTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });

        btnBanHang.setBackground(new java.awt.Color(139, 119, 101));
        btnBanHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBanHang.setForeground(new java.awt.Color(255, 255, 255));
        btnBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cart.png"))); // NOI18N
        btnBanHang.setText("Bán hàng");
        btnBanHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        btnHoaDon.setBackground(new java.awt.Color(139, 119, 101));
        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bill.png"))); // NOI18N
        btnHoaDon.setText("Hóa đơn");
        btnHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });

        btnSanPham.setBackground(new java.awt.Color(139, 119, 101));
        btnSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tea-cup.png"))); // NOI18N
        btnSanPham.setText("Sản phẩm");
        btnSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnKhuyenMai.setBackground(new java.awt.Color(139, 119, 101));
        btnKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        btnKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/coupon.png"))); // NOI18N
        btnKhuyenMai.setText("Khuyến mại");
        btnKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhuyenMaiActionPerformed(evt);
            }
        });

        btnThongKe.setBackground(new java.awt.Color(139, 119, 101));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/increase.png"))); // NOI18N
        btnThongKe.setText("Thống kê");
        btnThongKe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        btnNhanVien.setBackground(new java.awt.Color(139, 119, 101));
        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/group.png"))); // NOI18N
        btnNhanVien.setText("Nhân viên");
        btnNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        btnKhachHang.setBackground(new java.awt.Color(139, 119, 101));
        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/group.png"))); // NOI18N
        btnKhachHang.setText("Khách hàng");
        btnKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpMenuLayout = new javax.swing.GroupLayout(jpMenu);
        jpMenu.setLayout(jpMenuLayout);
        jpMenuLayout.setHorizontalGroup(
            jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
            .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpMenuLayout.setVerticalGroup(
            jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        getContentPane().add(jpMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 740));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
private void updateDateTime() {
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
//            timeLabel.setText("Time: " + now.format(timeFormatter));
            dateLabel.setText(now.format(timeFormatter) +"  "+ now.format(dateFormatter));
        }
    });
    timer.start();
}
    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
        pnlCard.removeAll();
        pnlCard.add(pnlHoaDon);
        pnlCard.repaint();
        pnlCard.revalidate();
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnTraSuaVQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraSuaVQActionPerformed
        // TODO add your handling code here:
        String maTU = "TS03"; // Thay thế bằng mã thức uống thực tế trong cơ sở dữ liệu
// Lấy thông tin thức uống từ cơ sở dữ liệu thông qua ThucUongDAO
    ThucUong thucUong = null;
    try {
        thucUong = ThucUongDAO.getThucUongFromDB(maTU);
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi lấy thông tin thức uống: " + ex.getMessage());
        return;
    }   catch (Exception ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    if (thucUong != null) {
        // Thêm thông tin vào bảng tableOrder
        DefaultTableModel model = (DefaultTableModel) tableOder.getModel();
        double giaTU = Double.parseDouble(thucUong.getGiaTU());
        int soLuong = 1; 
        double tongTien = giaTU * soLuong; 

        model.addRow(new Object[]{
            thucUong.getMaTU(),
            thucUong.getTenTU(),
            giaTU,
            thucUong.getSizeTU(),
            soLuong, 
            tongTien 
        });
    } else {
        JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin thức uống.");
    }
    }//GEN-LAST:event_btnTraSuaVQActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        // TODO add your handling code here:
        pnlCard.removeAll();
        pnlCard.add(pnlTrangChu);
        pnlCard.repaint();
        pnlCard.revalidate();
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        // TODO add your handling code here:
        pnlCard.removeAll();
        pnlCard.add(pnlBanHang);
        pnlCard.repaint();
        pnlCard.revalidate();
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        // TODO add your handling code here:
        pnlCard.removeAll();
        pnlCard.add(pnlSanPham);
        pnlCard.repaint();
        pnlCard.revalidate();
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhuyenMaiActionPerformed
        // TODO add your handling code here:
        pnlCard.removeAll();
        pnlCard.add(pnlKhuyenMai);
        pnlCard.repaint();
        pnlCard.revalidate();
    }//GEN-LAST:event_btnKhuyenMaiActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
        pnlCard.removeAll();
        pnlCard.add(pnlThongKe);
        pnlCard.repaint();
        pnlCard.revalidate();
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        pnlCard.removeAll();
        pnlCard.add(pnlNhanVien);
        pnlCard.repaint();
        pnlCard.revalidate();
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
        pnlCard.removeAll();
        pnlCard.add(pnlKhachHang);
        pnlCard.repaint();
        pnlCard.revalidate();
    }//GEN-LAST:event_btnKhachHangActionPerformed
    
    private void showDulieu() throws Exception{
        try {
            tableNhanVien.removeAll();
            String[] arr = {"Mã NV", "Họ và tên","Số điện thoại", "Ngày sinh","cccd","Địa chỉ"};
            DefaultTableModel model = new DefaultTableModel(arr, 0);
            Connection connection = DBConnection.getConnection();
            String query = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[nhanVien]";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Vector vector = new Vector();
                vector.add(rs.getString("maNV"));
                vector.add(rs.getString("hoVaTen"));
                vector.add(rs.getString("soDT"));
                vector.add(rs.getString("ngaySinh"));
                vector.add(rs.getString("cccd"));
                vector.add(rs.getString("diaChi"));
                
                model.addRow(vector);
            }
            DBConnection.closeConnection(connection);
            tableNhanVien.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void showDulieuTU() throws Exception{
        try {
            tableThucUong.removeAll();
            String[] arr = {"Mã TU", "Tên TU","Giá TU", "Size TU","Mã loại","Trạng thái"};
            DefaultTableModel model = new DefaultTableModel(arr, 0);
            Connection connection = DBConnection.getConnection();
            String query = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[ThucUong]";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Vector vector = new Vector();
                vector.add(rs.getString("maTU"));
                vector.add(rs.getString("tenTU"));
                vector.add(rs.getInt("giaTU"));
                vector.add(rs.getString("sizeTU"));
                vector.add(rs.getString("maLoai"));
                vector.add(rs.getString("trangThai"));
                model.addRow(vector);
            }
            DBConnection.closeConnection(connection);
            tableThucUong.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showDulieuHD() throws Exception{
        try {
            tableLichSuHD.removeAll();
            String[] arr = {"Số Hóa đơn", "Tên khách hàng","Người tạo", "Thời gian tạo","Trạng thái","Tổng tiền"};
            DefaultTableModel model = new DefaultTableModel(arr, 0);
            Connection connection = DBConnection.getConnection();
            String query = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[HoaDon]";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Vector vector = new Vector();
                vector.add(rs.getInt("soHD"));
                vector.add(rs.getString("tenKH"));
                vector.add(rs.getString("tenDN"));
                vector.add(rs.getString("thoiGianTao"));
                vector.add(rs.getString("trangThai"));
                vector.add(rs.getFloat("tongTien"));
                model.addRow(vector);
            }
            DBConnection.closeConnection(connection);
            tableLichSuHD.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showDulieuKH() throws Exception{
        try {
            tableKhachHang.removeAll();
            String[] arr = {"Mã KH", "Tên KH","Ngày sinh", "Số điện thoại","Điểm tích lũy"};
            DefaultTableModel model = new DefaultTableModel(arr, 0);
            Connection connection = DBConnection.getConnection();
            String query = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[KhachHang]";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Vector vector = new Vector();
                vector.add(rs.getString("maKH"));
                vector.add(rs.getString("tenKH"));
                vector.add(rs.getString("ngaySinhKH"));
                vector.add(rs.getString("soDTKH"));
                vector.add(rs.getInt("tinhLuy"));
                
                model.addRow(vector);
            }
            DBConnection.closeConnection(connection);
            tableKhachHang.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    private void showDulieuChiTietHD() throws Exception{
//        try {
//            tableChiTietHD.removeAll();
//            String[] arr = {"Mã TU", "Tên TU","Giá TU", "Size TU","Số lượng"};
//            DefaultTableModel model = new DefaultTableModel(arr, 0);
//            Connection connection = DBConnection.getConnection();
//            String query = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[ChiTietHD]";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                Vector vector = new Vector();
//                vector.add(rs.getString("maTU"));
//                vector.add(rs.getString("tenTU"));
//                vector.add(rs.getInt("giaTU"));
//                vector.add(rs.getString("sizeTU"));
//                vector.add(rs.getInt("soLuong"));                
//                model.addRow(vector);
//            }
//            DBConnection.closeConnection(connection);
//            tableChiTietHD.setModel(model);
//        } catch (SQLException ex) {
//            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    
    private void tableNhanVienComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tableNhanVienComponentShown
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tableNhanVienComponentShown

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        try {
            // TODO add your handling code here:
            showDulieu();
        } catch (Exception ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            showDulieuTU();
        } catch (Exception ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            showDulieuKH();
        } catch (Exception ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            showDulieuHD();
        } catch (Exception ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            showDulieuChiTietHD();
//        } catch (Exception ex) {
//            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_formComponentShown
   
//        private boolean checkTrungMaNV(){
//        try{
//            Connection connection = DBConnection.getConnection();
//            String query = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[nhanVien] WHERE maNV = ?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1,txtMANV.getText());
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                return true;
//            }
//            DBConnection.closeConnection(connection);
//        }catch(SQLException ex){
//            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//        
//    private boolean checkTrungMaTU(){
//        try{
//            Connection connection = DBConnection.getConnection();
//            String query = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[ThucUong] WHERE maTU = ?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1,txtMATU.getText());
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                return true;
//            }
//            DBConnection.closeConnection(connection);
//        }catch(SQLException ex){
//            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//    private boolean checkTrungSizeTU(){
//        try{
//            Connection connection = DBConnection.getConnection();
//            String query = "SELECT TOP (1000)*FROM [QLChidori].[dbo].[ThucUong] WHERE sizeTU = ?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1,txtSIZETU.getText());
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                return true;
//            }
//            DBConnection.closeConnection(connection);
//        }catch(SQLException ex){
//            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
    
    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        if(txtMANV.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Mã nhân viên không được trống");
            txtMANV.requestFocus();
            return;
        }else if(txtHOTEN.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Họ tên nhân viên không được trống");
            txtHOTEN.requestFocus();
            return;
        }else if(txtSODT.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Số điện thoại không được trống");
            txtSODT.requestFocus();
            return;
        }else if(txtNGAYSINH.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ngày sinh không được trống");
            txtNGAYSINH.requestFocus();
            return;
        }else if(txtCanCuoc.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Căn cước công dân không được trống");
            txtCanCuoc.requestFocus();
            return;
        }else if(txtDIACHI.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Địa chỉ không được trống");
            txtDIACHI.requestFocus();
            return;
        }
        
        String manv = txtMANV.getText();
        if(manv.length()!=4){
        JOptionPane.showMessageDialog(null, "Mã nhân viên phải có 4 kí tự");
        txtMANV.requestFocus();
        return;
        
        }
                boolean b1 = Character.isLetter(manv.charAt(0));
                boolean b2 = Character.isLetter(manv.charAt(1));
                boolean b3 = Character.isDigit(manv.charAt(2));
                boolean b4 = Character.isDigit(manv.charAt(3));
                if((b1 && b2 && b3 && b4) == false){
                    JOptionPane.showMessageDialog(null, "Mã nhân viên không đúng định dạng. Mẫu NV01");
                    txtMANV.requestFocus();
                    return;
                }
        if(txtSODT.getText().matches("(\\+84|0)\\d{9,10}")==false){
            JOptionPane.showMessageDialog(null, "Số điện thoại chưa đúng dịnh dạng!");
            txtSODT.requestFocus();
            return;
        }
        
        else{
            try{
            NhanVien nv = new NhanVien();
            
            nv.setMaNV(txtMANV.getText());
            nv.setHoVaTen(txtHOTEN.getText());
            nv.setSoDT(txtSODT.getText());
            nv.setNgaySinh(txtNGAYSINH.getText());
            nv.setCccd(txtCanCuoc.getText());
            nv.setDiaChi(txtDIACHI.getText());
            
            if(nhanVienDAO.checkTrungMaNV(nv)==true){
            JOptionPane.showMessageDialog(null, "Mã nhân viên đã tồn tại");
            txtMANV.requestFocus();
            return;
            }
            if(nhanVienDAO.checkTrungCCCD(nv)==true){
            JOptionPane.showMessageDialog(null, "CCCD nhân viên đã tồn tại");
            txtCanCuoc.requestFocus();
            return;
            }
            if(nhanVienDAO.ThemNV(nv)){
                JOptionPane.showMessageDialog(null, "Thêm thành công");
            }else{
                JOptionPane.showMessageDialog(null, "Thêm thất bại!");
            }         
            showDulieu();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void loadNV(String maNV) throws Exception{
        
        try {
        tableNhanVien.removeAll();
        String[] arr = {"Mã NV", "Họ và tên", "Số điện thoại", "Ngày sinh", "cccd", "Địa chỉ"};
        DefaultTableModel model = new DefaultTableModel(arr, 0);
        Connection connection = DBConnection.getConnection();
        String query;
        PreparedStatement ps;

        if (maNV.equals("Tất cả nhân viên")) {
            query = "SELECT TOP 1000 * FROM [QLChidori].[dbo].[nhanVien]";
            ps = connection.prepareStatement(query);
        } else {
            query = "SELECT TOP 1000 * FROM [QLChidori].[dbo].[nhanVien] WHERE maNV = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, maNV);
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Vector<String> vector = new Vector<>();
            vector.add(rs.getString("maNV"));
            vector.add(rs.getString("hoVaTen"));
            vector.add(rs.getString("soDT"));
            vector.add(rs.getString("ngaySinh"));
            vector.add(rs.getString("cccd"));
            vector.add(rs.getString("diaChi"));

            model.addRow(vector);
        }
        tableNhanVien.setModel(model);
        rs.close();
        ps.close();
        DBConnection.closeConnection(connection);
    } catch (SQLException ex) {
        Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    private void loadKH(String tenKH) throws Exception{
        
        try {
        tableKhachHang.removeAll();
        String[] arr = {"Mã KH", "Tên KH", "Ngày sinh", "Số điện thoại", "Tích Lũy"};
        DefaultTableModel model = new DefaultTableModel(arr, 0);
        Connection connection = DBConnection.getConnection();
        String query;
        PreparedStatement ps;

        if (tenKH.equals("Tất cả khách hàng")) {
            query = "SELECT TOP 1000 * FROM [QLChidori].[dbo].[KhachHang]";
            ps = connection.prepareStatement(query);
        } else {
            query = "SELECT TOP 1000 * FROM [QLChidori].[dbo].[nhanVien] WHERE tenKH = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, tenKH);
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Vector<String> vector = new Vector<>();
            vector.add(rs.getString("maKH"));
            vector.add(rs.getString("tenKH"));
            vector.add(rs.getString("ngaySinhKH"));
            vector.add(rs.getString("soDTKH"));
            vector.add(rs.getString("tinhLuy"));
            

            model.addRow(vector);
        }
        tableKhachHang.setModel(model);
        rs.close();
        ps.close();
        DBConnection.closeConnection(connection);
    } catch (SQLException ex) {
        Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    private void coboBoxNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coboBoxNVActionPerformed
        // TODO add your handling code here:
        String selectedMaNV = (String) coboBoxNV.getSelectedItem();
    if (selectedMaNV != null) {
            try {
                loadNV(selectedMaNV);
            } catch (Exception ex) {
                Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }//GEN-LAST:event_coboBoxNVActionPerformed

    private void txtSODTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSODTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSODTActionPerformed

    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    int position = tableNhanVien.getSelectedRow();
                    if (position >= 0) {
                        String maNV = tableNhanVien.getModel().getValueAt(position, 0).toString();
                        try {
                            boolean success = NhanVienDAO.xoaNhanVien(maNV);
                            if (success) {
                                loadNV("Tất cả nhân viên"); // Refresh the table
                                JOptionPane.showMessageDialog(null, "Xóa thành công");
                            } else {
                                JOptionPane.showMessageDialog(null, "Xóa không thành công");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(this, "Xóa không thành công: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(this, "Error loading data", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để xóa");
                    }
                }
    }//GEN-LAST:event_btnXoaNVActionPerformed

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        // TODO add your handling code here:
 int position = tableNhanVien.getSelectedRow();
    if (position == -1) {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để sửa");
        return;
    }

    // Lấy mã nhân viên từ bảng
    String originalMaNV = (String) tableNhanVien.getValueAt(position, 0);

    // Kiểm tra xem mã nhân viên có bị sửa đổi không
    if (!txtMANV.getText().equals(originalMaNV)) {
        JOptionPane.showMessageDialog(null, "Mã nhân viên không được phép sửa đổi");
        txtMANV.setText(originalMaNV); // Đặt lại mã nhân viên ban đầu
        txtMANV.requestFocus();
        return;
    }

    if(txtHOTEN.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Họ tên nhân viên không được trống");
        txtHOTEN.requestFocus();
        return;
    } else if(txtSODT.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Số điện thoại không được trống");
        txtSODT.requestFocus();
        return;
    } else if(txtNGAYSINH.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Ngày sinh không được trống");
        txtNGAYSINH.requestFocus();
        return;
    } else if(txtCanCuoc.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Căn cước công dân không được trống");
        txtCanCuoc.requestFocus();
        return;
    } else if(txtDIACHI.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Địa chỉ không được trống");
        txtDIACHI.requestFocus();
        return;
    }

    if (!txtSODT.getText().matches("(\\+84|0)\\d{9,10}")) {
        JOptionPane.showMessageDialog(null, "Số điện thoại chưa đúng định dạng!");
        txtSODT.requestFocus();
        return;
    }

    NhanVien nv = new NhanVien();
    nv.setMaNV(txtMANV.getText());
    nv.setHoVaTen(txtHOTEN.getText());
    nv.setSoDT(txtSODT.getText());
    nv.setNgaySinh(txtNGAYSINH.getText());
    nv.setCccd(txtCanCuoc.getText());
    nv.setDiaChi(txtDIACHI.getText());

    try {
        boolean success = NhanVienDAO.suaNhanVien(nv);
        if (success) {
            try {
                showDulieu(); // Refresh the table data
            } catch (Exception ex) {
                Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Sửa không thành công");
        }
    } catch (SQLException ex) {
        Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Sửa không thành công: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }   catch (Exception ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSuaNVActionPerformed

    private void tableNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhanVienMouseClicked
        // TODO add your handling code here:
        int selectedRow = tableNhanVien.getSelectedRow();

        // Kiểm tra nếu có hàng được chọn
        if (selectedRow != -1) {
            // Cập nhật các trường văn bản với dữ liệu từ hàng được chọn
            txtMANV.setText(tableNhanVien.getValueAt(selectedRow, 0).toString());
            txtHOTEN.setText(tableNhanVien.getValueAt(selectedRow, 1).toString());
            txtSODT.setText(tableNhanVien.getValueAt(selectedRow, 2).toString());
            txtNGAYSINH.setText(tableNhanVien.getValueAt(selectedRow, 3).toString());
            txtCanCuoc.setText(tableNhanVien.getValueAt(selectedRow, 4).toString());
            txtDIACHI.setText(tableNhanVien.getValueAt(selectedRow, 5).toString());
        }
    }//GEN-LAST:event_tableNhanVienMouseClicked

    private void btnLamMoiNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiNVActionPerformed
        // TODO add your handling code here:
        txtMANV.setText("");
        txtHOTEN.setText("");
        txtNGAYSINH.setText("");
        txtSODT.setText("");
        txtCanCuoc.setText("");
        txtDIACHI.setText("");
        
    }//GEN-LAST:event_btnLamMoiNVActionPerformed

    private void txtTimKiemMNVFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemMNVFocusGained
        // TODO add your handling code here:
        if (txtTimKiemMNV.getText().equals("Tìm kiếm bằng mã...")) {
        txtTimKiemMNV.setText("");
        txtTimKiemMNV.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtTimKiemMNVFocusGained

    private void txtTimKiemMNVFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemMNVFocusLost
        // TODO add your handling code here:
        if (txtTimKiemMNV.getText().isEmpty()) {
        txtTimKiemMNV.setForeground(Color.GRAY);
        txtTimKiemMNV.setText("Tìm kiếm bằng mã...");
    }
    }//GEN-LAST:event_txtTimKiemMNVFocusLost

    private void txtTimKiemTenNVFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemTenNVFocusGained
        if (txtTimKiemTenNV.getText().equals("Tìm kiếm bằng tên...")) {
        txtTimKiemTenNV.setText("");
        txtTimKiemTenNV.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtTimKiemTenNVFocusGained

    private void txtTimKiemTenNVFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemTenNVFocusLost
        // TODO add your handling code here:
        if(txtTimKiemTenNV.getText().isEmpty()){
            txtTimKiemTenNV.setForeground(Color.GRAY);
            txtTimKiemTenNV.setText("Tìm kiếm bằng tên...");
        }
    }//GEN-LAST:event_txtTimKiemTenNVFocusLost

    private void txtTimKiemSTDNVFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemSTDNVFocusGained
        // TODO add your handling code here:
        if (txtTimKiemSTDNV.getText().equals("Tìm kiếm bằng số điện thoại...")) {
        txtTimKiemSTDNV.setText("");
        txtTimKiemSTDNV.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtTimKiemSTDNVFocusGained

    private void txtTimKiemSTDNVFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemSTDNVFocusLost
        // TODO add your handling code here:
        if(txtTimKiemSTDNV.getText().isEmpty()){
            txtTimKiemSTDNV.setForeground(Color.GRAY);
            txtTimKiemSTDNV.setText("Tìm kiếm bằng số điện thoại...");
        }
    }//GEN-LAST:event_txtTimKiemSTDNVFocusLost

    private void txtTimKiemNgaySNVFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemNgaySNVFocusGained
        // TODO add your handling code here:
        if (txtTimKiemNgaySNV.getText().equals("Tìm kiếm bằng ngày sinh...")) {
        txtTimKiemNgaySNV.setText("");
        txtTimKiemNgaySNV.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtTimKiemNgaySNVFocusGained

    private void txtTimKiemNgaySNVFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemNgaySNVFocusLost
        // TODO add your handling code here:
        if(txtTimKiemNgaySNV.getText().isEmpty()){
            txtTimKiemNgaySNV.setForeground(Color.GRAY);
            txtTimKiemNgaySNV.setText("Tìm kiếm bằng ngày sinh...");
        }
    }//GEN-LAST:event_txtTimKiemNgaySNVFocusLost

    private void txtTimKiemCCCDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemCCCDFocusGained
        // TODO add your handling code here:
        if (txtTimKiemCCCD.getText().equals("Tìm kiếm bằng căn cước...")) {
        txtTimKiemCCCD.setText("");
        txtTimKiemCCCD.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtTimKiemCCCDFocusGained

    private void txtTimKiemCCCDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemCCCDFocusLost
        // TODO add your handling code here:
        if(txtTimKiemCCCD.getText().isEmpty()){
            txtTimKiemCCCD.setForeground(Color.GRAY);
            txtTimKiemCCCD.setText("Tìm kiếm bằng căn cước...");
        }
    }//GEN-LAST:event_txtTimKiemCCCDFocusLost

    private void txtTimKiemDiaChiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemDiaChiFocusGained
        // TODO add your handling code here:
        if (txtTimKiemDiaChi.getText().equals("Tìm kiếm bằng địa chỉ...")) {
        txtTimKiemDiaChi.setText("");
        txtTimKiemDiaChi.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtTimKiemDiaChiFocusGained

    private void txtTimKiemDiaChiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemDiaChiFocusLost
        // TODO add your handling code here:
        if(txtTimKiemDiaChi.getText().isEmpty()){
            txtTimKiemDiaChi.setForeground(Color.GRAY);
            txtTimKiemDiaChi.setText("Tìm kiếm bằng địa chỉ...");
        }
    }//GEN-LAST:event_txtTimKiemDiaChiFocusLost
 //   Tìm kiếm nhân viên
//    private void searchEmployee(String maNV, String hoVaTen, String soDT, String ngaySinh, String cccd, String diaChi) {
//        DefaultTableModel model = (DefaultTableModel) tableNhanVien.getModel();
//        model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng
//
//        try {
//            Connection connection = DBConnection.getConnection();
//            StringBuilder query = new StringBuilder("SELECT * FROM dbo.[NhanVien] WHERE 1=1");
//
//            if (!maNV.isEmpty() && !maNV.equals("Tìm kiếm bằng mã...")) {
//                query.append(" AND maNV = ?");
//            }
//            if (!hoVaTen.isEmpty() && !hoVaTen.equals("Tìm kiếm bằng tên...")) {
//                query.append(" AND hoVaTen LIKE ?");
//            }
//            if (!soDT.isEmpty() && !soDT.equals("Tìm kiếm bằng số điện thoại...")) {
//                query.append(" AND soDT = ?");
//            }
//            if (!ngaySinh.isEmpty() && !ngaySinh.equals("Tìm kiếm bằng ngày sinh...")) {
//                query.append(" AND ngaySinh = ?");
//            }
//            if (!cccd.isEmpty() && !cccd.equals("Tìm kiếm bằng căn cước...")) {
//                query.append(" AND cccd = ?");
//            }
//            if (!diaChi.isEmpty() && !diaChi.equals("Tìm kiếm bằng địa chỉ...")) {
//                query.append(" AND diaChi LIKE ?");
//            }
//
//            PreparedStatement ps = connection.prepareStatement(query.toString());
//            int paramIndex = 1;
//
//            if (!maNV.isEmpty() && !maNV.equals("Tìm kiếm bằng mã...")) {
//                ps.setString(paramIndex++, maNV);
//            }
//            if (!hoVaTen.isEmpty() && !hoVaTen.equals("Tìm kiếm bằng tên...")) {
//                ps.setString(paramIndex++, "%" + hoVaTen + "%");
//            }
//            if (!soDT.isEmpty() && !soDT.equals("Tìm kiếm bằng số điện thoại...")) {
//                ps.setString(paramIndex++, soDT);
//            }
//            if (!ngaySinh.isEmpty() && !ngaySinh.equals("Tìm kiếm bằng ngày sinh...")) {
//                ps.setString(paramIndex++, ngaySinh);
//            }
//            if (!cccd.isEmpty() && !cccd.equals("Tìm kiếm bằng căn cước...")) {
//                ps.setString(paramIndex++, cccd);
//            }
//            if (!diaChi.isEmpty() && !diaChi.equals("Tìm kiếm bằng địa chỉ...")) {
//                ps.setString(paramIndex++, "%" + diaChi + "%");
//            }
//
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                String ma = rs.getString("maNV");
//                String ten = rs.getString("hoVaTen");
//                String sdt = rs.getString("soDT");
//                String ns = rs.getString("ngaySinh");
//                String cc = rs.getString("cccd");
//                String dc = rs.getString("diaChi");
//                model.addRow(new Object[]{ma, ten, sdt, ns, cc, dc});
//            }
//            
//            if (model.getRowCount() == 0) {
//            JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên nào phù hợp");
//        }
//            
//            rs.close();
//            ps.close();
//            DBConnection.closeConnection(connection);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm: " + ex.getMessage());
//        }
//}
    private void btnTimKiemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemNVActionPerformed
        String maNV = txtTimKiemMNV.getText().trim();
    String hoVaTen = txtTimKiemTenNV.getText().trim();
    String soDT = txtTimKiemSTDNV.getText().trim();
    String ngaySinh = txtTimKiemNgaySNV.getText().trim();
    String cccd = txtTimKiemCCCD.getText().trim();
    String diaChi = txtTimKiemDiaChi.getText().trim();
    
    if ((maNV.isEmpty() || maNV.equals("Tìm kiếm bằng mã...")) &&
        (hoVaTen.isEmpty() || hoVaTen.equals("Tìm kiếm bằng tên...")) &&
        (soDT.isEmpty() || soDT.equals("Tìm kiếm bằng số điện thoại...")) &&
        (ngaySinh.isEmpty() || ngaySinh.equals("Tìm kiếm bằng ngày sinh...")) &&
        (cccd.isEmpty() || cccd.equals("Tìm kiếm bằng căn cước...")) &&
        (diaChi.isEmpty() || diaChi.equals("Tìm kiếm bằng địa chỉ..."))) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập ít nhất một tiêu chí để tìm kiếm");
        txtTimKiemMNV.requestFocus();
        return;
    }
    
    DefaultTableModel model = (DefaultTableModel) tableNhanVien.getModel();
    model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

    try {
        ArrayList<NhanVien> nhanVienList = NhanVienDAO.timKiemNhanVien(maNV, hoVaTen, soDT, ngaySinh, cccd, diaChi);
        for (NhanVien nv : nhanVienList) {
            model.addRow(new Object[]{nv.getMaNV(), nv.getHoVaTen(), nv.getSoDT(), nv.getNgaySinh(), nv.getCccd(), nv.getDiaChi()});
        }
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên nào phù hợp");
        }
    } catch (SQLException ex) {
        Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }   catch (Exception ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTimKiemNVActionPerformed

    private void txtResetTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResetTimKiemActionPerformed
        // TODO add your handling code here:
        txtTimKiemMNV.setText("Tìm kiếm bằng mã...");
        txtTimKiemMNV.setForeground(Color.GRAY);
        txtTimKiemTenNV.setText("Tìm kiếm bằng tên...");
        txtTimKiemTenNV.setForeground(Color.GRAY);
        txtTimKiemSTDNV.setText("Tìm kiếm bằng số điện thoại...");
        txtTimKiemSTDNV.setForeground(Color.GRAY);
        txtTimKiemNgaySNV.setText("Tìm kiếm bằng ngày sinh...");
        txtTimKiemNgaySNV.setForeground(Color.GRAY);
        txtTimKiemCCCD.setText("Tìm kiếm bằng căn cước...");
        txtTimKiemCCCD.setForeground(Color.GRAY);
        txtTimKiemDiaChi.setText("Tìm kiếm bằng địa chỉ...");
        txtTimKiemDiaChi.setForeground(Color.GRAY);
    }//GEN-LAST:event_txtResetTimKiemActionPerformed

    private void btnThemTUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTUActionPerformed
        // TODO add your handling code here:
//        if(txtMATU.getText().equals("")){
//            JOptionPane.showMessageDialog(null, "Mã thức uống không được trống");
//            txtMATU.requestFocus();
//            return;
//        }else if(txtTENTU.getText().equals("")){
//            JOptionPane.showMessageDialog(null, "Tên thức uống không được trống");
//            txtTENTU.requestFocus();
//            return;
//        }else if(txtGIATU.getText().equals("")){
//            JOptionPane.showMessageDialog(null, "Giá không được trống");
//            txtGIATU.requestFocus();
//            return;
//        }else if(txtSIZETU.getText().equals("")){
//            JOptionPane.showMessageDialog(null, "Size không được trống");
//            txtSIZETU.requestFocus();
//            return;
//        }else if(coboMALOAI.getSelectedItem() == null){
//            
//            coboMALOAI.requestFocus();
//            return;
//        }else if(coboTRANGTHAI.getSelectedItem() == null){
//            
//            coboTRANGTHAI.requestFocus();
//            return;
//        }
//        
//        
//        String matu = txtMATU.getText();
//        if(matu.length()!=4){
//        JOptionPane.showMessageDialog(null, "Mã thức uống phải có 4 kí tự");
//        txtMATU.requestFocus();
//        return;
//        
//        }
//                boolean b1 = Character.isLetter(matu.charAt(0));
//                boolean b2 = Character.isLetter(matu.charAt(1));
//                boolean b3 = Character.isDigit(matu.charAt(2));
//                boolean b4 = Character.isDigit(matu.charAt(3));
//                if((b1 && b2 && b3 && b4) == false){
//                    JOptionPane.showMessageDialog(null, "Mã thức uống không đúng định dạng. Mẫu TR01");
//                    txtMANV.requestFocus();
//                    return;
//                }
//        
//        if(checkTrungMaTU()==true){
//            JOptionPane.showMessageDialog(null, "Mã thức uống đã tồn tại");
//            txtMATU.requestFocus();
//            return;
//        }else if(checkTrungSizeTU()==true){
//            JOptionPane.showMessageDialog(null, "sizeTU thức uống đã tồn tại");
//            txtSIZETU.requestFocus();
//            return;
//        }else{
//            try{
//             Connection connection = DBConnection.getConnection();
//            String query = "INSERT INTO dbo.[ThucUong](maTU,tenTU,giaTU,sizeTU,maLoai,trangThai)"
//                    +"VALUES(?,?,?,?,?,?)";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1,txtMATU.getText());
//            ps.setString(2,txtTENTU.getText());
//            ps.setString(3,txtGIATU.getText());
//            ps.setString(4,txtSIZETU.getText());
//            ps.setString(5,coboMALOAI.getSelectedItem().toString());
//            ps.setString(6,coboTRANGTHAI.getSelectedItem().toString());
//            ps.executeUpdate();
//            showDulieuTU();
//            DBConnection.closeConnection(connection);
//            JOptionPane.showMessageDialog(null, "Thêm thành công");
//            }catch(SQLException ex){
//                ex.printStackTrace();
//            }
//        }
    String maTU = txtMATU.getText().trim();
    String tenTU = txtTENTU.getText().trim();
    String giaTU = txtGIATU.getText().trim();
    String sizeTU = txtSIZETU.getText().trim();
    String maLoai = coboMALOAI.getSelectedItem().toString();
    String trangThai = coboTRANGTHAI.getSelectedItem().toString();
    
        try {
            // Gọi phương thức themThucUong từ ThucUongDAO để thêm thức uống
            
            if(ThucUongDAO.themThucUong(maTU, tenTU, giaTU, sizeTU, maLoai, trangThai)) {
                showDulieuTU(); // Hiển thị lại dữ liệu sau khi thêm thành công
                JOptionPane.showMessageDialog(null, "Thêm thành công");
            }   } catch (Exception ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemTUActionPerformed

    private void btnCapNhatTUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatTUActionPerformed
        // TODO add your handling code here:
//            int position = tableThucUong.getSelectedRow();
//        if (position == -1) {
//            JOptionPane.showMessageDialog(null, "Vui lòng chọn một thức uống để sửa");
//            return;
//        }
//
//        // Lấy mã nhân viên từ bảng
//        String originalMaTU = (String) tableThucUong.getValueAt(position, 0);
//
//        // Kiểm tra xem mã nhân viên có bị sửa đổi không
//        if (!txtMATU.getText().equals(originalMaTU)) {
//            JOptionPane.showMessageDialog(null, "Mã thức uống không được phép sửa đổi");
//            txtMATU.setText(originalMaTU); // Đặt lại mã nhân viên ban đầu
//            txtMATU.requestFocus();
//            return;
//        }
//
//        if(txtTENTU.getText().equals("")){
//            JOptionPane.showMessageDialog(null, "Tên thức uống không được trống");
//            txtTENTU.requestFocus();
//            return;
//        } else if(txtGIATU.getText().equals("")){
//            JOptionPane.showMessageDialog(null, "Giá không được trống");
//            txtGIATU.requestFocus();
//            return;
//        } else if(txtSIZETU.getText().equals("")){
//            JOptionPane.showMessageDialog(null, "Size không được trống");
//            txtSIZETU.requestFocus();
//            return;
//        } else if(coboMALOAI.getSelectedItem() == null){
//            
//            coboMALOAI.requestFocus();
//            return;
//        } else if(coboTRANGTHAI.getSelectedItem() == null){
//            
//            coboTRANGTHAI.requestFocus();
//            return;
//        }
//
//        try {
//            Connection connection = DBConnection.getConnection();
//            String query = "UPDATE dbo.[ThucUong] SET tenTU = ?, giaTU = ?, sizeTU = ?, maLoai = ?, trangThai = ? WHERE maTU = ?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, txtTENTU.getText());
//            ps.setString(2, txtGIATU.getText());
//            ps.setString(3, txtSIZETU.getText());
//            ps.setString(4, coboMALOAI.getSelectedItem().toString());
//            ps.setString(5, coboTRANGTHAI.getSelectedItem().toString());
//            ps.setString(6, txtMATU.getText());
//            ps.executeUpdate();
//            showDulieuTU();
//            DBConnection.closeConnection(connection);
//            JOptionPane.showMessageDialog(null, "Sửa thành công");
//        } catch(SQLException ex){
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Sửa không thành công: " + ex.getMessage());
//        }
    String maTU = txtMATU.getText().trim();
    String tenTU = txtTENTU.getText().trim();
    String giaTU = txtGIATU.getText().trim();
    String sizeTU = txtSIZETU.getText().trim();
    String maLoai = coboMALOAI.getSelectedItem().toString();
    String trangThai = coboTRANGTHAI.getSelectedItem().toString();
    
    try {
        int position = tableThucUong.getSelectedRow();
        if (position == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một thức uống để sửa");
            return;
        }

        String originalMaTU = (String) tableThucUong.getValueAt(position, 0);
        String originalSizeTU = (String) tableThucUong.getValueAt(position, 3);
        if (!maTU.equals(originalMaTU)) {
            JOptionPane.showMessageDialog(null, "Mã thức uống không được phép sửa đổi");
            txtMATU.setText(originalMaTU);
            txtMATU.requestFocus();
            return;
        }
        // Kiểm tra trùng size thức uống
        if (!sizeTU.equals(originalSizeTU)) {
            JOptionPane.showMessageDialog(null, "Size thức uống không được phép sửa đổi");
            txtSIZETU.setText(originalSizeTU);
            txtSIZETU.requestFocus();
            return;
        }

        if (ThucUongDAO.capNhatThucUong(maTU, tenTU, giaTU, sizeTU, maLoai, trangThai)) {
            showDulieuTU();
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        }
    } catch (Exception ex) {
        Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btnCapNhatTUActionPerformed

    private void tableThucUongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableThucUongMouseClicked
        // TODO add your handling code here:
        int selectedRow = tableThucUong.getSelectedRow();

        // Kiểm tra nếu có hàng được chọn
        if (selectedRow != -1) {
            // Cập nhật các trường văn bản với dữ liệu từ hàng được chọn
            txtMATU.setText(tableThucUong.getValueAt(selectedRow, 0).toString());
            txtTENTU.setText(tableThucUong.getValueAt(selectedRow, 1).toString());
            txtGIATU.setText(tableThucUong.getValueAt(selectedRow, 2).toString());
            txtSIZETU.setText(tableThucUong.getValueAt(selectedRow, 3).toString());
            coboMALOAI.setSelectedItem(tableThucUong.getValueAt(selectedRow, 4).toString());
            coboTRANGTHAI.setSelectedItem(tableThucUong.getValueAt(selectedRow, 5).toString());
        }
    }//GEN-LAST:event_tableThucUongMouseClicked
//tìm kiếm thức uống
    private void btnTimTUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimTUActionPerformed
         String tenTU = txtTimKiemTU.getText().trim();

    if (tenTU.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập tên thức uống để tìm kiếm");
        txtTimKiemTU.requestFocus();
        return;
    }

    try {
        ArrayList<ThucUong> resultList = ThucUongDAO.timKiemThucUongTheoTenTU(tenTU);
        if (resultList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy thức uống nào phù hợp");
        } else {
            // Hiển thị kết quả tìm kiếm trên giao diện người dùng
            DefaultTableModel model = (DefaultTableModel) tableThucUong.getModel();
            model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

            for (ThucUong tu : resultList) {
                model.addRow(new Object[]{tu.getMaTU(), tu.getTenTU(), tu.getGiaTU(), tu.getSizeTU(), tu.getMaLoai(), tu.getTrangThai()});
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnTimTUActionPerformed

    private void coboBoxLoaiTUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coboBoxLoaiTUActionPerformed
         // Lấy mã loại được chọn từ combobox
    String maLoai = (String) coboBoxLoaiTU.getSelectedItem();

    // Kiểm tra nếu người dùng đã chọn "Tất cả"
    if (maLoai.equals("Tất cả")) {
        // Nếu chọn "Tất cả", hiển thị tất cả danh sách thức uống
        try {
            ArrayList<ThucUong> allThucUong = ThucUongDAO.layTatCaThucUong();
            hienThiDanhSachThucUong(allThucUong);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }
    } else {
        // Nếu không chọn "Tất cả", thực hiện tìm kiếm thức uống theo loại
        try {
            ArrayList<ThucUong> resultList = ThucUongDAO.timKiemThucUongTheoMaLoai(maLoai);
            hienThiDanhSachThucUong(resultList);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm: " + ex.getMessage());
        }
    }
    }//GEN-LAST:event_coboBoxLoaiTUActionPerformed

    private void coboBoxTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coboBoxTrangThaiActionPerformed
        String trangThai = (String) coboBoxTrangThai.getSelectedItem();

    // Kiểm tra nếu người dùng đã chọn "Tất cả"
    if (trangThai.equals("Tất cả")) {
        // Nếu chọn "Tất cả", hiển thị tất cả danh sách thức uống
        try {
            ArrayList<ThucUong> allThucUong = ThucUongDAO.layTatCaThucUong();
            hienThiDanhSachThucUong(allThucUong);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }
    } else {
        // Nếu không chọn "Tất cả", thực hiện tìm kiếm thức uống theo loại
        try {
            ArrayList<ThucUong> resultList = ThucUongDAO.timKiemThucUongTheoTrangThai(trangThai);
            hienThiDanhSachThucUong(resultList);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm: " + ex.getMessage());
        }
    }
    }//GEN-LAST:event_coboBoxTrangThaiActionPerformed
private void hienThiDanhSachThucUong(ArrayList<ThucUong> thucUongList) {
    DefaultTableModel model = (DefaultTableModel) tableThucUong.getModel();
    model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

    for (ThucUong tu : thucUongList) {
        model.addRow(new Object[]{tu.getMaTU(), tu.getTenTU(), tu.getGiaTU(), tu.getSizeTU(), tu.getMaLoai(), tu.getTrangThai()});
    }
}
    
    
//    private ThucUong getThucUongFromDB(String maTU) {
//    ThucUong thucUong = null;
//    try {
//        Connection connection = DBConnection.getConnection();
//        String query = "SELECT * FROM dbo.[ThucUong] WHERE maTU = ?";
//        PreparedStatement ps = connection.prepareStatement(query);
//        ps.setString(1, maTU);
//        ResultSet rs = ps.executeQuery();
//        
//        if (rs.next()) {
//            String tenTU = rs.getString("tenTU");
//            String giaTU = rs.getString("giaTU");
//            String sizeTU = rs.getString("sizeTU");
//            String maLoai = rs.getString("maLoai");
//            String trangThai = rs.getString("trangThai");
//            thucUong = new ThucUong(maTU, tenTU, giaTU, sizeTU, maLoai, trangThai);
//        }
//        
//        rs.close();
//        ps.close();
//        DBConnection.closeConnection(connection);
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//        JOptionPane.showMessageDialog(null, "Lỗi khi lấy thông tin từ cơ sở dữ liệu: " + ex.getMessage());
//    }
//    return thucUong;
//}

    private void btnTraSuaDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraSuaDauActionPerformed
        // TODO add your handling code here:
    ThemVAO("TS01");
    }//GEN-LAST:event_btnTraSuaDauActionPerformed

    private void btnTSDuongDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTSDuongDenActionPerformed
        // TODO add your handling code here:
        ThemVAO("TS05");
    }//GEN-LAST:event_btnTSDuongDenActionPerformed

    private void btnTSsoColaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTSsoColaActionPerformed
        // TODO add your handling code here:
        String maTU = "TS07"; // Thay thế bằng mã thức uống thực tế trong cơ sở dữ liệu
//
// Lấy thông tin thức uống từ cơ sở dữ liệu thông qua ThucUongDAO
    ThucUong thucUong = null;
    try {
        thucUong = ThucUongDAO.getThucUongFromDB(maTU);
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi lấy thông tin thức uống: " + ex.getMessage());
        return;
    }   catch (Exception ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    if (thucUong != null) {
        // Thêm thông tin vào bảng tableOrder
        DefaultTableModel model = (DefaultTableModel) tableOder.getModel();
        double giaTU = Double.parseDouble(thucUong.getGiaTU());
        int soLuong = 1; 
        double tongTien = giaTU * soLuong; 

        model.addRow(new Object[]{
            thucUong.getMaTU(),
            thucUong.getTenTU(),
            giaTU,
            thucUong.getSizeTU(),
            soLuong, 
            tongTien 
        });
    } else {
        JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin thức uống.");
    }
    }//GEN-LAST:event_btnTSsoColaActionPerformed

    private void btnTRxoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTRxoaiActionPerformed
        // TODO add your handling code here:
        ThemVAO("TR01");
    }//GEN-LAST:event_btnTRxoaiActionPerformed

    private void btnTRduaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTRduaActionPerformed
        // TODO add your handling code here:
       ThemVAO("TR02");
    }//GEN-LAST:event_btnTRduaActionPerformed

    private void btnTRnhietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTRnhietActionPerformed
        // TODO add your handling code here:
        ThemVAO("TR03");
    }//GEN-LAST:event_btnTRnhietActionPerformed

    private void btnTRdaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTRdaoActionPerformed
        // TODO add your handling code here:
        ThemVAO("TR04");
    }//GEN-LAST:event_btnTRdaoActionPerformed

    private void btnCFsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCFsuaActionPerformed
        // TODO add your handling code here:
       ThemVAO("CF01");
    }//GEN-LAST:event_btnCFsuaActionPerformed
    private void ThemVAO(String maTU){
        // Lấy thông tin thức uống từ cơ sở dữ liệu thông qua ThucUongDAO
    ThucUong thucUong = null;
    try {
        thucUong = ThucUongDAO.getThucUongFromDB(maTU);
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi lấy thông tin thức uống: " + ex.getMessage());
        return;
    }   catch (Exception ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    if (thucUong != null) {
        // Thêm thông tin vào bảng tableOrder
        DefaultTableModel model = (DefaultTableModel) tableOder.getModel();
        double giaTU = Double.parseDouble(thucUong.getGiaTU());
        int soLuong = 1; 
        double tongTien = giaTU * soLuong; 

        model.addRow(new Object[]{
            thucUong.getMaTU(),
            thucUong.getTenTU(),
            giaTU,
            thucUong.getSizeTU(),
            soLuong, 
            tongTien 
        });
    } else {
        JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin thức uống.");
    }
    }
    private void btnCFdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCFdenActionPerformed
        // TODO add your handling code here:        
        ThemVAO("CF02");
    }//GEN-LAST:event_btnCFdenActionPerformed

    private void btnCFduaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCFduaActionPerformed
        // TODO add your handling code here:
        ThemVAO("CF03");
    }//GEN-LAST:event_btnCFduaActionPerformed

    private void txtTongTienSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienSPActionPerformed
        // TODO add your handling code here:
        updateTotalAmount();
        
    }//GEN-LAST:event_txtTongTienSPActionPerformed

    private void btnTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTSPActionPerformed
        // TODO add your handling code here:
        updateTotalAmount();
    }//GEN-LAST:event_btnTSPActionPerformed

    private void txtTongTienHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienHDActionPerformed
        // TODO add your handling code here:
        updateTotalAmount();
    }//GEN-LAST:event_txtTongTienHDActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            dispose();
            DangNhapAD dangnhap = new DangNhapAD();
            dangnhap.setVisible(true);
        }
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void txtTimKiemTenNVCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemTenNVCaretUpdate
        // TODO add your handling code here:
     
    }//GEN-LAST:event_txtTimKiemTenNVCaretUpdate

    private void btnTimSDTKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSDTKHActionPerformed
        // TODO add your handling code here:
        String soDTKH = txtTimSoDTKH.getText().trim();

    if (soDTKH.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại để tìm kiếm");
        txtTimSoDTKH.requestFocus();
        return;
    }

    try {
        ArrayList<KhachHang> resultList = KhachHangDAO.timKiemKhachHangTheoSDT(soDTKH);
        if (resultList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng nào phù hợp");
        } else {
            
            KhachHang khachHang = resultList.get(0);
            txtTenKHOder.setText(khachHang.getTenKH());
            txtDiemTL.setText(String.valueOf(khachHang.getTinhLuy()));
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnTimSDTKHActionPerformed

    private void txtDangHoatDongAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtDangHoatDongAncestorAdded
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtDangHoatDongAncestorAdded

    private void btnGiaoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaoHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGiaoHangActionPerformed

    private void btnTaoSoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoSoHDActionPerformed
        // TODO add your handling code here:
//        try {
//        // Tạo số hóa đơn ngẫu nhiên và gán vào txtSoHD
//        String soHD = HoaDonHelper.generateUniqueSoHD();
//        txtSoHD.setText(soHD);
//        String dangHoatDong = txtDangHoatDong.getText().trim();
//        String datelb = dateLabel.getText().trim();
//        String tenKHOder = txtTenKHOder.getText().trim();
//        String trangThaiOder = txtTrangThai.getSelectedItem().toString().trim();
//        // Thêm số hóa đơn vào cột đầu tiên của bảng tableHDcho
//        DefaultTableModel model = (DefaultTableModel) tableHDcho.getModel();
//        model.addRow(new Object[]{soHD, dangHoatDong,tenKHOder,datelb});
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Lỗi khi tạo số hóa đơn: " + ex.getMessage());
//    }   catch (Exception ex) {
//            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
        // Tạo số hóa đơn mới và gán vào txtSoHD
        String soHD = HoaDonHelper.generateUniqueSoHD();
        txtSoHD.setText(soHD);

        String dangHoatDong = txtDangHoatDong.getText().trim();
        String datelb = dateLabel.getText().trim();
        String tenKHOder = txtTenKHOder.getText().trim();
        String trangThaiOder = txtTrangThai.getSelectedItem().toString().trim();

        // Nếu `tenKHOder` trống thì điền giá trị mặc định là "Yiqi"
        if (tenKHOder == null || tenKHOder.isEmpty()) {
            tenKHOder = "Yiqi";
        }

        // Thêm số hóa đơn vào cột đầu tiên của bảng tableHDcho
        DefaultTableModel model = (DefaultTableModel) tableHDcho.getModel();
        model.addRow(new Object[]{soHD, dangHoatDong, tenKHOder, datelb, trangThaiOder});
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tạo số hóa đơn: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnTaoSoHDActionPerformed

    private void txtTenKHOderCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTenKHOderCaretUpdate
        // TODO add your handling code here:
        String tenKHOder = txtTenKHOder.getText().trim();

    // Cập nhật nội dung mới vào cột thứ 2 của tableHDcho
    int row = tableHDcho.getRowCount() - 1; // Lấy chỉ số hàng cuối cùng
    if (row >= 0) {
        tableHDcho.setValueAt(tenKHOder, row, 2); // Cập nhật nội dung mới vào cột thứ 2 (chỉ số cột 1)
    }
    }//GEN-LAST:event_txtTenKHOderCaretUpdate

    private void txtGhiChuHDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGhiChuHDCaretUpdate
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtGhiChuHDCaretUpdate

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelHDcho = (DefaultTableModel) tableHDcho.getModel();
    DefaultTableModel modelOder = (DefaultTableModel) tableOder.getModel();
    DefaultTableModel modelHoaDon = (DefaultTableModel) tableHoaDon.getModel();
    DefaultTableModel modelChiTietHD = (DefaultTableModel) tableChiTietHD.getModel();

    // Kiểm tra nếu bảng `tableHDcho` có hàng nào
    if (modelHDcho.getRowCount() > 0) {
        // Duyệt qua từng hàng của bảng `tableHDcho`
        for (int i = 0; i < modelHDcho.getRowCount(); i++) {
            String soHD = modelHDcho.getValueAt(i, 0).toString();
            String dangHoatDong = modelHDcho.getValueAt(i, 1).toString();
            String tenKHOder = modelHDcho.getValueAt(i, 2).toString();
            String datelb = modelHDcho.getValueAt(i, 3).toString();
            String trangThaiOder = modelHDcho.getValueAt(i, 4).toString();
            String tongTienHD = modelHDcho.getValueAt(i, 5).toString();

            // Nếu `tenKHOder` trống thì điền "Yiqi"
            if (tenKHOder == null || tenKHOder.trim().isEmpty()) {
                tenKHOder = "Yiqi";
                modelHDcho.setValueAt(tenKHOder, i, 2); // Cập nhật lại giá trị trong bảng
            }

            modelHoaDon.addRow(new Object[]{soHD, dangHoatDong, tenKHOder, datelb, trangThaiOder, tongTienHD});
        }

        // Xóa tất cả các hàng trong bảng `tableHDcho` sau khi đã chuyển dữ liệu
        modelHDcho.setRowCount(0);

        // Nếu có hàng nào trong bảng `tableOder`, thêm dữ liệu vào bảng `tableChiTietHD`
        if (modelOder.getRowCount() > 0) {
            for (int i = 0; i < modelOder.getRowCount(); i++) {
                String maTU = modelOder.getValueAt(i, 0).toString();
                String tenTU = modelOder.getValueAt(i, 1).toString();
                String giaTU = modelOder.getValueAt(i, 2).toString();
                String sizeTU = modelOder.getValueAt(i, 3).toString();
                Object soLuong = modelOder.getValueAt(i, 4);
                modelChiTietHD.addRow(new Object[]{maTU, tenTU, giaTU, sizeTU, soLuong});
            }
        }

        // Lấy dữ liệu từ hàng đầu tiên của bảng `tableHoaDon`
        String soHD = modelHoaDon.getValueAt(0, 0).toString();
        txtSOHD.setText(soHD);
        String tenKHOder = modelHoaDon.getValueAt(0, 2).toString();
        txtKHACHHANG.setText(tenKHOder);
        String datelb = modelHoaDon.getValueAt(0, 3).toString();
        txtTGTAO.setText(datelb);
        String trangThaiOder = modelHoaDon.getValueAt(0, 4).toString();
        txtTRANGTHAI.setText(trangThaiOder);

        // Truyền giá trị của soDTKH vào txtSODTKH
        String soDTKH = txtTimSoDTKH.getText().trim();
        txtSDTKH.setText(soDTKH);

        String ghiChu = txtGhiChuHD.getText().trim();
        txtGHICHU.setText(ghiChu);
        String loaiTT = coboLoaiTT.getSelectedItem().toString().trim();
        txtLOAITT.setText(loaiTT);
        String tongTienNOI = txtTongTienHD.getText().trim();
        txtTONGTIENSP.setText(tongTienNOI);
        txtTONGTIENHD.setText(tongTienNOI);
        String diaChiNOI = txtDiaChiGiao.getText().trim();
        txtDIACHIGIAO.setText(diaChiNOI);

        // Nếu có số điện thoại khách hàng thì tính điểm tích lũy
        if (!soDTKH.isEmpty()) {
            // Tính tổng điểm tích lũy từ các mặt hàng trong `tableOder`
            int tongDiemTichLuy = modelOder.getRowCount() * 200;

            // Cập nhật điểm tích lũy cho khách hàng
            try {
                KhachHang khachHang = KhachHangDAO.timKiemKhachHangTheoSDT(soDTKH).get(0);
                int diemTichLuyHienTai = khachHang.getTinhLuy();
                int diemTichLuyMoi = diemTichLuyHienTai + tongDiemTichLuy;

                khachHang.setTinhLuy(diemTichLuyMoi);
                boolean isUpdated = KhachHangDAO.capNhatDiemTichLuy(khachHang);

                if (isUpdated) {
                    txtDiemTL.setText(String.valueOf(diemTichLuyMoi));
                    JOptionPane.showMessageDialog(this, "Thanh toán thành công! Điểm tích lũy mới: " + diemTichLuyMoi);
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật điểm tích lũy. Vui lòng thử lại.");
                }
                showDulieuKH();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật điểm tích lũy: " + ex.getMessage());
            }
        } else {
            // Nếu không có số điện thoại, thông báo không tính điểm tích lũy
            JOptionPane.showMessageDialog(this, "Thanh toán thành công! Không tính điểm tích lũy do không có số điện thoại khách hàng.");
        }

    } else {
        JOptionPane.showMessageDialog(this, "Không có thông tin để thanh toán.");
    }
     
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrangThaiActionPerformed
        // TODO add your handling code here:String tenKHOder = txtTenKHOder.getText().trim();

    // Lấy giá trị từ txtTenKHOder và txtTrangThai
    
    String trangThaiOder = txtTrangThai.getSelectedItem().toString().trim();

    // Cập nhật nội dung mới vào cột thứ 3 của tableHDcho (chỉ số cột 2)
    int row = tableHDcho.getRowCount() - 1; // Lấy chỉ số hàng cuối cùng
    if (row >= 0) {
        tableHDcho.setValueAt(trangThaiOder, row, 4); // Cập nhật nội dung mới vào cột thứ 2 (chỉ số cột 1)
    }  
    }//GEN-LAST:event_txtTrangThaiActionPerformed

    private void btnXACNHANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXACNHANActionPerformed
//        try {
//            // TODO add your handling code here:
//            int selectedRow = tableHoaDon.getSelectedRow();
//            if (selectedRow == -1) {
//                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xác nhận.");
//                return;
//            }
//            
//            // Lấy dữ liệu từ bảng tableHoaDon
//            DefaultTableModel modelHoaDon = (DefaultTableModel) tableHoaDon.getModel();
//            String soHD = modelHoaDon.getValueAt(selectedRow, 0).toString();
//            String tenKH = txtKHACHHANG.getText().trim();
//            String tenDN = modelHoaDon.getValueAt(selectedRow, 1).toString();
//            String thoiGianTao = modelHoaDon.getValueAt(selectedRow, 3).toString();
//            String trangThai = modelHoaDon.getValueAt(selectedRow, 4).toString();
//            String tongTienStr = modelHoaDon.getValueAt(selectedRow, 5).toString();
//            
//            // Chuyển đổi tongTien sang kiểu float
//            float tongTien = Float.parseFloat(tongTienStr);
//            
//            // Tạo đối tượng HoaDon
//            HoaDon hoaDon = new HoaDon();
//            hoaDon.setSoHD(Integer.parseInt(soHD));
//            hoaDon.setTenKH(tenKH);
//            hoaDon.setTenDN(tenDN);
//            hoaDon.setThoiGianTao(thoiGianTao);
//            hoaDon.setTrangThai(trangThai);
//            hoaDon.setTongTien(tongTien);
//            
//            // Thêm hóa đơn vào cơ sở dữ liệu
//            HoaDonDAO hoaDonDAO = new HoaDonDAO();
//            boolean isAdded = hoaDonDAO.addHoaDon(hoaDon);
//            
//            // Tạo đối tượng HoaDon
//            ChiTietHD chiTiet = new ChiTietHD();
//            chiTiet.setSoHD(Integer.parseInt(soHD));
//            chiTiet.setMaTU(maTU);
//            chiTiet.setTenTU(tenTU);
//            chiTiet.setGiaTU(giaTU);
//            chiTiet.setSoLuong(soLuong);
//            chiTiet.setSizeTU(sizeTU);
//            chiTiet.setTenKH(tenKH);
//            chiTiet.setSoDTKH(soDTKH);
//            chiTiet.setThoiGianTao(thoiGianTao);
//            chiTiet.setTrangThai(trangThai);
//            chiTiet.setLoaiThanhToan(loaiThanhToan);
//            chiTiet.setTongTien(tongTien);
//            // Thêm hóa đơn vào cơ sở dữ liệu
//            ChiTietDAO chiTietDAO = new ChiTietDAO();
//            boolean isAdded = chiTietDAO.addChiTietHD(chiTiet);
//            
//            if (isAdded) {
//                JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công!");
//            } else {
//                JOptionPane.showMessageDialog(this, "Lỗi khi thêm hóa đơn. Vui lòng thử lại.");
//            }   } catch (Exception ex) {
//            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
//        }           
           

    try {
        int selectedRow = tableHoaDon.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xác nhận.");
            return;
        }

        // Lấy dữ liệu từ bảng tableHoaDon
        DefaultTableModel modelHoaDon = (DefaultTableModel) tableHoaDon.getModel();
        String soHD = modelHoaDon.getValueAt(selectedRow, 0).toString();
        String tenKH = txtKHACHHANG.getText().trim();
        String tenDN = modelHoaDon.getValueAt(selectedRow, 1).toString();
        String thoiGianTao = modelHoaDon.getValueAt(selectedRow, 3).toString();
        String trangThai = modelHoaDon.getValueAt(selectedRow, 4).toString();
        String tongTienStr = modelHoaDon.getValueAt(selectedRow, 5).toString();

        // Chuyển đổi tongTien sang kiểu float
        float tongTien = Float.parseFloat(tongTienStr);

        // Tạo đối tượng HoaDon
        HoaDon hoaDon = new HoaDon();
        hoaDon.setSoHD(Integer.parseInt(soHD));
        hoaDon.setTenKH(tenKH);
        hoaDon.setTenDN(tenDN);
        hoaDon.setThoiGianTao(thoiGianTao);
        hoaDon.setTrangThai(trangThai);
        hoaDon.setTongTien(tongTien);

        // Thêm hóa đơn vào cơ sở dữ liệu
        HoaDonDAO hoaDonDAO = new HoaDonDAO();
        boolean isHoaDonAdded = hoaDonDAO.addHoaDon(hoaDon);
        
        // Lấy dữ liệu từ bảng tableChiTietHD
        DefaultTableModel modelChiTietHD = (DefaultTableModel) tableChiTietHD.getModel();
        boolean isChiTietAdded = true;

        for (int i = 0; i < modelChiTietHD.getRowCount(); i++) {
            
            String maTU = modelChiTietHD.getValueAt(i, 0).toString(); // Đảm bảo đây là chuỗi
            String tenTU = modelChiTietHD.getValueAt(i, 1).toString(); // Đảm bảo đây là chuỗi
            float giaTU = Float.parseFloat(modelChiTietHD.getValueAt(i, 2).toString()); // Sử dụng float cho giá tiền
            int soLuong = Integer.parseInt(modelChiTietHD.getValueAt(i, 4).toString()); // Đảm bảo đây là số nguyên
            String sizeTU = modelChiTietHD.getValueAt(i, 3).toString(); // Đảm bảo đây là chuỗi
            String soDTKH = txtSDTKH.getText().trim(); // Đảm bảo đây là chuỗi
            String loaiThanhToan = txtLOAITT.getText().trim(); // Hoặc giá trị khác phù hợp
            String ghiChu = txtGHICHU.getText().trim();
            // Tạo đối tượng ChiTietHD
            ChiTietHD chiTiet = new ChiTietHD();
            chiTiet.setSoHD(Integer.parseInt(soHD)); // Đảm bảo đây là số nguyên
            chiTiet.setMaTU(maTU);
            chiTiet.setTenTU(tenTU);
            chiTiet.setGiaTU((int)giaTU);
            chiTiet.setSoLuong(soLuong);
            chiTiet.setSizeTU(sizeTU);
            chiTiet.setTenKH(tenKH);
            chiTiet.setSoDTKH(soDTKH);
            chiTiet.setThoiGianTao(thoiGianTao);
            chiTiet.setTrangThai(trangThai);
            chiTiet.setLoaiThanhToan(loaiThanhToan);
            chiTiet.setTongTien(tongTien);
            chiTiet.setGhiChu(ghiChu);
            // Thêm chi tiết hóa đơn vào cơ sở dữ liệu
            ChiTietDAO chiTietDAO = new ChiTietDAO();
            if (!chiTietDAO.addCToaDon(chiTiet)) {
                isChiTietAdded = false;
                break;
            }
        }

        if (isHoaDonAdded && isChiTietAdded) {
            JOptionPane.showMessageDialog(this, "Thêm hóa đơn và chi tiết hóa đơn thành công!");
            modelHoaDon.setRowCount(0);
            modelChiTietHD.setRowCount(0);
            txtSOHD.setText("");
            txtKHACHHANG.setText("");
            txtSDTKH.setText("");
            txtLOAITT.setText("");
            txtTGTAO.setText("");
            txtGHICHU.setText("");
            txtTRANGTHAI.setText("");
            txtTONGTIENSP.setText("");
            txtTONGTIENHD.setText("");
            txtDIACHIGIAO.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm hóa đơn hoặc chi tiết hóa đơn. Vui lòng thử lại.");
        }
        showDulieuHD();
    } catch (Exception ex) {
        Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
    } 
    }//GEN-LAST:event_btnXACNHANActionPerformed

    private void txtTongTienHDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTongTienHDCaretUpdate
        // TODO add your handling code here:
        String tongTienHD = txtTongTienHD.getText().trim();

    // Cập nhật nội dung mới vào cột thứ 2 của tableHDcho
    int row = tableHDcho.getRowCount() - 1; // Lấy chỉ số hàng cuối cùng
    if (row >= 0) {
        tableHDcho.setValueAt(tongTienHD, row, 5); // Cập nhật nội dung mới vào cột thứ 2 (chỉ số cột 1)
    }
    }//GEN-LAST:event_txtTongTienHDCaretUpdate

    private void btnXoaTrongTableOderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrongTableOderActionPerformed
        // TODO add your handling code here:
        int selectedRow = tableOder.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.");
        return;
    }

    // Lấy mô hình của bảng tableOder
    DefaultTableModel modelOder = (DefaultTableModel) tableOder.getModel();

    // Xóa hàng được chọn
    modelOder.removeRow(selectedRow);

    // Cập nhật bảng
    tableOder.setModel(modelOder);
    }//GEN-LAST:event_btnXoaTrongTableOderActionPerformed

    private void btnDenNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDenNgayActionPerformed
        // TODO add your handling code here:
        SqlDateModel model = new SqlDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Hiển thị JDatePicker trong một dialog
        int result = JOptionPane.showConfirmDialog(null, datePicker, "Chọn Ngày", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
            txtDENNGAY.setText(selectedDate.toString());
        }
    }//GEN-LAST:event_btnDenNgayActionPerformed

    private void btnTuNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTuNgayActionPerformed
        // TODO add your handling code here:
        SqlDateModel model = new SqlDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Hiển thị JDatePicker trong một dialog
        int result = JOptionPane.showConfirmDialog(null, datePicker, "Chọn Ngày", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
            txtTUNGAY.setText(selectedDate.toString());
        }
    }//GEN-LAST:event_btnTuNgayActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        try {
        // Lấy dữ liệu từ JTextField
        String tuNgayStr = txtTUNGAY.getText().trim();
        String denNgayStr = txtDENNGAY.getText().trim();

        // Định dạng ngày
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Phân tích cú pháp chuỗi ngày thành đối tượng Date
        java.util.Date tuNgay = sdf.parse(tuNgayStr);
        java.util.Date denNgay = sdf.parse(denNgayStr);

        // Định dạng chuỗi cho phần truy vấn SQL
        SimpleDateFormat sqlSdf = new SimpleDateFormat("yyyy-MM-dd");

        String tuNgaySqlStr = sqlSdf.format(tuNgay) + " 00:00:00";
        String denNgaySqlStr = sqlSdf.format(denNgay) + " 23:59:59";

        // Kiểm tra tính hợp lệ của ngày
        if (tuNgay.after(denNgay)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tableLichSuHD.getModel();
        model.setRowCount(0); // Xóa các hàng cũ

        // Sử dụng hàm DATE() để chuyển đổi chuỗi thành ngày tháng trong SQL
        String sql = "SELECT soHD, tenKH, tenDN, thoiGianTao, trangThai, tongTien " +
                     "FROM HoaDon " +
                     "WHERE CAST(thoiGianTao AS DATETIME) BETWEEN ? AND ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, tuNgaySqlStr);
            pstmt.setString(2, denNgaySqlStr);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int soHD = rs.getInt("soHD");
                String tenKH = rs.getString("tenKH");
                String tenDN = rs.getString("tenDN");
                String thoiGianTao = rs.getString("thoiGianTao");
                String trangThai = rs.getString("trangThai");
                float tongTien = rs.getFloat("tongTien");

                model.addRow(new Object[]{soHD, tenKH, tenDN, thoiGianTao, trangThai, tongTien});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi truy vấn dữ liệu: " + ex.getMessage());
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi thực hiện lọc dữ liệu: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnLocActionPerformed

    private void txtTuNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTuNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTuNgayActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnResetOderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetOderActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelOder = (DefaultTableModel) tableOder.getModel();
        modelOder.setRowCount(0);
        txtTrangThai.setSelectedItem("Chờ");
        coboLoaiTT.setSelectedItem("Tiền mặt");
        txtSoHD.setText("");
        txtTimSoDTKH.setText("");
        txtTenKHOder.setText("");
        txtDiemTL.setText("");
        txtGhiChuHD.setText("");
        txtTongTienSP.setText("");
        txtTongTienHD.setText("");
        txtDiaChiGiao.setText("");
    }//GEN-LAST:event_btnResetOderActionPerformed

    private void btnDoiDiemTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiDiemTLActionPerformed
        // TODO add your handling code here:
         // Hiển thị một JTextField để nhập điểm muốn đổi
    String inputDiem = JOptionPane.showInputDialog(this, "Nhập điểm muốn đổi (phải lớn hơn 1000 và chia hết cho 1000):");

    if (inputDiem == null || inputDiem.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập điểm muốn đổi.");
        return;
    }

    try {
        int diemDoi = Integer.parseInt(inputDiem.trim());

        // Kiểm tra điều kiện điểm nhập vào
        if (diemDoi < 1200 || diemDoi % 1200 != 0) {
            JOptionPane.showMessageDialog(this, "Điểm muốn đổi phải lớn hơn 1000 và chia hết cho 1000.");
            return;
        }

        // Lấy điểm tích lũy hiện tại của khách hàng
        String soDTKH = txtTimSoDTKH.getText().trim();
        KhachHang khachHang = KhachHangDAO.timKiemKhachHangTheoSDT(soDTKH).get(0);
        int diemTichLuyHienTai = khachHang.getTinhLuy();

        // Kiểm tra nếu điểm tích lũy hiện tại của khách hàng đủ để đổi
        if (diemTichLuyHienTai < diemDoi) {
            JOptionPane.showMessageDialog(this, "Điểm tích lũy không đủ để đổi.");
            return;
        }

        // Cập nhật điểm tích lũy mới của khách hàng
        int diemTichLuyMoi = diemTichLuyHienTai - diemDoi;
        khachHang.setTinhLuy(diemTichLuyMoi);
        
        // Cập nhật điểm tích lũy của khách hàng trong cơ sở dữ liệu
        boolean isUpdated = KhachHangDAO.capNhatDiemTichLuy(khachHang);
        
        if (isUpdated) {
            txtDiemTL.setText(String.valueOf(diemTichLuyMoi));
            JOptionPane.showMessageDialog(this, "Đổi điểm thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi đổi điểm. Vui lòng thử lại.");
        }
        showDulieuKH();
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Điểm muốn đổi phải là số nguyên hợp lệ.");
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi đổi điểm: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnDoiDiemTLActionPerformed

    private void tableHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHoaDonMouseClicked
        
    }//GEN-LAST:event_tableHoaDonMouseClicked

    private void tableLichSuHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLichSuHDMouseClicked
        // TODO add your handling code here:
         int selectedRow = tableLichSuHD.getSelectedRow();

    if (selectedRow != -1) {
        
        txtSOHD1.setText(tableLichSuHD.getValueAt(selectedRow, 0).toString());
        txtKHACHHANG1.setText(tableLichSuHD.getValueAt(selectedRow, 1).toString());
        txtTGTAO1.setText(tableLichSuHD.getValueAt(selectedRow, 3).toString());
        txtTRANGTHAI1.setText(tableLichSuHD.getValueAt(selectedRow, 4).toString());
        txtTONGTIENSP1.setText(tableLichSuHD.getValueAt(selectedRow, 5).toString());
        txtTONGTIENHD1.setText(tableLichSuHD.getValueAt(selectedRow, 5).toString());

        int soHD = Integer.parseInt(tableLichSuHD.getValueAt(selectedRow, 0).toString());
        String tenKH = tableLichSuHD.getValueAt(selectedRow, 1).toString();

        try {
            
            String soDTKH = KhachHangDAO.getSoDTKHByTenKH(tenKH);
            if (soDTKH != null) {
                txtSODTKH1.setText(soDTKH);
            } else {
                txtSODTKH1.setText("Không tìm thấy số ĐT");
            }
            
            String ghiChu = ChiTietDAO.getGhiChuBySoHD(soHD);
            if (ghiChu != null) {
                txtGHICHU1.setText(ghiChu);
            } else {
                txtGHICHU1.setText("Không có ghi chú");
            }
            
            String loaiThanhToan = ChiTietDAO.getLoaiTTBySoHD(soHD);
            if (loaiThanhToan != null) {
                txtLOAITT1.setText(loaiThanhToan);
            } else {
                txtLOAITT1.setText("Không có loại thanh toán");
            }
            
            ArrayList<ChiTietHD> chiTietList = ChiTietDAO.getChiTietHDBySoHD(soHD);
            DefaultTableModel modelChiTietHD = (DefaultTableModel) tableOderCT.getModel();
            modelChiTietHD.setRowCount(0);

            for (ChiTietHD chiTiet : chiTietList) {
                modelChiTietHD.addRow(new Object[]{
                    chiTiet.getMaTU(),
                    chiTiet.getTenTU(),
                    chiTiet.getGiaTU(),
                    chiTiet.getSizeTU(),
                    chiTiet.getSoLuong()
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy chi tiết hóa đơn hoặc số điện thoại khách hàng: " + ex.getMessage());
        }
    }
    }//GEN-LAST:event_tableLichSuHDMouseClicked

    private void btnTUDAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTUDAYActionPerformed
        // TODO add your handling code here:
        SqlDateModel model = new SqlDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Hiển thị JDatePicker trong một dialog
        int result = JOptionPane.showConfirmDialog(null, datePicker, "Chọn Ngày", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
            txtDENNGAY.setText(selectedDate.toString());
        }
    }//GEN-LAST:event_btnTUDAYActionPerformed

    private void btnDENDAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDENDAYActionPerformed
        // TODO add your handling code here:
        SqlDateModel model = new SqlDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Hiển thị JDatePicker trong một dialog
        int result = JOptionPane.showConfirmDialog(null, datePicker, "Chọn Ngày", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
            txtDENNGAY.setText(selectedDate.toString());
        }
    }//GEN-LAST:event_btnDENDAYActionPerformed

    private void btnTimKiemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemHDActionPerformed
        // TODO add your handling code here:
       int soHD = Integer.parseInt(txtTimKiemHD.getText().trim());

    if (soHD == 0) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập số hóa đơn để tìm kiếm");
        txtTimKiemHD.requestFocus();
        return;
    }

    try {
        ArrayList<HoaDon> resultList = HoaDonDAO.timKiemHoaDon(soHD);
        if (resultList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn nào phù hợp");
        } else {
            // Hiển thị kết quả tìm kiếm trên giao diện người dùng
            DefaultTableModel model = (DefaultTableModel) tableLichSuHD.getModel();
            model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

            for (HoaDon hd : resultList) {
                model.addRow(new Object[]{hd.getSoHD(), hd.getTenKH(), hd.getTenDN(), hd.getThoiGianTao(), hd.getTrangThai(), hd.getTongTien()});
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnTimKiemHDActionPerformed

    private void btnTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKHActionPerformed
        // TODO add your handling code here:
         String soDTKH = txtTimKH.getText().trim();

    if (soDTKH.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại để tìm kiếm");
        txtTimKH.requestFocus();
        return;
    }

    try {
        ArrayList<KhachHang> resultList = KhachHangDAO.timKiemKhachHangTheoSDT(soDTKH);
        if (resultList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng nào phù hợp");
        } else {
            // Hiển thị kết quả tìm kiếm trên giao diện người dùng
            DefaultTableModel model = (DefaultTableModel) tableKhachHang.getModel();
            model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

            for (KhachHang kh : resultList) {
                model.addRow(new Object[]{kh.getMaKH(), kh.getTenKH(), kh.getNgaySinhKH(), kh.getSoDTKH(), kh.getTinhLuy()});
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnTimKHActionPerformed

    private void tableKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKhachHangMouseClicked
        // TODO add your handling code here:
        int selectedRow = tableKhachHang.getSelectedRow();

        // Kiểm tra nếu có hàng được chọn
        if (selectedRow != -1) {
            // Cập nhật các trường văn bản với dữ liệu từ hàng được chọn
            txtMaKH.setText(tableKhachHang.getValueAt(selectedRow, 0).toString());
            txtTenKH.setText(tableKhachHang.getValueAt(selectedRow, 1).toString());
            txtNgaySinh.setText(tableKhachHang.getValueAt(selectedRow, 2).toString());
            txtSoDTKH.setText(tableKhachHang.getValueAt(selectedRow, 3).toString());
            txtDiem.setText(tableKhachHang.getValueAt(selectedRow, 4).toString());
            
        }
    }//GEN-LAST:event_tableKhachHangMouseClicked

    private void btnCapNhatKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatKHActionPerformed
        // TODO add your handling code here:
        int position = tableKhachHang.getSelectedRow();
    if (position == -1) {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để sửa");
        return;
    }

    // Lấy tên từ bảng
    String originalTenKH = (String) tableKhachHang.getValueAt(position, 1);

    // Kiểm tra xem mã nhân viên có bị sửa đổi không
    if (!txtTenKH.getText().equals(originalTenKH)) {
        JOptionPane.showMessageDialog(null, "Tên khách hàng không được phép sửa đổi");
        txtTenKH.setText(originalTenKH); // Đặt lại mã nhân viên ban đầu
        txtTenKH.requestFocus();
        return;
    }

    if(txtMaKH.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Mã không được trống");
        txtMaKH.requestFocus();
        return;
    } else if(txtNgaySinh.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Ngày sinh không được trống");
        txtNgaySinh.requestFocus();
        return;  
    } else if(txtDiem.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Điểm không được trống");
        txtDiem.requestFocus();
        return;
    }

    if (!txtSoDTKH.getText().matches("(\\+84|0)\\d{9,10}")) {
        JOptionPane.showMessageDialog(null, "Số điện thoại chưa đúng định dạng!");
        txtSoDTKH.requestFocus();
        return;
    }

    KhachHang kh = new KhachHang();
    kh.setMaKH(txtMaKH.getText());
    kh.setTenKH(txtTenKH.getText());
    kh.setNgaySinhKH(txtNgaySinh.getText());
    kh.setSoDTKH(txtSoDTKH.getText());
    try {
    int tinhLuy = Integer.parseInt(txtDiem.getText());
    kh.setTinhLuy(tinhLuy);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Điểm tích lũy phải là một số nguyên hợp lệ");
        txtDiem.requestFocus();
    }
    

    try {
        boolean success = KhachHangDAO.suaKhachHang(kh);
        if (success) {
            try {
                showDulieuKH(); // Refresh the table data
            } catch (Exception ex) {
                Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Sửa không thành công");
        }
    } catch (SQLException ex) {
        Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Sửa không thành công: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }   catch (Exception ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCapNhatKHActionPerformed

    private void btnDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoanhThuActionPerformed
        // TODO add your handling code here:
        updateDoanhThuToday();
        updateSoLuongHoaDonToday();
        updateThongKe();
    }//GEN-LAST:event_btnDoanhThuActionPerformed

    private void btnThemKHNEWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHNEWActionPerformed
        // TODO add your handling code here:
        JFrame frame = new JFrame("Thêm khách hàng");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblMaKH = new JLabel("Mã khách hàng:");
        JTextField txtMaKH1 = new JTextField(20);

        JLabel lblTenKH = new JLabel("Tên khách hàng:");
        JTextField txtTenKH1 = new JTextField(20);

        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
        JTextField txtNgaySinh1 = new JTextField(20);

        JLabel lblSoDTKH = new JLabel("Số điện thoại:");
        JTextField txtSoDTKH1 = new JTextField(20);

        JLabel lblDiem = new JLabel("Điểm tích lũy:");
        JTextField txtDiem1 = new JTextField(20);

        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(txtMaKH1.getText());
                    kh.setTenKH(txtTenKH1.getText());
                    kh.setNgaySinhKH(txtNgaySinh1.getText());
                    kh.setSoDTKH(txtSoDTKH1.getText());
                    kh.setTinhLuy(Integer.parseInt(txtDiem1.getText()));

                    if (KhachHangDAO.themKhachHang(kh)) {
                        JOptionPane.showMessageDialog(null, "Thêm thành công");
                        showDulieuKH(); // Refresh the table data
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm thất bại!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Thêm thất bại: " + ex.getMessage());
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(lblMaKH);
        panel.add(txtMaKH1);
        panel.add(lblTenKH);
        panel.add(txtTenKH1);
        panel.add(lblNgaySinh);
        panel.add(txtNgaySinh1);
        panel.add(lblSoDTKH);
        panel.add(txtSoDTKH1);
        panel.add(lblDiem);
        panel.add(txtDiem1);
        panel.add(new JLabel()); 
        panel.add(btnOK);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }//GEN-LAST:event_btnThemKHNEWActionPerformed

    private void btnXuatFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatFileExcelActionPerformed

    private void btnXuatFileEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileEXCELActionPerformed
        // TODO add your handling code here:
         // Mở hộp thoại để người dùng chọn nơi lưu file
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            // Kiểm tra và thêm đuôi file .xlsx nếu chưa có
            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            // Sử dụng ExcelExporter để xuất dữ liệu ra file
            ExcelExporter exporter = new ExcelExporter();
            try {
                exporter.exportTable(tableLichSuHD, filePath);
                JOptionPane.showMessageDialog(null, "Xuất file thành công!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Xuất file thất bại: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnXuatFileEXCELActionPerformed

    private void btnResetLSHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetLSHDActionPerformed
        try {
            // TODO add your handling code here:
            showDulieuHD();
        } catch (Exception ex) {
            Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnResetLSHDActionPerformed

//    private void searchEmployeeByTU(String tenTU) {
//    DefaultTableModel model = (DefaultTableModel) tableThucUong.getModel();
//    model.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng
//
//    try {
//        Connection connection = DBConnection.getConnection();
//        String query = "SELECT * FROM dbo.[ThucUong] WHERE tenTU LIKE ?";
//        
//        PreparedStatement ps = connection.prepareStatement(query);
//         ps.setString(1, "%" + tenTU + "%");
//        ResultSet rs = ps.executeQuery();
//
//        while (rs.next()) {
//            // Lấy dữ liệu từ kết quả truy vấn và thêm vào bảng
//            String matu = rs.getString("maTU");
//            String tentu = rs.getString("tenTU");
//            String gia = rs.getString("giaTU");
//            String size = rs.getString("sizeTU");
//            String maloai = rs.getString("maLoai");
//            String trangthai = rs.getString("trangThai");
//            model.addRow(new Object[]{matu, tentu, gia, size, maloai, trangthai});
//        }
//        if (model.getRowCount() == 0) {
//            JOptionPane.showMessageDialog(null, "Không tìm thấy thức uống nào phù hợp");
//        }
//
//        rs.close();
//        ps.close();
//        DBConnection.closeConnection(connection);
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//        JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm: " + ex.getMessage());
//    }
//}
//    private void loadTrangThai(String trangThai){
//        
//        try {
//        tableThucUong.removeAll();
//        String[] arr = {"Mã TU", "Tên TU", "Giá TU", "Size TU", "Mã loại", "Trạng Thái"};
//        DefaultTableModel model = new DefaultTableModel(arr, 0);
//        Connection connection = DBConnection.getConnection();
//        String query;
//        PreparedStatement ps;
//
//        if (trangThai.equals("Tất cả")) {
//            query = "SELECT TOP 1000 * FROM [QLChidori].[dbo].[ThucUong]";
//            ps = connection.prepareStatement(query);
//        } else {
//            query = "SELECT TOP 1000 * FROM [QLChidori].[dbo].[ThucUong] WHERE trangThai = ?";
//            ps = connection.prepareStatement(query);
//            ps.setString(1, trangThai);
//        }
//
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            Vector<String> vector = new Vector<>();
//            vector.add(rs.getString("maTU"));
//            vector.add(rs.getString("tenTU"));
//            vector.add(String.valueOf(rs.getInt("giaTU")));;
//            vector.add(rs.getString("sizeTU"));
//            vector.add(rs.getString("maLoai"));
//            vector.add(rs.getString("trangThai"));
//
//            model.addRow(vector);
//        }
//        tableThucUong.setModel(model);
//        rs.close();
//        ps.close();
//        DBConnection.closeConnection(connection);
//    } catch (SQLException ex) {
//        Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    }
//        private void loadLoaiTU(String maLoai){
//        
//        try {
//        tableThucUong.removeAll();
//        String[] arr = {"Mã TU", "Tên TU", "Giá TU", "Size TU", "Mã loại", "Trạng Thái"};
//        DefaultTableModel model = new DefaultTableModel(arr, 0);
//        Connection connection = DBConnection.getConnection();
//        String query;
//        PreparedStatement ps;
//
//        if (maLoai.equals("Tất cả")) {
//            query = "SELECT TOP 1000 * FROM [QLChidori].[dbo].[ThucUong]";
//            ps = connection.prepareStatement(query);
//        } else {
//            query = "SELECT TOP 1000 * FROM [QLChidori].[dbo].[ThucUong] WHERE maLoai = ?";
//            ps = connection.prepareStatement(query);
//            ps.setString(1, maLoai);
//        }
//
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            Vector<String> vector = new Vector<>();
//            vector.add(rs.getString("maTU"));
//            vector.add(rs.getString("tenTU"));
//            vector.add(String.valueOf(rs.getInt("giaTU")));;
//            vector.add(rs.getString("sizeTU"));
//            vector.add(rs.getString("maLoai"));
//            vector.add(rs.getString("trangThai"));
//
//            model.addRow(vector);
//        }
//        tableThucUong.setModel(model);
//        rs.close();
//        ps.close();
//        DBConnection.closeConnection(connection);
//    } catch (SQLException ex) {
//        Logger.getLogger(GiaoDienTEA.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GiaoDienTEA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDienTEA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDienTEA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDienTEA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiaoDienTEA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnCFden;
    private javax.swing.JButton btnCFdua;
    private javax.swing.JButton btnCFsua;
    private javax.swing.JButton btnCapNhatKH;
    private javax.swing.JButton btnCapNhatTU;
    private javax.swing.JButton btnDENDAY;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDenNgay;
    private javax.swing.JButton btnDoanhThu;
    private javax.swing.JButton btnDoiDiemTL;
    private javax.swing.JButton btnGiaoCa;
    private javax.swing.JButton btnGiaoHang;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnHuyHD;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnKhuyenMai;
    private javax.swing.JButton btnLamMoiKH;
    private javax.swing.JButton btnLamMoiNV;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnResetLSHD;
    private javax.swing.JButton btnResetOder;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnTRdao;
    private javax.swing.JButton btnTRdua;
    private javax.swing.JButton btnTRnhiet;
    private javax.swing.JButton btnTRxoai;
    private javax.swing.JButton btnTSDuongDen;
    private javax.swing.JButton btnTSP;
    private javax.swing.JButton btnTSsoCola;
    private javax.swing.JButton btnTUDAY;
    private javax.swing.JButton btnTaoSoHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemKHNEW;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnThemTU;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnTimKH;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemHD;
    private javax.swing.JButton btnTimKiemNV;
    private javax.swing.JButton btnTimSDTKH;
    private javax.swing.JButton btnTimTU;
    private javax.swing.JButton btnTraSuaDau;
    private javax.swing.JButton btnTraSuaVQ;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JButton btnTuNgay;
    private javax.swing.JButton btnXACNHAN;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.JButton btnXoaTrongTableOder;
    private javax.swing.JButton btnXuatFileEXCEL;
    private javax.swing.JButton btnXuatFileExcel;
    private javax.swing.JComboBox<String> cbbLoaiSP;
    private javax.swing.JComboBox<String> coboBoxLoaiTU;
    private javax.swing.JComboBox<String> coboBoxNV;
    private javax.swing.JComboBox<String> coboBoxTrangThai;
    private javax.swing.JComboBox<String> coboLoaiTT;
    private javax.swing.JComboBox<String> coboMALOAI;
    private javax.swing.JComboBox<String> coboTRANGTHAI;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpChucNang;
    private javax.swing.JPanel jpMenu;
    private javax.swing.JPanel jpMenu2;
    private javax.swing.JPanel jpMenuTU;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JPanel pnlCard;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlKhuyenMai;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlThongKe;
    private javax.swing.JPanel pnlTrangChu;
    private javax.swing.JTable tableChiTietHD;
    private javax.swing.JTable tableGiaoHang;
    private javax.swing.JTable tableHDcho;
    private javax.swing.JTable tableHoaDon;
    private javax.swing.JTable tableKhachHang;
    private javax.swing.JTable tableLichSuHD;
    private javax.swing.JTable tableNhanVien;
    private javax.swing.JTable tableOder;
    private javax.swing.JTable tableOderCT;
    private javax.swing.JTable tableThucUong;
    private javax.swing.JTextField txtCanCuoc;
    private javax.swing.JLabel txtDENNGAY;
    private javax.swing.JTextField txtDIACHI;
    private javax.swing.JTextArea txtDIACHIGIAO;
    private javax.swing.JLabel txtDangHoatDong;
    private javax.swing.JTextField txtDenNgay;
    private javax.swing.JTextArea txtDiaChiGiao;
    private javax.swing.JTextField txtDiem;
    private javax.swing.JTextField txtDiemTL;
    private javax.swing.JLabel txtDoanhThu7Ngay;
    private javax.swing.JLabel txtDoanhThuThangNay;
    private javax.swing.JLabel txtDoanhThuToday;
    private javax.swing.JTextArea txtGHICHU;
    private javax.swing.JLabel txtGHICHU1;
    private javax.swing.JTextField txtGIATU;
    private javax.swing.JTextArea txtGhiChuHD;
    private javax.swing.JTextField txtHOTEN;
    private javax.swing.JLabel txtHoatDong;
    private javax.swing.JLabel txtKHACHHANG;
    private javax.swing.JLabel txtKHACHHANG1;
    private javax.swing.JLabel txtLOAITT;
    private javax.swing.JLabel txtLOAITT1;
    private javax.swing.JLabel txtLoaiSP;
    private javax.swing.JLabel txtLogo;
    private javax.swing.JTextField txtMANV;
    private javax.swing.JTextField txtMATU;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtNGAYSINH;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JButton txtResetTimKiem;
    private javax.swing.JLabel txtSDTKH;
    private javax.swing.JTextField txtSIZETU;
    private javax.swing.JTextField txtSODT;
    private javax.swing.JLabel txtSODTKH1;
    private javax.swing.JLabel txtSOHD;
    private javax.swing.JLabel txtSOHD1;
    private javax.swing.JTextField txtSoDTKH;
    private javax.swing.JTextField txtSoHD;
    private javax.swing.JLabel txtSoLuongHoaDon7Ngay;
    private javax.swing.JLabel txtSoLuongHoaDonThangNay;
    private javax.swing.JLabel txtSoLuongHoaDonToday;
    private javax.swing.JLabel txtSoLuongTongDT;
    private javax.swing.JTextField txtTENTU;
    private javax.swing.JLabel txtTGTAO;
    private javax.swing.JLabel txtTGTAO1;
    private javax.swing.JLabel txtTONGTIENHD;
    private javax.swing.JLabel txtTONGTIENHD1;
    private javax.swing.JLabel txtTONGTIENSP;
    private javax.swing.JLabel txtTONGTIENSP1;
    private javax.swing.JLabel txtTRANGTHAI;
    private javax.swing.JLabel txtTRANGTHAI1;
    private javax.swing.JLabel txtTUNGAY;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenKHOder;
    private javax.swing.JLabel txtTensp;
    private javax.swing.JTextField txtTienKhachTra;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimKH;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemCCCD;
    private javax.swing.JTextField txtTimKiemDiaChi;
    private javax.swing.JTextField txtTimKiemHD;
    private javax.swing.JTextField txtTimKiemMNV;
    private javax.swing.JTextField txtTimKiemNgaySNV;
    private javax.swing.JTextField txtTimKiemSTDNV;
    private javax.swing.JTextField txtTimKiemTU;
    private javax.swing.JTextField txtTimKiemTenNV;
    private javax.swing.JTextField txtTimSoDTKH;
    private javax.swing.JLabel txtTongDoanhThu;
    private javax.swing.JTextField txtTongTienHD;
    private javax.swing.JTextField txtTongTienSP;
    private javax.swing.JComboBox<String> txtTrangThai;
    private javax.swing.JTextField txtTuNgay;
    private javax.swing.JLabel x;
    // End of variables declaration//GEN-END:variables
}
