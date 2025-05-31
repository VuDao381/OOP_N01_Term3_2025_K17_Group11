import java.util.Scanner;

public class TestBookManagement {

    public static void testbook() {
        BookManagement bm = new BookManagement();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập số lượng sách muốn thêm:");
        int n = 0;
        while (true) {
            try {
                n = Integer.parseInt(scanner.nextLine());
                if (n < 0) {
                    System.out.println("Số lượng phải là số nguyên không âm. Nhập lại:");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ:");
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.printf("Nhập thông tin sách thứ %d:\n", i + 1);

            System.out.print("Tiêu đề sách: ");
            String title = scanner.nextLine();

            System.out.print("Tác giả: ");
            String author = scanner.nextLine();

            System.out.print("Nhà xuất bản: ");
            String publisher = scanner.nextLine();

            int pages = 0;
            while (true) {
                System.out.print("Số trang: ");
                try {
                    pages = Integer.parseInt(scanner.nextLine());
                    if (pages <= 0) {
                        System.out.println("Số trang phải lớn hơn 0. Nhập lại:");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số hợp lệ cho số trang:");
                }
            }

            int quantity = 0;
            while (true) {
                System.out.print("Số lượng: ");
                try {
                    quantity = Integer.parseInt(scanner.nextLine());
                    if (quantity < 0) {
                        System.out.println("Số lượng không được âm. Nhập lại:");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số hợp lệ cho số lượng:");
                }
            }

            Book book = new Book(title, author, publisher, pages, quantity);
            bm.addBook(book);
        }

        System.out.println("\nDanh sách sách hiện có:");
        bm.printBooks();

        System.out.println("\nNhập tiêu đề sách muốn xóa:");
        String deleteTitle = scanner.nextLine();

        bm.deleteBookByTitle(deleteTitle);

        System.out.println("\nDanh sách sách sau khi xóa:");
        bm.printBooks();

        scanner.close();
    }
}
