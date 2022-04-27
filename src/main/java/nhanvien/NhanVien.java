package nhanvien;

import duan.DuAn;
import phongban.PhongBan;
import dungchung.CauHinh;
import phongban.QuanLyPhongBan;

import java.text.ParseException;
import java.util.*;

public abstract class NhanVien {
    private static int dem = 0;
    /**
     * Quy ước
     * NhanVienQuanLy: 000
     * NhanVienBinhThuong: 001
     * LapTrinhVien: 002
     * ThietKeVien: 003
     * KiemThuVien: 004
     */
    private String maNhanVien = String.format("%05d", ++dem);
    private Calendar ngaySinh = new GregorianCalendar();
    private String hoTen;
    private String email;
    private String gioiTinh;
    private PhongBan phongBan;
    private double heSoLuong;
    private double luongCoBan;
    private List<DuAn> danhSachDuAnThamGia = new ArrayList<>();

    //Phương thức khởi tạo

    /**
     * Phương thức khởi tạo không tham số này dùng cho phương thức themNhanVien ở class QuanLyNhanVien
     */
    public NhanVien() {}

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
    public void nhapThongTin(QuanLyPhongBan danhSachPhongBan) throws ParseException {
        System.out.print("Nhập họ và tên: ");
        this.hoTen = CauHinh.sc.nextLine();

        System.out.print("Nhập ngày/tháng/năm sinh: " +
                "\nNhập ngày: ");
        int ngay = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("Nhập tháng: ");
        int thang = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("Nhập năm: ");
        int nam = Integer.parseInt(CauHinh.sc.nextLine());
        Date d = CauHinh.f.parse(ngay + "/" + thang + "/" + nam);
        this.ngaySinh.setTime(d);

        System.out.print("Nhập email: ");
        this.setEmail(CauHinh.sc.nextLine());

        System.out.print("Nhập giới tính: ");
        this.gioiTinh = CauHinh.sc.nextLine();

        System.out.print("Nhập hệ số lương: ");
        this.heSoLuong = Double.parseDouble(CauHinh.sc.nextLine());

        System.out.print("Nhập lương cơ bản: ");
        this.luongCoBan = Double.parseDouble(CauHinh.sc.nextLine());

        int maPhongBan;
        do {
            System.out.print("Nhập mã phòng ban: ");
            maPhongBan = Integer.parseInt(CauHinh.sc.nextLine());
            if (!danhSachPhongBan.tonTaiPhongBan(maPhongBan)) {
                System.out.println("Mã phòng ban không tồn tại, nhập lại!");
            }
        } while (!danhSachPhongBan.tonTaiPhongBan(maPhongBan));
        this.setPhongBan(danhSachPhongBan.timPhongBan(maPhongBan));
    }

    /**
     * Xem thông tin đối tượng nhân viên được gọi
     */
    public void xemThongTin() {
        System.out.printf("Mã nhân viên: %s\n", this.getMaNhanVien());
        System.out.printf("Họ và tên: %s\n", this.hoTen);
        System.out.printf("Ngày sinh: %s/%s/%s\n", this.ngaySinh.get(Calendar.DATE), this.ngaySinh.get(Calendar.MONTH) + 1, this.ngaySinh.get(Calendar.YEAR));
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
        if (this.danhSachDuAnThamGia.isEmpty()) {
            System.out.println("Danh sách rỗng!");
        } else {
            for (DuAn duAn : danhSachDuAnThamGia) {
                System.out.printf("Mã dự án: %d\nTên dự án: %s\nNgày bắt đầu: %d/%d/%d\nNgày kết thúc: %d/%d/%d" +
                                "\nTổng kinh phí: %.1f VNĐ\nChủ nhiệm dự án: %s\n", duAn.getMaDuAn(), duAn.getTenDuAn(),
                        duAn.getNgayBatDau().get(Calendar.DATE), duAn.getNgayBatDau().get(Calendar.MONTH) + 1, duAn.getNgayBatDau().get(Calendar.YEAR),
                        duAn.getNgayKetThuc().get(Calendar.DATE), duAn.getNgayKetThuc().get(Calendar.MONTH) + 1, duAn.getNgayKetThuc().get(Calendar.YEAR), duAn.getTongKinhPhi(),
                        duAn.getChuNhiemDuAn().getHoTen());
            }
        }
    }

    /**
     * Thêm phòng ban trực thuộc, điều kiện phòng ban này có trong danh sách phòng ban
     * @param ds
     */
    public void hoanThienThongTinPhongBanTrucThuoc(List<PhongBan> ds) {
        for (PhongBan pb : ds) {
            if (pb.getMaPhongBan() == this.getPhongBan().getMaPhongBan()) {
                this.phongBan = pb;
                return;
            }
        }
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

    public boolean tonTaiDuAnThamGia(int maDuAn) {
        for(DuAn duAn: danhSachDuAnThamGia) {
            if (duAn.getMaDuAn() == maDuAn) {
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
