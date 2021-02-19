package ru.geekbrains.jsf.repo;

import ru.geekbrains.jsf.entity.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class UserRepository {

    private final Map<Long, User> userMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);


    @PostConstruct
    public void init() {
        this.saveOrUpdate(new User(null, "alex", "Alex"));
        this.saveOrUpdate(new User(null, "leo", "Leo"));
        this.saveOrUpdate(new User(null, "alis", "Alisa"));
    }

    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    public User findById(Long id) {
        return userMap.get(id);
    }

    public void saveOrUpdate(User product) {

        if (product.getId() == null) {
            Long id = identity.incrementAndGet();
            product.setId(id);
        }
        userMap.put(product.getId(), product);
    }

    public void deleteById(Long id) {
        userMap.remove(id);
    }

}
