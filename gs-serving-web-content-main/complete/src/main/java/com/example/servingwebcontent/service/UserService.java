package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Lấy tất cả người dùng
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lấy người dùng theo ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Lưu hoặc cập nhật người dùng
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Xóa người dùng theo ID
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Tìm theo username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Tìm theo email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByUseremail(email);
    }

    public long countUsers() {
    return userRepository.count();
    }

}
