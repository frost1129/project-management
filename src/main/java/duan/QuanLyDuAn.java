package duan;

import dungchung.CauHinh;
import nhanvien.NhanVien;
import nhanvien.NhanVienQuanLy;
import nhanvien.QuanLyNhanVien;
import phongban.PhongBan;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class QuanLyDuAn {
    private static List<DuAn> danhSachDuAn = new ArrayList<>();

    {
        /**
         * Ghi chú dữ liệu file
         * Dòng 1: Mã dự án
         * Dòng 2: Tên dự án
         * Dòng 3: Thời điểm bắt đầu
         * Dòng 4: Thời điểm kết thúc
         * Dòng 5: Kinh phí đầu tư
         * Dòng 6: Mã chủ nhiệm dự án (-1 là chưa có chủ nhiệm)
         * Dòng 7: Kí tự "#" phân cách dữ liệu
         */
        String url = "src/main/resources/danhsachduan.txt";
        File file = new File(url);
        try {
            Scanner sf = new Scanner(file);
            while (sf.hasNextLine()) {
                int maDuAn = Integer.parseInt(sf.nextLine());

                String tenDuAn = sf.nextLine();

                Calendar ngayBatDau = new GregorianCalendar();
                ngayBatDau.setTime(CauHinh.f.parse(sf.nextLine()));

                Calendar ngayKetThuc = new GregorianCalendar();
                ngayKetThuc.setTime(CauHinh.f.parse(sf.nextLine()));

                double tongKinhPhi = Double.parseDouble(sf.nextLine());
                String maChuNhiem = sf.nextLine();

                DuAn duAn = new DuAn();
                duAn.setMaDuAn(maDuAn);
                duAn.setTenDuAn(tenDuAn);
                duAn.setNgayBatDau(ngayBatDau);
                duAn.setNgayKetThuc(ngayKetThuc);
                duAn.setTongKinhPhi(tongKinhPhi);

                NhanVien chuNhiem = new NhanVien() {
                    @Override
                    public double tinhLuong() {
                        return 0;
                    }
                };
                chuNhiem.setMaNhanVien(maChuNhiem);
                duAn.setChuNhiemDuAn(chuNhiem);
                danhSachDuAn.add(duAn);

                sf.nextLine(); //Bỏ qua kí tự '#' phân cách dữ liệu
            }

        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    public QuanLyDuAn() {};

    public void dongBoHoaDuLieu() {
        danhSachDuAn.forEach(duAn -> duAn.hoanThienThongTinChuNhiemDuAn(QuanLyNhanVien.getDanhSachNhanVien()));
    }

    /**
     * Hàm thêm 1 dự án
     * @param danhSachNhanVien
     * @throws ParseException
     */
    public void themDuAn(QuanLyNhanVien danhSachNhanVien) throws ParseException {
        DuAn duAn = new DuAn();
        duAn.nhapThongTin(danhSachNhanVien);
        danhSachDuAn.add(duAn);
        System.out.println("Thêm dự án thành công!");
    }

    /**
     * Hàm sửa thông tin dự án theo mã dự án
     * @param danhSachNhanVien
     * @throws ParseException
     */
    public void suaDuAn(QuanLyNhanVien danhSachNhanVien) throws ParseException {
        int maDuAn;
        do {
            System.out.print("* Nhập mã dự án: ");
            maDuAn = Integer.parseInt(CauHinh.sc.nextLine());
            if (!this.tonTaiDuAn(maDuAn)) {
                System.out.println("* Mã dự án không tồn tại, nhập lại!");
            }
        } while (!this.tonTaiDuAn(maDuAn));

        this.timDuAn(maDuAn).suaThongTin(danhSachNhanVien);
    }

    /**
     * Hàm xóa 1 dự án
     * @return xóa thành công trả về true, ngược lại false
     */
    public void xoaDuAn() {
        int maDuAn;
        do {
            System.out.print("* Nhập mã dự án: ");
            maDuAn = Integer.parseInt(CauHinh.sc.nextLine());
            if (!this.tonTaiDuAn(maDuAn)) {
                System.out.println("* Mã dự án không tồn tại, nhập lại!");
            }
        } while (!this.tonTaiDuAn(maDuAn));

        danhSachDuAn.remove(this.timDuAn(maDuAn));
        System.out.println("* Xóa thành công!");
    }

    /**
     * Hàm xem danh sách dự án hiện có
     */
    public void xemDanhSachDuAn() {
        danhSachDuAn.forEach(duAn -> {
            duAn.xemThongTin();
            System.out.println();
        });
    }

    /**
     * Hàm xem danh sách nhân viên tham gia theo mã dự án
     */
    public void xemDanhSachNhanVienCuaDuAn() {
        int maDuAn;
        do {
            System.out.print("* Nhập mã dự án muốn xem: ");
            maDuAn = Integer.parseInt(CauHinh.sc.nextLine());
            if (!this.tonTaiDuAn(maDuAn)) {
                System.out.println("* Mã dự án không tồn tại, nhập lại!");
            }
        } while (!this.tonTaiDuAn(maDuAn));

        this.timDuAn(maDuAn).xemDanhSachNhanVienThamGia();
    }

    /**
     * Hàm sắp xếp dự án tăng theo tổng kinh phí đầu tư
     */
    public void sapXepDuAn() {
        danhSachDuAn.sort((duAn1, duAn2) -> (Double.compare(duAn2.getTongKinhPhi(), duAn1.getTongKinhPhi())));
    }

    /**
     * Hàm kiểm tra dự án với mã tương ứng có tồn tại hay không
     * @param maDuAn
     * @return true nếu tìm thấy, ngược lại fasle
     */
    public boolean tonTaiDuAn(int maDuAn) {
        for (DuAn duAn: danhSachDuAn) {
            if (duAn.getMaDuAn() == maDuAn) {
                return true;
            }
        }
        return false;
    }

    /**
     * Hàm trả về dự án với mã tương ứng
     * @param maDuAn
     * @return trả về 1 dự án nếu tìm thấy, ngược lại trả null
     */
    public DuAn timDuAn(int maDuAn) {
        for (DuAn duAn: danhSachDuAn) {
            if (duAn.getMaDuAn() == maDuAn) {
                return duAn;
            }
        }
        return null;
    }

    /**
     * Hàm tìm danh sách dự án theo tên và ngày bắt đầu
     * @param tenDuAn
     * @param ngayBatDau
     * @return
     */
    public List<DuAn> timDuAn(String tenDuAn, Calendar ngayBatDau) {
        return danhSachDuAn.stream().filter(duAn -> (duAn.getTenDuAn().equals(tenDuAn) &&
                (duAn.getNgayBatDau().get(Calendar.DATE) == ngayBatDau.get(Calendar.DATE) && duAn.getNgayBatDau().get(Calendar.MONTH) == ngayBatDau.get(Calendar.MONTH) &&
                        duAn.getNgayBatDau().get(Calendar.YEAR) == ngayBatDau.get(Calendar.YEAR)))).collect(Collectors.toList());
    }

    /**
     * Hàm in ra danh sách các dự án tìm được thỏa điều kiện
     * @throws ParseException
     */
    public void xemDanhSachDuAnTimDuoc() throws ParseException {
        System.out.print("* Nhập tên dự án: ");
        String tenDuAn = CauHinh.sc.nextLine();

        System.out.println("* Nhập thời điểm bắt đầu: ");
        System.out.print("** Nhập ngày: ");
        int ngay = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("** Nhập tháng: ");
        int thang = Integer.parseInt(CauHinh.sc.nextLine());
        System.out.print("** Nhập năm: ");
        int nam = Integer.parseInt(CauHinh.sc.nextLine());
        CauHinh.c.setTime(CauHinh.f.parse(ngay + "/" + thang + "/" + nam));

        List<DuAn> ds = this.timDuAn(tenDuAn, CauHinh.c);
        System.out.println("========== DANH SÁCH DỰ ÁN TÌM ĐƯỢC ==========");
        if (ds == null) {
            System.out.println("Rỗng!");
        } else {
            ds.forEach(duAn -> {
                duAn.xemThongTin();
                System.out.println();
            });
        }
    }

    /**
     * Hàm thêm nhân viên tham gia cho dự án
     * @param danhSachNhanVien
     */
    public void themNhanVienChoDuAn(QuanLyNhanVien danhSachNhanVien) {
        int maDuAn;
        String maNhanVien;
        do {
            System.out.print("* Nhập mã dự án: ");
            maDuAn = Integer.parseInt(CauHinh.sc.nextLine());
            if (!this.tonTaiDuAn(maDuAn)) {
                System.out.println("* Mã dự án không tồn tại, nhập lại!");
            }
        } while (!this.tonTaiDuAn(maDuAn));

        //Kiểm tra điều kiện tối thiểu 5 người
        if (this.timDuAn(maDuAn).getDanhSachNhanVienThamGia().size() < 5) {
            System.out.println("* Dự án đang không đủ số người tối thiểu (>= 5), tiến hành nhập đủ 5 nhân viên: ");
            while (this.timDuAn(maDuAn).getDanhSachNhanVienThamGia().size() < 5) {
                boolean flag;
                do {
                    flag = true;
                    System.out.print("* Nhập mã nhân viên muốn thêm: ");
                    maNhanVien = CauHinh.sc.nextLine();
                    if (!danhSachNhanVien.tonTaiNhanVien(maNhanVien)) {
                        flag = false;
                        System.out.println("* Mã nhân viên không tồn tại, nhập lại!");
                    } else {
                        if (this.timDuAn(maDuAn).getDanhSachNhanVienThamGia().contains(danhSachNhanVien.timNhanVien(maNhanVien))) {
                            flag = false;
                            System.out.println("* Nhân viên đang tham gia dự án này, nhập lại!");
                        }
                    }
                } while (!flag);
            }
        }
        //Kiểm tra điều kiện tối đa 10 người
        else if (this.timDuAn(maDuAn).getDanhSachNhanVienThamGia().size() < 10) {
            boolean flag;
            do {
                flag = true;
                System.out.print("* Nhập mã nhân viên muốn thêm: ");
                maNhanVien = CauHinh.sc.nextLine();
                if (!danhSachNhanVien.tonTaiNhanVien(maNhanVien)) {
                    flag = false;
                    System.out.println("* Mã nhân viên không tồn tại, nhập lại!");
                } else {
                    if (this.timDuAn(maDuAn).getDanhSachNhanVienThamGia().contains(danhSachNhanVien.timNhanVien(maNhanVien))) {
                        flag = false;
                        System.out.println("* Nhân viên đang tham gia dự án này, nhập lại!");
                    }
                }
            } while (!flag);
            this.timDuAn(maDuAn).themNhanVienThamGia(danhSachNhanVien.timNhanVien(maNhanVien));
            System.out.println("* Thêm nhân viên thành công!");
        } else {
            System.out.println("* Thất bại, dự án vượt quá số người tham gia!");
        }
    }

    public void themChuNhiemChoDuAn(QuanLyNhanVien danhSachNhanVien) {
        int maDuAn;
        boolean flag;
        do {
            flag = true;
            System.out.print("* Nhập mã dự án: ");
            maDuAn = Integer.parseInt(CauHinh.sc.nextLine());
            if (!this.tonTaiDuAn(maDuAn)) {
                flag = false;
                System.out.println("* Mã dự án không tồn tại, nhập lại!");
            } else {
                if (!this.timDuAn(maDuAn).getChuNhiemDuAn().getMaNhanVien().equals("-1")) {
                    flag = false;
                    System.out.println("* Dự án này đã có chủ nhiệm, nhập lại!");
                }
            }
        } while (!flag);

        String maChuNhiem;
        do {
            System.out.print("* Nhập mã chủ nhiệm: ");
            maChuNhiem = CauHinh.sc.nextLine();
            if (!danhSachNhanVien.tonTaiNhanVien(maChuNhiem)) {
                System.out.println("* Mã nhân viên không tồn tại, nhập lại!");
            }
        } while (!danhSachNhanVien.tonTaiNhanVien(maChuNhiem));
        this.timDuAn(maDuAn).setChuNhiemDuAn(danhSachNhanVien.timNhanVien(maChuNhiem));
        System.out.println("* Thêm chủ nhiệm thành công!");
    }

    //Các setter và getter
    public static List<DuAn> getDanhSachDuAn() {
        return danhSachDuAn;
    }

    public static void setDanhSachDuAn(List<DuAn> danhSachDuAn) {
        QuanLyDuAn.danhSachDuAn = danhSachDuAn;
    }
}
