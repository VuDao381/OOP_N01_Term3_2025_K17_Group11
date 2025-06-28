package com.example.servingwebcontent.dto;

public class ReportDTO {
    private long totalBorrowed;
    private long totalReturned;
    private long totalOverdue;

    public ReportDTO(long totalBorrowed, long totalReturned, long totalOverdue) {
        this.totalBorrowed = totalBorrowed;
        this.totalReturned = totalReturned;
        this.totalOverdue = totalOverdue;
    }

    public long getTotalBorrowed() { return totalBorrowed; }
    public long getTotalReturned() { return totalReturned; }
    public long getTotalOverdue() { return totalOverdue; }

    public void setTotalBorrowed(long totalBorrowed) { this.totalBorrowed = totalBorrowed; }
    public void setTotalReturned(long totalReturned) { this.totalReturned = totalReturned; }
    public void setTotalOverdue(long totalOverdue) { this.totalOverdue = totalOverdue; }
}