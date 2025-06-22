package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Hiển thị danh sách người dùng
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list"; // trỏ tới user-list.html
    }

    // Hiển thị form thêm người dùng
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form"; // trỏ tới user-form.html
    }

    // Lưu người dùng mới hoặc cập nhật
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    // Hiển thị form sửa người dùng
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user", user);
        return "user-form";
    }

    // Xóa người dùng
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
