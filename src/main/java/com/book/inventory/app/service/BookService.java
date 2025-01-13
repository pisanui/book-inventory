package com.book.inventory.app.service;

import com.book.inventory.app.controller.BookRequest;
import com.book.inventory.app.controller.BookResponse;
import com.book.inventory.app.model.Book;
import com.book.inventory.app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<BookResponse> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);

        List<BookResponse> bookResponseList = new java.util.ArrayList<>(List.of());

        books.forEach(book -> {
                    BookResponse bookResponse = new BookResponse();
                    bookResponse.setTitle(book.getTitle());
                    bookResponse.setAuthor(book.getAuthor());
                    bookResponse.setPublishedDate(book.convertZonedDateTimeToStringBuddhistDate(book.getPublishedDate()));
                    bookResponseList.add(bookResponse);
                }
        );
        return bookResponseList;
    }

    public BookRequest createBook(BookRequest book) {
        bookRepository.save(new Book().toModel(book));
        return book;
    }

}
