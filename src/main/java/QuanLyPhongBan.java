import java.util.ArrayList;
import java.util.List;

public class QuanLyPhongBan {
    private List<PhongBan> danhSachPhongBan = new ArrayList<>();

    //Các setter và getter
    public List<PhongBan> getDanhSachPhongBan() {
        return danhSachPhongBan;
    }

    public void setDanhSachPhongBan(List<PhongBan> danhSachPhongBan) {
        this.danhSachPhongBan = danhSachPhongBan;
    }
}
