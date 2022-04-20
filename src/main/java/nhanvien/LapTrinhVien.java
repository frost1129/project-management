package nhanvien;

import phongban.PhongBan;

public class LapTrinhVien extends NhanVien {
    private double luongOvertime;

    public LapTrinhVien(int maNhanVien, String hoTen, String email, String gioiTinh, PhongBan phongBan,
                    double heSoLuong, double luongCoBan) {
        super(maNhanVien, hoTen, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
    }

    @Override
    public double tinhLuong() {
        return super.getHeSoLuong() * super.getLuongCoBan() + this.luongOvertime;
    }
}