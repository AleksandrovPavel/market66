package com.market.searchservice.controller.api;


import com.market.searchservice.model.Product;
import com.market.searchservice.model.dto.ProductDto;
import com.market.searchservice.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class RestProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public RestProductController(ProductService productService,
                                 ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        return new ResponseEntity<>(productService.getAllProduct()
                .stream()
                .map(this::convertToProductDto)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("product-id") Long productId) {
        return new ResponseEntity<>(convertToProductDto(productService.getProductById(productId)),
                HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> saveProduct(@RequestBody ProductDto productDto) {
        Product product = convertToProduct(productDto);
        productService.saveProduct(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{product-id}")
    public ResponseEntity<HttpStatus> updateProduct(@RequestBody ProductDto productDto,
                                                    @PathVariable("product-id") Long productId) {

        Product product = convertToProduct(productDto);
        productService.updateProduct(productId, product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("product-id") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Product convertToProduct(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    private ProductDto convertToProductDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}

