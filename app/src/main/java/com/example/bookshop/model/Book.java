package com.example.bookshop.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String _id,title,language,image;
    private int number_of_pages;
    private boolean is_active=true;
    private String publication_date;
    private Author author;
    private Category category;
    private Publisher publisher;

    public Book(String _id, String title, String language, String image, int number_of_pages, boolean is_active, String publication_date, Author author, Category category, Publisher publisher) {
        this._id = _id;
        this.title = title;
        this.language = language;
        this.image = image;
        this.number_of_pages = number_of_pages;
        this.is_active = is_active;
        this.publication_date = publication_date;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNumber_of_pages() {
        return number_of_pages;
    }

    public void setNumber_of_pages(int number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
