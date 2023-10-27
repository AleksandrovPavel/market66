package com.market.searchservice.service;


import com.market.searchservice.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    Product getProductById(Long productId);

    void saveProduct(Product product);

    void updateProduct(Long productId, Product product);

    void deleteProduct(Long productId);
}
