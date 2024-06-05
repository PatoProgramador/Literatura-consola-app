package com.literaturaPhatos.literaturaAPI.model;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String language;
    private Double downloads;
    @ManyToOne
    private Author author;

    public Book(BookData bookData) {
        this.title = bookData.title();
        this.language = bookData.languages().get(0);
        Optional<AuthorData> autor = bookData.authors().stream()
                .findFirst();
        if (autor.isPresent()) {
            this.author = new Author();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getDownloads() {
        return downloads;
    }

    public void setDownloads(Double downloads) {
        this.downloads = downloads;
    }
}