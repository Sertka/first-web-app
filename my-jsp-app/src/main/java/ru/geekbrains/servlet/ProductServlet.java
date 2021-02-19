package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.entity.Product;
import ru.geekbrains.repo.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException{
        this.productRepository = (ProductRepository)getServletContext().getAttribute("productRepository");
        if (productRepository == null){
            throw new ServletException("ProductRepository not initialized");
        }
        logger.info("productRepository initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("products", productRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, res);

        } else if (req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                res.setStatus(400);
                return;
            }
            Product product = productRepository.findById(id);
            if (product == null) {
                res.setStatus(404);
                return;
            }
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, res);

        } else if (req.getPathInfo().equals("/delete")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                res.setStatus(400);
                return;
            }
            productRepository.deleteById(id);
            req.setAttribute("products", productRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, res);

        } else if (req.getPathInfo().equals("/new")) {
            Product product = new Product();
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, res);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ex) {
            id = null;
        }
        BigDecimal price;
        try {
            price = new BigDecimal(req.getParameter("price"));
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return;
        }
        Product product = new Product(id, req.getParameter("name"), req.getParameter("description"), price);
        productRepository.saveOrUpdate(product);
        resp.sendRedirect(getServletContext().getContextPath() + "/product");
    }

}















