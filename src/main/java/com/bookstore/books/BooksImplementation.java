package com.bookstore.books;

import java.util.Date;
import org.springframework.stereotype.Service;

import java.util.Optional;
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



    @Override
    public Page<BooksEntity> findBooksByAuthorName(String authorName, Pageable pageable) {
        return booksRepo.findBooksByAuthorNameContainsIgnoreCase(authorName, pageable);
    }

    @Override
    public Page<BooksEntity> findBooksByPublishDate(Date publishDate, Pageable pageable) {
        return booksRepo.findBooksByPublishDate(publishDate, pageable);
    }

    @Override
    public Page<BooksEntity> findBooksByTitle(String title, Pageable pageable) {
        return booksRepo.findBooksByTitleContainsIgnoreCase(title, pageable);
    }


    
}
