package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Lấy toàn bộ sách
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Tìm sách theo ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Thêm hoặc cập nhật sách
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Xoá sách theo ID
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Tìm sách theo tên
    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    // Tìm sách theo tác giả
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
}
