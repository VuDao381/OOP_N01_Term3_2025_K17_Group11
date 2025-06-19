package com.example.servingwebcontent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserBorrowedController {

    @Autowired
    private UserBorrowed userBorrowed;

    @GetMapping("/borrowed")
    public String showBorrowedBooks(@RequestParam("uid") int userId, Model model) {
        List<BorrowSlip> allSlips = userBorrowed.getAllBorrowSlips();
        List<BorrowSlip> borrowedList = allSlips.stream()
                .filter(slip -> slip.getUID() == userId)
                .collect(Collectors.toList());

        LocalDate today = LocalDate.now();
        List<BorrowSlipNotice> dueSoonList = borrowedList.stream()
                .filter(slip -> !slip.isReturned())
                .filter(slip -> {
                    long daysLeft = ChronoUnit.DAYS.between(today, slip.getDueDate());
                    return daysLeft >= 0 && daysLeft <= 3;
                })
                .map(slip -> new BorrowSlipNotice(slip, ChronoUnit.DAYS.between(today, slip.getDueDate())))
                .collect(Collectors.toList());

        model.addAttribute("borrowedList", borrowedList);
        model.addAttribute("dueSoonList", dueSoonList);

        return "user-borrowed";
    }
}
