import java.time.LocalDate;

public class BorrowSlipTest {
    public static void testBorrowSlip(){
        // ✅ Dùng LocalDate.of(năm, tháng, ngày)
        LocalDate borrowDate = LocalDate.of(2025, 1, 1);
        LocalDate dueDate = LocalDate.of(2025, 2, 1);

        BorrowSlip slip1 = new BorrowSlip("Detective Conan", 800001, borrowDate, dueDate);

        System.out.println("Book Title: " + slip1.getBookTitle());
        System.out.println("UserID: " + slip1.getUID()); // ✅ phương thức đã đổi tên
        System.out.println("Borrow Date: " + slip1.getBorrowDate());
        System.out.println("Due Date: " + slip1.getDueDate());
        System.out.println("Status: " + (slip1.isReturned() ? "Đã trả" : "Chưa trả"));
    }
}
