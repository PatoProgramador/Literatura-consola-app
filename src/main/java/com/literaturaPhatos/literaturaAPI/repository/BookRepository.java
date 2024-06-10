package com.literaturaPhatos.literaturaAPI.repository;

import com.literaturaPhatos.literaturaAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByTitleContainsIgnoreCase(String title);

    @Query("SELECT b FROM Book b WHERE b.language = :lan")
    List<Book> filterBooksByLanguage(String lan);

    @Query(value = "SELECT * FROM book ORDER BY downloads DESC LIMIT 10", nativeQuery= true)
    List<Book> filterBooksByDownloads();
}
