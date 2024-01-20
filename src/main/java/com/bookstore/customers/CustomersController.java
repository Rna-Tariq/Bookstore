package com.bookstore.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@RestController
@RequestMapping("/customer")
public class CustomersController {

    @Autowired
    private final CustomersRepo customersRepo;
    
    private final MessageSource messageSource;
    
    public CustomersController(CustomersRepo customersRepo, org.springframework.context.MessageSource messageSource) {
        this.customersRepo = customersRepo;
        this.messageSource = messageSource;
    }
    
    @GetMapping
    public String getPageName() {
        return messageSource.getMessage("customersPage.message", null, LocaleContextHolder.getLocale());
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

    @PutMapping("/{id}")
    public CustomersEntity update(@PathVariable Long id, @RequestBody CustomersEntity customer) {
        return customersRepo.save(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customersRepo.deleteById(id);
    }
}
