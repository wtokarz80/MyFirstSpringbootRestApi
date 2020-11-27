package com.company.repository;

import com.company.model.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // lista juz posortowana po nazwiskach, ale mozemy to zrobic przy paginacji, patrz service
    List<Client> findAllByOrderByLastNameAsc(Pageable page);

    @Query("select c from Client c")
    List<Client> findAllClients(Pageable page);

    @Query("select c from Client c where c.firstName = ?1")
    List<Client> findAllByFirstName(String firstName);


}
