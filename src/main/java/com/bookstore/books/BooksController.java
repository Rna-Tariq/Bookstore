package com.bookstore.books;

import com.bookstore.authors.AuthorsEntity;
import com.bookstore.authors.AuthorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Books")
public class BooksController {

    @Autowired
    private BooksRepo booksRepo;

    @Autowired
    private AuthorsRepo authorsRepo;

    @GetMapping
    public List<BooksEntity> getAllBooks() {
        return booksRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<BooksEntity> getBookById(@PathVariable Long id) {
        return booksRepo.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody BookReq bookReq) {
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

    @PutMapping("/{id}")
    public BooksEntity update(@PathVariable Long id, @RequestBody BooksEntity book) {
        return booksRepo.save(book);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId, @RequestParam Long authorId) {
        Optional<BooksEntity> optionalBook = booksRepo.findById(bookId);
        Optional<AuthorsEntity> optionalAuthor = authorsRepo.findById(authorId);

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
