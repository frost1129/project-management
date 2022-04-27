package duan;

import dungchung.CauHinh;
import nhanvien.NhanVien;
import nhanvien.QuanLyNhanVien;

import java.text.ParseException;
import java.util.*;

public class DuAn {
    private static int dem = 0;
    private int maDuAn;
    private String tenDuAn;
    private Calendar ngayBatDau;
    private Calendar ngayKetThuc;
    private double tongKinhPhi;
    private NhanVien chuNhiemDuAn;
    private List<NhanVien> danhSachNhanVienThamGia = new ArrayList<>();

    public DuAn() {dem++;}

    public void nhapThongTin(QuanLyNhanVien danhSachNhanVien) throws ParseException {
        this.maDuAn = dem;
        int ngay, thang, nam;

        System.out.print("Nhập tên dự án: ");
        this.tenDuAn = CauHinh.sc.nextLine();

        System.out.print("Nhập thời điểm bắt đầu: \n");
        System.out.print("Nhập ngày: ");
        ngay = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("Nhập tháng: ");
        thang = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("Nhập năm: ");
        nam = Integer.parseInt(CauHinh.sc.nextLine());
        Calendar ngayBatDau = new GregorianCalendar();
        ngayBatDau.setTime(CauHinh.f.parse(ngay + "/" + thang + "/" + nam));
        this.setNgayBatDau(ngayBatDau);

        System.out.print("Nhập thời điểm kết thúc: \n");
        System.out.print("Nhập ngày: ");
        ngay = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("Nhập tháng: ");
        thang = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("Nhập năm: ");
        nam = Integer.parseInt(CauHinh.sc.nextLine());
        Calendar ngayKetThuc = new GregorianCalendar();
        ngayKetThuc.setTime(CauHinh.f.parse(ngay + "/" + thang + "/" + nam));
        this.setNgayKetThuc(ngayKetThuc);

        System.out.print("Nhập kinh phí đầu tư: ");
        this.tongKinhPhi = Double.parseDouble(CauHinh.sc.nextLine());

        String maChuNhiem;
        do {
            System.out.print("Nhập mã chủ nhiệm dự án: ");
            maChuNhiem = CauHinh.sc.nextLine();
            if (!danhSachNhanVien.tonTaiNhanVien(maChuNhiem)) {
                System.out.println("Mã nhân viên không tồn tại, nhập lại");
            }
        } while (!danhSachNhanVien.tonTaiNhanVien(maChuNhiem));
        this.setChuNhiemDuAn(danhSachNhanVien.timNhanVien(maChuNhiem));
    }

    /**
     * Ham xem thong tin cua 1 du an
     */
    public void xemThongTin() {
        System.out.printf("* Mã dự án: %d\n", this.maDuAn);
        System.out.printf("* Tên dự án: %s\n", this.tenDuAn);
        System.out.printf("* Ngày bắt đầu: %d/%d/%d\n", this.getNgayBatDau().get(Calendar.DATE), this.getNgayBatDau().get(Calendar.MONTH) + 1, this.getNgayBatDau().get(Calendar.YEAR));
        System.out.printf("* Ngày bắt đầu: %d/%d/%d\n", this.getNgayKetThuc().get(Calendar.DATE), this.getNgayKetThuc().get(Calendar.MONTH) + 1, this.getNgayKetThuc().get(Calendar.YEAR));
        System.out.printf("* Tổng kinh phí: %.1f VNĐ\n", this.tongKinhPhi);
        System.out.printf("* Chủ nhiệm dự án: %s\n", this.getChuNhiemDuAn().getHoTen());
    }

    /**
     * Ham cap nhat thong tin cua du an nay
     * @param danhSachNhanVien
     * @throws ParseException
     */
    public void suaThongTin(QuanLyNhanVien danhSachNhanVien) throws ParseException {
        int ngay, thang, nam;
        System.out.print("* Nhập tên dự án mới: ");
        this.setTenDuAn(CauHinh.sc.nextLine());

        System.out.print("* Nhập thời gian bắt đầu: \n** Nhập ngày: ");
        ngay = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("** Nhập tháng: ");
        thang = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("** Nhập năm: ");
        nam = Integer.parseInt(CauHinh.sc.nextLine());
        Calendar ngayBatDau = new GregorianCalendar();
        ngayBatDau.setTime(CauHinh.f.parse(ngay + "/" + thang + "/" + nam));
        this.setNgayBatDau(ngayBatDau);

        System.out.print("* Nhập thời gian kết thúc: \n** Nhập ngày: ");
        ngay = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("** Nhập tháng: ");
        thang = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("** Nhập năm: ");
        nam = Integer.parseInt(CauHinh.sc.nextLine());
        Calendar ngayKetThuc = new GregorianCalendar();
        ngayKetThuc.setTime(CauHinh.f.parse(ngay + "/" + thang + "/" + nam));
        this.setNgayKetThuc(ngayKetThuc);

        System.out.print("* Nhập tổng kinh phí mới: ");
        this.setTongKinhPhi(Double.parseDouble(CauHinh.sc.nextLine()));

        String maChuNhiemDuAn;
        do {
            System.out.print("* Nhập mã chủ nhiệm mới: ");
            maChuNhiemDuAn = CauHinh.sc.nextLine();
            if (!danhSachNhanVien.tonTaiNhanVien(maChuNhiemDuAn)) {
                System.out.println("* Mã nhân viên không tồn tại!");
            }
        } while (!danhSachNhanVien.tonTaiNhanVien(maChuNhiemDuAn));
        this.setChuNhiemDuAn(danhSachNhanVien.timNhanVien(maChuNhiemDuAn));
    }

    /**
     * Ham xem danh sach nhan vien tham gia du an
     */
    public void xemDanhSachNhanVienThamGia() {
        System.out.println("========== DANH SÁCH NHÂN VIÊN THAM GIA DỰ ÁN =========");
        if (danhSachNhanVienThamGia.isEmpty()) {
            System.out.println("Rỗng!");
        }
        int dem = 0;
        for (NhanVien nv: this.danhSachNhanVienThamGia) {
            System.out.printf("%d. %s - %s\n", ++dem, nv.getMaNhanVien(), nv.getHoTen());
        }
    }

    /**
     * Ham them 1 nhan vien tham gia du an
     */
    public void themNhanVienThamGia(NhanVien nhanVienThamGia) {
        this.danhSachNhanVienThamGia.add(nhanVienThamGia);
    }

    /**
     * Ham them chu nhiem du an
     * @param chuNhiemDuAn
     */
    public void themChuNhiem(NhanVien chuNhiemDuAn) {
        this.chuNhiemDuAn =  chuNhiemDuAn;
    }

    public void hoanThienThongTinChuNhiemDuAn(List<NhanVien> ds) {
        for (NhanVien nv: ds) {
            if (nv.getMaNhanVien().equals(this.getChuNhiemDuAn().getMaNhanVien())) {
                this.chuNhiemDuAn = nv;
                return;
            }
            if (this.getChuNhiemDuAn().getMaNhanVien().equals("-1")) {
                this.getChuNhiemDuAn().setHoTen("Chưa có chủ nhiệm dự án");
            }
        }
    }

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
