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
                "\n1. Thêm 1 nhân viên (bình thường)" +
                "\n2. Thêm 1 nhân viên (lập trình viên)" +
                "\n3. Thêm 1 nhân viên (kiểm thử viên)" +
                "\n4. Thêm 1 nhân viên (thiết kế viên)" +
                "\n5. Xem danh sách dự án tham gia của 1 nhân viên" +
                "\n6. Tìm kiếm nhân viên theo họ tên, ngày sinh, theo phòng ban" +
                "\nNhập lựa chọn (0 - 6): ");
    }
}
