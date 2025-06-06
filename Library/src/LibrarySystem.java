import java.time.LocalDate; // ✅ Dùng LocalDate thay vì String
import java.util.ArrayList;
import java.util.HashMap;

public class LibrarySystem {
    ArrayList<BorrowSlip> borrowSlips = new ArrayList<>();
    HashMap<String, Boolean> bookStatus = new HashMap<>(); // key: bookTitle, value: isBorrowed

    // Khởi tạo trạng thái sách
    public void addBook(String bookTitle) {
        bookStatus.put(bookTitle, false); // ban đầu chưa mượn
    }

    // ✅ Chuyển borrowDate và returnDate sang kiểu LocalDate
    public void MuonSach(User user, String bookTitle, LocalDate borrowDate, LocalDate returnDate) {
        if (!bookStatus.containsKey(bookTitle)) {
            System.out.println("Cuốn sách '" + bookTitle + "' không tồn tại trong thư viện.");
            return;
        }

        if (bookStatus.get(bookTitle)) {
            System.out.println("Cuốn sách '" + bookTitle + "' hiện đang được mượn, không thể mượn thêm.");
            return;
        }

        // ✅ Tạo BorrowSlip mới với LocalDate
        BorrowSlip slip = new BorrowSlip(bookTitle, user.getUID(), borrowDate, returnDate);
        borrowSlips.add(slip);
        bookStatus.put(bookTitle, true); // cập nhật sách đã mượn

        // ✅ In ngày trực tiếp từ LocalDate
        System.out.println("Đã cho mượn sách thành công:");
        System.out.println("User: " + user.getName() + ", Book: " + bookTitle +
                ", Borrow Date: " + borrowDate + ", Return Date: " + returnDate);
    }
}
