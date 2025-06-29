package com.example.servingwebcontent.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servingwebcontent.dto.DashboardStats;
import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.model.BorrowSlip;
import com.example.servingwebcontent.service.BookService;
import com.example.servingwebcontent.service.BorrowSlipService;
import com.example.servingwebcontent.service.UserService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final BookService bookService;
    private final UserService userService;
    private final BorrowSlipService borrowSlipService;

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    public DashboardController(BookService bookService,
                               UserService userService,
                               BorrowSlipService borrowSlipService) {
        this.bookService = bookService;
        this.userService = userService;
        this.borrowSlipService = borrowSlipService;
    }

    // 1. Thống kê tổng quan
    @GetMapping("/stats")
    public DashboardStats getStats() {
        try {
            long totalBooks = bookService.countBooks();
            long totalUsers = userService.countUsers();
            long borrowedBooks = borrowSlipService.countCurrentlyBorrowedBooks();
            long overdueBooks = borrowSlipService.countOverdueBooks();

            logger.info("Lấy thống kê tổng quan dashboard thành công.");
            return new DashboardStats(totalBooks, totalUsers, borrowedBooks, overdueBooks);
        } catch (Exception e) {
            logger.error("Lỗi khi lấy thống kê tổng quan", e);
            return new DashboardStats(0, 0, 0, 0); // fallback an toàn
        }
    }

    // 2. Danh sách 5 lượt mượn gần nhất
    @GetMapping("/borrows/recent")
    public List<Map<String, Object>> getRecentBorrows() {
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            List<BorrowSlip> slips = borrowSlipService.getRecentBorrowSlips(5);

            for (BorrowSlip slip : slips) {
                Map<String, Object> map = new HashMap<>();
                map.put("bookTitle", slip.getBook().getTitle());
                map.put("userName", slip.getUser().getUsername());
                map.put("borrowDate", slip.getBorrowDate());
                map.put("dueDate", slip.getDueDate());
                result.add(map);
            }

            logger.info("Lấy 5 lượt mượn gần nhất thành công.");
        } catch (Exception e) {
            logger.error("Lỗi khi lấy lượt mượn gần nhất", e);
        }
        return result;
    }

    // 3. Danh sách sách mượn nhiều nhất
    @GetMapping("/books/popular")
    public List<Map<String, Object>> getPopularBooks() {
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            List<Book> books = borrowSlipService.getPopularBooks(5);

            for (Book book : books) {
                Map<String, Object> map = new HashMap<>();
                map.put("title", book.getTitle());
                map.put("author", book.getAuthor());
                result.add(map);
            }

            logger.info("Lấy sách được mượn nhiều nhất thành công.");
        } catch (Exception e) {
            logger.error("Lỗi khi lấy sách phổ biến", e);
        }
        return result;
    }
}
