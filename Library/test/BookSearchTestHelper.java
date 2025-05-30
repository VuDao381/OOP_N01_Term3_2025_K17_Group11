import java.util.List;
import java.util.Scanner;

public class BookSearchTestHelper {

    public static void testSearchWithInput() {
        // Khởi tạo BookManagement và thêm sách
        BookManagement bm = new BookManagement();
        bm.addBook(new Book("Java Programming", "Nguyen Van A", "NXB Tre", 500, 10));
        bm.addBook(new Book("Python Basics", "Le Thi B", "NXB Tre", 350, 5));
        bm.addBook(new Book("Effective Java", "Joshua Bloch", "Addison-Wesley", 400, 3));
        bm.addBook(new Book("C++ Primer", "Stanley Lippman", "Addison-Wesley", 450, 7));

        // Tạo scanner để đọc từ bàn phím
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ khóa tìm kiếm (title hoặc author): ");
        String keyword = scanner.nextLine();

        // Tìm kiếm
        BookSearch search = new BookSearch();
        List<Book> results = search.searchByTitleOrAuthor(bm.getBooks(), keyword);

        // In kết quả tìm kiếm
        System.out.println("Kết quả tìm kiếm với từ khóa '" + keyword + "':");
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy sách nào.");
        } else {
            for (Book b : results) {
                System.out.println(b.getTitle() + " - " + b.getAuthor());
            }
        }

        // Đóng scanner (nếu bạn không cần đọc tiếp)
        scanner.close();
    }
}