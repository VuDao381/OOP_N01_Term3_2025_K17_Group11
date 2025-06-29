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

import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Cho phép frontend truy cập từ domain khác
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users - lấy danh sách người dùng
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            logger.info("Lấy danh sách người dùng thành công. Số lượng: {}", users.size());
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error("Lỗi khi lấy danh sách người dùng", e);
            return ResponseEntity.status(500).body("Không thể lấy danh sách người dùng.");
        }
    }

    // GET /api/users/{id} - lấy người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            Optional<User> user = userService.getUserById(id);
            if (user.isPresent()) {
                logger.info("Lấy người dùng với ID: {}", id);
                return ResponseEntity.ok(user.get());
            } else {
                logger.warn("Không tìm thấy người dùng với ID: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Lỗi khi lấy người dùng với ID: {}", id, e);
            return ResponseEntity.status(500).body("Lỗi hệ thống.");
        }
    }

    // POST /api/users - tạo người dùng mới
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User saved = userService.saveUser(user);
            logger.info("Tạo người dùng mới: {}", saved.getUsername());
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            logger.error("Lỗi khi tạo người dùng mới", e);
            return ResponseEntity.status(500).body("Không thể tạo người dùng.");
        }
    }

    // PUT /api/users/{id} - cập nhật người dùng
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        try {
            Optional<User> existing = userService.getUserById(id);
            if (existing.isPresent()) {
                updatedUser.setId(id);
                User saved = userService.saveUser(updatedUser);
                logger.info("Cập nhật người dùng ID: {}", id);
                return ResponseEntity.ok(saved);
            } else {
                logger.warn("Không tìm thấy người dùng để cập nhật, ID: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Lỗi khi cập nhật người dùng ID: {}", id, e);
            return ResponseEntity.status(500).body("Không thể cập nhật người dùng.");
        }
    }

    // DELETE /api/users/{id} - xoá người dùng
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            boolean deleted = userService.deleteUser(id);
            if (deleted) {
                logger.info("Xoá người dùng ID: {}", id);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("Không tìm thấy người dùng để xoá, ID: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Lỗi khi xoá người dùng ID: {}", id, e);
            return ResponseEntity.status(500).body("Không thể xoá người dùng.");
        }
    }
}
