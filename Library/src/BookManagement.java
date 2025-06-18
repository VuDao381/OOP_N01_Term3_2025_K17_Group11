import java.util.ArrayList;
import java.util.Objects;

public class BookManagement {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book newBook) {
        Objects.requireNonNull(newBook, "New book cannot be null.");

        try {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(newBook.getTitle())
                        && book.getAuthor().equalsIgnoreCase(newBook.getAuthor())
                        && book.getPublisher().equalsIgnoreCase(newBook.getPublisher())) {
                    // If book already exists, accumulate quantity
                    int updatedQuantity = book.getQuantity() + newBook.getQuantity();
                    book.setQuantity(updatedQuantity);
                    System.out.println("Cập nhật số lượng sách: " + newBook.getTitle());
                    return;
                }
            }
            books.add(newBook);
            System.out.println("Đã thêm sách mới: " + newBook.getTitle());
        } catch (NullPointerException e) {
            System.err.println("Lỗi dữ liệu sách: Một trường thông tin sách bị thiếu hoặc rỗng. " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi không xác định khi thêm sách: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean deleteBookByTitle(String title) {
        Objects.requireNonNull(title, "Tiêu đề không được null.");
        if (title.trim().isEmpty()) {
            System.err.println("Tiêu đề không được để trống.");
            return false;
        }

        boolean removed = false;
        try {
            removed = books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
            if (removed) {
                System.out.println("Đã xóa sách với tiêu đề: " + title);
            } else {
                System.out.println("Không tìm thấy sách với tiêu đề: " + title + " để xóa.");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa sách theo tiêu đề: " + e.getMessage());
            e.printStackTrace();
        }
        return removed;
    }

    public void printBooks() {
        if (books.isEmpty()) {
            System.out.println("Danh sách sách trống.");
            return;
        }
        System.out.println("--- Danh sách sách hiện có ---");
        try {
            for (Book book : books) {
                System.out.println("Tiêu đề: " + book.getTitle() +
                        " | Tác giả: " + book.getAuthor() +
                        " | Nhà xuất bản: " + book.getPublisher() +
                        " | Số trang: " + book.getNumPages() +
                        " | Số lượng: " + book.getQuantity());
            }
        } catch (NullPointerException e) {
            System.err.println("Lỗi hiển thị sách: Một số thông tin sách bị thiếu. " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Lỗi không xác định khi hiển thị danh sách sách: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("--- Kết thúc danh sách sách ---");
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}