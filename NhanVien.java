/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.model;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class NhanVien implements Serializable{
    private String maNV;
    private String hoVaTen;
    private String soDT;
    private String ngaySinh;
    private String cccd;
    private String diaChi;

    public NhanVien() {
    }

    
    public NhanVien(String maNV, String hoVaTen, String soDT, String ngaySinh, String cccd, String diaChi) {
        this.maNV = maNV;
        this.hoVaTen = hoVaTen;
        this.soDT = soDT;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.diaChi = diaChi;
    }
    

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public String getSoDT() {
        return soDT;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getCccd() {
        return cccd;
    }

    public String getDiaChi() {
        return diaChi;
    }
}
