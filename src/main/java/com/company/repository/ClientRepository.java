package com.company.repository;

import com.company.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

//    @Query("SELECT c.lastName FROM Client c WHERE c.firstName LIKE 'W%'")
    List<Client> findAllByFirstNameLike(String pattern);
}
