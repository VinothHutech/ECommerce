package com.vinoth.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinoth.demo.exception.ResourceNotFoundException;
import com.vinoth.demo.model.Cart;
import com.vinoth.demo.repository.CartRepository;

@RestController
public class CartController
{
	@Autowired
	CartRepository cartRepository;
	
	@GetMapping("/getAllItemsFromMyCart")
	public List<Cart> getAllItemsFromMyCart() {
		return cartRepository.findAll();
	}
	
	@PostMapping("/addItemToCart")
	public Cart addItemToCart(@RequestBody Cart item)
	{
		return cartRepository.save(item);
	}
	
	@DeleteMapping("/deleteItemFromMyCart/{id}")
	public Map<String, Boolean> deleteItemFromMyCart(@PathVariable(value = "id") Integer itemId)
			throws ResourceNotFoundException {
		Cart item = cartRepository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("CartItem not found for this id :: " + itemId));

		cartRepository.delete(item);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
