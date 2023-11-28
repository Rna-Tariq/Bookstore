package com.bookstore.authors;

import java.util.List;
import java.util.Optional;

public interface AuthorsService {

    List<AuthorsEntity> findAllAuthors();
    Optional<AuthorsEntity> findAuthorById(Long id);
    AuthorsEntity saveAuthor(AuthorsEntity author);
    AuthorsEntity updateAuthor(AuthorsEntity author);
    void deleteAuthor(Long id);
}
