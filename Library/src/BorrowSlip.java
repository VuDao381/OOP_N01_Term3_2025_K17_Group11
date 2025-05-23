public class BorrowSlip {
    private String bookTitle;
    private int userid;
    private String borrowDate;
    private String returnDate;

    public BorrowSlip(String bookTitle, int userid, String borrowDate, String returnDate) {
        this.bookTitle = bookTitle;
        this.userid = userid;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
    public String getBookTitle(){
        return bookTitle;
    }
    public int getUID(){
        return userid;
    }
    public String getBorrowDate(){
        return borrowDate;
    }
    public String getReturnDate(){
        return returnDate;
    }
    public void setBorrowSlip(String bookTitle, int userid, String borrowDate, String returnDate){
        this.bookTitle = bookTitle;
        this.userid = userid;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
}
