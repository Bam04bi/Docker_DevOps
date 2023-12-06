package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public String getStatus() {
		return "Application is up and running";
	}

	@GetMapping("/product-all")
	public List<Product> allProduct(){
		return productRepository.findAll();
	}

	@PostMapping("/product/save")
	public Product save(@RequestBody Product p){
		return productRepository.save(p);
	}

	@DeleteMapping("/product/{id}")
	public void delete(@PathVariable long id){
		productRepository.deleteById(id);
	}

	@PutMapping("/product/update")
	public List<Product> edit(@RequestBody Product p){
		Optional<Product> product = productRepository.findById(p.getId());
		if (product.isPresent()){
			product.get().setName(p.getName());
			product.get().setDescription(p.getDescription());
			productRepository.save(product.get());
		}
		return productRepository.findAll();
	}
}
