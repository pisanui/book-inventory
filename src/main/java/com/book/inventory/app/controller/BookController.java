package com.book.inventory.app.controller;

import com.book.inventory.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooksByAuthor(@RequestParam("author") String author) {
        List<BookResponse> books = bookService.getBooksByAuthor(author);
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(books);
    }

    @PostMapping
    public ResponseEntity<BookRequest> createBook(@RequestBody BookRequest book) {
        if (!book.validBookRequest()) {
            book.setTitle("Title is required");
            book.setAuthor("Author is required");
            book.setPublishedDate("PublishedDate must be greater than 1000");
            return ResponseEntity.badRequest().body(book);
        }
        return ResponseEntity.ok().body(bookService.createBook(book));
    }

}
