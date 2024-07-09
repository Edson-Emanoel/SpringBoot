package com.example.restexemplo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restexemplo.core.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long>  {}
