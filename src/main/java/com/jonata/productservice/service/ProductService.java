package com.jonata.productservice.service;

import com.jonata.productservice.dto.ProductRequest;
import com.jonata.productservice.dto.ProductResponse;
import com.jonata.productservice.model.Product;
import com.jonata.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} has been saved.", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(product -> mapToProductResponse(product)).toList();

    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
