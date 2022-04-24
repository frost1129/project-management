package phongban;

import dungchung.CauHinh;
import nhanvien.NhanVien;
import nhanvien.NhanVienQuanLy;
import nhanvien.QuanLyNhanVien;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanLyPhongBan {
    private static List<PhongBan> danhSachPhongBan = new ArrayList<>();

    static {
        String url = "src/main/resources/danhsachphongban.txt";
        File file = new File(url);
        try {
            Scanner sf = new Scanner(file);
            while (sf.hasNextLine()) {
                /**
                 * Chú thích dữ liệu trong file
                 * Dòng 1: Mã phòng ban
                 * Dòng 2: Tên phòng ban
                 * Dòng 3: Mã nhân viên quản lý
                 * Dòng 4: Ngày nhậm chức của quản lý
                 * Dòng 5: Ký tự "#" ngăn cách dữ liệu
                 */
                PhongBan phongBan = new PhongBan();
                int maPhongBan = Integer.parseInt(sf.nextLine());
                phongBan.setMaPhongBan(maPhongBan);

                phongBan.setTenPhongBan(sf.nextLine());

                String maNhanVienQuanLy = sf.nextLine();
                CauHinh.c.setTime(CauHinh.f.parse(sf.nextLine()));
                phongBan.setNgayQuanLyNhamChuc(CauHinh.c);

                NhanVienQuanLy nv = new NhanVienQuanLy();
                nv.setMaNhanVien(maNhanVienQuanLy);
                nv.setNgayNhamChuc(CauHinh.c);
                nv.setPhongBanQuanLy(maPhongBan);

                phongBan.setNhanVienQuanLy(nv);
                sf.nextLine(); //Bỏ qua kí tự "#"
                danhSachPhongBan.add(phongBan);
            }

        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm tìm xem phòng ban có tồn tại hay không thông qua mã phòng ban
     * @param maPhongBan
     * @return true nếu tìm thấy, ngược lại false
     */
    public boolean tonTaiPhongBan(int maPhongBan) {
        for (PhongBan phongBan: danhSachPhongBan) {
            if (phongBan.getMaPhongBan() == maPhongBan) {
                return true;
            }
        }
        return false;
    }

    public PhongBan timPhongBan(int maPhongBan) {
        for (PhongBan phongBan: danhSachPhongBan) {
            if (phongBan.getMaPhongBan() == maPhongBan) {
                return phongBan;
            }
        }
        return null;
    }

    /**
     * Hàm xem danh sách phòng ban hiện có
     */
    public void xemDanhSachPhongBan() {
        danhSachPhongBan.forEach(phongBan -> {
            phongBan.xemThongTin();
            System.out.println();
        });
    }

    /**
     * Hàm xem danh sách nhân viên của 1 phòng ban
     */
    public void xemDanhSachThanhVienCuaPhongBan() {
        int maPhongBan;
        do {
            System.out.print("* Nhập mã phòng ban: ");
            maPhongBan = Integer.parseInt(CauHinh.sc.nextLine());
            if (!this.tonTaiPhongBan(maPhongBan)) {
                System.out.println("* Mã phòng ban không tồn tại!");
            }
        } while (!this.tonTaiPhongBan(maPhongBan));
        System.out.printf("========== DANH SÁCH NHÂN VIÊN CỦA %s ===========\n", this.timPhongBan(maPhongBan).getTenPhongBan());

        int finalMaPhongBan = maPhongBan;
        danhSachPhongBan.forEach(phongBan -> {
            if (phongBan.getMaPhongBan() == finalMaPhongBan) {
                phongBan.getDanhSachNhanVienTrucThuoc().forEach(nhanVien -> {
                    int dem = 0;
                    System.out.printf("%d. %s - %s\n", ++dem, nhanVien.getMaNhanVien(), nhanVien.getHoTen());
                });
            }
        });
    }

    /**
     * Hàm thêm 1 nhân viên vào phòng ban
     */
    public void themNhanVienVaoPhongBan(QuanLyNhanVien ds) {
        int maPhongBan;
        do {
            System.out.print("* Nhập mã phòng ban: ");
            maPhongBan = Integer.parseInt(CauHinh.sc.nextLine());
            if (!this.tonTaiPhongBan(maPhongBan)) {
                System.out.println("* Mã phòng ban không tồn tại!");
            }
        } while (!this.tonTaiPhongBan(maPhongBan));

        String maNhanVien;
        do {
            do {
                System.out.print("Nhập mã nhân viên muốn thêm: ");
                maNhanVien = CauHinh.sc.nextLine();
                if (this.timPhongBan(maPhongBan).getDanhSachNhanVienTrucThuoc().contains(ds.timNhanVien(maNhanVien))) {
                    System.out.println("* Nhân viên này đã tồn tại trong phòng ban, nhập lại!");
                }
                if (!ds.tonTaiNhanVien(maNhanVien)) {
                    System.out.println("* Mã nhân viên không tồn tại, nhập lại!");
                }
            } while (this.timPhongBan(maPhongBan).getDanhSachNhanVienTrucThuoc().contains(ds.timNhanVien(maNhanVien)) ||
                    !ds.tonTaiNhanVien(maNhanVien));
            this.timPhongBan(maPhongBan).getDanhSachNhanVienTrucThuoc().add(ds.timNhanVien(maNhanVien));
            System.out.print("* Thêm thành công! Tiếp tục (No - 0 / Yes - 1): ");
        } while (Integer.parseInt(CauHinh.sc.nextLine()) != 0);
    }

    /**
     * Đồng bộ hóa dữ liệu từ các danh sách quản lý liên quan khác
     */
    public void hoanThienThongTinPhongBan() {
        danhSachPhongBan.forEach(phongBan -> phongBan.hoanThienThongTinNhanVienQuanLy(QuanLyNhanVien.getDanhSachNhanVien()));
    }

    public static List<PhongBan> getDanhSachPhongBan() {
        return danhSachPhongBan;
    }

    public void setDanhSachPhongBan(List<PhongBan> danhSachPhongBan) {
        QuanLyPhongBan.danhSachPhongBan = danhSachPhongBan;
    }
}
