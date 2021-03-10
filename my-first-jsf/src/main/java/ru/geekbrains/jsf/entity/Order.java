package ru.geekbrains.jsf.entity;

import ru.geekbrains.jsf.service.OrderRepr;

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

    @ManyToOne
    private Product product;



    public Order() {
    }

    public Order(Long id, String description, BigDecimal orderPrice) {
        this.id = id;
        this.description = description;
        this.orderPrice = orderPrice;
    }

    public Order(OrderRepr orderRepr, Product product) {
        this(orderRepr.getId(), orderRepr.getDescription(), orderRepr.getOrderPrice());
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
