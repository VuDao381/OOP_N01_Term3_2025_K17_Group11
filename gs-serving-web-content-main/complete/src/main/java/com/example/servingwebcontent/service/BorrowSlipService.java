package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.model.BorrowSlip;
import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.repository.BorrowSlipRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowSlipService {

    private final BorrowSlipRepository borrowSlipRepository;
    private final BookService bookService;

    public BorrowSlipService(BorrowSlipRepository borrowSlipRepository, BookService bookService) {
        this.borrowSlipRepository = borrowSlipRepository;
        this.bookService = bookService;
    }

    public List<BorrowSlip> getAllBorrowSlips() {
        return borrowSlipRepository.findAll();
    }

    public Optional<BorrowSlip> getBorrowSlipById(Long id) {
        return borrowSlipRepository.findById(id);
    }

    public List<BorrowSlip> getBorrowSlipsByUser(User user) {
        return borrowSlipRepository.findByUser(user);
    }

    public List<BorrowSlip> getBorrowSlipsByBook(Book book) {
        return borrowSlipRepository.findByBook(book);
    }

    public List<BorrowSlip> getBorrowSlipsByReturnedStatus(boolean isReturned) {
        return borrowSlipRepository.findByIsReturned(isReturned);
    }

    public BorrowSlip saveBorrowSlip(BorrowSlip borrowSlip) {
        return borrowSlipRepository.save(borrowSlip);
    }

    public boolean deleteBorrowSlip(Long id) {
        if (borrowSlipRepository.existsById(id)) {
            borrowSlipRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public long countCurrentlyBorrowedBooks() {
        return borrowSlipRepository.countByIsReturned(false);
    }

    public long countOverdueBooks() {
        return borrowSlipRepository.countByIsReturnedFalseAndDueDateBefore(LocalDate.now());
    }

    public List<BorrowSlip> getRecentBorrowSlips(int limit) {
        return borrowSlipRepository.findTop5ByOrderByBorrowDateDesc();
    }

    public List<Book> getPopularBooks(int limit) {
        List<Object[]> results = borrowSlipRepository.findTopBorrowedBooks();
        List<Book> topBooks = new ArrayList<>();
        int count = 0;

        for (Object[] row : results) {
            Long bookId = (Long) row[0];
            Optional<Book> bookOpt = bookService.getBookById(bookId);
            bookOpt.ifPresent(topBooks::add);
            if (++count >= limit) break;
        }

        return topBooks;
    }
}
