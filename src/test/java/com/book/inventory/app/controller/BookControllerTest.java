package com.book.inventory.app.controller;

import com.book.inventory.app.model.Book;
import com.book.inventory.app.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BookControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void getBooksByAuthor() {
        Book book = new Book();
        book.setTitle("Ready to read");
        book.setAuthor("The chain");
        book.setPublishedDate(ZonedDateTime.now());
        bookRepository.save(book);
        ResponseEntity<List<BookResponse>> response = restTemplate.exchange(
                "/books?author=The chain",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get(0).getAuthor());
        assertEquals(book.getAuthor(), response.getBody().get(0).getAuthor());
    }

    @Test
    void createBookSuccessful() {
        BookRequest book = new BookRequest();
        book.setTitle("Ready to read");
        book.setAuthor("God chain");
        book.setPublishedDate("2568-01-01");
        ResponseEntity<BookRequest> response = restTemplate.postForEntity("/books", book, BookRequest.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(book.getAuthor(), response.getBody().getAuthor());
        assertEquals(1, bookRepository.findAll().size());
    }

    @Test
    void createBookWithValidateTitle() {
        BookRequest book = new BookRequest();
        book.setTitle("");
        book.setAuthor("test");
        book.setPublishedDate("2568-01-01");
        ResponseEntity<BookRequest> response = restTemplate.postForEntity("/books", book, BookRequest.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void createBookWithValidateAuthor() {
        BookRequest book = new BookRequest();
        book.setTitle("test");
        book.setAuthor("");
        book.setPublishedDate("2568-01-01");
        ResponseEntity<BookRequest> response = restTemplate.postForEntity("/books", book, BookRequest.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void createBookWithValidatePublishedDateLT() {
        BookRequest book = new BookRequest();
        book.setTitle("test");
        book.setAuthor("test");
        book.setPublishedDate("0500-01-01");
        ResponseEntity<BookRequest> response = restTemplate.postForEntity("/books", book, BookRequest.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void createBookWithValidatePublishedDateGT() {
        BookRequest book = new BookRequest();
        book.setTitle("test");
        book.setAuthor("test");
        book.setPublishedDate("2569-01-01");
        ResponseEntity<BookRequest> response = restTemplate.postForEntity("/books", book, BookRequest.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }



    @Test
    void createBookWithValidatePublishedDate() {
        BookRequest book = new BookRequest();
        book.setTitle("test");
        book.setAuthor("test");
        book.setPublishedDate("2569-01-40");
        ResponseEntity<BookRequest> response = restTemplate.postForEntity("/books", book, BookRequest.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}