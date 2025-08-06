package com.microservice.Main.service;

import com.microservice.Main.model.RequestLog;
import com.microservice.Main.repository.SignUpRequestsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRequestService {

    private static final Logger log = LoggerFactory.getLogger(AccountRequestService.class);
    @Autowired
    private SignUpRequestsRepository signUpRequestsRepository;
    public void saveRequest(RequestLog requestLog){
        signUpRequestsRepository.save(requestLog);
    }
}
