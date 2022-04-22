package nhanvien;

import phongban.PhongBan;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class QuanLyNhanVien {
    private List<NhanVien> danhSachNhanVien = new ArrayList<>();

    /**
     * Thêm 1 nhân viên với loại nhân viên bất kì: LapTrinhVien, KiemThuVien,....
     * @param loaiNhanVien
     * @param danhSachPhongBan
     * @param maPhongBan
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void themNhanVien(String loaiNhanVien, List<PhongBan> danhSachPhongBan, int maPhongBan) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class c = Class.forName(loaiNhanVien);
        NhanVien nv = (NhanVien) c.getConstructor().newInstance();
        nv.nhapThongTin();
        if (nv.nhapPhongBanTrucThuoc(danhSachPhongBan, maPhongBan)) {
            this.danhSachNhanVien.add(nv);
            System.out.println("Thêm nhân viên thành công!");
        } else {
            System.out.println("Thêm nhân viên thất bại! Phòng ban không tồn tại");
        }
    }

    /**
     * Hàm in ra danh sách nhân viên hiện có
     */
    public void xemDanhSachNhanVien() {
        System.out.println("========== DANH SÁCH NHÂN VIÊN ==========");
        danhSachNhanVien.forEach(nhanVien -> {
            nhanVien.xemThongTin();
            System.out.println();
        });
    }

    /**
     * In ra màn hinh danh sách dự án mà 1 nhân viên tham gia
     * @param maNhanVien
     */
    public void xemDanhSachDuAnThamGia(String maNhanVien) {
        this.danhSachNhanVien.forEach(nhanVien -> {
            if (nhanVien.getMaNhanVien() == maNhanVien) {
                nhanVien.danhSachDuAnThamGia();
                System.out.println();
                return;
            }
        });
    }

    /**
     * Tìm nhân viên theo Mã nhân viên
     * @param maNhanVien
     * @return Nhân viên với mã tương ứng
     */
    public NhanVien timNhanVien(String maNhanVien) {
        for(NhanVien nhanVien : danhSachNhanVien) {
            if (nhanVien.getMaNhanVien().equals(maNhanVien)) {
                return nhanVien;
            }
        }
        return null;
    }

    /**
     * Tìm nhân viên theo họ tên và ngày sinh và phòng ban
     * @param hoTen
     * @param ngaySinh
     * @param tenPhongBan
     * @return danh sách nhân viên
     */
    public List<NhanVien> timNhanVien(String hoTen, Calendar ngaySinh, String tenPhongBan) {
        return this.danhSachNhanVien.stream().filter(nhanVien ->
                nhanVien.getHoTen().equals(hoTen) && nhanVien.getNgaySinh() == ngaySinh &&
                        nhanVien.getPhongBan().getTenPhongBan().equals(tenPhongBan)).collect(Collectors.toList());
    }

    //Các setter và getter
    public List<NhanVien> getDanhSachNhanVien() {
        return danhSachNhanVien;
    }

    public void setDanhSachNhanVien(List<NhanVien> danhSachNhanVien) {
        this.danhSachNhanVien = danhSachNhanVien;
    }
}
