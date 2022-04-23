package duan;

import java.util.ArrayList;
import java.util.List;

public class QuanLyDuAn {
    private static List<DuAn> danhSachDuAn = new ArrayList<>();

    public QuanLyDuAn() {};

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

    //Các setter và getter
    public static List<DuAn> getDanhSachDuAn() {
        return danhSachDuAn;
    }

    public static void setDanhSachDuAn(List<DuAn> danhSachDuAn) {
        QuanLyDuAn.danhSachDuAn = danhSachDuAn;
    }
}
