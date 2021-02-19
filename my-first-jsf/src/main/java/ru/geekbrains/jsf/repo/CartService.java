package ru.geekbrains.jsf.repo;

import ru.geekbrains.jsf.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class CartService {
    private final Map<Long, Product> cartMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public List<Product> findAll() {
        return new ArrayList<>(cartMap.values());
    }

    public Product findById(Long id) {
        return cartMap.get(id);
    }

    public void addProduct(Product product) {
        cartMap.put(product.getId(), product);
    }

    public void deleteById(Long id) {
        cartMap.remove(id);
    }
}
