package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.BorrowSlip;
import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowSlipRepository extends JpaRepository<BorrowSlip, Long> {
    List<BorrowSlip> findByUser(User user);
    List<BorrowSlip> findByBook(Book book);
    List<BorrowSlip> findByIsReturned(boolean isReturned);

    long countByIsReturned(boolean isReturned);
    long countByIsReturnedFalseAndDueDateBefore(LocalDate date);

    List<BorrowSlip> findTop5ByOrderByBorrowDateDesc();

    @Query("SELECT bs.book.id, COUNT(bs.book.id) as borrowCount " +
       "FROM BorrowSlip bs GROUP BY bs.book.id ORDER BY borrowCount DESC")
    List<Object[]> findTopBorrowedBooks();

}
