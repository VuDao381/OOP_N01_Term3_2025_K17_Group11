public class BookTest {
    public static void TestBook(){
        Book book1 = new Book("Detective Conan","Aoyama Goushou","Kim Đồng",100);
        System.out.println("Title: "+ book1.getTitle());
        System.out.println("Author: "+ book1.getAuthor());
        System.out.println("Publisher: "+ book1.getPublisher());
        System.out.println("Number Pages: "+ book1.getNumPages());
    }
}
