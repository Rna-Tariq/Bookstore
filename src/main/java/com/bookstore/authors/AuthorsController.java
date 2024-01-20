package com.bookstore.authors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorsRepo authorsRepo;
    
//    private final MessageSource messageSource;
//
//    public AuthorsController(MessageSource messageSource) {
//        this.messageSource = messageSource;
//    }
//
//    
//    @GetMapping
//    public String getPageName() {
//        return messageSource.getMessage("authorsPage.message", null, LocaleContextHolder.getLocale());
//    }

    @GetMapping
    public List<AuthorsEntity> findAllAuthors() {
        return authorsRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<AuthorsEntity> getAuthorById(@PathVariable Long id) {
        return authorsRepo.findById(id);
    }

    @PostMapping
    public AuthorsEntity saveAuthor(@RequestBody AuthorsEntity author) {
        return authorsRepo.save(author);
    }

    @PutMapping("/{id}")
    public AuthorsEntity updateAuthor(@PathVariable Long id, @RequestBody AuthorsEntity author) {
        return authorsRepo.save(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorsRepo.deleteById(id);
    }
}
