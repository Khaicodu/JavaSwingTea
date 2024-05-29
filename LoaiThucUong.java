/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.model;

/**
 *
 * @author Admin
 */
public class LoaiThucUong {

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoaiTU() {
        return tenLoaiTU;
    }

    public void setTenLoaiTU(String tenLoaiTU) {
        this.tenLoaiTU = tenLoaiTU;
    }

    public LoaiThucUong(String maLoai, String tenLoaiTU) {
        this.maLoai = maLoai;
        this.tenLoaiTU = tenLoaiTU;
    }

    public LoaiThucUong() {
    }
    private String maLoai;
    private String tenLoaiTU;
}
