package org.example.product.controller;

import lombok.RequiredArgsConstructor;
import org.example.product.domain.Product;
import org.example.product.repo.ProductRepository;
import org.example.product.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping("/{id}")
    @Cacheable(value = "Products")
    public Product getProduct(@PathVariable("id") Long id)  {
        return productService.getById(id);
    }
    @DeleteMapping("{id}")
    @CacheEvict(value = "Products",key = "#id")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id)  {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
