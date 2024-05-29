/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.model;

/**
 *
 * @author Admin
 */
public class MaGiamGia {

    public String getMaGG() {
        return maGG;
    }

    public void setMaGG(String maGG) {
        this.maGG = maGG;
    }

    public String getTenGG() {
        return tenGG;
    }

    public void setTenGG(String tenGG) {
        this.tenGG = tenGG;
    }

    public String getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(String ngayBD) {
        this.ngayBD = ngayBD;
    }

    public String getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(String ngayKT) {
        this.ngayKT = ngayKT;
    }

    public int getSoHD() {
        return soHD;
    }

    public void setSoHD(int soHD) {
        this.soHD = soHD;
    }

    public MaGiamGia(String maGG, String tenGG, String ngayBD, String ngayKT, int soHD) {
        this.maGG = maGG;
        this.tenGG = tenGG;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.soHD = soHD;
    }

    public MaGiamGia() {
    }

    
    private String maGG;
    private String tenGG;
    private String ngayBD;
    private String ngayKT;
    private int soHD;
    
}
