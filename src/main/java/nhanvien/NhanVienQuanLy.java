package nhanvien;

import phongban.PhongBan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NhanVienQuanLy extends NhanVien {
    private final List<Calendar> ngayNhamChuc = new ArrayList<>();
    private final List<Integer> phongBanQuanLy = new ArrayList<>();

    {
        super.setMaNhanVien("000" + super.getMaNhanVien());
    }
    public NhanVienQuanLy() {}

    public NhanVienQuanLy(String hoTen, Calendar ngaySinh, String email, String gioiTinh, PhongBan phongBan,
                       double heSoLuong, double luongCoBan) {
        super(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
    }

    /**
     * Hàm kiểm tra phòng ban quản lý của nhân viên có tồn tại hay không
     * @param maPhongBan
     * @return nếu có trả true, ngược lại false
     */
    public boolean tonTaiPhongBanDaQuanLy(int maPhongBan) {
        for(Integer ma : phongBanQuanLy) {
            if (ma == maPhongBan) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double tinhLuong() {
        return super.getHeSoLuong() * super.getLuongCoBan();
    }

    public List<Calendar> getNgayNhamChuc() {
        return ngayNhamChuc;
    }

    public void setNgayNhamChuc(Calendar ngayNhamChuc) {
        this.ngayNhamChuc.add(ngayNhamChuc);
    }

    public List<Integer> getPhongBanQuanLy() {
        return phongBanQuanLy;
    }

    public void setPhongBanQuanLy(int maPhongBan) {
        this.phongBanQuanLy.add(maPhongBan);
    }
}
