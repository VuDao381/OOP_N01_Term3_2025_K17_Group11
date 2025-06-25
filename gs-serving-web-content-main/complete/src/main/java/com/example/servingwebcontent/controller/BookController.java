package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*") // Cho phép frontend truy cập (CORS)
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET: /api/books - Lấy danh sách tất cả sách
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // GET: /api/books/{id} - Lấy thông tin 1 sách theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // POST: /api/books - Tạo mới sách
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book created = bookService.saveBook(book);
        return ResponseEntity.ok(created);
    }

    // PUT: /api/books/{id} - Cập nhật thông tin sách
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.getBookById(id)
                .map(existingBook -> {
                    updatedBook.setId(id); // Đảm bảo ID đúng
                    Book savedBook = bookService.saveBook(updatedBook);
                    return ResponseEntity.ok(savedBook);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: /api/books/{id} - Xoá sách theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBook(id);
        return deleted ? ResponseEntity.noContent().build()
                       : ResponseEntity.notFound().build();
    }
}
