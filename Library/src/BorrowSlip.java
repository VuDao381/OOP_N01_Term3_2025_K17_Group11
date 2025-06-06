import java.time.LocalDate;
public class BorrowSlip {
    private String bookTitle;
    private int userid;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean isReturned;

    public BorrowSlip(String bookTitle, int userid, LocalDate borrowDate, LocalDate dueDate) {
        this.bookTitle = bookTitle;
        this.userid = userid;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.isReturned = false;
    }
    public String getBookTitle(){
        return bookTitle;
    }
    public int getUID(){
        return userid;
    }
    public LocalDate getBorrowDate(){
        return borrowDate;
    }
    public LocalDate getDueDate(){
        return dueDate;
    }
    public boolean isReturned(){
        return isReturned;
    }
    public void setBorrowSlip(String bookTitle, int userid, LocalDate borrowDate, LocalDate dueDate){
        this.bookTitle = bookTitle;
        this.userid = userid;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }
    public void setReturned(boolean returned) {
    this.isReturned = returned;
    }
}
