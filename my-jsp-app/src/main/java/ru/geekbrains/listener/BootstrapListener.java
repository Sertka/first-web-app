package ru.geekbrains.listener;

import ru.geekbrains.entity.Product;
import ru.geekbrains.entity.User;
import ru.geekbrains.repo.ProductRepository;
import ru.geekbrains.repo.UserRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();

        productRepository.saveOrUpdate(new Product(null, "Product  1",
                "Description of product 1", new BigDecimal(100)));
        productRepository.saveOrUpdate(new Product(null, "Product  2",
                "Description of product 2", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product  3",
                "Description of product 3", new BigDecimal(200)));

        sce.getServletContext().setAttribute("productRepository", productRepository);


        UserRepository userRepository = new UserRepository();

        userRepository.saveOrUpdate(new User(null, "alex", "Alex"));
        userRepository.saveOrUpdate(new User(null, "leo", "Leo"));
        userRepository.saveOrUpdate(new User(null, "alis", "Alisa"));

        sce.getServletContext().setAttribute("userRepository", userRepository);
    }
}
