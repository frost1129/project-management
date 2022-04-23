package nhanvien;

import phongban.PhongBan;

import java.util.Calendar;

public class LapTrinhVien extends NhanVien {
    private double luongOvertime;

    {
        super.setMaNhanVien("002" + super.getMaNhanVien());
    }
    public LapTrinhVien() {};

    public LapTrinhVien(String hoTen, Calendar ngaySinh, String email, String gioiTinh, PhongBan phongBan,
                       double heSoLuong, double luongCoBan, double luongOvertime) {
        super(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
        this.luongOvertime = luongOvertime;
    }

    @Override
    public double tinhLuong() {
        return super.getHeSoLuong() * super.getLuongCoBan() + this.getLuongOvertime();
    }

    public double getLuongOvertime() {
        return luongOvertime;
    }

    public void setLuongOvertime(double luongOvertime) {
        this.luongOvertime = luongOvertime;
    }
}
