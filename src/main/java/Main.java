import duan.QuanLyDuAn;
import dungchung.CauHinh;
import nhanvien.QuanLyNhanVien;
import phongban.QuanLyPhongBan;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class Main {
    public static QuanLyNhanVien danhSachNhanVien = new QuanLyNhanVien();
    public static QuanLyPhongBan danhSachPhongBan = new QuanLyPhongBan();
    public static QuanLyDuAn danhSachDuAn = new QuanLyDuAn();

    public static void main (String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ParseException {
        //Khai báo biến
        int luaChonChinh;
        QuanLyNhanVien danhSachNhanVien;

        while (true) {
            menuChinh();
            luaChonChinh = Integer.parseInt(CauHinh.sc.nextLine());
            if (luaChonChinh == 0) {
                System.out.println("Thoát chương trình...");
                break;
            } else if (luaChonChinh >= 1 && luaChonChinh <= 3) {
                int luaChonPhu;
                switch (luaChonChinh) {
                    case 1:
                        menuNhanVien();
                        break;
                    case 2:
                        break;
                    case 3:

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
                "\n2. Dự án" +
                "\n3. Phòng ban" +
                "\n0. Thoát chương trình" +
                "\nNhập lựa chọn (0 - 3): ");
    }

    //Hàm in menu các chức năng của nhân viên
    public static void menuNhanVien () throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ParseException {
        int luaChon;
        while (true) {
            System.out.print("--- CHỨC NĂNG NHÂN VIÊN ---" +
                    "\n1. Thêm 1 nhân viên" +
                    "\n2. Xem danh sách nhân viên" +
                    "\n3. Thêm dự án tham gia cho nhân viên" +
                    "\n4. Xóa dự án tham gia của nhân viên" +
                    "\n5. Xem danh sách dự án tham gia của 1 nhân viên" +
                    "\n6. Tìm kiếm nhân viên theo họ tên, ngày sinh, theo phòng ban" +
                    "\n7. Tính lương cho 1 nhân viên" +
                    "\n0. Thoát menu nhân viên" +
                    "\nNhập lựa chọn (0 - 7): ");
            luaChon = Integer.parseInt(CauHinh.sc.nextLine());
            if (luaChon == 0) {
                System.out.println("Thoát chức năng nhân viên...");
                break;
            } else if (luaChon >= 1 && luaChon <= 7) {
                switch (luaChon) {
                    case 1:
                        //Lựa chọn tạo loại nhân viên
                        int luaChonNhanVien;
                        do {
                            System.out.print("* 1. Nhân viên bình thường" +
                                    "\n* 2. Lập trình viên" +
                                    "\n* 3. Thiết kế viên" +
                                    "\n* 4. Kiểm thử viên" +
                                    "\n Nhập lựa chọn (1 - 4): ");
                            luaChonNhanVien = Integer.parseInt(CauHinh.sc.nextLine());
                        } while (luaChonNhanVien < 1 || luaChonNhanVien > 4);

                        //Kiểm tra tồn tại dữ liệu phòng ban
                        int maPhongBan;
                        do {
                            System.out.print("** Nhập mã phòng ban cho nhân viên mới: ");
                            maPhongBan = Integer.parseInt(CauHinh.sc.nextLine());
                            if (!danhSachPhongBan.tonTaiPhongBan(maPhongBan)) {
                                System.out.println("Phòng ban không tồn tại, nhập lại!");
                            }
                        } while (!danhSachPhongBan.tonTaiPhongBan(maPhongBan));

                        //Case thực thi tạo loại nhân viên
                        switch (luaChonNhanVien) {
                            case 1:
                                danhSachNhanVien.themNhanVien("nhanvien.NhanVienBinhThuong",
                                        QuanLyPhongBan.getDanhSachPhongBan(), maPhongBan);
                                break;
                            case 2:
                                danhSachNhanVien.themNhanVien("nhanvien.LapTrinhVien",
                                        QuanLyPhongBan.getDanhSachPhongBan(), maPhongBan);
                                break;
                            case 3:
                                danhSachNhanVien.themNhanVien("nhanvien.ThietKeVien",
                                        QuanLyPhongBan.getDanhSachPhongBan(), maPhongBan);
                                break;
                            case 4:
                                danhSachNhanVien.themNhanVien("nhanvien.KiemThuVien",
                                        QuanLyPhongBan.getDanhSachPhongBan(), maPhongBan);
                                break;
                        }
                        break;
                    case 2:
                        danhSachNhanVien.xemDanhSachNhanVien();
                        break;
                    case 3:

                        System.out.print("Nhập mã dự án muốn thêm: ");
                        int maDuAn = Integer.parseInt(CauHinh.sc.nextLine());

                    case 5:
                        System.out.print("** Nhập mã nhân viên muốn xem: ");
                        danhSachNhanVien.xemDanhSachDuAnThamGia(CauHinh.sc.nextLine());
                        break;
                    case 6:
                        System.out.print("** Nhập họ tên nhân viên: ");
                        String hoTen = CauHinh.sc.nextLine();

                        System.out.print("** Nhập ngày sinh: ");
                        int ngay = Integer.parseInt(CauHinh.sc.nextLine());
                        System.out.print("** Nhập tháng sinh: ");
                        int thang = Integer.parseInt(CauHinh.sc.nextLine());
                        System.out.print("** Nhập năm sinh: ");
                        int nam = Integer.parseInt(CauHinh.sc.nextLine());
                        CauHinh.c.set(nam, thang, ngay);

                        System.out.print("** Nhập tên phòng ban: ");
                        String tenPhongBan = CauHinh.sc.nextLine();

                        System.out.println("========== DANH SÁCH NHÂN VIÊN TÌM ĐƯỢC ==========");
                        danhSachNhanVien.timNhanVien(hoTen, CauHinh.c, tenPhongBan).forEach(nhanVien -> nhanVien.xemThongTin());
                        break;
                    case 7:
                        System.out.print("Nhập mã số nhân viên muốn tính lương: ");
                        String maNhanVien = CauHinh.sc.nextLine();

                        danhSachNhanVien.timNhanVien(maNhanVien).tinhLuong();
                        break;
                }
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
    //Hàm in menu các chức năng của dự án
    public static void menuDuAn () {
        System.out.print("--- CHỨC NĂNG DỰ ÁN ---" +
                "\n1. Thêm 1 dự án" +
                "\n2. Sửa 1 dự án" +
                "\n3. Xóa 1 dự án" +
                "\n4. Xem danh sách nhân viên của 1 dự án" +
                "\n5. Tìm kiếm dự án theo tên và thời điểm bắt đầu" +
                "\n6. Sắp xếp dự án theo kinh phí đầu tư" +
                "\n7. Gán nhân viên cho dự án" +
                "\n8. Gán người quản lý cho dự án" +
                "\n0. Thoát menu dự án" +
                "\nNhập lựa chọn (0 - 8): ");
    }
    //Hàm in menu các chức năng của phòng ban
    public static void menuPhongBan () {
        System.out.print("--- CHỨC NĂNG PHÒNG BAN ---" +
                "\n1. Thêm 1 phòng ban" +
                "\n2. Sửa 1 phòng ban" +
                "\n3. Xóa 1 phòng ban" +
                "\n4. Xem danh sách phòng ban" +
                "\n0. Thoát menu phòng ban" +
                "\nNhập lựa chọn (0 - 4): ");
    }
}
