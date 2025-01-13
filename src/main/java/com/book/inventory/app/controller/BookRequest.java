package com.book.inventory.app.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookRequest {

    private String title;

    private String author;

    private String publishedDate;

    @Override
    public String toString() {
        return "BookRequest{" +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                '}';
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

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }


    public boolean validPublishedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(publishedDate, formatter);
        return localDate.getYear() > 1000 && localDate.getYear() <= LocalDate.now().getYear() + 543 ;
    }

    public boolean validBookRequest() {
        return !title.isEmpty() && !author.isEmpty() && validPublishedDate();
    }

}
