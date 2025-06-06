import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestCheck {
    public static void main(String[] args) {
        // Danh sách người dùng
        List<User> users = new ArrayList<>();
        users.add(new User("Nguyễn Văn A", "a@gmail.com", "123456")); // ID tự sinh, giả sử 1
        users.add(new User("Trần Thị B", "b@gmail.com", "abcdef"));   // ID tự sinh, giả sử 2

        // Danh sách sách
        List<Book> books = new ArrayList<>();
        books.add(new Book("Java Cơ Bản", "Tác giả A", "NXB Giáo Dục", 300, 5));
        books.add(new Book("Lập Trình Nâng Cao", "Tác giả B", "NXB Khoa Học", 450, 3));

        // Danh sách phiếu mượn
        List<BorrowSlip> borrowSlips = new ArrayList<>();

        // ✅ Dùng LocalDate.of hoặc LocalDate.parse
        borrowSlips.add(new BorrowSlip(
            "Java Cơ Bản", 
            1, 
            LocalDate.of(2024, 5, 1), 
            LocalDate.of(2024, 6, 1) // quá hạn
        ));

        borrowSlips.add(new BorrowSlip(
            "Lập Trình Nâng Cao", 
            2, 
            LocalDate.of(2025, 6, 1), 
            LocalDate.of(2025, 6, 10) // chưa quá hạn
        ));

        // Gọi hàm kiểm tra quá hạn
        Check.checkAndDisplayOverdue(borrowSlips, users, books);
    }
}
