import java.util.ArrayList;
import java.util.List;

public class UserBorrowed {
    private List<BorrowSlip> borrowSlips = new ArrayList<>();

    // Phương thức thêm phiếu mượn vào danh sách
    public void addBorrowSlip(BorrowSlip borrowSlip) {
        borrowSlips.add(borrowSlip);
    }

    // Phương thức hiển thị danh sách sách đã mượn của người dùng theo userID
    public void displayUserBorrowedBooks(int userId) {
        boolean found = false;
        System.out.println("Danh sách sách đã mượn của người dùng với ID: " + userId);
        for (BorrowSlip slip : borrowSlips) {
            if (slip.getUID() == userId) {
                found = true;
                System.out.println("- " + slip.getBookTitle() + " (Ngày mượn: " + slip.getBorrowDate() + ", Ngày trả: " + slip.getReturnDate() + ")");
            }
        }
        if (!found) {
            System.out.println("Không có sách nào được mượn bởi người dùng này.");
        }
    }

    // Phương thức tìm phiếu mượn của người dùng theo tên sách
    public BorrowSlip getBorrowSlipByBookTitle(String bookTitle) {
        for (BorrowSlip slip : borrowSlips) {
            if (slip.getBookTitle().equals(bookTitle)) {
                return slip;
            }
        }
        return null; // Trả về null nếu không tìm thấy sách
    }
}
