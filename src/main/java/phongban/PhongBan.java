package phongban;

import nhanvien.NhanVien;
import nhanvien.NhanVienQuanLy;

import java.util.ArrayList;
import java.util.List;

public class PhongBan {
    private int maPhongBan;
    private String tenPhongBan;
    private NhanVien nhanVienQuanLy;
    private List<NhanVien> danhSachNhanVienTrucThuoc = new ArrayList<>();

    public void nhapNhanVienQuanLy(List<NhanVien> ds, String maNhanVien) {
        ds.forEach(nhanVien -> {
            if (nhanVien.getMaNhanVien().equals(maNhanVien)) {
                this.nhanVienQuanLy = nhanVien;
            }
        });
    }

    //Các setter và getter
    public int getMaPhongBan() {
        return maPhongBan;
    }

    public void setMaPhongBan(int maPhongBan) {
        this.maPhongBan = maPhongBan;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public List<NhanVien> getDanhSachNhanVienTrucThuoc() {
        return danhSachNhanVienTrucThuoc;
    }

    public void setDanhSachNhanVienTrucThuoc(List<NhanVien> danhSachNhanVienTrucThuoc) {
        this.danhSachNhanVienTrucThuoc = danhSachNhanVienTrucThuoc;
    }

    public NhanVien getNhanVienQuanLy() {
        return nhanVienQuanLy;
    }

    public void setNhanVienQuanLy(NhanVien nhanVienQuanLy) {
        this.nhanVienQuanLy = nhanVienQuanLy;
    }
}
