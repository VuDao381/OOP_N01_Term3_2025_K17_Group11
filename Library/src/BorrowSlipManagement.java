import java.util.ArrayList;

public class BorrowSlipManagement {
    private ArrayList<BorrowSlip> borrowSlips = new ArrayList<>();

    // Thêm phiếu mượn mới
    public void addBorrowSlip(BorrowSlip newSlip) {
        try{
        borrowSlips.add(newSlip);
        }
        catch(Exception e){
            System.err.println("Lỗi khi thêm phiếu mượn: " + e.getMessage());
        }
        finally{
            System.out.println("Kết thúc thêm phiếu mượn");
        }
    }

    // Xóa phiếu mượn theo bookTitle và userid
    public boolean deleteBorrowSlip(String bookTitle, int userid) {
        boolean removed = false;
        try{
        removed = borrowSlips.removeIf(slip -> 
            slip.getBookTitle().equalsIgnoreCase(bookTitle) && slip.getUID() == userid);
        }
        catch(Exception e){
            System.err.println("Lỗi khi xóa phiếu mượn: " + e.getMessage());
        }
        finally{
            System.out.println("Kết thúc xóa phiếu mượn");
        }
        return removed;
    }

    // In danh sách phiếu mượn
    public void printBorrowSlips() {
    try{
        if (borrowSlips.isEmpty()) {
            System.out.println("Danh sách phiếu mượn trống.");
            return;
        }
        for (BorrowSlip slip : borrowSlips) {
            System.out.println("Sách: " + slip.getBookTitle() +
                               " | User ID: " + slip.getUID() +
                               " | Ngày mượn: " + slip.getBorrowDate() +
                               " | Hạn trả: " + slip.getDueDate() +
                               " | Tình trạng:" + (slip.isReturned() ? "Đã trả" : "Chưa "));
        }
    }
    catch(Exception e){
        System.err.println("Lỗi khi hiển thị danh sách phiếu mượn: " + e.getMessage());
    }
    }
}
