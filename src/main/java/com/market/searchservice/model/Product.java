package com.market.searchservice.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "goods")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article number", nullable = false, unique = true)
    private Long articleNumber;

    @Column(name = "product name")
    private String name;

    private String description;

    private String city;

    @Column(name = "quantity of goods")
    private Long quantity;

    private Integer price;

    public Product() {
    }

    public Product(Long id,
                   Long articleNumber,
                   String name,
                   String description,
                   String city,
                   Long quantity,
                   Integer price) {
        this.id = id;
        this.articleNumber = articleNumber;
        this.name = name;
        this.description = description;
        this.city = city;
        this.quantity = quantity;
        this.price = price;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id)
                && Objects.equals(articleNumber, product.articleNumber)
                && Objects.equals(name, product.name)
                && Objects.equals(description, product.description)
                && Objects.equals(city, product.city)
                && Objects.equals(quantity, product.quantity)
                && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, articleNumber, name, description, city, quantity, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", articleNumber=" + articleNumber +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}