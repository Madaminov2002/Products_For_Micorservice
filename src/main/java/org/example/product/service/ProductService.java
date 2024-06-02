package org.example.product.service;

import lombok.RequiredArgsConstructor;
import org.example.product.domain.Product;
import org.example.product.repo.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public  Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

}
