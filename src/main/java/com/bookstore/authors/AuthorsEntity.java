package com.bookstore.authors;

import com.bookstore.books.BooksEntity;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Authors")
public class AuthorsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", nullable = false)
    private Long author_id;
    @Column(name = "name")
    private String name;

    @Column(name = "birthdate")
    private Date birthdate;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BooksEntity> books = new ArrayList<>();

    public AuthorsEntity() {
    }

    public AuthorsEntity(Long author_id, String name, Date birthdate) {
        this.author_id = author_id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
