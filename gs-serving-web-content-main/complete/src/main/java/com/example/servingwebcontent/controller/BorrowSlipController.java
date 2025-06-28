package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.BorrowSlip;
import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.service.BorrowSlipService;
import com.example.servingwebcontent.service.BookService;
import com.example.servingwebcontent.service.UserService;
import com.example.servingwebcontent.dto.BorrowSlipDTO;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/borrow-slips")
@CrossOrigin(origins = "*")
public class BorrowSlipController {

    private final BorrowSlipService borrowSlipService;
    private final UserService userService;
    private final BookService bookService;

    public BorrowSlipController(BorrowSlipService borrowSlipService,
                                UserService userService,
                                BookService bookService) {
        this.borrowSlipService = borrowSlipService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping
    public List<BorrowSlip> getAllBorrowSlips() {
        return borrowSlipService.getAllBorrowSlips();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowSlip> getBorrowSlipById(@PathVariable Long id) {
        return borrowSlipService.getBorrowSlipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createBorrowSlip(@RequestBody BorrowSlipDTO dto) {
        Optional<User> user = userService.getUserById(dto.getUserId());
        Optional<Book> book = bookService.getBookById(dto.getBookId());

        if (user.isEmpty() || book.isEmpty()) {
            return ResponseEntity.badRequest().body("User hoặc Book không tồn tại.");
        }

        BorrowSlip slip = new BorrowSlip();
        slip.setUser(user.get());
        slip.setBook(book.get());

        // ✅ Chuyển String -> LocalDate
        slip.setBorrowDate(LocalDate.parse(dto.getBorrowDate()));
        slip.setDueDate(LocalDate.parse(dto.getDueDate()));

        slip.setReturned(false);

        BorrowSlip savedSlip = borrowSlipService.saveBorrowSlip(slip);
        return ResponseEntity.ok(savedSlip);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateBorrowSlip(@PathVariable Long id, @RequestBody BorrowSlipDTO dto) {
        return borrowSlipService.getBorrowSlipById(id)
                .map(existingSlip -> {
                    Optional<User> user = userService.getUserById(dto.getUserId());
                    Optional<Book> book = bookService.getBookById(dto.getBookId());

                    if (user.isEmpty() || book.isEmpty()) {
                        return ResponseEntity.badRequest().body("User hoặc Book không tồn tại.");
                    }

                    existingSlip.setUser(user.get());
                    existingSlip.setBook(book.get());
                    existingSlip.setBorrowDate(LocalDate.parse(dto.getBorrowDate()));
                    existingSlip.setDueDate(LocalDate.parse(dto.getDueDate()));


                    BorrowSlip updated = borrowSlipService.saveBorrowSlip(existingSlip);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowSlip(@PathVariable Long id) {
        boolean deleted = borrowSlipService.deleteBorrowSlip(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/recent")
    public List<Map<String, Object>> getRecentBorrowedBooks() {
        List<BorrowSlip> slips = borrowSlipService.getRecentBorrowSlips(5);
        List<Map<String, Object>> result = new ArrayList<>();

        for (BorrowSlip slip : slips) {
            Map<String, Object> map = new HashMap<>();
            map.put("bookTitle", slip.getBook().getTitle());
            map.put("userName", slip.getUser().getUsername());
            map.put("borrowDate", slip.getBorrowDate());
            map.put("dueDate", slip.getDueDate());
            result.add(map);
        }

        return result;
    }

    @GetMapping("/popular")
    public List<Map<String, Object>> getPopularBooks() {
        List<Book> books = borrowSlipService.getPopularBooks(5);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Book book : books) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", book.getTitle());
            map.put("author", book.getAuthor());
            map.put("publisher", book.getPublisher());
            result.add(map);
        }

        return result;
    }
}
