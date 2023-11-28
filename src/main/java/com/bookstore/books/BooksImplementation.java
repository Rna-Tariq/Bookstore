package com.bookstore.books;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksImplementation implements BooksService{

    private final BooksRepo booksRepo;

    public BooksImplementation(BooksRepo booksRepo) {
        this.booksRepo = booksRepo;
    }

    @Override
    public List<BooksEntity> findAllBooks() {
        return booksRepo.findAll();
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
}
