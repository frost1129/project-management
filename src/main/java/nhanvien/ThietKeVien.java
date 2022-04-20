package nhanvien;

import phongban.PhongBan;

public class ThietKeVien extends NhanVien {
    private double bonus;

    public ThietKeVien(int maNhanVien, String hoTen, String email, String gioiTinh, PhongBan phongBan,
                        double heSoLuong, double luongCoBan) {
        super(maNhanVien, hoTen, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
    }

    @Override
    public double tinhLuong() {
        return super.getHeSoLuong() * super.getLuongCoBan() + this.bonus;
    }
}
