package com.example.servingwebcontent.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.example.servingwebcontent.dto.BorrowSlipDTO;
import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.model.BorrowSlip;
import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.service.BookService;
import com.example.servingwebcontent.service.BorrowSlipService;
import com.example.servingwebcontent.service.UserService;

@RestController
@RequestMapping("/api/borrow-slips")
@CrossOrigin(origins = "*")
public class BorrowSlipController {

    private final BorrowSlipService borrowSlipService;
    private final UserService userService;
    private final BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BorrowSlipController.class);

    public BorrowSlipController(BorrowSlipService borrowSlipService,
                                UserService userService,
                                BookService bookService) {
        this.borrowSlipService = borrowSlipService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBorrowSlips() {
        try {
            List<BorrowSlip> slips = borrowSlipService.getAllBorrowSlips();
            return ResponseEntity.ok(slips);
        } catch (Exception e) {
            logger.error("Lỗi khi lấy danh sách phiếu mượn", e);
            return ResponseEntity.status(500).body("Lỗi khi lấy danh sách phiếu mượn.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBorrowSlipById(@PathVariable Long id) {
        try {
            return borrowSlipService.getBorrowSlipById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Lỗi khi lấy phiếu mượn với ID: {}", id, e);
            return ResponseEntity.status(500).body("Lỗi khi lấy phiếu mượn.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createBorrowSlip(@RequestBody BorrowSlipDTO dto) {
        try {
            Optional<User> user = userService.getUserById(dto.getUserId());
            Optional<Book> book = bookService.getBookById(dto.getBookId());

            if (user.isEmpty() || book.isEmpty()) {
                logger.warn("Tạo phiếu mượn thất bại: User ID {} hoặc Book ID {} không tồn tại.", dto.getUserId(), dto.getBookId());
                return ResponseEntity.badRequest().body("User hoặc Book không tồn tại.");
            }

            BorrowSlip slip = new BorrowSlip();
            slip.setUser(user.get());
            slip.setBook(book.get());
            slip.setBorrowDate(LocalDate.parse(dto.getBorrowDate()));
            slip.setDueDate(LocalDate.parse(dto.getDueDate()));
            slip.setReturned(false);

            BorrowSlip savedSlip = borrowSlipService.saveBorrowSlip(slip);
            logger.info("Đã tạo phiếu mượn mới cho User ID {} và Book ID {}", dto.getUserId(), dto.getBookId());
            return ResponseEntity.ok(savedSlip);
        } catch (Exception e) {
            logger.error("Lỗi khi tạo phiếu mượn", e);
            return ResponseEntity.status(500).body("Lỗi khi tạo phiếu mượn.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBorrowSlip(@PathVariable Long id, @RequestBody BorrowSlipDTO dto) {
        try {
            return borrowSlipService.getBorrowSlipById(id)
                    .map(existingSlip -> {
                        Optional<User> user = userService.getUserById(dto.getUserId());
                        Optional<Book> book = bookService.getBookById(dto.getBookId());

                        if (user.isEmpty() || book.isEmpty()) {
                            logger.warn("Cập nhật thất bại: User ID {} hoặc Book ID {} không tồn tại.", dto.getUserId(), dto.getBookId());
                            return ResponseEntity.badRequest().body("User hoặc Book không tồn tại.");
                        }

                        existingSlip.setUser(user.get());
                        existingSlip.setBook(book.get());
                        existingSlip.setBorrowDate(LocalDate.parse(dto.getBorrowDate()));
                        existingSlip.setDueDate(LocalDate.parse(dto.getDueDate()));

                        BorrowSlip updated = borrowSlipService.saveBorrowSlip(existingSlip);
                        logger.info("Cập nhật phiếu mượn ID {}", id);
                        return ResponseEntity.ok(updated);
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Lỗi khi cập nhật phiếu mượn ID {}", id, e);
            return ResponseEntity.status(500).body("Lỗi khi cập nhật phiếu mượn.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBorrowSlip(@PathVariable Long id) {
        try {
            boolean deleted = borrowSlipService.deleteBorrowSlip(id);
            if (deleted) {
                logger.info("Đã xoá phiếu mượn ID {}", id);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("Xoá phiếu mượn thất bại: không tìm thấy ID {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Lỗi khi xoá phiếu mượn ID {}", id, e);
            return ResponseEntity.status(500).body("Lỗi khi xoá phiếu mượn.");
        }
    }

    @GetMapping("/recent")
    public ResponseEntity<?> getRecentBorrowedBooks() {
        try {
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

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Lỗi khi lấy danh sách mượn gần đây", e);
            return ResponseEntity.status(500).body("Lỗi khi lấy danh sách mượn gần đây.");
        }
    }

    @GetMapping("/popular")
    public ResponseEntity<?> getPopularBooks() {
        try {
            List<Book> books = borrowSlipService.getPopularBooks(5);
            List<Map<String, Object>> result = new ArrayList<>();

            for (Book book : books) {
                Map<String, Object> map = new HashMap<>();
                map.put("title", book.getTitle());
                map.put("author", book.getAuthor());
                map.put("publisher", book.getPublisher());
                result.add(map);
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Lỗi khi lấy sách mượn phổ biến", e);
            return ResponseEntity.status(500).body("Lỗi khi lấy sách được mượn nhiều nhất.");
        }
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<?> markAsReturned(@PathVariable Long id) {
        try {
            Optional<BorrowSlip> optional = borrowSlipService.getBorrowSlipById(id);
            if (optional.isEmpty()) {
                logger.warn("Không tìm thấy phiếu mượn để đánh dấu trả: ID {}", id);
                return ResponseEntity.notFound().build();
            }

            BorrowSlip slip = optional.get();
            if (slip.isReturned()) {
                logger.warn("Phiếu mượn ID {} đã được trả trước đó.", id);
                return ResponseEntity.badRequest().body("Phiếu mượn này đã được trả trước đó.");
            }

            slip.setReturned(true);
            slip.setreturnDate(LocalDate.now());
            borrowSlipService.saveBorrowSlip(slip);

            logger.info("Đánh dấu đã trả cho phiếu mượn ID {}", id);
            return ResponseEntity.ok("Đã đánh dấu là đã trả.");
        } catch (Exception e) {
            logger.error("Lỗi khi đánh dấu phiếu mượn ID {} đã trả", id, e);
            return ResponseEntity.status(500).body("Lỗi khi đánh dấu đã trả.");
        }
    }
}
