package com.bookstore.books;

import com.bookstore.authors.AuthorsEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.sql.Date;

@Entity
@Table(name = "Books")
public class BooksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "book_id", nullable = false)
    private Long id;
    @Column(name = "title")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Book title must contain only literal characters (a-z, A-Z)")
    @NotNull(message = "Please enter a valid value")
    private String title;
    @Column(name = "price")
    @Min(value = 0, message = "Price must be a positive value")
    @NotNull(message = "Please enter a valid value")
    private Float price;
    @Column(name = "publishDate")
    @Past(message = "Publish date must be in the past")
    @NotNull(message = "Please enter a valid value")
    private Date publishDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private AuthorsEntity author;

    public BooksEntity() {
    }

    public BooksEntity(Long book_id, String title, Float price, Date publishDate, AuthorsEntity author) {
        this.id = book_id;
        this.title = title;
        this.price = price;
        this.publishDate = publishDate;
        this.author = author;
    }

    public Long getBook_id() {
        return id;
    }

    public void setBook_id(Long book_id) {
        this.id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public AuthorsEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorsEntity author) {
        this.author = author;
    }
}
