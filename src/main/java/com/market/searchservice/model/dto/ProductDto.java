package com.market.searchservice.model.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductDto {
    private Long id;

    @Min(value = 1000, message = "Артикул товара должен начинаться от 1000")
    @Max(value = 9000, message = "Артикул товара должн быть до 9000")
    private Long articleNumber;

    @NotBlank(message = "Имя товара не должно быть пустым")
    @Size(min = 4, max = 25, message = "Длина имени товара должна составлять от 4 до 25 символов")
    private String name;

    @NotBlank(message = "Описание товара должно быть заполнено")
    @Size(min = 10, max = 50, message = "Длина описания товара должна составлять от 10 до 50 символов")
    private String description;

    @NotBlank(message = "Название Города должно быть заполнено")
    @Size(min = 2, max = 25, message = "Длина названия города должна составлять от 2 до 25 символов")
    private String city;

    @Min(value = 0, message = "Колличесво товара должно составлять от 0 шт.")
    @Max(value = 2500, message = "Колличесво товара должно составлять до 2500 шт.")
    private Long quantity;

    @Min(value = 1, message = "Цены должна составлять от 1 р.")
    @Max(value = 2500, message = "Цеены должна составлять до 2500 р.")
    private Integer price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(Long articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
