package com.bookstore.customers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepo extends JpaRepository<CustomersEntity, Long> {
}
