package com.akhm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akhm.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
