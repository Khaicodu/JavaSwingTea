/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.model;

/**
 *
 * @author Admin
 */
public class HoaDon {

    public int getSoHD() {
        return soHD;
    }

    public void setSoHD(int soHD) {
        this.soHD = soHD;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
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

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public HoaDon(int soHD, String tenKH, String tenDN, String thoiGianTao, String trangThai, float tongTien) {
        this.soHD = soHD;
        this.tenKH = tenKH;
        this.tenDN = tenDN;
        this.thoiGianTao = thoiGianTao;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
    }

    public HoaDon() {
    }

    
    private int soHD;
    private String tenKH;
    private String tenDN;
    private String thoiGianTao;
    private String trangThai;
    private float tongTien;
    
}
