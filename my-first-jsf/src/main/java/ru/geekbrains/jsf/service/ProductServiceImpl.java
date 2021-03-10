package ru.geekbrains.jsf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.jsf.entity.Category;
import ru.geekbrains.jsf.repo.CategoryRepository;
import ru.geekbrains.jsf.repo.ProductRepository;
import ru.geekbrains.jsf.entity.Product;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ru.geekbrains.jsf.service.ProductServiceImpl.class);

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductRepr findById(Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            return new ProductRepr(product);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(ProductRepr product) {
        logger.info("Saving product with id {}" , product.getId());

        Category category = null;
        if (product.getCategoryId() != null) {
            category = categoryRepository.getReference(product.getCategoryId());
        }
        productRepository.saveOrUpdate(new Product(product, category));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}