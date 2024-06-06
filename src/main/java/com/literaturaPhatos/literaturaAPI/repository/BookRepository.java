package com.literaturaPhatos.literaturaAPI.repository;

import com.literaturaPhatos.literaturaAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByTitleContainsIgnoreCase(String title);
}
