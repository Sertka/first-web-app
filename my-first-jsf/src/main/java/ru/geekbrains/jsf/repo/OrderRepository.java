package ru.geekbrains.jsf.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.jsf.entity.Order;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<Order> findAll() {
        return em.createNamedQuery("findAllOrders", Order.class)
                .getResultList();
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public Long countAll() {
        return em.createNamedQuery("countAllOrders", Long.class)
                .getSingleResult();
    }

    public void saveOrUpdate(Order Order) {
        if (Order.getId() == null) {
            em.persist(Order);
        }
        em.merge(Order);
    }

    public void deleteById(Long id) {
        em.createNamedQuery("deleteOrderById")
                .setParameter("id", id)
                .executeUpdate();
    }
}
