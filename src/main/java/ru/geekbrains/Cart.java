package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Cart extends HttpServlet {
    private transient ServletConfig config;
    public static final Logger logger = LoggerFactory.getLogger(Product.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Ваша корзина</h1>");
        resp.getWriter().println("<a href=http://127.0.0.1:8080/first-web-app/catalog>Вернуться в каталог</a>");
        resp.getWriter().println("<a href=http://127.0.0.1:8080/first-web-app/main>Вернуться в главное меню</a>");

    }

    @Override
    public void destroy() {

    }
}


