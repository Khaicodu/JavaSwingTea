/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.model;

/**
 *
 * @author Admin
 */
public class UserAD {
    private String tenDN;
    private String matKhau;
    private boolean isAdmin;
    private boolean canSelect;
    private boolean canDelete;
    private boolean canUpdate;
    private boolean canSearch;

    public UserAD(String tenDN, String matKhau, boolean isAdmin, boolean canSelect, boolean canDelete, boolean canUpdate, boolean canSearch) {
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.isAdmin = isAdmin;
        this.canSelect = canSelect;
        this.canDelete = canDelete;
        this.canUpdate = canUpdate;
        this.canSearch = canSearch;
    }

    public UserAD() {
    }

    // Getter và Setter cho các thuộc tính

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean canSelect() {
        return canSelect;
    }

    public void setCanSelect(boolean canSelect) {
        this.canSelect = canSelect;
    }

    public boolean canDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public boolean canUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public boolean canSearch() {
        return canSearch;
    }

    public void setCanSearch(boolean canSearch) {
        this.canSearch = canSearch;
    }
}