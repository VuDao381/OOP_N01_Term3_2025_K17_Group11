public class TestBookManagement {
    public static void testbook(String[] args) {
        BookManagement bm = new BookManagement();

        Book b1 = new Book("Lập trình Java", "Nguyễn Văn A", "NXB Trẻ", 500, 10);
        Book b2 = new Book("Lập trình Java", "Nguyễn Văn A", "NXB Trẻ", 500, 5);
        Book b3 = new Book("Cấu trúc dữ liệu", "Trần Thị B", "NXB Giáo dục", 300, 7);

        bm.addBook(b1);
        bm.addBook(b2); // sẽ cộng dồn số lượng với b1
        bm.addBook(b3);

        bm.printBooks();

        System.out.println("\nXóa sách 'Lập trình Java'");
        bm.deleteBookByTitle("Lập trình Java");

        bm.printBooks();
    }
}
