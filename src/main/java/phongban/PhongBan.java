package phongban;

import dungchung.CauHinh;
import nhanvien.NhanVien;
import nhanvien.NhanVienQuanLy;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PhongBan {
    private int maPhongBan;
    private String tenPhongBan;
    private NhanVienQuanLy nhanVienQuanLy;
    private List<NhanVien> danhSachNhanVienTrucThuoc = new ArrayList<>();

    public PhongBan() {};

    /**
     * Hàm thêm nhân viên quản lý cho phòng ban này
     * @param ds
     * @param maNhanVien
     */
    public void nhapNhanVienQuanLy(List<NhanVien> ds, String maNhanVien) {
        ds.forEach(nhanVien -> {
            if (nhanVien.getMaNhanVien().equals(maNhanVien)) {
                this.nhanVienQuanLy = (NhanVienQuanLy) nhanVien;
                System.out.println("Nhập thời gian nhậm chức: ");
                System.out.print("Nhập ngày: ");
                int ngay = Integer.parseInt(CauHinh.sc.nextLine());
                System.out.print("Nhập tháng: ");
                int thang = Integer.parseInt(CauHinh.sc.nextLine());
                System.out.print("Nhập năm: ");
                int nam = Integer.parseInt(CauHinh.sc.nextLine());
                try {
                    CauHinh.d = CauHinh.f.parse(ngay + "/" + thang + "/" + nam);
                    CauHinh.c.setTime(CauHinh.d);
                    this.nhanVienQuanLy.setNgayNhamChuc(CauHinh.c);

                    PhongBan phongBanQuanLy = new PhongBan();
                    phongBanQuanLy.setMaPhongBan(this.maPhongBan);
                    phongBanQuanLy.setTenPhongBan(this.tenPhongBan);
                    phongBanQuanLy.setNhanVienQuanLy(this.nhanVienQuanLy);
                    phongBanQuanLy.setDanhSachNhanVienTrucThuoc(this.danhSachNhanVienTrucThuoc);
                    this.nhanVienQuanLy.setPhongBanQuanLy(phongBanQuanLy);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
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

    public void setNhanVienQuanLy(NhanVienQuanLy nhanVienQuanLy) {
        this.nhanVienQuanLy = nhanVienQuanLy;
    }
}
