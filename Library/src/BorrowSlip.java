public class BorrowSlip {
    private String bookTitle;
    private String userName;
    private String borrowDate;
    private String returnDate;

    public BorrowSlip(String bookTitle, String userName, String borrowDate, String returnDate) {
        this.bookTitle = bookTitle;
        this.userName = userName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
    public String getBookTitle(){
        return bookTitle;
    }
    public String getUserName(){
        return userName;
    }
    public String getBorrowDate(){
        return borrowDate;
    }
    public String getReturnDate(){
        return returnDate;
    }
    public void setBorrowSlip(String bookTitle, String userName, String borrowDate, String returnDate){
        this.bookTitle = bookTitle;
        this.userName = userName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
}
