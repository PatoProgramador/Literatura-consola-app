package com.literaturaPhatos.literaturaAPI.main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.literaturaPhatos.literaturaAPI.model.Author;
import com.literaturaPhatos.literaturaAPI.model.Book;
import com.literaturaPhatos.literaturaAPI.model.BookData;
import com.literaturaPhatos.literaturaAPI.model.Data;
import com.literaturaPhatos.literaturaAPI.repository.AuthorRepository;
import com.literaturaPhatos.literaturaAPI.repository.BookRepository;
import com.literaturaPhatos.literaturaAPI.service.ConsumeAPI;
import com.literaturaPhatos.literaturaAPI.service.Conversor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String URL_PAGINATION = "?page=";
    private final String URL_SEARCH_BY_NAME = "?search=";
    private final Scanner SCANNER = new Scanner(System.in);
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private Conversor conversor = new Conversor();
    private ConsumeAPI consumeAPI = new ConsumeAPI();

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    private final String MENU = """
            Selecciona la opción ingresando el número correspondiente:
            1- Buscar libro por título.
            2- Listar libros registrados.
            3- Listar autores registrados.
            4- Listar autores vivos en un determinado año.
            5- Listar libros por idioma.
            0- Salir.
            """;
    private int option = -1;

    public void app() {
        while (option != 0) {
            System.out.println(MENU);
            option = SCANNER.nextInt();
            SCANNER.nextLine();

            switch (option) {
                case 1:
                    searchBookByTitle();
                    break;
                default:
                    System.out.println("Opción invalida");
                    break;
            }
        }
        String json = consumeAPI.getData(URL_BASE + URL_SEARCH_BY_NAME + "great+expectations");
        JsonArray results = consumeAPI.getDataResultsAsJson(json);
        System.out.println(results);
    }

    public Optional<BookData> getBookData(String userTitle) {
        String json = consumeAPI.getData(URL_BASE + URL_SEARCH_BY_NAME + userTitle.toLowerCase().replace(" ", "+"));
        List<BookData> books = conversor.convertData(json, Data.class).results();

        Optional<BookData> book = books.stream()
                .filter(l -> l.title().toLowerCase().contains(userTitle.toLowerCase()))
                .findFirst();

        return book;
    }

    public void searchBookByTitle() {
        System.out.println("Ingresa el titulo del libro que deseas buscar: ");
        String userTitle = SCANNER.nextLine();

        Optional<BookData> apiBook = getBookData(userTitle);
         //busqueda para evitar que se repitan libros en la db
        Optional<Book> dbBook = bookRepository.findByTitleContainsIgnoreCase(userTitle);
        if (dbBook.isPresent()) {
            System.out.println(dbBook.get());
            // si encontramos el libro en la api...
        } else if (apiBook.isPresent()) {
            // busqueda de autores
            List<Author> authorList = apiBook.get().authors().stream()
                    .map(a -> authorRepository.findByNameContainsIgnoreCase(a.name())
                            .orElseGet(() -> authorRepository.save(new Author(a))))
                    .collect(Collectors.toList());

            Book newDbBook = new Book(apiBook.get(),authorList);
            bookRepository.save(newDbBook);
            System.out.println(newDbBook);
        } else {
            System.out.println("Libro no encontrado :(");
        }
    }
}
