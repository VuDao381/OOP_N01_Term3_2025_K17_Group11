package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.BorrowSlip;
import com.example.servingwebcontent.repository.BorrowSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowSlipService {

    @Autowired
    private BorrowSlipRepository borrowSlipRepository;

    public List<BorrowSlip> getAllBorrowSlips() {
        return borrowSlipRepository.findAll();
    }

    public Optional<BorrowSlip> getBorrowSlipById(Long id) {
        return borrowSlipRepository.findById(id);
    }

    public List<BorrowSlip> getBorrowSlipsByUserId(Long userid) {
        return borrowSlipRepository.findByUserid(userid);
    }

    public BorrowSlip saveBorrowSlip(BorrowSlip borrowSlip) {
        return borrowSlipRepository.save(borrowSlip);
    }

    public void deleteBorrowSlip(Long id) {
        borrowSlipRepository.deleteById(id);
    }

    public List<BorrowSlip> getBorrowSlipsByBookTitle(String bookTitle) {
        return borrowSlipRepository.findByBookTitle(bookTitle);
    }
}
