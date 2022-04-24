package nhanvien;

import java.util.ArrayList;
import java.util.List;

public class DanhSachNhanVienQuanLy {
    private List<NhanVienQuanLy> danhSach = new ArrayList<>();

    /**
     * Hàm tìm nhân viên quản lý với mã tương ứng
     * @param maNhanVien
     * @return trả về 1 nhân viên nếu tìm thấy, ngược lại trả null
     */
    public NhanVienQuanLy timNhanVienQuanLy(String maNhanVien) {
        for (NhanVienQuanLy nv: this.danhSach) {
            if (nv.getMaNhanVien().equals(maNhanVien)) {
                return nv;
            }
        }
        return null;
    }

    //Các setter và getter
    public List<NhanVienQuanLy> getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(List<NhanVienQuanLy> danhSach) {
        this.danhSach = danhSach;
    }
}
