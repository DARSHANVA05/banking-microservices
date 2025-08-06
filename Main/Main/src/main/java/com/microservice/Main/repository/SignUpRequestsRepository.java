package com.microservice.Main.repository;

import com.microservice.Main.model.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface SignUpRequestsRepository extends JpaRepository<RequestLog,Integer> {
    List<RequestLog>getByStatus(String status);
}
