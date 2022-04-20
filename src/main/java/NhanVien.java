import java.util.ArrayList;
import java.util.List;

public class NhanVien {
    private int maNhanVien;
    private String hoTen;
    private String email;
    private String gioiTinh;
    private PhongBan phongBan;
    private double heSoLuong;
    private double luongCoBan;
    private List<DuAn> danhSachDuAn = new ArrayList<>();

    //Phương thức khởi tạo
    public NhanVien(int maNhanVien, String hoTen, String email, String gioiTinh, PhongBan phongBan,
                    double heSoLuong, double luongCoBan) {
        this.setMaNhanVien(maNhanVien);
        this.setHoTen(hoTen);
        this.setEmail(email);
        this.setGioiTinh(gioiTinh);
        this.setPhongBan(phongBan);
        this.setHeSoLuong(heSoLuong);
        this.setLuongCoBan(luongCoBan);
    }

    //Hàm thêm thông tin 1 nhân viên
    public void nhapThongTin() {
        System.out.print("Nhập mã nhân viên: ");
        this.maNhanVien = Integer.parseInt(CauHinh.sc.nextLine());

        System.out.print("Nhập họ và tên: ");
        this.hoTen = CauHinh.sc.nextLine();

        System.out.print("Nhập email: ");
        this.setEmail(CauHinh.sc.nextLine());

        System.out.print("Nhập giới tính: ");
        this.gioiTinh = CauHinh.sc.nextLine();

        System.out.print("Nhập mã phòng ban: ");
        int maPhongBan = Integer.parseInt(CauHinh.sc.nextLine());

        System.out.print("Nhập hệ số lương: ");
        this.heSoLuong = Integer.parseInt(CauHinh.sc.nextLine());
    }


    //Các setter và getter
    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public List<DuAn> getDanhSachDuAn() {
        return danhSachDuAn;
    }

    public void setDanhSachDuAn(List<DuAn> danhSachDuAn) {
        this.danhSachDuAn = danhSachDuAn;
    }
}
