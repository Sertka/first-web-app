package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Product extends HttpServlet{

    public static final Logger logger = LoggerFactory.getLogger(Product.class);
    private transient ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().println("<h1>Продукт</h1>");
        res.getWriter().println("<a href=order>Оформить заказ</a>");
        res.getWriter().println("<a href=catalog>Вернуться в каталог</a>");
    }

    @Override
    public void destroy() {

    }
}

