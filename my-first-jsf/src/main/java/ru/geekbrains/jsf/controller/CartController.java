package ru.geekbrains.jsf.controller;

import ru.geekbrains.jsf.service.CartService;
import ru.geekbrains.jsf.service.ProductRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {


    @EJB
    private CartService cartService;

    public String addToCart(ProductRepr product) {
        cartService.addToCart(product);
        return  "/cart.xhtml?faces-redirect-true";
    }

    public String removeFromCart(ProductRepr product) {
        cartService.removeFromCart(product);
        return  "/cart.xhtml?faces-redirect-true";
    }

    public List<ProductRepr> getAllProducts() {
        return cartService.getAllProducts();
    }
}
