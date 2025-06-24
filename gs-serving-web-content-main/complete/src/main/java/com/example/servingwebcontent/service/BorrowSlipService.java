package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.model.BorrowSlip;
import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.repository.BorrowSlipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowSlipService {

    private final BorrowSlipRepository borrowSlipRepository;


    public BorrowSlipService(BorrowSlipRepository borrowSlipRepository) {
        this.borrowSlipRepository = borrowSlipRepository;
    }

    // Lấy tất cả phiếu mượn
    public List<BorrowSlip> getAllBorrowSlips() {
        return borrowSlipRepository.findAll();
    }

    // Lấy phiếu mượn theo ID
    public Optional<BorrowSlip> getBorrowSlipById(Long id) {
        return borrowSlipRepository.findById(id);
    }

    // Lấy phiếu mượn theo người dùng
    public List<BorrowSlip> getBorrowSlipsByUser(User user) {
        return borrowSlipRepository.findByUser(user);
    }

    // Lấy phiếu mượn theo sách
    public List<BorrowSlip> getBorrowSlipsByBook(Book book) {
        return borrowSlipRepository.findByBook(book);
    }

    // Lấy danh sách đã hoặc chưa trả
    public List<BorrowSlip> getBorrowSlipsByReturnedStatus(boolean isReturned) {
        return borrowSlipRepository.findByIsReturned(isReturned);
    }

    // Thêm hoặc cập nhật phiếu mượn
    public BorrowSlip saveBorrowSlip(BorrowSlip borrowSlip) {
        return borrowSlipRepository.save(borrowSlip);
    }

    // Xoá phiếu mượn
    public boolean deleteBorrowSlip(Long id) {
        if (borrowSlipRepository.existsById(id)) {
            borrowSlipRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
