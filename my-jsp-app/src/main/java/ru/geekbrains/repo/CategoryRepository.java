package ru.geekbrains.repo;

import ru.geekbrains.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CategoryRepository {
    private final Map<Long, Category> catMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public List<Category> findAll() {
        return new ArrayList<>(catMap.values());
    }

    public Category findById(Long id) {
        return catMap.get(id);
    }

    public void saveOrUpdate(Category product) {
        if (product.getId() == null) {
            Long id = identity.incrementAndGet();
            product.setId(id);
        }
        catMap.put(product.getId(), product);
    }

    public void deleteById(Long id) {
        catMap.remove(id);
    }
}
