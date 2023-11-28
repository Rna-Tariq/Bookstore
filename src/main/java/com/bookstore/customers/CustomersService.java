package com.bookstore.customers;

import java.util.List;
import java.util.Optional;

public interface CustomersService {

    List<CustomersEntity> findAllCustomers();
    Optional<CustomersEntity> findById(Long id);
    CustomersEntity save(CustomersEntity customer);
    CustomersEntity update(CustomersEntity customer);
    void deleteCustomer(Long id);
}
