package com.microservice.Accounts.service;

import com.microservice.Accounts.exception.AccountCreationException;
import com.microservice.Accounts.repository.AccountHoldersRepository;
import com.microservice.Accounts.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private static final Logger log = LoggerFactory.getLogger(ValidationService.class);

    @Autowired
    private AccountHoldersRepository accountHoldersRepository;

    public void saveClient(Customer customer) {
        try{
            validateClient(customer);
            accountHoldersRepository.save(customer);
            log.info(customer.getName()+" successfully saved into database.");
            //successful message should be sent to message queue.

        }catch (AccountCreationException accountCreationException){
            log.error("Credentials do not obey the standards.");
            log.error("Request to dead letter queue.");
            //generate a message to error queue.
        }catch (Exception exception){
            log.error("Database error: "+ exception);
            //object should be sent to unsuccessful queue for processing again.
        }
    }

    public void validateClient(Customer customer) throws AccountCreationException {
        if(checkInputData(customer))
            throw new AccountCreationException(customer);
    }

    private boolean checkInputData(Customer customer) {
        return (customer.getContactNo().length()!=10) ? true : false;
    }

}



