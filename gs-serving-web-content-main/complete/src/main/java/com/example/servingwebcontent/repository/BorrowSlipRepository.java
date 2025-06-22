package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.BorrowSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowSlipRepository extends JpaRepository<BorrowSlip, Long> {
    List<BorrowSlip> findByUserid(Long userid);
    List<BorrowSlip> findByBookTitle(String bookTitle);
}
