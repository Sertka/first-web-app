package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/header")
public class PageHeaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String header = (String) req.getAttribute("pageHeader");
        res.getWriter().println("<a href=main>Главное меню</a>");
        res.getWriter().println("<a href=catalog>Каталог товаров</a>");
        res.getWriter().println("<a href=order>Оформление заказа</a>");
        res.getWriter().println("<a href=cart>Корзина</a>");
    }
}
