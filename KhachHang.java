/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.model;

/**
 *
 * @author Admin
 */
public class KhachHang {

    
    private String maKH;
    private String tenKH;
    private String ngaySinhKH;
    private String soDTKH;
    private int tinhLuy;

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKH, String ngaySinhKH, String soDTKH, int tinhLuy) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.ngaySinhKH = ngaySinhKH;
        this.soDTKH = soDTKH;
        this.tinhLuy = tinhLuy;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getNgaySinhKH() {
        return ngaySinhKH;
    }

    public void setNgaySinhKH(String ngaySinhKH) {
        this.ngaySinhKH = ngaySinhKH;
    }

    public String getSoDTKH() {
        return soDTKH;
    }

    public void setSoDTKH(String soDTKH) {
        this.soDTKH = soDTKH;
    }

    public int getTinhLuy() {
        return tinhLuy;
    }

    public void setTinhLuy(int tinhLuy) {
        this.tinhLuy = tinhLuy;
    }
}
