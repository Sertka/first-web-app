package ru.geekbrains.jsf.service;

import ru.geekbrains.jsf.entity.Category;
import ru.geekbrains.jsf.entity.Order;
import ru.geekbrains.jsf.entity.Product;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

// DTO
public class OrderRepr implements Serializable {

    private Long id;

    private String description;

    private BigDecimal orderPrice;

    private Long productId;

    private String productName;

    public OrderRepr() {
    }

    public OrderRepr(Order order) {
        id = order.getId();
        description = order.getDescription();
        orderPrice = order.getOrderPrice();
        Product product = order.getProduct();
        productId = product != null ? product.getId() : null;
        productName = product!= null ? product.getName() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}