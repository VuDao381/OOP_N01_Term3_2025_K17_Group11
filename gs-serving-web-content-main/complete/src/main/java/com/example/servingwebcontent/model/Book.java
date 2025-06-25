package com.example.servingwebcontent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "library_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "author", length = 30)
    private String author;

    @Column(name = "publisher", length = 30)
    private String publisher;

    @Column(name = "numPages")
    private int numPages;

    @Column(name = "quantity")
    private int quantity;

    public Book() {
    }

    public Book(String title, String author, String publisher, int numPages, int quantity) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.numPages = numPages;
        this.quantity = quantity;
    }

    // Getter và Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumPages() {
        return numPages;
    }
    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
