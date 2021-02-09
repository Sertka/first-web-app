package ru.geekbrains;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        import javax.servlet.*;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

public class Catalog extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().println("<h1>Каталог</h1>");
        res.getWriter().println("<a href=product>Продукт</a>");
        res.getWriter().println("<a href=main>Вернуться в главное меню</a>");
    }

    @Override
    public void destroy() {

    }
}