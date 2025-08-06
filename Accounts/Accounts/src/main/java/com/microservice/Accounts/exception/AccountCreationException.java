package com.microservice.Accounts.exception;

import com.microservice.Accounts.model.Customer;

public class AccountCreationException extends Exception{

    public AccountCreationException(Customer customer){
        super();
    }

}
