import java.time.LocalDate;
import java.util.List;

public class Check {

    // Tìm User theo userid
    public static User findUserById(List<User> users, int userid) {
        for (User user : users) {
            if (user.getUID() == userid) {
                return user;
            }
        }
        return null;
    }

    // Tìm Book theo tiêu đề
    public static Book findBookByTitle(List<Book> books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Kiểm tra và hiển thị danh sách phiếu mượn quá hạn
    public static void checkAndDisplayOverdue(List<BorrowSlip> borrowSlips, List<User> users, List<Book> books) {
        LocalDate today = LocalDate.now();
        boolean foundOverdue = false;

        for (BorrowSlip slip : borrowSlips) {
            if (!slip.isReturned() && slip.getDueDate().isBefore(today)) {
                foundOverdue = true;

                User user = findUserById(users, slip.getUID());
                Book book = findBookByTitle(books, slip.getBookTitle());

                System.out.println("=== Phiếu mượn quá hạn ===");
                if (user != null) {
                    System.out.println("User: " + user.getName() + " (ID: " + user.getUID() + ")");
                    System.out.println("Email: " + user.getEmail());
                } else {
                    System.out.println("User: Không tìm thấy user với ID " + slip.getUID());
                }

                if (book != null) {
                    System.out.println("Book: " + book.getTitle());
                    System.out.println("  - Tác giả: " + book.getAuthor());
                    System.out.println("  - Nhà xuất bản: " + book.getPublisher());
                    System.out.println("  - Số trang: " + book.getNumPages());
                    System.out.println("  - Số lượng hiện có: " + book.getQuantity());
                } else {
                    System.out.println("Book: Không tìm thấy sách với tiêu đề \"" + slip.getBookTitle() + "\"");
                }

                System.out.println("Ngày mượn: " + slip.getBorrowDate());
                System.out.println("Ngày trả dự kiến: " + slip.getDueDate());
                System.out.println();
            }
        }

        if (!foundOverdue) {
            System.out.println("Không có phiếu mượn nào quá hạn trong ngày hôm nay.");
        }
    }
}
