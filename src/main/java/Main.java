import duan.QuanLyDuAn;
import dungchung.CauHinh;
import nhanvien.QuanLyNhanVien;
import phongban.QuanLyPhongBan;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class Main {

    public static void main (String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ParseException {
        //Phần khai báo
        int luaChonChinh;
        QuanLyNhanVien danhSachNhanVien = new QuanLyNhanVien();
        QuanLyPhongBan danhSachPhongBan = new QuanLyPhongBan();
        QuanLyDuAn danhSachDuAn = new QuanLyDuAn();

        danhSachNhanVien.dongBoHoaDuLieu();
        danhSachPhongBan.dongBoHoaDuLieu();
        danhSachDuAn.dongBoHoaDuLieu();

        while (true) {
            menuChinh();
            luaChonChinh = Integer.parseInt(CauHinh.sc.nextLine());
            if (luaChonChinh == 0) {
                System.out.println("Thoát chương trình...");
                break;
            } else if (luaChonChinh >= 1 && luaChonChinh <= 3) {
                switch (luaChonChinh) {
                    case 1 -> menuNhanVien(danhSachNhanVien, danhSachPhongBan, danhSachDuAn);
                    case 2 -> menuPhongBan(danhSachNhanVien, danhSachPhongBan, danhSachDuAn);
                    case 3 -> menuDuAn(danhSachNhanVien, danhSachPhongBan, danhSachDuAn);
                }
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    //Hàm in menu lựa chọn chính
    public static void menuChinh () {
        System.out.print("--- DANH SÁCH CHỨC NĂNG ---" +
                "\n1. Nhân viên" +
                "\n2. Phòng ban" +
                "\n3. Dự án" +
                "\n0. Thoát chương trình" +
                "\nNhập lựa chọn (0 - 3): ");
    }
    //Hàm in menu các chức năng của nhân viên
    public static void menuNhanVien (QuanLyNhanVien danhSachNhanVien, QuanLyPhongBan danhSachPhongBan, QuanLyDuAn danhSachDuAn) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ParseException {
        int luaChon;
        while (true) {
            System.out.print("--- CHỨC NĂNG NHÂN VIÊN ---" +
                    "\n1. Thêm 1 nhân viên" +
                    "\n2. Xem danh sách nhân viên" +
                    "\n3. Thêm dự án tham gia cho nhân viên" +
                    "\n4. Xem dự án tham gia của nhân viên" +
                    "\n5. Xóa dự án tham gia của 1 nhân viên" +
                    "\n6. Tìm kiếm nhân viên theo họ tên, ngày sinh, theo phòng ban" +
                    "\n7. Tính lương cho 1 nhân viên" +
                    "\n8. Thêm phòng ban cho nhân viên quản lý" +
                    "\n0. Thoát menu nhân viên" +
                    "\nNhập lựa chọn (0 - 8): ");
            luaChon = Integer.parseInt(CauHinh.sc.nextLine());
            if (luaChon == 0) {
                System.out.println("Thoát chức năng nhân viên...");
                break;
            } else if (luaChon >= 1 && luaChon <= 8) {
                switch (luaChon) {
                    case 1 -> danhSachNhanVien.themNhanVien(danhSachPhongBan);
                    case 2 -> danhSachNhanVien.xemDanhSachNhanVien();
                    case 3 -> danhSachNhanVien.themDuAnThamGiaCuaNhanVien(danhSachDuAn);
                    case 4 -> danhSachNhanVien.xemDuAnThamGiaCuaNhanVien();
                    case 5 -> danhSachNhanVien.xoaDuAnThamGiaCuaNhanVien(danhSachDuAn);
                    case 6 -> danhSachNhanVien.xuatDanhSachNhanVienTimDuoc();
                    case 7 -> danhSachNhanVien.tinhLuongChoNhanVien();
                    case 8 -> danhSachNhanVien.themPhongBanCuaNhanVienQuanLy(danhSachPhongBan);
                }
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
    //Hàm in menu các chức năng của phòng ban
    public static void menuPhongBan (QuanLyNhanVien danhSachNhanVien, QuanLyPhongBan danhSachPhongBan, QuanLyDuAn danhSachDuAn) {
        int luaChon;
        while (true) {
            System.out.print("--- CHỨC NĂNG PHÒNG BAN ---" +
                    "\n1. Xem danh sách phòng ban" +
                    "\n2. Xem thông tin người quản lý phòng ban" +
                    "\n3. Thêm nhân viên vào phòng ban" +
                    "\n4. Xem danh sách nhân viên của phòng ban" +
                    "\n0. Thoát menu phòng ban" +
                    "\nNhập lựa chọn (0 - 4): ");
            luaChon = Integer.parseInt(CauHinh.sc.nextLine());
            if (luaChon == 0) {
                System.out.println("* Thoát menu phòng ban...");
                break;
            } else if (luaChon >= 1 && luaChon <= 4) {
                switch (luaChon) {
                    case 1 -> danhSachPhongBan.xemDanhSachPhongBan();
                    case 2 -> danhSachPhongBan.xemThongTinNhanVienQuanLyPhongBan();
                    case 3 -> danhSachPhongBan.themNhanVienVaoPhongBan(danhSachNhanVien);
                    case 4 -> {
                        danhSachPhongBan.xemDanhSachThanhVienCuaPhongBan();
                        System.out.println();
                    }
                }
            }
        }
    }
    //Hàm in menu các chức năng của dự án
    public static void menuDuAn (QuanLyNhanVien danhSachNhanVien, QuanLyPhongBan danhSachPhongBan, QuanLyDuAn danhSachDuAn) throws ParseException {
        int luaChon;
        while (true) {
            System.out.print("--- CHỨC NĂNG DỰ ÁN ---" +
                    "\n1. Xem danh sách dự án hiện có" +
                    "\n2. Thêm 1 dự án" +
                    "\n3. Sửa 1 dự án" +
                    "\n4. Xóa 1 dự án" +
                    "\n5. Xem danh sách nhân viên của 1 dự án" +
                    "\n6. Tìm kiếm dự án theo tên và thời điểm bắt đầu" +
                    "\n7. Sắp xếp dự án theo kinh phí đầu tư" +
                    "\n8. Gán nhân viên cho dự án" +
                    "\n9. Gán người quản lý cho dự án" +
                    "\n0. Thoát menu dự án" +
                    "\nNhập lựa chọn (0 - 9): ");
            luaChon = Integer.parseInt(CauHinh.sc.nextLine());
            if (luaChon == 0) {
                System.out.println("Thoát menu dự án...");
                break;
            } else if (luaChon >= 1 && luaChon <= 9) {
                switch (luaChon) {
                    case 1 -> danhSachDuAn.xemDanhSachDuAn();
                    case 2 -> danhSachDuAn.themDuAn(danhSachNhanVien);
                    case 3 -> danhSachDuAn.suaDuAn(danhSachNhanVien);
                    case 4 -> danhSachDuAn.xoaDuAn();
                    case 5 -> danhSachDuAn.xemDanhSachNhanVienCuaDuAn();
                    case 6 -> danhSachDuAn.xemDanhSachDuAnTimDuoc();
                    case 7 -> {
                        danhSachDuAn.sapXepDuAn();
                        danhSachDuAn.xemDanhSachDuAn();
                    }
                    case 8 -> danhSachDuAn.themNhanVienChoDuAn(danhSachNhanVien);
                    case 9 -> danhSachDuAn.themChuNhiemChoDuAn(danhSachNhanVien);
                }
            } else {
                System.out.println("Lựa chọn không hợp lệ, nhập lại!");
            }
        }
    }
}
