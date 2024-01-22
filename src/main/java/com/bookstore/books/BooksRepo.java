package com.bookstore.books;

import java.sql.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BooksRepo extends JpaRepository<BooksEntity, Long> {
    
    @Query("SELECT b FROM BooksEntity b WHERE " + "(:authorName IS NULL OR LOWER(b.author.name) LIKE LOWER(CONCAT('%', :authorName, '%'))) ")
    Page<BooksEntity> findBooksByAuthorNameContainsIgnoreCase(String authorName, Pageable pageable);
    Page<BooksEntity> findBooksByTitleContainsIgnoreCase(String Title, Pageable pageable);
    Page<BooksEntity> findBooksByPublishDate(Date pubishDate, Pageable pageable);    

    Page<BooksEntity> findAll(Pageable pageable);

}
