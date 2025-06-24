package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.BorrowSlip;
import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowSlipRepository extends JpaRepository<BorrowSlip, Long> {
    List<BorrowSlip> findByUser(User user);
    List<BorrowSlip> findByBook(Book book);
    List<BorrowSlip> findByIsReturned(boolean isReturned);
}
