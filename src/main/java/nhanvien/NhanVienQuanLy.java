package nhanvien;

import phongban.PhongBan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NhanVienQuanLy extends NhanVien {
    private final List<Calendar> ngayNhamChuc = new ArrayList<>();
    private final List<PhongBan> phongBanQuanLy = new ArrayList<>();

    public NhanVienQuanLy(int maNhanVien, String hoTen, String email, String gioiTinh, PhongBan phongBan,
                              double heSoLuong, double luongCoBan) {
        super(maNhanVien, hoTen, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
    }

    @Override
    public double tinhLuong() {
        return super.getHeSoLuong() * super.getLuongCoBan();
    }
}
