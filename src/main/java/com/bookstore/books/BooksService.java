package com.bookstore.books;

import java.sql.Date;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public interface BooksService {

    Page<BooksEntity> findAllBooks(Pageable pageable);
    Optional<BooksEntity> findById(Long id);
    BooksEntity save(BooksEntity book);
    BooksEntity update(BooksEntity book);
    void deleteBook(Long bookId, Long authorId);
    
    Page<BooksEntity> findBooksByAuthorName(String authorName, Pageable pageable);
    Page<BooksEntity> findBooksByPublishDate(Date publishDate, Pageable pageable);
    Page<BooksEntity> findBooksByTitle(String title, Pageable pageable);    
}
