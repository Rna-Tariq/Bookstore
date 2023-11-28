package com.bookstore.books;

import com.bookstore.authors.AuthorsEntity;
import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "Books")
public class BooksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "book_id", nullable = false)
    private Long book_id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private Float price;
    @Column(name = "publish_date")
    @Temporal(TemporalType.DATE)
    private Date publish_date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private AuthorsEntity author;

    public BooksEntity() {
    }

    public BooksEntity(Long book_id, String title, Float price, Date publish_date, AuthorsEntity author) {
        this.book_id = book_id;
        this.title = title;
        this.price = price;
        this.publish_date = publish_date;
        this.author = author;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
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

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public AuthorsEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorsEntity author) {
        this.author = author;
    }
}
