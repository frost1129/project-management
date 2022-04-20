package duan;

import nhanvien.NhanVien;

import java.util.Calendar;
import java.util.List;

public class DuAn {
    private int maDuAn;
    private String tenDuAn;
    private Calendar ngayBatDau;
    private Calendar ngayKetThuc;
    private double tongKinhPhi;
    private NhanVien chuNhiemDuAn;
    private List<NhanVien> danhSachNhanVienThamGia;

    public DuAn() {}

    //Các setter và getter
    public int getMaDuAn() {
        return maDuAn;
    }

    public void setMaDuAn(int maDuAn) {
        this.maDuAn = maDuAn;
    }

    public String getTenDuAn() {
        return tenDuAn;
    }

    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }

    public Calendar getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Calendar ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Calendar getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Calendar ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public double getTongKinhPhi() {
        return tongKinhPhi;
    }

    public void setTongKinhPhi(double tongKinhPhi) {
        this.tongKinhPhi = tongKinhPhi;
    }

    public NhanVien getChuNhiemDuAn() {
        return chuNhiemDuAn;
    }

    public void setChuNhiemDuAn(NhanVien chuNhiemDuAn) {
        this.chuNhiemDuAn = chuNhiemDuAn;
    }

    public List<NhanVien> getDanhSachNhanVienThamGia() {
        return danhSachNhanVienThamGia;
    }

    public void setDanhSachNhanVienThamGia(List<NhanVien> danhSachNhanVienThamGia) {
        this.danhSachNhanVienThamGia = danhSachNhanVienThamGia;
    }
}
