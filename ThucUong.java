/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.model;

/**
 *
 * @author Admin
 */
public class ThucUong {
    private String maTU;
    private String tenTU;
    private String giaTU;
    private String sizeTU;
    private String maLoai;
    private String trangThai;

    public ThucUong() {
    }


    public void setMaTU(String maTU) {
        this.maTU = maTU;
    }

    public void setTenTU(String tenTU) {
        this.tenTU = tenTU;
    }

    public void setGiaTU(String giaTU) {
        this.giaTU = giaTU;
    }

    public void setSizeTU(String sizeTU) {
        this.sizeTU = sizeTU;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public ThucUong(String maTU, String tenTU, String giaTU, String sizeTU, String maLoai, String trangThai) {
        this.maTU = maTU;
        this.tenTU = tenTU;
        this.giaTU = giaTU;
        this.sizeTU = sizeTU;
        this.maLoai = maLoai;
        this.trangThai = trangThai;
    }

    // Getters
    public String getMaTU() { return maTU; }
    public String getTenTU() { return tenTU; }
    public String getGiaTU() { return giaTU; }
    public String getSizeTU() { return sizeTU; }
    public String getMaLoai() { return maLoai; }
    public String getTrangThai() { return trangThai; }
}

