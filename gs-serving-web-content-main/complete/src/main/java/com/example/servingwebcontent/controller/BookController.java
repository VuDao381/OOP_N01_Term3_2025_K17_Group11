package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Hiển thị danh sách sách
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book-list"; // trỏ tới file book-list.html trong thư mục templates
    }

    // Hiển thị form thêm sách
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form"; // trỏ tới file book-form.html
    }

    // Lưu sách mới hoặc cập nhật sách
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        model.addAttribute("book", book);
        return "book-form";
    }

    // Xoá sách
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
