package com.example.restexemplo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restexemplo.core.models.Clientes;

public interface ClienteRepository extends JpaRepository<Clientes, Long>{}
