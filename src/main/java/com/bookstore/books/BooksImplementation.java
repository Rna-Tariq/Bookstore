package com.bookstore.books;

import java.sql.Date;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class BooksImplementation implements BooksService{

    private final BooksRepo booksRepo;

    public BooksImplementation(BooksRepo booksRepo) {
        this.booksRepo = booksRepo;
    }

    @Override
    public Page<BooksEntity> findAllBooks(Pageable pageable) {
        return booksRepo.findAll(pageable);
    }

    @Override
    public Optional<BooksEntity> findById(Long id) {
        return booksRepo.findById(id);
    }

    @Override
    public BooksEntity save(BooksEntity book) {
        return booksRepo.save(book);
    }

    @Override
    public BooksEntity update(BooksEntity book) {
        return booksRepo.save(book);
    }

    @Override
    public void deleteBook(Long bookId, Long authorId) {
        booksRepo.deleteById(bookId);
    }

    @Cacheable(value = "booksByAuthor", key = "#authorName")
    @Override
    public Page<BooksEntity> findBooksByAuthorName(String authorName, Pageable pageable) {
        System.out.println("Executing database query to get books by author: " + authorName);
        return booksRepo.findBooksByAuthorNameContainsIgnoreCase(authorName, pageable);
    }
    
    @Cacheable(value = "booksBypublishDate", key = "#publishDate")
    @Override
    public Page<BooksEntity> findBooksByPublishDate(Date publishDate, Pageable pageable) {
        System.out.println("Executing database query to get books by publishDate: " + publishDate);
        return booksRepo.findBooksByPublishDate(publishDate, pageable);
    }
    
    @Cacheable(value = "booksByTitle", key = "#title")
    @Override
    public Page<BooksEntity> findBooksByTitle(String title, Pageable pageable) {
        System.out.println("Executing database query to get books by title: " + title);
        return booksRepo.findBooksByTitleContainsIgnoreCase(title, pageable);
    }


    
}
