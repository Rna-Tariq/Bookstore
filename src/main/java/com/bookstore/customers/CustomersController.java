package com.bookstore.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Customer")
public class CustomersController {

    @Autowired
    private final CustomersRepo customersRepo;

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

    @PutMapping("/{id}")
    public CustomersEntity update(@PathVariable Long id, @RequestBody CustomersEntity customer) {
        return customersRepo.save(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customersRepo.deleteById(id);
    }
}
