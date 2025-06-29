package com.example.servingwebcontent.testcontroller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.servingwebcontent.controller.BorrowSlipController;
import com.example.servingwebcontent.dto.BorrowSlipDTO;
import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.model.BorrowSlip;
import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.service.BookService;
import com.example.servingwebcontent.service.BorrowSlipService;
import com.example.servingwebcontent.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BorrowSlipController.class)
public class BorrowSlipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowSlipService borrowSlipService;

    @MockBean
    private BookService bookService;

    @MockBean
    private UserService userService;

    private BorrowSlip sampleSlip;
    private User sampleUser;
    private Book sampleBook;

    @BeforeEach
    void setup() {
        sampleUser = new User();
        sampleUser.setId(1L);
        sampleUser.setUsername("tester");

        sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setTitle("Java Book");

        sampleSlip = new BorrowSlip();
        sampleSlip.setId(1L);
        sampleSlip.setUser(sampleUser);
        sampleSlip.setBook(sampleBook);
        sampleSlip.setBorrowDate(LocalDate.now());
        sampleSlip.setDueDate(LocalDate.now().plusDays(7));
        sampleSlip.setReturned(false);
    }

    @Test
    void testGetAllBorrowSlips() throws Exception {
        when(borrowSlipService.getAllBorrowSlips()).thenReturn(List.of(sampleSlip));

        mockMvc.perform(get("/api/borrow-slips"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBorrowSlipById_Found() throws Exception {
        when(borrowSlipService.getBorrowSlipById(1L)).thenReturn(Optional.of(sampleSlip));

        mockMvc.perform(get("/api/borrow-slips/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testCreateBorrowSlip_Success() throws Exception {
        BorrowSlipDTO dto = new BorrowSlipDTO();
        dto.setUserId(1L);
        dto.setBookId(1L);
        dto.setBorrowDate(LocalDate.now().toString());
        dto.setDueDate(LocalDate.now().plusDays(7).toString());

        when(userService.getUserById(1L)).thenReturn(Optional.of(sampleUser));
        when(bookService.getBookById(1L)).thenReturn(Optional.of(sampleBook));
        when(borrowSlipService.saveBorrowSlip(any())).thenReturn(sampleSlip);

        mockMvc.perform(post("/api/borrow-slips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testDeleteBorrowSlip_Success() throws Exception {
        when(borrowSlipService.deleteBorrowSlip(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/borrow-slips/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testMarkAsReturned_Success() throws Exception {
        sampleSlip.setReturned(false);
        when(borrowSlipService.getBorrowSlipById(1L)).thenReturn(Optional.of(sampleSlip));
        when(borrowSlipService.saveBorrowSlip(any())).thenReturn(sampleSlip);

        mockMvc.perform(put("/api/borrow-slips/1/return"))
                .andExpect(status().isOk())
                .andExpect(content().string("Đã đánh dấu là đã trả."));
    }
}
