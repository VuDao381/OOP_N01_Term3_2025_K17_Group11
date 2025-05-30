import java.util.ArrayList;

public class BookManagement {
    private ArrayList<Book> books = new ArrayList<>();

    // Thêm sách mới (nếu sách cùng tiêu đề, tác giả, nhà xuất bản tồn tại thì tăng số lượng)
    public void addBook(Book newBook) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(newBook.getTitle())
                    && book.getAuthor().equalsIgnoreCase(newBook.getAuthor())
                    && book.getPublisher().equalsIgnoreCase(newBook.getPublisher())) {
                // Nếu sách đã có, cộng dồn số lượng
                int updatedQuantity = book.getQuantity() + newBook.getQuantity();
                book.setQuantity(updatedQuantity);
                return;
            }
        }
        // Nếu sách chưa có thì thêm mới
        books.add(newBook);
    }

    // Xóa sách theo tiêu đề (nếu có nhiều sách trùng tiêu đề thì xóa tất cả)
    public boolean deleteBookByTitle(String title) {
        return books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    // Xem danh sách sách
    public void printBooks() {
        if (books.isEmpty()) {
            System.out.println("Danh sách sách trống.");
            return;
        }
        for (Book book : books) {
            System.out.println("Tiêu đề: " + book.getTitle() +
                               " | Tác giả: " + book.getAuthor() +
                               " | Nhà xuất bản: " + book.getPublisher() +
                               " | Số trang: " + book.getNumPages() +
                               " | Số lượng: " + book.getQuantity());
        }
    }
    public ArrayList<Book> getBooks() {
        return books;
    }
}
