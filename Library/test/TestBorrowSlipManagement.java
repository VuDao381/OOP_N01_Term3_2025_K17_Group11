import java.time.LocalDate;

public class TestBorrowSlipManagement {
    public static void testborrowslip() {
        BorrowSlipManagement bsm = new BorrowSlipManagement();

        // ✅ Tạo LocalDate từ chuỗi (nếu cần), hoặc dùng LocalDate.of(...)
        BorrowSlip slip1 = new BorrowSlip("Lập trình Java", 12345,
                LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 15));

        BorrowSlip slip2 = new BorrowSlip("Cấu trúc dữ liệu", 101010,
                LocalDate.of(2025, 5, 3), LocalDate.of(2025, 5, 18));

        bsm.addBorrowSlip(slip1);
        bsm.addBorrowSlip(slip2);

        bsm.printBorrowSlips();

        System.out.println("\nXóa phiếu mượn sách 'Lập trình Java' của user 12345");
        bsm.deleteBorrowSlip("Lập trình Java", 12345);

        bsm.printBorrowSlips();
    }
}
