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

    public void displayBorrowSlip() {
        System.out.println("Book Title: " + bookTitle);
        System.out.println("User Name: " + userName);
        System.out.println("Borrow Date: " + borrowDate);
        System.out.println("Return Date: " + returnDate);
    }
}
