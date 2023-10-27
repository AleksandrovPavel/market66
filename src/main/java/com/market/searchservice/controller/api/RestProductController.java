package com.market.searchservice.controller.api;


import com.market.searchservice.exception.*;
import com.market.searchservice.model.Product;
import com.market.searchservice.model.dto.ProductDto;
import com.market.searchservice.service.ProductService;
import com.market.searchservice.validators.ProductValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class RestProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final ProductValidator productValidator;

    public RestProductController(ProductService productService,
                                 ModelMapper modelMapper, ProductValidator productValidator) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.productValidator = productValidator;
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
    public ResponseEntity<HttpStatus> saveProduct(@RequestBody @Valid ProductDto productDto,
                                                  BindingResult bindingResult) {
        Product product = convertToProduct(productDto);
        productValidator.validate(product, bindingResult);

        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }

        productService.saveProduct(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{product-id}")
    public ResponseEntity<HttpStatus> updateProduct(@RequestBody @Valid ProductDto ProductDto,
                                                    BindingResult bindingResult,
                                                    @PathVariable("product-id") Long productId) {

        Product product = convertToProduct(ProductDto);
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }

        productService.updateProduct(productId, product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("product-id") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException e) {
        ProductErrorResponse response = new ProductErrorResponse(
                "Продукт с таким индентификатором не найден!"
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Product convertToProduct(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    private ProductDto convertToProductDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}

