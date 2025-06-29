package com.example.servingwebcontent.testcontroller;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.servingwebcontent.controller.ReportController;
import com.example.servingwebcontent.dto.ReportDTO;
import com.example.servingwebcontent.service.ReportService;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    @Test
    void testGetReport_ValidPeriod() throws Exception {
        ReportDTO mockReport = new ReportDTO(10, 8, 2);

        when(reportService.getReport("week")).thenReturn(mockReport);

        mockMvc.perform(get("/api/reports?period=week"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalBorrowed").value(10))
        .andExpect(jsonPath("$.totalReturned").value(8))
        .andExpect(jsonPath("$.totalOverdue").value(2));
    }

    @Test
    void testGetReport_InvalidPeriod() throws Exception {
        when(reportService.getReport("invalid")).thenThrow(new IllegalArgumentException("Invalid period"));

        mockMvc.perform(get("/api/reports?period=invalid"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Khoảng thời gian không hợp lệ. Chấp nhận: day, week, month, year."));
    }

    @Test
    void testGetReport_ServerError() throws Exception {
        when(reportService.getReport("week")).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(get("/api/reports?period=week"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Lỗi hệ thống khi lấy báo cáo."));
    }
}
