package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.BorrowSlip;
import com.example.servingwebcontent.service.BorrowSlipService;
import com.example.servingwebcontent.service.BookService;
import com.example.servingwebcontent.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/borrow-slips")
public class BorrowSlipController {

    private final BorrowSlipService borrowSlipService;
    private final UserService userService;
    private final BookService bookService;

    public BorrowSlipController(BorrowSlipService borrowSlipService, UserService userService, BookService bookService) {
        this.borrowSlipService = borrowSlipService;
        this.userService = userService;
        this.bookService = bookService;
    }

    // Hiển thị danh sách phiếu mượn
    @GetMapping
    public String listBorrowSlips(Model model) {
        model.addAttribute("borrowSlips", borrowSlipService.getAllBorrowSlips());
        return "borrow-slip-list";
    }

    // Hiển thị form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("borrowSlip", new BorrowSlip());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("books", bookService.getAllBooks());
        return "borrow-slip-form";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        BorrowSlip slip = borrowSlipService.getBorrowSlipById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid slip ID: " + id));
        model.addAttribute("borrowSlip", slip);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("books", bookService.getAllBooks());
        return "borrow-slip-form";
    }

    // Lưu phiếu mượn
    @PostMapping("/save")
    public String saveBorrowSlip(@ModelAttribute("borrowSlip") BorrowSlip slip) {
        borrowSlipService.saveBorrowSlip(slip);
        return "redirect:/borrow-slips";
    }

    // Xoá phiếu mượn
    @GetMapping("/delete/{id}")
    public String deleteBorrowSlip(@PathVariable("id") Long id) {
        borrowSlipService.deleteBorrowSlip(id);
        return "redirect:/borrow-slips";
    }
}
