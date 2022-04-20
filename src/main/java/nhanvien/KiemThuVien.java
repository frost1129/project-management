package nhanvien;

import phongban.PhongBan;

public class KiemThuVien extends NhanVien {
    private int soLoiPhatHien;

    public KiemThuVien(int maNhanVien, String hoTen, String email, String gioiTinh, PhongBan phongBan,
                        double heSoLuong, double luongCoBan) {
        super(maNhanVien, hoTen, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
    }

    @Override
    public double tinhLuong() {
        return super.getHeSoLuong() * super.getLuongCoBan() + (this.soLoiPhatHien * 200000);
    }
}
