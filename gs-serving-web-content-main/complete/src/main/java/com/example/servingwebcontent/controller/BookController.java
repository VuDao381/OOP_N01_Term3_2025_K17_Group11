package com.example.servingwebcontent.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.service.BookService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            logger.error("Lỗi khi lấy danh sách sách", e);
            return ResponseEntity.status(500).body("Lỗi máy chủ khi lấy danh sách sách.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            Optional<Book> book = bookService.getBookById(id);
            return book.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Lỗi khi lấy sách với ID: {}", id, e);
            return ResponseEntity.status(500).body("Lỗi máy chủ khi lấy sách.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        try {
            Book created = bookService.saveBook(book);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            logger.error("Lỗi khi tạo sách mới", e);
            return ResponseEntity.status(500).body("Lỗi máy chủ khi tạo sách.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        try {
            return bookService.getBookById(id)
                    .map(existingBook -> {
                        updatedBook.setId(id);
                        Book savedBook = bookService.saveBook(updatedBook);
                        return ResponseEntity.ok(savedBook);
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Lỗi khi cập nhật sách với ID: {}", id, e);
            return ResponseEntity.status(500).body("Lỗi máy chủ khi cập nhật sách.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            boolean deleted = bookService.deleteBook(id);
            return deleted ? ResponseEntity.noContent().build()
                           : ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Lỗi khi xoá sách với ID: {}", id, e);
            return ResponseEntity.status(500).body("Lỗi máy chủ khi xoá sách.");
        }
    }
}
