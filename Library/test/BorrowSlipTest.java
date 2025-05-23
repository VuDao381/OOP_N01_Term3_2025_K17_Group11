public class BorrowSlipTest {
    public static void testBorrowSlip(){
    BorrowSlip slip1 = new BorrowSlip("Detective Conan","Alex","1/1/2025","1/2/2025");    
        System.out.println("Book Title: " + slip1.getBookTitle());
        System.out.println("User Name: " + slip1.getUserName());
        System.out.println("Borrow Date: " + slip1.getBorrowDate());
        System.out.println("Return Date: " + slip1.getReturnDate());
    }
}
