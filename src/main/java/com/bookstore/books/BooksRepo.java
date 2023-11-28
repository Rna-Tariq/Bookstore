package com.bookstore.books;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepo extends JpaRepository<BooksEntity, Long> {
}
