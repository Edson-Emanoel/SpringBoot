package com.example.restexemplo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restexemplo.core.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
