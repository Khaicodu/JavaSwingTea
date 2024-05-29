/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.model;

/**
 *
 * @author Admin
 */
public class ChiTietHD {

    public int getSoHD() {
        return soHD;
    }

    public void setSoHD(int soHD) {
        this.soHD = soHD;
    }

    public String getMaTU() {
        return maTU;
    }

    public void setMaTU(String maTU) {
        this.maTU = maTU;
    }

    public String getTenTU() {
        return tenTU;
    }

    public void setTenTU(String tenTU) {
        this.tenTU = tenTU;
    }

    public int getGiaTU() {
        return giaTU;
    }

    public void setGiaTU(int giaTU) {
        this.giaTU = giaTU;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getSizeTU() {
        return sizeTU;
    }

    public void setSizeTU(String sizeTU) {
        this.sizeTU = sizeTU;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoDTKH() {
        return soDTKH;
    }

    public void setSoDTKH(String soDTKH) {
        this.soDTKH = soDTKH;
    }

    public String getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(String thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getLoaiThanhToan() {
        return loaiThanhToan;
    }

    public void setLoaiThanhToan(String loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public ChiTietHD(int soHD, String maTU, String tenTU, int giaTU, int soLuong, String sizeTU, String tenKH, String soDTKH, String thoiGianTao, String trangThai, String loaiThanhToan, float tongTien, String ghiChu) {
        this.soHD = soHD;
        this.maTU = maTU;
        this.tenTU = tenTU;
        this.giaTU = giaTU;
        this.soLuong = soLuong;
        this.sizeTU = sizeTU;
        this.tenKH = tenKH;
        this.soDTKH = soDTKH;
        this.thoiGianTao = thoiGianTao;
        this.trangThai = trangThai;
        this.loaiThanhToan = loaiThanhToan;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public ChiTietHD() {
    }

     private int soHD;
    private String maTU;
    private String tenTU;
    private int giaTU;
    private int soLuong;
    private String sizeTU;
    private String tenKH;
    private String soDTKH;
    private String thoiGianTao;
    private String trangThai;
    private String loaiThanhToan;
    private float tongTien;
    private String ghiChu;
   
}
