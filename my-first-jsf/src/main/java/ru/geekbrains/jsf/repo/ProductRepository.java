package ru.geekbrains.jsf.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.jsf.entity.Category;
import ru.geekbrains.jsf.entity.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<Product> findAll() {
        return em.createNamedQuery("findAllProducts", Product.class)
                .getResultList();
    }

    public Product getReference(Long id) {
        return em.getReference(Product.class, id);
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public Long countAll() {
        return em.createNamedQuery("countAllProducts", Long.class)
                .getSingleResult();
    }

    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }

    public void deleteById(Long id) {
        em.createNamedQuery("deleteProductById")
                .setParameter("id", id)
                .executeUpdate();
    }
}
