package nhanvien;

import duan.DuAn;
import dungchung.CauHinh;
import phongban.PhongBan;
import phongban.QuanLyPhongBan;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class QuanLyNhanVien {
    private static List<NhanVien> danhSachNhanVien = new ArrayList<>();

    /**
     * Thêm dữ liệu từ file
     */
    static {
        String url = "src/main/resources/danhsachnhanvien.txt";
        File file = new File(url);
        try {
            Scanner sf = new Scanner(file);
            while (sf.hasNextLine()) {
                /**
                 * Chú thích dữ liệu trong file
                 * Dòng 1: Mã loại nhân viên
                 * Dòng 2: Họ tên nhân viên
                 * Dòng 3: Ngày sinh
                 * Dòng 4: Email
                 * Dòng 5: Giới tính
                 * Dòng 6: Phòng ban trực thuộc
                 * Dòng 7: Hệ số lương
                 * Dòng 8: Lương cơ bản
                 * Dòng 9 (Trừ nhân viên quản lý với nhân viên bình thường): Lương thưởng thêm / số lỗi phát hiện
                 * Dòng 10: Kí tự "#" ngăn cách dữ liệu
                 */
                NhanVien nhanVien;

                String loaiNhanVien = sf.nextLine();
                String hoTen = sf.nextLine();

                Date d = CauHinh.f.parse(sf.nextLine());
                Calendar ngaySinh = new GregorianCalendar();
                ngaySinh.setTime(d);

                String email = sf.nextLine();
                String gioiTinh = sf.nextLine();
                PhongBan phongBan = new PhongBan();
                phongBan.setMaPhongBan(Integer.parseInt(sf.nextLine()));


                double heSoLuong = Double.parseDouble(sf.nextLine());
                double luongCoBan = Double.parseDouble(sf.nextLine());

                switch (loaiNhanVien) {
                    case "000" -> {
                        sf.nextLine(); //Bỏ qua lương thưởng thêm, mặc định "-1"
                        nhanVien = new NhanVienQuanLy(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
                        nhanVien.hoanThienThongTinPhongBanTrucThuoc(QuanLyPhongBan.getDanhSachPhongBan(), phongBan.getMaPhongBan());
                        danhSachNhanVien.add(nhanVien);
                    }
                    case "001" -> {
                        sf.nextLine(); //Bỏ qua lương thưởng thêm, mặc định "-1"
                        nhanVien = new NhanVienBinhThuong(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
                        nhanVien.hoanThienThongTinPhongBanTrucThuoc(QuanLyPhongBan.getDanhSachPhongBan(), phongBan.getMaPhongBan());
                        danhSachNhanVien.add(nhanVien);
                    }
                    case "002" -> {
                        double luongOverTime = Double.parseDouble(sf.nextLine());
                        nhanVien = new LapTrinhVien(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan, luongOverTime);
                        nhanVien.hoanThienThongTinPhongBanTrucThuoc(QuanLyPhongBan.getDanhSachPhongBan(), phongBan.getMaPhongBan());
                        danhSachNhanVien.add(nhanVien);
                    }
                    case "003" -> {
                        double bonus = Double.parseDouble(sf.nextLine());
                        nhanVien = new ThietKeVien(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan, bonus);
                        nhanVien.hoanThienThongTinPhongBanTrucThuoc(QuanLyPhongBan.getDanhSachPhongBan(), phongBan.getMaPhongBan());
                        danhSachNhanVien.add(nhanVien);
                    }
                    case "004" -> {
                        int loiPhatHien = Integer.parseInt(sf.nextLine());
                        nhanVien = new KiemThuVien(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan, loiPhatHien);
                        nhanVien.hoanThienThongTinPhongBanTrucThuoc(QuanLyPhongBan.getDanhSachPhongBan(), phongBan.getMaPhongBan());
                        danhSachNhanVien.add(nhanVien);
                    }
                }
                sf.nextLine();
            }
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }

    }

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
    public void themNhanVien(String loaiNhanVien, List<PhongBan> danhSachPhongBan, int maPhongBan) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ParseException {
        Class c = Class.forName(loaiNhanVien);
        NhanVien nv = (NhanVien) c.getConstructor().newInstance();
        nv.nhapThongTin();
        nv.hoanThienThongTinPhongBanTrucThuoc(danhSachPhongBan, maPhongBan);
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
        danhSachNhanVien.forEach(nhanVien -> {
            if (nhanVien.getMaNhanVien() == maNhanVien) {
                nhanVien.danhSachDuAnThamGia();
                System.out.println();
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

    public boolean tonTaiNhanVien(String maNhanVien) {
        for(NhanVien nhanVien : danhSachNhanVien) {
            if (nhanVien.getMaNhanVien().equals(maNhanVien)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tìm nhân viên theo họ tên và ngày sinh và phòng ban
     * @param hoTen
     * @param ngaySinh
     * @param tenPhongBan
     * @return danh sách nhân viên
     */
    public List<NhanVien> timNhanVien(String hoTen, Calendar ngaySinh, String tenPhongBan) {
        return danhSachNhanVien.stream().filter(nhanVien ->
                nhanVien.getHoTen().equals(hoTen) &&
                        (nhanVien.getNgaySinh().get(Calendar.DATE) == ngaySinh.get(Calendar.DATE) &&
                                nhanVien.getNgaySinh().get(Calendar.MONTH) == ngaySinh.get(Calendar.MONTH) &&
                                nhanVien.getNgaySinh().get(Calendar.YEAR) == ngaySinh.get(Calendar.YEAR)) &&
                        nhanVien.getPhongBan().getTenPhongBan().equals(tenPhongBan)).collect(Collectors.toList());
    }

    /**
     * Thêm dự án cho nhân viên có mã tương ứng
     * @param maNhanVien
     * @param ds
     * @param maDuAn
     */
    public void themDuAnThamGiaCuaNhanVien(String maNhanVien, List<DuAn> ds, int maDuAn) {
        danhSachNhanVien.forEach(nhanVien -> {
            if (nhanVien.getMaNhanVien().equals(maNhanVien)) {
                ds.forEach(duAn -> {
                    if (duAn.getMaDuAn() == maDuAn) {
                        nhanVien.themDuAnThamGia(duAn);
                        System.out.println("Thêm dự án thành công!");
                        return;
                    }
                });
            }
        });
        System.out.println("Thêm dự án thất bại!");
    }

    /**
     * Xóa dự án của nhân viên có mã tương ứng
     * @param maNhanVien
     * @param ds
     * @param maDuAn
     */
    public void xoaDuAnThamGiaCuaNhanVien(String maNhanVien, List<DuAn> ds, int maDuAn) {
        danhSachNhanVien.forEach(nhanVien -> {
            if (nhanVien.getMaNhanVien().equals(maNhanVien)) {
                ds.forEach(duAn -> {
                    if (duAn.getMaDuAn() == maDuAn && nhanVien.xoaDuAnThamGia(maDuAn)) {
                        System.out.println("Xóa dự án thành công!");
                        return;
                    }
                });
            }
        });
        System.out.println("Xóa dự án thất bại!");
    }

    //Các setter và getter
    public static List<NhanVien> getDanhSachNhanVien() {
        return danhSachNhanVien;
    }

    public void setDanhSachNhanVien(List<NhanVien> danhSachNhanVien) {
        QuanLyNhanVien.danhSachNhanVien = danhSachNhanVien;
    }
}
