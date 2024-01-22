package com.bookstore.authors;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorsRepo authorsRepo;
    
    @Autowired
    MessageSource messageSource;
    
    @GetMapping("/authorsPageName")
    public String greeting() {
        return messageSource.getMessage("authorsPageName", null, LocaleContextHolder.getLocale());
    }

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
    
    // Extract the error message from the exception and 
    // Customize the response body with the error message
    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(ConstraintViolationException.class)
        @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
        public ResponseEntity<String> handleValidationException(ConstraintViolationException e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
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
