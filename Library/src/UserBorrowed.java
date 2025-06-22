package com.example.servingwebcontent;
// UserBorrowed.java
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component 
public class UserBorrowed {
    private List<BorrowSlip> borrowSlips = new ArrayList<>();

    // Phương thức thêm phiếu mượn vào danh sách
    public void addBorrowSlip(BorrowSlip borrowSlip) {
    try{
        borrowSlips.add(borrowSlip);
    }
    catch(Exception e){
        System.err.println("Lỗi khi thêm phiếu mượn vào danh sách: " + e.getMessage());
    }
    finally{
        System.out.println("Kết thúc thêm phiếu mượn vào danh sách");
    }
    }

    // Phương thức hiển thị danh sách sách đã mượn của người dùng theo userID
    public void displayUserBorrowedBooks(int userId) {
    try{
        boolean found = false;
        System.out.println("Danh sách sách đã mượn của người dùng với ID: " + userId);
        for (BorrowSlip slip : borrowSlips) {
            if (slip.getUID() == userId) {
                found = true;
                System.out.println("- " + slip.getBookTitle() + " (Ngày mượn: " + slip.getBorrowDate() + ", Ngày trả: " + slip.getDueDate() + ")");
            }
        }
        if (!found) {
            System.out.println("Không có sách nào được mượn bởi người dùng này.");
        }
    }
    catch(Exception e){
        System.err.println("Lỗi khi hiển thị dánh sách sách đã mượn của người dùng: " + e.getMessage());
    }
    finally{
        System.out.println("Kết thúc hiển thị danh sách sách đã mượn của người dùng.");
    }
    }

    // Phương thức tìm phiếu mượn của người dùng theo tên sách
    public BorrowSlip getBorrowSlipByBookTitle(String bookTitle) {
    try{
        for (BorrowSlip slip : borrowSlips) {
            if (slip.getBookTitle().equals(bookTitle)) {
                return slip;
            }
        }
    }
    catch(Exception e){
        System.err.println("Lỗi khi tìm phiếu mượn của người dùng theo tên sách: " + e.getMessage());
    }
    finally{
        System.out.println("Kết thúc tìm phiếu mượn của người dùng theo tên sách.");
    }
    return null;
}
    public void notifyUpcomingDueDates() {
        LocalDate today = LocalDate.now();
        boolean hasNotice = false;

        System.out.println("== THÔNG BÁO SÁCH SẮP ĐẾN HẠN TRẢ ==");

        try{
        for (BorrowSlip slip : borrowSlips) {
            LocalDate dueDate = slip.getDueDate();
            if (!slip.isReturned()) {
                long daysLeft = today.until(dueDate).getDays();
                if(daysLeft >= 0 && daysLeft <= 3){
                hasNotice = true;
                System.out.println("📚 Sách: " + slip.getBookTitle());
                System.out.println("   - ID người mượn: " + slip.getUID());
                System.out.println("   - Hạn trả: " + dueDate);
                System.out.println("   - Còn: " + daysLeft + "ngày.");
                System.out.println();
                }
            }
        }
        if (!hasNotice) {
            System.out.println("Không có sách nào sắp đến hạn trả trong 3 ngày tới.");
        }
        }
        catch(Exception e){
            System.err.println("Lỗi khi thông báo hạn trả: " + e.getMessage());
        }
        finally{
            System.out.println("Kết thúc thông báo hạn trả.");
        }
    }
    public List<BorrowSlip> getAllBorrowSlips() {
    return borrowSlips;
}
}
