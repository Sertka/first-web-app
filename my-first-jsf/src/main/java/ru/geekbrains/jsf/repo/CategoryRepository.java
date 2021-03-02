package ru.geekbrains.jsf.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.jsf.entity.Category;

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
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                ut.begin();

                saveOrUpdate(new Category(null, "Category  1"));
                saveOrUpdate(new Category(null, "Category  2"));
                saveOrUpdate(new Category(null, "Category  3"));

                ut.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                ut.rollback();
            }
        }
    }

    public List<Category> findAllCategories() {
        return em.createNamedQuery("findAllCategories", Category.class)
                .getResultList();
    }

    public Category findCategoryById(Long id) {
        return em.find(Category.class, id);
    }

    public Long countAll() {
        return em.createNamedQuery("countAllCategories", Long.class)
                .getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            em.persist(category);
        }
        em.merge(category);
    }

    @Transactional
    public void deleteCategoryById(Long id) {
        em.createNamedQuery("deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }
}
