package phongban;

import nhanvien.NhanVien;
import nhanvien.NhanVienQuanLy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PhongBan {
    private int maPhongBan;
    private String tenPhongBan;
    private NhanVien nhanVienQuanLy;
    private Calendar ngayQuanLyNhamChuc;
    private List<NhanVien> danhSachNhanVienTrucThuoc = new ArrayList<>();

    public PhongBan() {}

    /**
     * Hàm hoàn thiện thông tin thuộc tính nhanVienQuanLy
     * @param ds
     */
    public void hoanThienThongTinNhanVienQuanLy(List<NhanVien> ds) {
        for(NhanVien nv: ds) {
            if (nv.getMaNhanVien().equals(this.nhanVienQuanLy.getMaNhanVien())) {
                this.nhanVienQuanLy = nv;
                return;
            }
        }
    }

    /**
     * Hàm xem thông tin của 1 phòng ban
     */
    public void xemThongTin() {
        System.out.printf("Mã phòng ban: %d" +
                "\nTên phòng ban: %s" +
                "\nNhân viên quản lý: %s\n", this.maPhongBan, this.tenPhongBan, this.nhanVienQuanLy.getHoTen());
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
        return this.danhSachNhanVienTrucThuoc;
    }

    public void setDanhSachNhanVienTrucThuoc(List<NhanVien> danhSachNhanVienTrucThuoc) {
        this.danhSachNhanVienTrucThuoc = danhSachNhanVienTrucThuoc;
    }

    public NhanVien getNhanVienQuanLy() {
        return this.nhanVienQuanLy;
    }

    public void setNhanVienQuanLy(NhanVienQuanLy nhanVienQuanLy) {
        this.nhanVienQuanLy = nhanVienQuanLy;
    }

    public Calendar getNgayQuanLyNhamChuc() {
        return ngayQuanLyNhamChuc;
    }

    public void setNgayQuanLyNhamChuc(Calendar ngayQuanLyNhamChuc) {
        this.ngayQuanLyNhamChuc = ngayQuanLyNhamChuc;
    }
}
