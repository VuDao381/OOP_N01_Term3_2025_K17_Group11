public class TestBorrowSlipManagement {
    public static void testborrowslip() {
        BorrowSlipManagement bsm = new BorrowSlipManagement();

        BorrowSlip slip1 = new BorrowSlip("Lập trình Java", 12345, "2025-05-01", "2025-05-15");
        BorrowSlip slip2 = new BorrowSlip("Cấu trúc dữ liệu", 101010, "2025-05-03", "2025-05-18");

        bsm.addBorrowSlip(slip1);
        bsm.addBorrowSlip(slip2);

        bsm.printBorrowSlips();

        System.out.println("\nXóa phiếu mượn sách 'Lập trình Java' của user 12345");
        bsm.deleteBorrowSlip("Lập trình Java", 12345);

        bsm.printBorrowSlips();
    }
}
