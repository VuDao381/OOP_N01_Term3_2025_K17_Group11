import java.time.LocalDate;
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
                System.out.println("- " + slip.getBookTitle() + " (Ngày mượn: " + slip.getBorrowDate() + ", Ngày trả: " + slip.getDueDate() + ")");
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
    public void notifyUpcomingDueDates() {
        LocalDate today = LocalDate.now();
        boolean hasNotice = false;

        System.out.println("== THÔNG BÁO SÁCH SẮP ĐẾN HẠN TRẢ ==");

        for (BorrowSlip slip : borrowSlips) {
            LocalDate dueDate = slip.getDueDate();
            if (!slip.isReturned() && (dueDate.isEqual(today) || 
                (dueDate.isAfter(today))) ) {

                hasNotice = true;
                System.out.println("📚 Sách: " + slip.getBookTitle());
                System.out.println("   - ID người mượn: " + slip.getUID());
                System.out.println("   - Hạn trả: " + dueDate);
                System.out.println();
            }
        }

        if (!hasNotice) {
            System.out.println("Không có sách nào sắp đến hạn trả.");
        }
    }
}
