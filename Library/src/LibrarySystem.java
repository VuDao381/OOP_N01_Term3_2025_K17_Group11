import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class LibrarySystem {
    ArrayList<BorrowSlip> borrowSlips = new ArrayList<>();
    HashMap<String, Boolean> bookStatus = new HashMap<>(); // key: bookTitle, value: isBorrowed

    // Khởi tạo trạng thái sách
    public void addBook(String bookTitle) {
        try {
            bookStatus.put(bookTitle, false); // ban đầu chưa mượn
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm sách '" + bookTitle + "': " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Thao tác thêm sách đã được thực hiện.");
        }
    }

    // Cho mượn sách
    public void MuonSach(User user, String bookTitle, LocalDate borrowDate, LocalDate returnDate) {
        try {
            if (!bookStatus.containsKey(bookTitle)) {
                System.out.println("Cuốn sách '" + bookTitle + "' không tồn tại trong thư viện.");
                return;
            }

            if (bookStatus.get(bookTitle)) {
                System.out.println("Cuốn sách '" + bookTitle + "' hiện đang được mượn, không thể mượn thêm.");
                return;
            }

            // Tạo BorrowSlip mới với LocalDate
            BorrowSlip slip = new BorrowSlip(bookTitle, user.getUID(), borrowDate, returnDate);
            borrowSlips.add(slip);
            bookStatus.put(bookTitle, true); // cập nhật sách đã mượn

            System.out.println("Đã cho mượn sách thành công:");
            System.out.println("User: " + user.getName() + ", Book: " + bookTitle +
                    ", Borrow Date: " + borrowDate + ", Return Date: " + returnDate);
        } catch (Exception e) {
            System.err.println("Lỗi khi mượn sách '" + bookTitle + "': " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Thao tác mượn sách đã được thực hiện.");
        }
    }
}
