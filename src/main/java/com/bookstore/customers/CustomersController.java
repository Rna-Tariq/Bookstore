package com.bookstore.customers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/customer")
public class CustomersController {

    @Autowired
    private final CustomersRepo customersRepo;
    
    @Autowired
    MessageSource messageSource;
    
    @Autowired
    private EmailService emailService;

    @GetMapping("/customersPageName")
    public String greeting() {
        return messageSource.getMessage("customersPageName", null, LocaleContextHolder.getLocale());
    }
  
    public CustomersController(CustomersRepo customersRepo) {
        this.customersRepo = customersRepo;
    }

    @GetMapping
    public List<CustomersEntity> getAllCustomers() {
        return customersRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CustomersEntity> getCustomerById(@PathVariable Long id) {
        return customersRepo.findById(id);
    }

    @GetMapping("/sendEmail")
    public ResponseEntity<String> sendAsyncEmail() {
        // Invoke the asynchronous email sending method
        CompletableFuture<Void> future = emailService.sendEmail("recipient@example.com", "Async Subject", "Async Body");

        return ResponseEntity.ok("Email sending initiated");
    }
    
    @PostMapping
    public CustomersEntity save(@RequestBody CustomersEntity customer) {
        return customersRepo.save(customer);
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
    public CustomersEntity update(@PathVariable Long id, @RequestBody CustomersEntity customer) {
        return customersRepo.save(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customersRepo.deleteById(id);
    }
}
