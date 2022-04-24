package phongban;

import dungchung.CauHinh;
import nhanvien.NhanVien;
import nhanvien.NhanVienQuanLy;
import nhanvien.QuanLyNhanVien;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PhongBan {
    private int maPhongBan;
    private String tenPhongBan;
    private NhanVien nhanVienQuanLy = new NhanVienQuanLy();
    private List<NhanVien> danhSachNhanVienTrucThuoc = new ArrayList<>();

    public PhongBan() {};

    /**
     * Hàm thêm nhân viên quản lý cho phòng ban này
     */
    public void themNhanVienQuanLy(NhanVien nhanVienQuanLy) {
        this.setNhanVienQuanLy((NhanVienQuanLy) nhanVienQuanLy);
    }

    /**
     * Hàm xem thông tin của 1 phòng ban
     */
    public void xemThongTin() {
        System.out.printf("Mã phòng ban: %d" +
                "\nTên phòng ban: %s" +
                "\nNhân viên quản lý: %s\n", this.maPhongBan, this.tenPhongBan, this.nhanVienQuanLy.getMaNhanVien());
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

    public void setNhanVienQuanLy(NhanVienQuanLy nhanVienQuanLy) {
        this.nhanVienQuanLy = nhanVienQuanLy;
    }
}
