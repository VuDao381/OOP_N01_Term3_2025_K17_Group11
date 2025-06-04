import java.util.ArrayList;
import java.util.List;

public class TestCheck {
    public static void main(String[] args) {
        // Danh sách người dùng
        List<User> users = new ArrayList<>();
        users.add(new User("Nguyễn Văn A", "a@gmail.com", "123456"));
        users.add(new User("Trần Thị B", "b@gmail.com", "abcdef"));

        // Danh sách sách
        List<Book> books = new ArrayList<>();
        books.add(new Book("Java Cơ Bản", "Tác giả A", "NXB Giáo Dục", 300, 5));
        books.add(new Book("Lập Trình Nâng Cao", "Tác giả B", "NXB Khoa Học", 450, 3));

        // Danh sách phiếu mượn (1 phiếu quá hạn, 1 chưa quá hạn)
        List<BorrowSlip> borrowSlips = new ArrayList<>();
        borrowSlips.add(new BorrowSlip("Java Cơ Bản", 1, "2024-05-01", "2024-06-01"));  // quá hạn
        borrowSlips.add(new BorrowSlip("Lập Trình Nâng Cao", 2, "2025-06-01", "2025-06-10")); // chưa quá hạn

        // Gọi hàm kiểm tra quá hạn
        check.checkAndDisplayOverdue(borrowSlips, users, books);
    }
}
