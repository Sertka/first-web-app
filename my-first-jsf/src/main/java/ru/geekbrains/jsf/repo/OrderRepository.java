package ru.geekbrains.jsf.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.jsf.entity.Order;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.List;


@Named
@ApplicationScoped
public class OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws Exception {
        if (countAllOrders() == 0) {
            try {
                ut.begin();

                saveOrUpdate(new Order(null, "Order 1",
                        new BigDecimal(100), 1L));
                saveOrUpdate(new Order(null, "Order 2",
                        new BigDecimal(200), 2L));
                saveOrUpdate(new Order(null, "Order 3",
                        new BigDecimal(300), 3L));

                ut.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                ut.rollback();
            }
        }
    }

    public List<Order> findAllOrders() {
        return em.createNamedQuery("findAllOrders", Order.class)
                .getResultList();
    }

    public Order findOrderById(Long id) {
        return em.find(Order.class, id);
    }

    public Long countAllOrders() {
        return em.createNamedQuery("countAllOrders", Long.class)
                .getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Order order) {
        if (order.getId() == null) {
            em.persist(order);
        }
        em.merge(order);
    }

    @Transactional
    public void deleteOrderById(Long id) {
        em.createNamedQuery("deleteOrderById")
                .setParameter("id", id)
                .executeUpdate();
    }
}
