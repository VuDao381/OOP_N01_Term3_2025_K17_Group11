import java.util.ArrayList;
import java.util.HashMap;

public class LibrarySystem {
    ArrayList<BorrowSlip> borrowSlips = new ArrayList<>();
    HashMap<String, Boolean> bookStatus = new HashMap<>(); // key: bookTitle, value: isBorrowed

    // Khởi tạo trạng thái sách (giả sử bạn đã có danh sách sách)
    public void addBook(String bookTitle) {
        bookStatus.put(bookTitle, false); // ban đầu chưa mượn
    }

    public void MuonSach(User user, String bookTitle, String borrowDate, String returnDate) {
        if (!bookStatus.containsKey(bookTitle)) {
            System.out.println("Cuốn sách '" + bookTitle + "' không tồn tại trong thư viện.");
            return;
        }
        if (bookStatus.get(bookTitle)) {
            System.out.println("Cuốn sách '" + bookTitle + "' hiện đang được mượn, không thể mượn thêm.");
            return;
        }

        BorrowSlip slip = new BorrowSlip(bookTitle, user.getUID(), borrowDate, returnDate);
        borrowSlips.add(slip);
        bookStatus.put(bookTitle, true); // cập nhật sách đã mượn

        System.out.println("Đã cho mượn sách thành công:");
        System.out.println("User: " + user.getName() + ", Book: " + bookTitle +
                ", Borrow Date: " + borrowDate + ", Return Date: " + returnDate);
    }
}
