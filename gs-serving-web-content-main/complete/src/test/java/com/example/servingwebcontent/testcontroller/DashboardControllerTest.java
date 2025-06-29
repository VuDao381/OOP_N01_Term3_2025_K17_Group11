package com.example.servingwebcontent.testcontroller;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.servingwebcontent.controller.DashboardController;
import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.model.BorrowSlip;
import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.service.BookService;
import com.example.servingwebcontent.service.BorrowSlipService;
import com.example.servingwebcontent.service.UserService;

@WebMvcTest(DashboardController.class)
public class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private UserService userService;

    @MockBean
    private BorrowSlipService borrowSlipService;

    private Book book;
    private User user;
    private BorrowSlip slip;

    @BeforeEach
    void setup() {
        user = new User();
        user.setId(1L);
        user.setUsername("tester");

        book = new Book();
        book.setId(1L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");

        slip = new BorrowSlip();
        slip.setId(1L);
        slip.setUser(user);
        slip.setBook(book);
        slip.setBorrowDate(LocalDate.now().minusDays(2));
        slip.setDueDate(LocalDate.now().plusDays(5));
    }

    @Test
    void testGetStats() throws Exception {
        when(bookService.countBooks()).thenReturn(100L);
        when(userService.countUsers()).thenReturn(50L);
        when(borrowSlipService.countCurrentlyBorrowedBooks()).thenReturn(20L);
        when(borrowSlipService.countOverdueBooks()).thenReturn(5L);

        mockMvc.perform(get("/api/dashboard/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalBooks").value(100))
                .andExpect(jsonPath("$.totalUsers").value(50))
                .andExpect(jsonPath("$.borrowedBooks").value(20))
                .andExpect(jsonPath("$.overdueBooks").value(5));
    }

    @Test
    void testGetRecentBorrows() throws Exception {
        when(borrowSlipService.getRecentBorrowSlips(5)).thenReturn(List.of(slip));

        mockMvc.perform(get("/api/dashboard/borrows/recent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookTitle").value("Clean Code"))
                .andExpect(jsonPath("$[0].userName").value("tester"));
    }

    @Test
    void testGetPopularBooks() throws Exception {
        when(borrowSlipService.getPopularBooks(5)).thenReturn(List.of(book));

        mockMvc.perform(get("/api/dashboard/books/popular"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Clean Code"))
                .andExpect(jsonPath("$[0].author").value("Robert C. Martin"));
    }
}
