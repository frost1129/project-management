package nhanvien;

import phongban.PhongBan;

import java.util.Calendar;

public class NhanVienBinhThuong extends NhanVien {
    {
        super.setMaNhanVien("000" + super.getMaNhanVien());
    }
    public NhanVienBinhThuong() {};
    public NhanVienBinhThuong(String hoTen, Calendar ngaySinh, String email, String gioiTinh, PhongBan phongBan,
                       double heSoLuong, double luongCoBan) {
        super(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
    }

    @Override
    public double tinhLuong() {
        return super.getHeSoLuong() * super.getLuongCoBan();
    }
}
