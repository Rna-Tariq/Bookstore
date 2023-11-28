package com.bookstore.books;

import com.bookstore.authors.AuthorsEntity;
import com.bookstore.authors.AuthorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public interface BooksService {

    List<BooksEntity> findAllBooks();
    Optional<BooksEntity> findById(Long id);
    BooksEntity save(BooksEntity book);
    BooksEntity update(BooksEntity book);
    void deleteBook(Long bookId, Long authorId);
}
