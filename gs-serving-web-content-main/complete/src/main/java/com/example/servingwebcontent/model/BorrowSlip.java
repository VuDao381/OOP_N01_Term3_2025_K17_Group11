package com.example.servingwebcontent.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "BorrowSlip")
public class BorrowSlip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookTitle;
    private Long userid; // nên dùng Long để khớp với User ID
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean isReturned;

    public BorrowSlip() {}

    public BorrowSlip(String bookTitle, Long userid, LocalDate borrowDate, LocalDate dueDate) {
        this.bookTitle = bookTitle;
        this.userid = userid;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.isReturned = false;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Long getUserid() {
        return userid;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}
