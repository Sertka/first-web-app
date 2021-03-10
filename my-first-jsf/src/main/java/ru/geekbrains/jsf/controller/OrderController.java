package ru.geekbrains.jsf.controller;

import ru.geekbrains.jsf.entity.Category;
import ru.geekbrains.jsf.entity.Order;
import ru.geekbrains.jsf.entity.Product;
import ru.geekbrains.jsf.repo.CategoryRepository;
import ru.geekbrains.jsf.repo.OrderRepository;
import ru.geekbrains.jsf.repo.ProductRepository;
import ru.geekbrains.jsf.service.OrderRepr;
import ru.geekbrains.jsf.service.OrderService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class OrderController implements Serializable {

    @EJB
    private OrderService OrderService;

    @EJB
    private ProductRepository productRepository;

    private OrderRepr order;

    private List<OrderRepr> orders;

    private List<Product> products;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        orders = OrderService.findAll();
        products = productRepository.findAll();
    }

    public OrderRepr getOrder() {
        return order;
    }

    public void setOrder(OrderRepr order) {
        this.order = order;
    }

    public String createOrder() {
        this.order = new OrderRepr();
        return "/order_form.xhtml?faces-redirect=true";
    }

    public List<OrderRepr> getAllOrders() {
        return orders;
    }

    public String editOrder(OrderRepr order) {
        this.order = order;
        return "/order_form.xhtml?faces-redirect=true";
    }

    public void deleteOrder(OrderRepr order) {
        OrderService.deleteById(order.getId());
    }

    public String saveOrder() {
        OrderService.saveOrUpdate(order);
        return "/order.xhtml?faces-redirect=true";
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}