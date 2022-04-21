public class Main {
    public static void main (String[] args) {

    }

    //Hàm in menu lựa chọn chính
    public static void menuChinh () {
        System.out.print("--- DANH SÁCH CHỨC NĂNG ---" +
                "\n1. Nhân viên" +
                "\n2. Dự án" +
                "\n3. Phòng ban" +
                "\nNhập lựa chọn (0 - 3): ");
    }

    //Hàm in menu các chức năng của nhân viên
    public static void menuNhanVien () {
        System.out.print("--- CHỨC NĂNG NHÂN VIÊN ---" +
                "\n1. Thêm 1 nhân viên" +
                "\n2. Xem danh sách dự án tham gia của 1 nhân viên" +
                "\n3. Tìm kiếm nhân viên theo họ tên, ngày sinh, theo phòng ban" +
                "\n4. Tính lương cho 1 nhân viên" +
                "\nNhập lựa chọn (0 - 6): ");
    }
    //Hàm in menu các chức năng của dự án
    public static void menuDuAn () {
        System.out.print("--- CHỨC NĂNG DỰ ÁN ---" +
                "\n1. Thêm 1 dự án" +
                "\n2. Sửa 1 dự án" +
                "\n3. Xóa 1 dự án" +
                "\n3. Xem danh sách nhân viên của 1 dự án" +
                "\n4. Tìm kiếm dự án theo tên và thời điểm bắt đầu" +
                "\n5. Sắp xếp dự án theo kinh phí đầu tư" +
                "\n6. Gán nhân viên cho dự án" +
                "\n7. Gán người quản lý cho dự án" +
                "\nNhập lựa chọn (0 - 6): ");
    }
}
