package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Main extends HttpServlet {

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
        logger.info("Main servlet activated");

        resp.getWriter().println("<h1>Главное меню</h1>");
        getServletContext().getRequestDispatcher("/header").include(req, resp);

/*        resp.getWriter().println("<a href=catalog>Каталог товаров</a>");
        resp.getWriter().println("<a href=order>Оформление заказа</a>");
        resp.getWriter().println("<a href=cart>Корзина</a>");*/
        resp.getWriter().println("<a href=http://127.0.0.1:8080/first-web-app/error404>404</a>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
