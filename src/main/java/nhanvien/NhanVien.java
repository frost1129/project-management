package nhanvien;

import duan.DuAn;
import phongban.PhongBan;
import dungchung.CauHinh;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class NhanVien {
    private static int dem = 0;
    /**
     * Quy ước
     * NhanVienBinhThuong: 000
     * NhanVienQuanLy: 001
     * LapTrinhVien: 002
     * ThietKeVien: 003
     * KiemThuVien: 004
     */
    private String maNhanVien = String.format("%05d", ++dem);
    private Calendar ngaySinh;
    private String hoTen;
    private String email;
    private String gioiTinh;
    private PhongBan phongBan;
    private double heSoLuong;
    private double luongCoBan;
    private List<DuAn> danhSachDuAnThamGia = new ArrayList<>();

    //Phương thức khởi tạo
    public NhanVien() {};

    public NhanVien(String hoTen, Calendar ngaySinh, String email, String gioiTinh, PhongBan phongBan,
                    double heSoLuong, double luongCoBan) {
        this.setHoTen(hoTen);
        this.setNgaySinh(ngaySinh);
        this.setEmail(email);
        this.setGioiTinh(gioiTinh);
        this.setPhongBan(phongBan);
        this.setHeSoLuong(heSoLuong);
        this.setLuongCoBan(luongCoBan);
    }

    /**
     * Nhập thông tin nhân viên từ bàn phím
     */
    public void nhapThongTin() {
        System.out.print("Nhập họ và tên: ");
        this.hoTen = CauHinh.sc.nextLine();

        System.out.print("Nhập ngày/tháng/năm sinh: " +
                "\nNhập ngày: ");
        int ngay = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("Nhập tháng: ");
        int thang = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("Nhập năm: ");
        int nam = Integer.parseInt(CauHinh.sc.nextLine());
        this.ngaySinh.set(nam, thang, ngay);

        System.out.print("Nhập email: ");
        this.setEmail(CauHinh.sc.nextLine());

        System.out.print("Nhập giới tính: ");
        this.gioiTinh = CauHinh.sc.nextLine();

        System.out.print("Nhập hệ số lương: ");
        this.heSoLuong = Integer.parseInt(CauHinh.sc.nextLine());

        System.out.print("Nhập lương cơ bản: ");
        this.luongCoBan = Integer.parseInt(CauHinh.sc.nextLine());
    }

    /**
     * Xem thông tin đối tượng nhân viên được gọi
     */
    public void xemThongTin() {
        System.out.printf("Mã nhân viên: %s\n", this.getMaNhanVien());
        System.out.printf("Họ và tên: %s\n", this.hoTen);
        System.out.printf("Email: %s\n", this.email);
        System.out.printf("Giới tính: %s\n", this.gioiTinh);
        System.out.printf("Hệ số lương: %.1f\n", this.heSoLuong);
        System.out.printf("Lương cơ bản: %.1f VNĐ\n", this.luongCoBan);
        System.out.printf("Phòng ban trực thuộc: %s\n", this.phongBan.getTenPhongBan());
    }

    /**
     * Xem danh sách dự án mà nhân viên này tham gia
     */
    public void danhSachDuAnThamGia() {
        System.out.printf("Danh sách dự án tham gia của %s\n", this.hoTen);
        for (DuAn duAn: danhSachDuAnThamGia) {
            System.out.printf("Mã dự án: %d\nTên dự án: %s\nNgày bắt đầu: %s\nNgày kết thúc: %s" +
                    "\nTổng kinh phí: %f\nChủ nhiệm dự án: %s\n\n", duAn.getMaDuAn(), duAn.getTenDuAn(),
                    CauHinh.f.format(duAn.getNgayBatDau()), CauHinh.f.format(duAn.getNgayKetThuc()), duAn.getTongKinhPhi(),
                    duAn.getChuNhiemDuAn().getHoTen());
        }
    }

    /**
     * Thêm phòng ban trực thuộc, điều kiện phòng ban này có trong danh sách phòng ban
     * @param ds
     * @param maPhongBan
     * @return true nếu thêm thành công, ngược lại false
     */
    public boolean nhapPhongBanTrucThuoc(List<PhongBan> ds, int maPhongBan) {
        for (PhongBan pb : ds) {
            if (pb.getMaPhongBan() == maPhongBan) {
                this.phongBan = pb;
                return true;
            }
        }
        return false;
    }

    /**
     * Thêm dự án tham gia cho nhân viên
     * @param duAn
     */
    public void themDuAnThamGia(DuAn duAn) {
        danhSachDuAnThamGia.add(duAn);
    }

    /**
     * Xóa dự án tham gia
     * @param maDuAn
     * @return
     */
    public boolean xoaDuAnThamGia(int maDuAn) {
        for(DuAn duAn: danhSachDuAnThamGia) {
            if (duAn.getMaDuAn() == maDuAn) {
                danhSachDuAnThamGia.remove(duAn);
                return true;
            }
        }
        return false;
    }

    /**
     * Tính lương cho nhân viên, phương thức này là trừu tượng
     * @return tiền lương của nhân viên
     */
    public abstract  double tinhLuong();

    //Các setter và getter
    public String getMaNhanVien() {
        return this.maNhanVien;
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

    public Calendar getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Calendar ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
}
