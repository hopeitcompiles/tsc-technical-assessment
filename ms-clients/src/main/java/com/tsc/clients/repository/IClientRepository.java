package com.tsc.clients.repository;

import com.tsc.clients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByIdentificationNumber(String identificationNumber);
}
