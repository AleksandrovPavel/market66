package com.market.searchservice.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.searchservice.model.Product;
import com.market.searchservice.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;


import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(RestProductController.class)
class RestProductControllerTest {

    @Spy
    ModelMapper modelMapper;

    @MockBean
    private ProductServiceImpl productService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
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
    void getAllProduct() throws Exception {
        when(productService.getAllProduct()).thenReturn(products);
        mockMvc.perform(get("/api/product"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getProduct() throws Exception {
        when(productService.getProductById(productId)).thenReturn(product);
        mockMvc.perform(get("/api/product/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$").value(product));
    }

    @Test
    void saveProduct() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(newProduct));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void updateProduct() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.patch("/api/product/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(newProduct));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void deleteProduct() throws Exception {
        willDoNothing().given(productService).deleteProduct(productId);

        mockMvc.perform(delete("/api/product/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}