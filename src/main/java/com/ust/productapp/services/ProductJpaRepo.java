package com.ust.productapp.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.productapp.entities.Product;

public interface ProductJpaRepo extends JpaRepository<Product, Integer>{


}
