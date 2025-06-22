package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByPublisher(String publisher);
}
