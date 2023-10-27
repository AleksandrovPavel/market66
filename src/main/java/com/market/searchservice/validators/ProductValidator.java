package com.market.searchservice.validators;

import com.market.searchservice.model.Product;
import com.market.searchservice.service.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    private final ProductService productService;


    public ProductValidator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        if (productService.findByArticleNumber(product.getArticleNumber()).isPresent()) {
            errors.rejectValue("ArticleNumber", "Товар с таким артиклем уже существует");
        }
    }

}
