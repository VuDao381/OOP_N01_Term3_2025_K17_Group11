public class Book {
    private String title;
    private String author;
    private String publisher;
    private int numPages;

    public Book(String title, String author, String publisher, int numPages){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.numPages = numPages;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getPublisher(){
        return publisher;
    }
    public int getNumPages(){
        return numPages;
    }
    public void setBook(String title, String author, String publisher, int numPages){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.numPages = numPages;
    }
}
