package nhanvien;

import phongban.PhongBan;

import java.util.Calendar;

public class KiemThuVien extends NhanVien {
    private int soLoiPhatHien;
    {
        super.setMaNhanVien("004" + super.getMaNhanVien());
    }
    public KiemThuVien() {}

    public KiemThuVien(String hoTen, Calendar ngaySinh, String email, String gioiTinh, PhongBan phongBan,
                       double heSoLuong, double luongCoBan, int soLoiPhatHien) {
        super(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
        this.soLoiPhatHien = soLoiPhatHien;
    }

    @Override
    public double tinhLuong() {
        return super.getHeSoLuong() * super.getLuongCoBan() + (this.getSoLoiPhatHien() * 200000);
    }

    public int getSoLoiPhatHien() {
        return soLoiPhatHien;
    }

    public void setSoLoiPhatHien(int soLoiPhatHien) {
        this.soLoiPhatHien = soLoiPhatHien;
    }
}
