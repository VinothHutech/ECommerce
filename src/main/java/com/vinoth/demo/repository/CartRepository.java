package com.vinoth.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinoth.demo.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>
{

}
