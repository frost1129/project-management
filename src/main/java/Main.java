import duan.QuanLyDuAn;
import dungchung.CauHinh;
import nhanvien.NhanVien;
import nhanvien.NhanVienQuanLy;
import nhanvien.QuanLyNhanVien;
import phongban.QuanLyPhongBan;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class Main {
    public static QuanLyNhanVien danhSachNhanVien = new QuanLyNhanVien();
    public static QuanLyPhongBan danhSachPhongBan = new QuanLyPhongBan();
    public static QuanLyDuAn danhSachDuAn = new QuanLyDuAn();

    public static void main (String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ParseException {
        //Phần khai báo
        int luaChonChinh;
        danhSachPhongBan.hoanThienThongTinPhongBan();

        while (true) {
            menuChinh();
            luaChonChinh = Integer.parseInt(CauHinh.sc.nextLine());
            if (luaChonChinh == 0) {
                System.out.println("Thoát chương trình...");
                break;
            } else if (luaChonChinh >= 1 && luaChonChinh <= 3) {
                switch (luaChonChinh) {
                    case 1:
                        menuNhanVien();
                        break;
                    case 2:
                        menuPhongBan();
                        break;
                    case 3:
                        menuDuAn();
                        break;

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
                            System.out.print("* 0. Nhân viên quản lý" +
                                    "\n* 1. Nhân viên bình thường" +
                                    "\n* 2. Lập trình viên" +
                                    "\n* 3. Thiết kế viên" +
                                    "\n* 4. Kiểm thử viên" +
                                    "\n Nhập lựa chọn (0 - 4): ");
                            luaChonNhanVien = Integer.parseInt(CauHinh.sc.nextLine());
                        } while (luaChonNhanVien < 0 || luaChonNhanVien > 4);

                        //Kiểm tra tồn tại dữ liệu phòng ban
                        int maPhongBan;
                        do {
                            System.out.print("** Nhập mã phòng ban cho nhân viên mới: ");
                            maPhongBan = Integer.parseInt(CauHinh.sc.nextLine());
                            if (!danhSachPhongBan.tonTaiPhongBan(maPhongBan)) {
                                System.out.println("** Phòng ban không tồn tại, nhập lại!");
                            }
                        } while (!danhSachPhongBan.tonTaiPhongBan(maPhongBan));

                        //Case thực thi tạo loại nhân viên
                        switch (luaChonNhanVien) {
                            case 0:
                                danhSachNhanVien.themNhanVien("nhanvien.NhanVienQuanLy",
                                        QuanLyPhongBan.getDanhSachPhongBan(), maPhongBan);
                                break;
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
                        int maDuAn;
                        String maNhanVien;
                        do {
                            System.out.print("* Nhập mã nhân viên: ");
                            maNhanVien = CauHinh.sc.nextLine();
                            if (!danhSachNhanVien.tonTaiNhanVien(maNhanVien)) {
                                System.out.println("* Nhân viên không tồn tại, nhập lại!");
                            }
                        } while (!danhSachNhanVien.tonTaiNhanVien(maNhanVien));

                        do {
                            System.out.print("* Nhập mã dự án muốn thêm: ");
                            maDuAn = Integer.parseInt(CauHinh.sc.nextLine());
                            if (!danhSachDuAn.tonTaiDuAn(maDuAn)) {
                                System.out.println("* Mã dự án không tồn tại, nhập lại!");
                            }
                        } while (!danhSachDuAn.tonTaiDuAn(maDuAn));

                        //Kiểm tra điều kiện số lượng dự án cho phép tham gia
                        if (danhSachNhanVien.timNhanVien(maNhanVien).getDanhSachDuAnThamGia().size() <= 3) {
                            danhSachNhanVien.timNhanVien(maNhanVien).themDuAnThamGia(danhSachDuAn.timDuAn(maDuAn));
                        } else {
                            System.out.println("* Thêm thất bại! Nhân viên này tham gia vượt quá số lượng dự án cho phép");
                        }
                        break;
                    case 4:
                        do {
                            System.out.print("* Nhập mã nhân viên: ");
                            maNhanVien = CauHinh.sc.nextLine();
                            if (!danhSachNhanVien.tonTaiNhanVien(maNhanVien)) {
                                System.out.println("* Nhân viên không tồn tại, nhập lại!");
                            }
                        } while (!danhSachNhanVien.tonTaiNhanVien(maNhanVien));

                        do {
                            System.out.print("* Nhập mã dự án muốn xóa: ");
                            maDuAn = Integer.parseInt(CauHinh.sc.nextLine());
                            if (!danhSachNhanVien.timNhanVien(maNhanVien).tonTaiDuAnThamGia(maDuAn)) {
                                System.out.println("* Mã dự án không tồn tại, nhập lại!");
                            }
                        } while (!danhSachNhanVien.timNhanVien(maNhanVien).tonTaiDuAnThamGia(maDuAn));

                        if (danhSachNhanVien.timNhanVien(maNhanVien).xoaDuAnThamGia(maDuAn)) {
                            System.out.println("* Xóa dự án tham gia thành công!");
                        } else {
                            System.out.println("* Xóa thất bại!");
                        }
                    case 5:
                        do {
                            System.out.print("* Nhập mã nhân viên muốn xem: ");
                            maNhanVien = CauHinh.sc.nextLine();
                            if (!danhSachNhanVien.tonTaiNhanVien(maNhanVien)) {
                                System.out.println("* Nhân viên không tồn tại, nhập lại!");
                            }
                        } while (!danhSachNhanVien.tonTaiNhanVien(maNhanVien));
                        System.out.printf("========== DANH SÁCH DỰ ÁN THAM GIA CỦA: %s ==========", danhSachNhanVien.timNhanVien(maNhanVien).getHoTen());
                        if (danhSachNhanVien.timNhanVien(maNhanVien).getDanhSachDuAnThamGia() != null) {
                            danhSachNhanVien.xemDanhSachDuAnThamGia(maNhanVien);
                        } else {
                            System.out.println("* Rỗng!");
                        }
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
                        CauHinh.d = CauHinh.f.parse(ngay + "/" + thang + "/" + nam);
                        CauHinh.c.setTime(CauHinh.d);

                        System.out.print("** Nhập tên phòng ban: ");
                        String tenPhongBan = CauHinh.sc.nextLine();

                        System.out.println("========== DANH SÁCH NHÂN VIÊN TÌM ĐƯỢC ==========");
                        if (danhSachNhanVien.timNhanVien(hoTen, CauHinh.c, tenPhongBan) == null) {
                            System.out.println("Rỗng!");
                        } else {
                            danhSachNhanVien.timNhanVien(hoTen, CauHinh.c, tenPhongBan).forEach(NhanVien::xemThongTin);
                        }
                        break;
                    case 7:
                        do {
                            System.out.print("** Nhập mã nhân viên muốn xem: ");
                            maNhanVien = CauHinh.sc.nextLine();
                            if (danhSachNhanVien.timNhanVien(maNhanVien) == null) {
                                System.out.println("Nhân viên không tồn tại, nhập lại!");
                            }
                        } while (danhSachNhanVien.timNhanVien(maNhanVien) == null);
                        System.out.printf("Tiền lương của nhân viên %s là: %.1f VNĐ\n", danhSachNhanVien.timNhanVien(maNhanVien).getHoTen(),
                                danhSachNhanVien.timNhanVien(maNhanVien).tinhLuong());
                        break;
                }
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
    //Hàm in menu các chức năng của phòng ban
    public static void menuPhongBan () throws ParseException {
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
                    case 1:
                        System.out.println("========== DANH SÁCH PHÒNG BAN ==========");
                        danhSachPhongBan.xemDanhSachPhongBan();
                        break;
                    case 2:
                        int maPhongBan;
                        do {
                            System.out.print("* Nhập mã phòng ban: ");
                            maPhongBan = Integer.parseInt(CauHinh.sc.nextLine());
                            if (!danhSachPhongBan.tonTaiPhongBan(maPhongBan)) {
                                System.out.println("* Mã phòng ban không tồn tại!");
                            }
                        } while (!danhSachPhongBan.tonTaiPhongBan(maPhongBan));

                        System.out.println("* Thông tin nhân viên quản lý: ");
                        danhSachPhongBan.timPhongBan(maPhongBan).xemThongTinNhanVienQuanLy();
                        System.out.println();
                        break;
                    case 3:
                        do {
                            System.out.print("* Nhập mã phòng ban: ");
                            maPhongBan = Integer.parseInt(CauHinh.sc.nextLine());
                            if (!danhSachPhongBan.tonTaiPhongBan(maPhongBan)) {
                                System.out.println("* Mã phòng ban không tồn tại!");
                            }
                        } while (!danhSachPhongBan.tonTaiPhongBan(maPhongBan));

                        String maNhanVien;
                        while (true) {
                            do {
                                System.out.print("Nhập mã nhân viên muốn thêm: ");
                                maNhanVien = CauHinh.sc.nextLine();
                                if (danhSachPhongBan.timPhongBan(maPhongBan).getDanhSachNhanVienTrucThuoc().contains(danhSachNhanVien.timNhanVien(maNhanVien))) {
                                    System.out.println("* Nhân viên này đã tồn tại trong phòng ban, nhập lại!");
                                } if (!danhSachNhanVien.tonTaiNhanVien(maNhanVien)) {
                                    System.out.println("* Mã nhân viên không tồn tại, nhập lại!");
                                }
                            } while (danhSachPhongBan.timPhongBan(maPhongBan).getDanhSachNhanVienTrucThuoc().contains(danhSachNhanVien.timNhanVien(maNhanVien)) ||
                                    !danhSachNhanVien.tonTaiNhanVien(maNhanVien));
                            danhSachPhongBan.timPhongBan(maPhongBan).getDanhSachNhanVienTrucThuoc().add(danhSachNhanVien.timNhanVien(maNhanVien));
                            System.out.print("* Thêm thành công! Tiếp tục (No - 0 / Yes - 1): ");
                            if (Integer.parseInt(CauHinh.sc.nextLine()) == 0) {
                                break;
                            }
                        }
                        break;
                    case 4:
                        do {
                            System.out.print("* Nhập mã phòng ban: ");
                            maPhongBan = Integer.parseInt(CauHinh.sc.nextLine());
                            if (!danhSachPhongBan.tonTaiPhongBan(maPhongBan)) {
                                System.out.println("* Mã phòng ban không tồn tại!");
                            }
                        } while (!danhSachPhongBan.tonTaiPhongBan(maPhongBan));
                        System.out.printf("========== DANH SÁCH NHÂN VIÊN CỦA %s ===========\n", danhSachPhongBan.timPhongBan(maPhongBan).getTenPhongBan());
                        danhSachPhongBan.timPhongBan(maPhongBan).getDanhSachNhanVienTrucThuoc().forEach(nhanVien -> {
                            int dem = 0;
                            System.out.printf("%d. %s - %s\n", ++dem, nhanVien.getMaNhanVien(), nhanVien.getHoTen());
                        });
                        System.out.println();
                        break;
                }
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
}
