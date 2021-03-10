package ru.geekbrains.jsf.service;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {

    void addToCart(ProductRepr product);

    void removeFromCart(ProductRepr product);

    List<ProductRepr> getAllProducts();
}
