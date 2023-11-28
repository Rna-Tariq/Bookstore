package com.bookstore.authors;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepo extends JpaRepository<AuthorsEntity, Long> {
}
