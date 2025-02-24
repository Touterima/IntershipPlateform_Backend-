package com.microservices.repositories;

import com.microservices.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {
    List<Application> findByUserEmail( String email);

    boolean existsByUserEmail(String email);

    long count();

}
