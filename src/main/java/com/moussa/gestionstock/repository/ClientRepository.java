package com.moussa.gestionstock.repository;


import com.moussa.gestionstock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
