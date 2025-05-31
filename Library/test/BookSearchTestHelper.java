import java.util.List;
import java.util.Scanner;

public class BookSearchTestHelper {

    public static void testSearchWithInput() {
        

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