package com.ust.productapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.productapp.entities.Product;

@Service
public class ProductService {
	@Autowired
	ProductJpaRepo productRepository;
	
	
	public List<Product> fetchAllProducts() {
		return productRepository.findAll();
		
	}

	public void addProduct(Product input) {
		productRepository.save(input);
	}
	
	public boolean findByName(String name) {
		List<Product> pro = productRepository.findAll().stream()
									   .filter((p) -> p.productName.equals(name))
									   .collect(Collectors.toList());
		if(pro.size() == 0){
			return false;
		}
		return true;
	}
	
	public Product findProductById(int id) {
		Optional<Product> pro = productRepository.findById(id);
		if(pro.isPresent())
		{
			return pro.get();
		}
		return null;
	}

	public void deleteProduct(int id) {
		productRepository.deleteById(id);	
	}
	
	public void updateProduct(Product input) {	
		//productRepository.deleteById(input.id);
		productRepository.save(input);
	}
	
	

}
