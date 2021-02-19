package ru.geekbrains.jsf.controller;

import ru.geekbrains.jsf.entity.Product;
import ru.geekbrains.jsf.repo.CartService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {

    @Inject
    private CartService cartService;


    public String addToCart(Product product) {
        cartService.addProduct(product);
        return  "/cart.xhtml?faces-redirect-true";
    }

    public String removeFromCart(Product product) {
        cartService.deleteById(product.getId());
        return  "/cart.xhtml?faces-redirect-true";
    }

    public List<Product> getAllProducts() {
        return cartService.findAll();
    }
}
