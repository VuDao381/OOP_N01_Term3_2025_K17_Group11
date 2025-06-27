package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.dto.DashboardStats;
import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.model.BorrowSlip;
import com.example.servingwebcontent.service.BookService;
import com.example.servingwebcontent.service.BorrowSlipService;
import com.example.servingwebcontent.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final BookService bookService;
    private final UserService userService;
    private final BorrowSlipService borrowSlipService;

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
        long totalBooks = bookService.countBooks();
        long totalUsers = userService.countUsers();
        long borrowedBooks = borrowSlipService.countCurrentlyBorrowedBooks();
        long overdueBooks = borrowSlipService.countOverdueBooks();

        return new DashboardStats(totalBooks, totalUsers, borrowedBooks, overdueBooks);
    }

    // 2. Danh sách 5 lượt mượn gần nhất
    @GetMapping("/borrows/recent")
    public List<Map<String, Object>> getRecentBorrows() {
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

    // 3. Danh sách sách mượn nhiều nhất
    @GetMapping("/books/popular")
    public List<Map<String, Object>> getPopularBooks() {
        List<Book> books = borrowSlipService.getPopularBooks(5);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Book book : books) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", book.getTitle());
            map.put("author", book.getAuthor());
            result.add(map);
        }

        return result;
    }
} 
