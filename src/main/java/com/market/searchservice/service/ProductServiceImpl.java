package com.market.searchservice.service;

import com.market.searchservice.model.Product;
import com.market.searchservice.repositories.ProductRepository;
import com.market.searchservice.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long productId) {
        Optional<Product> foundProduct = productRepository.findById(productId);
        return foundProduct.orElseThrow(ProductNotFoundException::new);
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateProduct(Long productId, Product product) {
        Product getTheProduct = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        product.setId(getTheProduct.getId());
        product.setArticleNumber(getTheProduct.getArticleNumber());
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product productDelete = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        productRepository.delete(productDelete);
    }

    @Override
    public Optional<Product> findByArticleNumber(Long articleNumber) {
        return productRepository.findByArticleNumber(articleNumber);
    }
}
