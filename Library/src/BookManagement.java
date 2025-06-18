import java.util.ArrayList;

public class BookManagement {
    private ArrayList<Book> books = new ArrayList<>();

    // Thêm sách mới (nếu sách cùng tiêu đề, tác giả, nhà xuất bản tồn tại thì tăng số lượng)
    public void addBook(Book newBook) {
        try{
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
        books.add(newBook);
    }
    catch(Exception e){
        System.err.println("Lỗi khi thêm sách: " + e.getMessage());
    }
    finally{
        System.out.println("Kết thúc thêm sách.");
    }
    }

    // Xóa sách theo tiêu đề (nếu có nhiều sách trùng tiêu đề thì xóa tất cả)
    public boolean deleteBookByTitle(String title) {
        boolean removed = false;
    try{
        removed = books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }
    catch(Exception e){
        System.err.println("Lỗi khi xóa sách:" + e.getMessage());
    }
    finally{
        System.out.println("Kết thúc xóa sách.");
    }
    return removed;
    }
    public void printBooks() {
        try{
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
    catch(Exception e){
        System.err.println("Lỗi khi hiển thị danh sách sách:" + e.getMessage());
    }
    finally{
        System.out.println("Kết thúc hiển thị danh sách sách.");
    }
}
    public ArrayList<Book> getBooks() {
        return books;
    }
}
