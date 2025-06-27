package com.example.servingwebcontent.dto;

public class DashboardStats {

    private long totalBooks;
    private long totalUsers;
    private long borrowedBooks;
    private long overdueBooks;

    public DashboardStats(long totalBooks, long totalUsers, long borrowedBooks, long overdueBooks) {
        this.totalBooks = totalBooks;
        this.totalUsers = totalUsers;
        this.borrowedBooks = borrowedBooks;
        this.overdueBooks = overdueBooks;
    }

    public long getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(long totalBooks) {
        this.totalBooks = totalBooks;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(long borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public long getOverdueBooks() {
        return overdueBooks;
    }

    public void setOverdueBooks(long overdueBooks) {
        this.overdueBooks = overdueBooks;
    }
}
