package ru.geekbrains.jsf.controller;

import ru.geekbrains.jsf.entity.Order;
import ru.geekbrains.jsf.repo.OrderRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class OrderController implements Serializable {

    @Inject
    private OrderRepository orderRepository;

    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String createOrder() {
        this.order = new Order();
        return "/order_form.xhtml?faces-redirect-true";
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllOrders();
    }

    public String editOrder(Order order) {
        this.order = order;
        return "/order_form.xhtml?faces-redirect-true";
    }

    public void deleteOrder(Order Order) {
        orderRepository.deleteOrderById(Order.getId());
    }

    public String saveOrder() {
        orderRepository.saveOrUpdate(order);
        return "/order.xhtml?faces-redirect-true";
    }
}
