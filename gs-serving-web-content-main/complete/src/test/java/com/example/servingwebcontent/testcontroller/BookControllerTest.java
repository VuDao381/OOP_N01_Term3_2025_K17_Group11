package com.example.servingwebcontent.testcontroller;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.servingwebcontent.controller.BookController;
import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Book sampleBook;

    @BeforeEach
    void setup() {
        sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setTitle("Java Basics");
        sampleBook.setAuthor("John Doe");
        sampleBook.setPublisher("TechPress");
        sampleBook.setNumPages(300);
        sampleBook.setQuantity(10);
    }

    @Test
    void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(sampleBook));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Java Basics")));
    }

    @Test
    void testGetBookById_Found() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(Optional.of(sampleBook));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author", is("John Doe")));
    }

    @Test
    void testGetBookById_NotFound() throws Exception {
        when(bookService.getBookById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateBook() throws Exception {
        when(bookService.saveBook(Mockito.any(Book.class))).thenReturn(sampleBook);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(sampleBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Java Basics")));
    }

    @Test
    void testUpdateBook_Found() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(Optional.of(sampleBook));
        when(bookService.saveBook(Mockito.any(Book.class))).thenReturn(sampleBook);

        mockMvc.perform(put("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(sampleBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Java Basics")));
    }

    @Test
    void testUpdateBook_NotFound() throws Exception {
        when(bookService.getBookById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/books/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(sampleBook)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteBook_Found() throws Exception {
        when(bookService.deleteBook(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteBook_NotFound() throws Exception {
        when(bookService.deleteBook(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/books/999"))
                .andExpect(status().isNotFound());
    }
}
