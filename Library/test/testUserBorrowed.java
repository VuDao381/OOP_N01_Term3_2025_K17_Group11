public class testUserBorrowed {
    // Hàm kiểm tra các chức năng của hệ thống
    public static void test() {
        // Tạo người dùng
        User user1 = new User("Nguyen A", "nguyena@email.com", "password123");
        User user2 = new User("Tran B", "tranb@email.com", "password456");

        // Tạo các phiếu mượn
        BorrowSlip borrowSlip1 = new BorrowSlip("Cuốn sách 1", user1.getUID(), "2025-06-01", "2025-06-15");
        BorrowSlip borrowSlip2 = new BorrowSlip("Cuốn sách 2", user1.getUID(), "2025-06-05", "2025-06-19");
        BorrowSlip borrowSlip3 = new BorrowSlip("Cuốn sách 3", user2.getUID(), "2025-06-10", "2025-06-20");

        // Quản lý phiếu mượn của người dùng
        UserBorrowed userBorrowed = new UserBorrowed();

        // Thêm các phiếu mượn vào hệ thống
        userBorrowed.addBorrowSlip(borrowSlip1);
        userBorrowed.addBorrowSlip(borrowSlip2);
        userBorrowed.addBorrowSlip(borrowSlip3);

        // Kiểm tra hiển thị danh sách sách đã mượn của người dùng
        System.out.println("----- Kiểm tra danh sách sách đã mượn của người dùng -----");
        userBorrowed.displayUserBorrowedBooks(user1.getUID());
        userBorrowed.displayUserBorrowedBooks(user2.getUID());

        // Kiểm tra tìm phiếu mượn của một sách cụ thể
        System.out.println("\n----- Kiểm tra tìm phiếu mượn của sách -----");
        BorrowSlip slip = userBorrowed.getBorrowSlipByBookTitle("Cuốn sách 2");
        if (slip != null) {
            System.out.println("Đã tìm thấy phiếu mượn cho 'Cuốn sách 2':");
            System.out.println("- " + slip.getBookTitle() + " (Ngày mượn: " + slip.getBorrowDate() + ", Ngày trả: " + slip.getReturnDate() + ")");
        } else {
            System.out.println("Không tìm thấy phiếu mượn cho 'Cuốn sách 2'");
        }

        // Kiểm tra trường hợp sách không có trong danh sách
        System.out.println("\n----- Kiểm tra sách không có trong danh sách -----");
        BorrowSlip slipNotFound = userBorrowed.getBorrowSlipByBookTitle("Cuốn sách 4");
        if (slipNotFound != null) {
            System.out.println("Đã tìm thấy phiếu mượn cho 'Cuốn sách 4':");
            System.out.println("- " + slipNotFound.getBookTitle() + " (Ngày mượn: " + slipNotFound.getBorrowDate() + ", Ngày trả: " + slipNotFound.getReturnDate() + ")");
        } else {
            System.out.println("Không tìm thấy phiếu mượn cho 'Cuốn sách 4'");
        }
    }
}
        

