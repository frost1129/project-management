package phongban;

import nhanvien.*;

import java.io.File;
import java.io.FileNotFoundException;
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
                PhongBan phongBan = new PhongBan();
                int maPhongBan = Integer.parseInt(sf.nextLine());
                phongBan.setMaPhongBan(maPhongBan);

                String tenPhongBan = sf.nextLine();
                phongBan.setTenPhongBan(tenPhongBan);

                String maNhanVienQuanLy = sf.nextLine();
                phongBan.nhapNhanVienQuanLy(QuanLyNhanVien.getDanhSachNhanVien(), maNhanVienQuanLy);

                sf.nextLine(); //Bỏ qua kí tự "#"
                danhSachPhongBan.add(phongBan);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<PhongBan> getDanhSachPhongBan() {
        return danhSachPhongBan;
    }

    public static void setDanhSachPhongBan(List<PhongBan> danhSachPhongBan) {
        QuanLyPhongBan.danhSachPhongBan = danhSachPhongBan;
    }
}
