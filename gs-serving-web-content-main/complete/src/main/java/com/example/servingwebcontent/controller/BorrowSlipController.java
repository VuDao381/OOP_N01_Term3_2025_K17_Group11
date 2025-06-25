package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.BorrowSlip;
import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.service.BorrowSlipService;
import com.example.servingwebcontent.service.BookService;
import com.example.servingwebcontent.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrow-slips")
@CrossOrigin(origins = "*") // Cho phép frontend gọi từ domain khác
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

    // GET: /api/borrow-slips - Lấy tất cả phiếu mượn
    @GetMapping
    public List<BorrowSlip> getAllBorrowSlips() {
        return borrowSlipService.getAllBorrowSlips();
    }

    // GET: /api/borrow-slips/{id} - Lấy 1 phiếu mượn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<BorrowSlip> getBorrowSlipById(@PathVariable Long id) {
        return borrowSlipService.getBorrowSlipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: /api/borrow-slips - Tạo mới phiếu mượn
    @PostMapping
    public ResponseEntity<?> createBorrowSlip(@RequestBody BorrowSlip slip) {
        Optional<User> user = userService.getUserById(slip.getUser().getId());
        Optional<Book> book = bookService.getBookById(slip.getBook().getId());

        if (user.isEmpty() || book.isEmpty()) {
            return ResponseEntity.badRequest().body("User hoặc Book không tồn tại.");
        }

        slip.setUser(user.get());
        slip.setBook(book.get());
        BorrowSlip savedSlip = borrowSlipService.saveBorrowSlip(slip);
        return ResponseEntity.ok(savedSlip);
    }

    // PUT: /api/borrow-slips/{id} - Cập nhật phiếu mượn
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBorrowSlip(@PathVariable Long id, @RequestBody BorrowSlip updatedSlip) {
        return borrowSlipService.getBorrowSlipById(id)
                .map(existingSlip -> {
                    Optional<User> user = userService.getUserById(updatedSlip.getUser().getId());
                    Optional<Book> book = bookService.getBookById(updatedSlip.getBook().getId());

                    if (user.isEmpty() || book.isEmpty()) {
                        return ResponseEntity.badRequest().body("User hoặc Book không tồn tại.");
                    }

                    updatedSlip.setId(id);
                    updatedSlip.setUser(user.get());
                    updatedSlip.setBook(book.get());

                    BorrowSlip saved = borrowSlipService.saveBorrowSlip(updatedSlip);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: /api/borrow-slips/{id} - Xoá phiếu mượn
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowSlip(@PathVariable Long id) {
        boolean deleted = borrowSlipService.deleteBorrowSlip(id);
        return deleted ? ResponseEntity.noContent().build()
                       : ResponseEntity.notFound().build();
    }
}
