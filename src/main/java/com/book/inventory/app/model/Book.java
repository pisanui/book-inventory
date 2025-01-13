package com.book.inventory.app.model;

import com.book.inventory.app.controller.BookRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.*;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "The title is required.")
    private String title;

    @Column(nullable = false)
    @NotEmpty(message = "The author is required.")
    private String author;

    @Column
    private ZonedDateTime publishedDate;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ZonedDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(ZonedDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Book toModel(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(bookRequest.getPublishedDate(), formatter);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 0,0,0,0, ZoneId.of("Asia/Bangkok"));
        book.setPublishedDate(convertPublishedDateWithBuddhistDateToZonedDateTime(zonedDateTime));
        return book;
    }

    // To save to DB
    public ZonedDateTime convertPublishedDateWithBuddhistDateToZonedDateTime(ZonedDateTime publishedDate) {
        ThaiBuddhistDate buddhistDate = ThaiBuddhistDate.of(publishedDate.getYear(), publishedDate.getMonthValue(), publishedDate.getDayOfMonth());
        String fmt = buddhistDate.format(DateTimeFormatter.ISO_DATE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(fmt, formatter);
        return ZonedDateTime.of(localDate.getYear(), publishedDate.getMonthValue(), publishedDate.getDayOfMonth(), 7,0,0,0, publishedDate.getZone());
    }

    // To show the response
    public String convertZonedDateTimeToStringBuddhistDate(ZonedDateTime publishedDate) {
        Chronology buddhistChronology = ThaiBuddhistChronology.INSTANCE;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fmt = buddhistChronology.zonedDateTime(publishedDate.toInstant(), publishedDate.getZone()).format(formatter);
        LocalDate localDate = LocalDate.parse(fmt, formatter);
        return localDate.toString();
    }
}
