package nhanvien;

import phongban.PhongBan;

import java.util.Calendar;

public class ThietKeVien extends NhanVien {
    private double bonus;

    {
        super.setMaNhanVien("003" + super.getMaNhanVien());
    }

    public ThietKeVien() {};

    public ThietKeVien(String hoTen, Calendar ngaySinh, String email, String gioiTinh, PhongBan phongBan,
                       double heSoLuong, double luongCoBan, double bonus) {
        super(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
        this.bonus = bonus;
    }

    @Override
    public double tinhLuong() {
        return super.getHeSoLuong() * super.getLuongCoBan() + this.getBonus();
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
