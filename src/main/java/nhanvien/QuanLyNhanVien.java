package nhanvien;

import duan.DuAn;
import duan.QuanLyDuAn;
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
    {
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
                        NhanVien nhanVien = new NhanVienQuanLy(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
                        danhSachNhanVien.add(nhanVien);
                    }
                    case "001" -> {
                        sf.nextLine(); //Bỏ qua lương thưởng thêm, mặc định "-1"
                        NhanVien nhanVien = new NhanVienBinhThuong(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan);
                        danhSachNhanVien.add(nhanVien);
                    }
                    case "002" -> {
                        double luongOverTime = Double.parseDouble(sf.nextLine());
                        NhanVien nhanVien = new LapTrinhVien(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan, luongOverTime);
                        danhSachNhanVien.add(nhanVien);
                    }
                    case "003" -> {
                        double bonus = Double.parseDouble(sf.nextLine());
                        NhanVien nhanVien = new ThietKeVien(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan, bonus);
                        danhSachNhanVien.add(nhanVien);
                    }
                    case "004" -> {
                        int loiPhatHien = Integer.parseInt(sf.nextLine());
                        NhanVien nhanVien = new KiemThuVien(hoTen, ngaySinh, email, gioiTinh, phongBan, heSoLuong, luongCoBan, loiPhatHien);
                        danhSachNhanVien.add(nhanVien);
                    }
                }
                sf.nextLine(); //Bo qua ki tu "#" phan cach du lieu
            }
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    public QuanLyNhanVien() {}

    public void dongBoHoaDuLieu() {
        danhSachNhanVien.forEach(nhanVien -> nhanVien.hoanThienThongTinPhongBanTrucThuoc(QuanLyPhongBan.getDanhSachPhongBan()));
    }

    /**
     * Thêm 1 nhân viên bất kì
     * @param danhSachPhongBan
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ParseException
     */
    public void themNhanVien(QuanLyPhongBan danhSachPhongBan) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ParseException {
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

        //Case thực thi tạo loại nhân viên
        String loaiNhanVien = null;
        switch (luaChonNhanVien) {
            case 0 -> loaiNhanVien = "nhanvien.NhanVienQuanLy";
            case 1 -> loaiNhanVien = "nhanvien.NhanVienBinhThuong";
            case 2 -> loaiNhanVien = "nhanvien.LapTrinhVien";
            case 3 -> loaiNhanVien = "nhanvien.ThietKeVien";
            case 4 -> loaiNhanVien = "nhanvien.KiemThuVien";
        }
        Class c = Class.forName(loaiNhanVien);
        NhanVien nv = (NhanVien) c.getConstructor().newInstance();
        nv.nhapThongTin(danhSachPhongBan);
        danhSachNhanVien.add(nv);
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
            if (nhanVien.getMaNhanVien().equals(maNhanVien)) {
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

    /**
     * Hàm kiểm tra nhân viên có tồn tại hay không dựa vào mã nhân viên
     * @param maNhanVien
     * @return
     */
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
     * Hàm in danh sách các nhân viên thỏa điều kiện tìm
     * @throws ParseException
     */
    public void xuatDanhSachNhanVienTimDuoc() throws ParseException {
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
        if (!this.timNhanVien(hoTen, CauHinh.c, tenPhongBan).isEmpty()) {
            System.out.println("========== DANH SÁCH NHÂN VIÊN TÌM ĐƯỢC ==========");
            this.timNhanVien(hoTen, CauHinh.c, tenPhongBan).forEach(nhanVien -> {
                nhanVien.xemThongTin();
                System.out.println();
            });
        } else {
            System.out.println("========== DANH SÁCH NHÂN VIÊN TÌM ĐƯỢC ==========");
            System.out.println("Rỗng!");
        }
    }

    /**
     * Hàm thêm 1 dự án tham gia cho 1 nhân viên
     * @param danhSachDuAn
     */
    public void themDuAnThamGiaCuaNhanVien(QuanLyDuAn danhSachDuAn) {
        int maDuAn;
        String maNhanVien = this.xacThucMaNhanVienTonTai();
        do {
            System.out.print("* Nhập mã dự án muốn thêm: ");
            maDuAn = Integer.parseInt(CauHinh.sc.nextLine());
            if (!danhSachDuAn.tonTaiDuAn(maDuAn)) {
                System.out.println("* Mã dự án không tồn tại, nhập lại!");
            }
        } while (!danhSachDuAn.tonTaiDuAn(maDuAn));

        //Kiểm tra điều kiện số lượng dự án cho phép tham gia
        if (this.timNhanVien(maNhanVien).getDanhSachDuAnThamGia().size() <= 3) {
            danhSachDuAn.timDuAn(maDuAn).getDanhSachNhanVienThamGia().add(this.timNhanVien(maNhanVien));
            this.timNhanVien(maNhanVien).themDuAnThamGia(danhSachDuAn.timDuAn(maDuAn));
            System.out.println("---> Thêm dự án cho nhân viên này thành công!");
        } else {
            System.out.println("---> Thêm thất bại! Nhân viên này tham gia vượt quá số lượng dự án cho phép");
        }
    }

    public void xemDuAnThamGiaCuaNhanVien() {
        String maNhanVien;
        do {
            System.out.print("* Nhập mã nhân viên muốn xem: ");
            maNhanVien = CauHinh.sc.nextLine();
            if (!this.tonTaiNhanVien(maNhanVien)) {
                System.out.println("* Nhân viên không tồn tại, nhập lại!");
            }
        } while (!this.tonTaiNhanVien(maNhanVien));
        System.out.printf("========== DANH SÁCH DỰ ÁN THAM GIA CỦA: %s ==========\n", this.timNhanVien(maNhanVien).getHoTen());
        if (this.timNhanVien(maNhanVien).getDanhSachDuAnThamGia() != null) {
            this.xemDanhSachDuAnThamGia(maNhanVien);
        } else {
            System.out.println("* Rỗng!");
        }
        System.out.println();
    }

    /**
     * Hàm xóa 1 dự án đã tham gia của 1 nhân viên
     */
    public void xoaDuAnThamGiaCuaNhanVien(QuanLyDuAn danhSachDuAn) {
        String maNhanVien = this.xacThucMaNhanVienTonTai();

        System.out.println("========== DANH SÁCH DỰ ÁN ĐÃ THAM GIA ==========");
        int chiSoDem = 0;
        for (DuAn duAn: this.timNhanVien(maNhanVien).getDanhSachDuAnThamGia()) {
            System.out.printf("%d. %s - %s - %.1f VNĐ\n", ++chiSoDem, duAn.getMaDuAn(), duAn.getTenDuAn(), duAn.getTongKinhPhi());
        }

        int maDuAn;
        do {
            System.out.print("* Nhập mã dự án muốn xóa: ");
            maDuAn = Integer.parseInt(CauHinh.sc.nextLine());
            if (!this.timNhanVien(maNhanVien).tonTaiDuAnThamGia(maDuAn)) {
                System.out.println("* Mã dự án không tồn tại, nhập lại!");
            }
        } while (!this.timNhanVien(maNhanVien).tonTaiDuAnThamGia(maDuAn));

        if (this.timNhanVien(maNhanVien).xoaDuAnThamGia(maDuAn)) {
            danhSachDuAn.timDuAn(maDuAn).getDanhSachNhanVienThamGia().remove(this.timNhanVien(maNhanVien));
            System.out.println("---> Xóa dự án tham gia thành công!");
        } else {
            System.out.println("---> Xóa thất bại!");
        }
    }

    /**
     * Hàm hiển thị lương cho 1 nhân viên bất kì
     */
    public void tinhLuongChoNhanVien() {
        String maNhanVien = xacThucMaNhanVienTonTai();
        System.out.printf("Tiền lương của nhân viên %s là: %.1f VNĐ\n", this.timNhanVien(maNhanVien).getHoTen(),
                this.timNhanVien(maNhanVien).tinhLuong());
    }

    public void themPhongBanCuaNhanVienQuanLy(QuanLyPhongBan danhSachPhongBan) throws ParseException {
        System.out.println("========== DANH SÁCH NHÂN VIÊN QUẢN LÝ ==========");
        int dem = 0;
        for (NhanVien nv: danhSachNhanVien) {
            if (nv instanceof NhanVienQuanLy) {
                System.out.printf("* %d. %s - %s\n", ++dem, nv.getMaNhanVien(), nv.getHoTen());
            }
        }
        System.out.println();

        String maNhanVien = this.xacThucMaNhanVienTonTai();

        //Kiểm tra dữ liệu hợp lệ của phòng ban sắp được thêm
        int maPhongBan;
        boolean flag;
        do {
            flag = true;
            System.out.print("* Nhập mã phòng ban muốn quản lý: ");
            maPhongBan = Integer.parseInt(CauHinh.sc.nextLine());
            if (!danhSachPhongBan.tonTaiPhongBan(maPhongBan)) {
                flag = false;
                System.out.println("* Mã phòng ban không tồn tại, nhập lại!");
            } else {
                if (((NhanVienQuanLy)this.timNhanVien(maNhanVien)).tonTaiPhongBanDaQuanLy(maPhongBan)) {
                    flag = false;
                    System.out.println("* Nhân viên đang quản lý phòng ban này, nhập lại!");
                }
                if (!danhSachPhongBan.timPhongBan(maPhongBan).getNhanVienQuanLy().getMaNhanVien().equals("-1")) {
                    flag = false;
                    System.out.println("* Phòng ban này đã có người quản lý, nhập lại!");
                }
            }
        } while (!flag);

        //Kiểm tra số lượng phòng ban được phép quản lý
        NhanVienQuanLy nhanVienQuanLy = ((NhanVienQuanLy) this.timNhanVien(maNhanVien));
        PhongBan phongBanNhanQuanLy = danhSachPhongBan.timPhongBan(maPhongBan);
        if (nhanVienQuanLy.getPhongBanQuanLy().size() < 2) {
            System.out.print("* Nhập ngày nhậm chức: ");
            int ngay = Integer.parseInt(CauHinh.sc.nextLine());
            System.out.print("* Nhập tháng nhậm chức: ");
            int thang = Integer.parseInt(CauHinh.sc.nextLine());
            System.out.print("* Nhập năm nhậm chức: ");
            int nam = Integer.parseInt(CauHinh.sc.nextLine());

            CauHinh.c.setTime(CauHinh.f.parse(ngay + "/" + thang + "/" + nam));
            nhanVienQuanLy.setNgayNhamChuc(CauHinh.c);
            nhanVienQuanLy.setPhongBanQuanLy(maPhongBan);

            phongBanNhanQuanLy.setNhanVienQuanLy(nhanVienQuanLy);
            System.out.println("* Thêm thành công!");
        } else {
            System.out.println("* Vượt quá số lượng phòng ban cho phép quản lý!");
        }
    }

    /**
     * Hàm xác thực tồn tại nhân viên thông qua mã nhân viên
     * @return trả về mã nhân viên nếu tìm thấy
     */
    public String xacThucMaNhanVienTonTai() {
        String maNhanVien;
        do {
            System.out.print("* Nhập mã nhân viên: ");
            maNhanVien = CauHinh.sc.nextLine();
            if (!this.tonTaiNhanVien(maNhanVien)) {
                System.out.println("* Nhân viên không tồn tại, nhập lại!");
            }
        } while (!this.tonTaiNhanVien(maNhanVien));
        return maNhanVien;
    }

    //Các setter và getter
    public static List<NhanVien> getDanhSachNhanVien() {
        return danhSachNhanVien;
    }

    public void setDanhSachNhanVien(List<NhanVien> danhSachNhanVien) {
        QuanLyNhanVien.danhSachNhanVien = danhSachNhanVien;
    }
}
