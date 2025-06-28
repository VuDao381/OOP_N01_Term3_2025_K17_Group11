package com.example.servingwebcontent.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.servingwebcontent.dto.ReportDTO;
import com.example.servingwebcontent.repository.BorrowSlipRepository;

@Service
public class ReportService {

    @Autowired
    private BorrowSlipRepository borrowSlipRepository;

    public ReportDTO getReport(String period) {
        LocalDate now = LocalDate.now();

    LocalDate start = switch (period) {
        case "week"  -> now.minusDays(7);
        case "month" -> now.minusMonths(1);
        case "year"  -> now.minusYears(1);
        default      -> now.minusDays(7);
    };


        long totalBorrowed = borrowSlipRepository.findByBorrowDateBetween(start, now).size();
        long totalReturned = borrowSlipRepository.countReturnedBetween(start, now);
        long totalOverdue = borrowSlipRepository.countOverdueBetween(start, now);

        return new ReportDTO(totalBorrowed, totalReturned, totalOverdue);
    }
}
