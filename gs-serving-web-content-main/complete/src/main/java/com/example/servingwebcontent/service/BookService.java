package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Tìm sách theo tên (chính xác)
    public Optional<Book> getBookByTitle(String title) {
        return Optional.ofNullable(bookRepository.findByTitle(title));
    }

    // Tìm sách theo tác giả
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    // Tìm sách theo nhà xuất bản
    public List<Book> getBooksByPublisher(String publisher) {
        return bookRepository.findByPublisher(publisher);
    }

    // Tìm sách theo keyword
    public List<Book> globalSearch(String keyword) {
    List<Book> results = bookRepository.findByTitleContainingIgnoreCase(keyword);
    results.addAll(bookRepository.findByAuthorContainingIgnoreCase(keyword));
    results.addAll(bookRepository.findByPublisherContainingIgnoreCase(keyword));
    return results;
    }

    public long countBooks() {
    return bookRepository.count();
}

}

