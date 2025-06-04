import java.util.Date;
import java.util.ArrayList;

class User {
    String userId;
    String name;
    // Các thuộc tính khác
}

class Book {
    String bookId;
    String title;
    boolean isBorrowed; // trạng thái sách

    public Book(String bookId, String title) {
        this.bookId = bookId;
        this.title = title;
        this.isBorrowed = false;
    }
}

class BorrowSlip {
    User user;
    Book book;
    Date borrowDate;
    Date dueDate;

    public BorrowSlip(User user, Book book, Date borrowDate, Date dueDate) {
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "User: " + user.name + ", Book: " + book.title +
               ", Borrow Date: " + borrowDate + ", Due Date: " + dueDate;
    }
}

class LibrarySystem {
    ArrayList<BorrowSlip> borrowSlips = new ArrayList<>();

    public void MuonSach(User user, Book book, Date borrowDate, Date dueDate) {
        if (book.isBorrowed) {
            System.out.println("Cuốn sách '" + book.title + "' hiện đang được mượn, không thể mượn thêm.");
            return;
        }
        // Tạo phiếu mượn mới
        BorrowSlip slip = new BorrowSlip(user, book, borrowDate, dueDate);
        borrowSlips.add(slip);

        // Cập nhật trạng thái sách
        book.isBorrowed = true;

        System.out.println("Đã cho mượn sách thành công:");
        System.out.println(slip.toString());
    }
}
