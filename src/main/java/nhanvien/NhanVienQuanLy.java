package nhanvien;

import phongban.PhongBan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NhanVienQuanLy extends NhanVien {
    private final List<Calendar> ngayNhamChuc = new ArrayList<>();
    private final List<PhongBan> phongBanQuanLy = new ArrayList<>();

    {
        super.setMaNhanVien("001" + super.getMaNhanVien());
    }
    public NhanVienQuanLy() {};
    public NhanVienQuanLy(String hoTen, Calendar ngaySinh, String email, String gioiTinh, PhongBan phongBan,
                       double heSoLuong, double luongCoBan) {
        super(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
    }

    @Override
    public double tinhLuong() {
        return super.getHeSoLuong() * super.getLuongCoBan();
    }
}
