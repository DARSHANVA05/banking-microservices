package com.microservice.Accounts.consumer;

import com.microservice.Accounts.service.ValidationService;
import com.microservice.data.SignUpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AccountConsumer {

    private static final Logger log = LoggerFactory.getLogger(AccountConsumer.class);

    @Autowired
    ValidationService validationService;

    @JmsListener(destination = "validationQueue")
    public void clientValidation(SignUpRequest request){
        try {
            log.info("Received request ID: "+ request.getId());
            log.info("Received client data Client name: "+ request.getRequestPayload().getName());

            //validationService.saveClient(request.getRequestPayload());
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
    }

}
