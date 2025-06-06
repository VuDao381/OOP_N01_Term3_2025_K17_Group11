import java.util.ArrayList;

public class BorrowSlipManagement {
    private ArrayList<BorrowSlip> borrowSlips = new ArrayList<>();

    // Thêm phiếu mượn mới
    public void addBorrowSlip(BorrowSlip newSlip) {
        borrowSlips.add(newSlip);
    }

    // Xóa phiếu mượn theo bookTitle và userid
    public boolean deleteBorrowSlip(String bookTitle, int userid) {
        return borrowSlips.removeIf(slip -> 
            slip.getBookTitle().equalsIgnoreCase(bookTitle) && slip.getUID() == userid);
    }

    // In danh sách phiếu mượn
    public void printBorrowSlips() {
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
}
