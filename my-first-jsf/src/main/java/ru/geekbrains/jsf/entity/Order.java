package ru.geekbrains.jsf.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "findAllOrders", query = "from Order"),
        @NamedQuery(name = "countAllOrders", query = "select count(*) from Order"),
        @NamedQuery(name = "deleteOrderById", query = "delete from Order p where p.id = :id")
})

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (length = 1024)
    private String description;

    @Column
    private BigDecimal orderPrice;

    @Column
    private Long productId;

    public Order() {
    }

    public Order(Long id, String description, BigDecimal orderPrice, Long productId) {
        this.id = id;
        this.description = description;
        this.orderPrice = orderPrice;
        this.productId = productId;
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

}
