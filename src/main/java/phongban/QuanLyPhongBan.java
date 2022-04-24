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
                NhanVienQuanLy nv = new NhanVienQuanLy();
                nv.setMaNhanVien(maNhanVienQuanLy);
                nv.setNgayNhamChuc(CauHinh.c);

                phongBan.themNhanVienQuanLy(nv);
                sf.nextLine(); //Bỏ qua kí tự "#"
                danhSachPhongBan.add(phongBan);
            }

        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }

    }

    public void docFile() {

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

    public static List<PhongBan> getDanhSachPhongBan() {
        return danhSachPhongBan;
    }

    public void setDanhSachPhongBan(List<PhongBan> danhSachPhongBan) {
        QuanLyPhongBan.danhSachPhongBan = danhSachPhongBan;
    }
}
