package com.ust.productapp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ust.productapp.entities.Product;
import com.ust.productapp.services.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	

	@GetMapping("/")
	public List<Product> fetchAllProduct(){
		return productService.fetchAllProducts();
	}	
	@PostMapping("/")
	public ResponseEntity<Void> addProduct(@RequestBody Product input){
		System.out.println(input);
		if(productService.findByName(input.productName)){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		productService.addProduct(input);
		 return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@PutMapping("/")
	public ResponseEntity<String> updateProduct(@RequestBody Product input){
		System.out.println(input);
		ResponseEntity<String> re = null;
		try{
			productService.updateProduct(input);
			re = ResponseEntity.ok().body("Product has been updated successfully.");
		}catch(EmptyResultDataAccessException e){
			re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return re;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> fetchProduct(@PathVariable("id")int id){
		System.out.println("In Fetch employee: " + id);
		ResponseEntity<Product> re = null; 
		Product product = productService.findProductById(id);
		if(product==null){
			re = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else{
			re = new ResponseEntity<>( HttpStatus.OK);
		}
		return re;
		
		
	}
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
		
		ResponseEntity<String> re = null;
		try{
			productService.deleteProduct(id);
			re = ResponseEntity.ok().body("Product has been deleted successfully.");
		}
		catch(EmptyResultDataAccessException e){
			re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return re;
	}
	
	
}
