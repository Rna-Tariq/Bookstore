package com.bookstore.customers;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomersImplementation implements CustomersService{

    private final CustomersRepo customersRepo;

    public CustomersImplementation(CustomersRepo customersRepo) {
        this.customersRepo = customersRepo;
    }

    @Override
    public List<CustomersEntity> findAllCustomers() {
        return customersRepo.findAll();
    }

    @Override
    public Optional<CustomersEntity> findById(Long id) {
        return customersRepo.findById(id);
    }

    @Override
    public CustomersEntity save(CustomersEntity customer) {
        return customersRepo.save(customer);
    }

    @Override
    public CustomersEntity update(CustomersEntity customer) {
        return customersRepo.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customersRepo.deleteById(id);
    }
}
