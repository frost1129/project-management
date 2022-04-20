package nhanvien;

import duan.DuAn;
import phongban.PhongBan;
import dungchung.CauHinh;

import java.util.ArrayList;
import java.util.List;

public abstract class NhanVien {
    private int maNhanVien;
    private String hoTen;
    private String email;
    private String gioiTinh;
    private PhongBan phongBan;
    private double heSoLuong;
    private double luongCoBan;
    private List<DuAn> danhSachDuAnThamGia = new ArrayList<>();

    //Phương thức khởi tạo
    public NhanVien(int maNhanVien, String hoTen, String email, String gioiTinh, PhongBan phongBan,
                    double heSoLuong, double luongCoBan) {
        this.setMaNhanVien(maNhanVien);
        this.setHoTen(hoTen);
        this.setEmail(email);
        this.setGioiTinh(gioiTinh);
        this.setPhongBan(phongBan);
        this.setHeSoLuong(heSoLuong);
        this.setLuongCoBan(luongCoBan);
    }

    //Hàm thêm thông tin 1 nhân viên
    public void nhapThongTin() {
        System.out.print("Nhập mã nhân viên: ");
        this.maNhanVien = Integer.parseInt(CauHinh.sc.nextLine());

        System.out.print("Nhập họ và tên: ");
        this.hoTen = CauHinh.sc.nextLine();

        System.out.print("Nhập email: ");
        this.setEmail(CauHinh.sc.nextLine());

        System.out.print("Nhập giới tính: ");
        this.gioiTinh = CauHinh.sc.nextLine();

        System.out.print("Nhập mã phòng ban: ");
        int maPhongBan = Integer.parseInt(CauHinh.sc.nextLine());
        //Nhập phòng ban???

        System.out.print("Nhập hệ số lương: ");
        this.heSoLuong = Integer.parseInt(CauHinh.sc.nextLine());

        System.out.print("Nhập lương cơ bản: ");
        this.luongCoBan = Integer.parseInt(CauHinh.sc.nextLine());
    }

    //Hàm xem thông tin 1 nhân viên
    public void xemThongTin() {
        System.out.printf("Mã nhân viên: %s", this.maNhanVien);
        System.out.printf("Họ và tên: %s", this.hoTen);
        System.out.printf("Email: %s", this.email);
        System.out.printf("Giới tính: %s", this.gioiTinh);
        System.out.printf("Hệ số lương: %.1f", this.heSoLuong);
        System.out.printf("Lương cơ bản: %f", this.luongCoBan);
        //Xem phòng ban???
    }

    //Hàm xem danh sách dự án tham gia của nhân viên này
    public List<DuAn> xemDanhSachDuAnThamGia() {
        return this.getDanhSachDuAnThamGia();
    }

    //Hàm thêm dự án tham gia
    public void themDuAnThamGia(DuAn duAn) {
        danhSachDuAnThamGia.add(duAn);
    }

    public boolean xoaDuAnThamGia(int maDuAn) {
        for(DuAn duAn: danhSachDuAnThamGia) {
            if (duAn.getMaDuAn() == maDuAn) {
                danhSachDuAnThamGia.remove(duAn);
                return true;
            }
        }
        return false;
    }

    //Hàm tính lương - TRỪU TƯỢNG
    public abstract  double tinhLuong();

    //Các setter và getter
    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public List<DuAn> getDanhSachDuAnThamGia() {
        return danhSachDuAnThamGia;
    }

    public void setDanhSachDuAn(List<DuAn> danhSachDuAn) {
        this.danhSachDuAnThamGia = danhSachDuAn;
    }
}
