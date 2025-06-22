package com.example.servingwebcontent;

import java.time.LocalDate;

public class BorrowSlipNotice {
    private BorrowSlip slip;
    private long daysLeft;

    public BorrowSlipNotice(BorrowSlip slip, long daysLeft) {
        this.slip = slip;
        this.daysLeft = daysLeft;
    }

    public String getBookTitle() {
        return slip.getBookTitle();
    }

    public LocalDate getDueDate() {
        return slip.getDueDate();
    }

    public long getDaysLeft() {
        return daysLeft;
    }
}
