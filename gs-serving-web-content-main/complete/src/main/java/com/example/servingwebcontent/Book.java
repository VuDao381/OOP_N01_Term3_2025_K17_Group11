package com.example.servingwebcontent;
// Book.java
public class Book {
    private String title;
    private String author;
    private String publisher;
    private int numPages;
    private int quantity;

    public Book(String title, String author, String publisher, int numPages, int quantity){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.numPages = numPages;
        this.quantity = quantity;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getPublisher(){
        return publisher;
    }
    public int getNumPages(){
        return numPages;
    }
    public int getQuantity(){
        return quantity;
    }

    public void setBook(String title, String author, String publisher, int numPages, int quantity){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.numPages = numPages;
        this.quantity = quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
