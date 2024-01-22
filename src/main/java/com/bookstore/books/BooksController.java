package com.bookstore.books;

import com.bookstore.authors.AuthorsEntity;
import com.bookstore.authors.AuthorsRepo;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksRepo booksRepo;

    @Autowired
    private AuthorsRepo authorsRepo;
    
    @Autowired
    BooksService booksService;
    
    @Autowired
    MessageSource messageSource;
    
    @GetMapping("/booksPageName")
    public String greeting() {
        return messageSource.getMessage("booksPageName", null, LocaleContextHolder.getLocale());
    }
    
    @GetMapping
    public ResponseEntity<?> searchBooks(
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false) Date publish_date,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));


        Page<BooksEntity> books;

        if (authorName != null) {
            books = (Page<BooksEntity>) booksService.findBooksByAuthorName(authorName, pageable);
        } else if (publish_date != null) {
            books = (Page<BooksEntity>) booksService.findBooksByPublishDate(publish_date, pageable);
        } else if (title != null) {
            books = (Page<BooksEntity>) booksService.findBooksByTitle(title, pageable);
        } else {
            books = (Page<BooksEntity>) booksService.findAllBooks(pageable);
        }

        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Books Found");
        } else {
            return ResponseEntity.ok(books);
        }
    }

    @GetMapping("/{id}")
    public Optional<BooksEntity> getBookById(@PathVariable Long id) {
        return booksRepo.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookReq bookReq) {
        String regex = "^[a-zA-Z]+$";
        
        if (!bookReq.getTitle().matches(regex)) {
            return ResponseEntity.badRequest().body("Book title must be literal a-z/A-Z");
        }

        if (bookReq.getPrice() < 0) {
            return ResponseEntity.badRequest().body("Price must be a positive value");
        }

        Optional<AuthorsEntity> optionalAuthor = authorsRepo.findById(bookReq.getAuthor_id());
        
        if (optionalAuthor.isPresent()) {
            AuthorsEntity author = optionalAuthor.get();

            BooksEntity book = new BooksEntity();
            book.setTitle(bookReq.getTitle());
            book.setPrice(bookReq.getPrice());
            book.setPublish_date(bookReq.getPublish_date());
            book.setAuthor(author);

            booksRepo.save(book);

            return ResponseEntity.ok("Book added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Author not found");
        }
    }
    
    // Extract the error message from the exception and 
    // Customize the response body with the error message
//    @ControllerAdvice
//    public class GlobalExceptionHandler {
//        @ExceptionHandler(ConstraintViolationException.class)
//        @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
//        public ResponseEntity<String> handleValidationException(ConstraintViolationException e) {
//            String errorMessage = e.getMessage();
//            return ResponseEntity.badRequest().body(errorMessage);
//        }
//    }

    @PutMapping("/{id}")
    public BooksEntity update(@PathVariable Long id, @RequestBody BooksEntity book) {
        return booksRepo.save(book);
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long book_id, @RequestParam Long author_id) {
        Optional<BooksEntity> optionalBook = booksRepo.findById(book_id);
        Optional<AuthorsEntity> optionalAuthor = authorsRepo.findById(author_id);

        if (optionalBook.isPresent() && optionalAuthor.isPresent()) {
            BooksEntity book = optionalBook.get();
            AuthorsEntity author = optionalAuthor.get();

            if (book.getAuthor().equals(author)) {
                booksRepo.delete(book);
                return ResponseEntity.ok("Book deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to delete this book");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book or Author not found");
        }
    }
}
