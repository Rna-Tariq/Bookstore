package com.bookstore.customers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private final CustomersRepo customersRepo;
    
    @Autowired
    MessageSource messageSource;
    
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
