package ru.geekbrains.jsf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.jsf.entity.Category;
import ru.geekbrains.jsf.entity.Order;
import ru.geekbrains.jsf.entity.Product;
import ru.geekbrains.jsf.repo.CategoryRepository;
import ru.geekbrains.jsf.repo.OrderRepository;
import ru.geekbrains.jsf.repo.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class OrderServiceImpl implements OrderService{

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @EJB
    private OrderRepository OrderRepository;

    @EJB
    private ProductRepository productRepository;

    @Override
    public List<OrderRepr> findAll() {
        return OrderRepository.findAll().stream()
                .map(OrderRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public OrderRepr findById(Long id) {
        Order order = OrderRepository.findById(id);
        if (order != null) {
            return new OrderRepr(order);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return OrderRepository.countAll();
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(OrderRepr Order) {
        logger.info("Saving Order with id {}" , Order.getId());

        Product product = null;
        if (Order.getProductId() != null) {
            product = productRepository.getReference(Order.getProductId());
        }
        OrderRepository.saveOrUpdate(new Order(Order, product));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        OrderRepository.deleteById(id);
    }
}