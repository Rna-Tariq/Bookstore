package com.bookstore.customers;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.sql.Date;

@Entity
@Table(name = "Customers")
public class CustomersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "customer_id", nullable = false)
    @NotNull(message = "Please enter a valid value")
    private Long customer_id;
    @Column(name = "name")
    @NotNull(message = "Please enter a valid value")
    private String name;
    @Column(name = "email")
    @NotNull(message = "Please enter a valid value")
    private String email;
    @Column(name = "join_date")
    @NotNull(message = "Please enter a valid value")
    @Past(message = "join_date must be in the past")
    private Date join_date;

    public CustomersEntity() {
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }
}
