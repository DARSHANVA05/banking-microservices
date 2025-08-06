package com.microservice.Accounts.repository;

import com.microservice.Accounts.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHoldersRepository extends JpaRepository<Customer, Integer> {
}
