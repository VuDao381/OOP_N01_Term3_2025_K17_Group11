package com.example.servingwebcontent.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.servingwebcontent.dto.ReportDTO;
import com.example.servingwebcontent.service.ReportService;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @GetMapping
    public ResponseEntity<?> getReport(@RequestParam(defaultValue = "week") String period) {
        try {
            logger.info("Lấy báo cáo với khoảng thời gian: {}", period);
            ReportDTO report = reportService.getReport(period);
            return ResponseEntity.ok(report);
        } catch (IllegalArgumentException e) {
            logger.warn("Khoảng thời gian không hợp lệ: {}", period, e);
            return ResponseEntity.badRequest().body("Khoảng thời gian không hợp lệ. Chấp nhận: day, week, month, year.");
        } catch (Exception e) {
            logger.error("Lỗi khi lấy báo cáo", e);
            return ResponseEntity.status(500).body("Lỗi hệ thống khi lấy báo cáo.");
        }
    }
}
