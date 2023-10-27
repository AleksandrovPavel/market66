package com.market.searchservice.service;

import com.market.searchservice.model.Product;
import com.market.searchservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    private final Long productId = 1L;

    private final List<Product> products = List.of(
            new Product(
                    1L,
                    1000L,
                    "Ручка шариковая",
                    "Ручка шариковая, пишет синим цветом",
                    "Екатеринбург",
                    15L,
                    30
            ),
            new Product(
                    2L,
                    1001L,
                    "Карандаш",
                    "Карандаш, пишет чёрным цветом",
                    "Невьянск",
                    80L,
                    15
            )
    );

    private final Product newProduct = new Product(
            3L,
            1003L,
            "Фломастер",
            "Фломастер, пишет черным цветом",
            "Нижний тагил",
            100L,
            50
    );
    private final Product product = products.get(0);

    @Test
    void getAllProduct() {
        when(productRepository.findAll()).thenReturn(products);
        List<Product> result = productService.getAllProduct();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(products, result);
    }

    @Test
    void getProductById() {
        when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(product));
        Product result = productService.getProductById(productId);
        assertNotNull(result);
        assertEquals(product, result);
    }

    @Test
    void saveProduct() {
        when(productRepository.save(newProduct)).thenReturn(newProduct);
        productService.saveProduct(newProduct);
        verify(productRepository).save(newProduct);
    }

    @Test
    void updateProduct() {
        when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(product));
        productService.updateProduct(productId, newProduct);
        verify(productRepository).save(newProduct);
    }

    @Test
    void deleteProduct() {
        when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(product));
        productService.deleteProduct(productId);
        assert product != null;
        verify(productRepository).delete(product);
    }
}