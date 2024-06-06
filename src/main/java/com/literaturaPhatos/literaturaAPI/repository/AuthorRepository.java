package com.literaturaPhatos.literaturaAPI.repository;

import com.literaturaPhatos.literaturaAPI.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameContainsIgnoreCase(String name);
}
