package com.bookstore.authors;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorsImplementation implements AuthorsService{

    private final AuthorsRepo authorsRepo;

    public AuthorsImplementation(AuthorsRepo authorsRepo) {
        this.authorsRepo = authorsRepo;
    }

    @Override
    public List<AuthorsEntity> findAllAuthors() {
        return authorsRepo.findAll();
    }

    @Override
    public Optional<AuthorsEntity> findAuthorById(Long id) {
        return authorsRepo.findById(id);
    }

    @Override
    public AuthorsEntity saveAuthor(AuthorsEntity author) {
        return authorsRepo.save(author);
    }

    @Override
    public AuthorsEntity updateAuthor(AuthorsEntity author) {
        return authorsRepo.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorsRepo.deleteById(id);
    }
}
