package com.Doome.microservice.product_service.service;

import com.Doome.microservice.product_service.dto.ProductRequest;
import com.Doome.microservice.product_service.dto.ProductResponse;
import com.Doome.microservice.product_service.model.Product;
import com.Doome.microservice.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();
        productRepository.save(product);
        log.info("Product created successfully!!");
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice())).toList();
    }
}
