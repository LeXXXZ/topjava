package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoInMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExcess;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealDao dao = new MealDaoInMemoryImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action") == null) {
            List<MealTo> mealsWithExcess = getFilteredWithExcess(dao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
            request.setAttribute("meals", mealsWithExcess);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        } else {
            String action = request.getParameter("action");
            int mealId = 0;
            switch (action) {
                case "delete":
                    mealId = Integer.parseInt(request.getParameter("mealId"));
                    dao.delete(mealId);
                    response.sendRedirect("meals");
                    break;
                case "edit":
                    mealId = Integer.parseInt(request.getParameter("mealId"));
                case "insert":
                    Meal meal = dao.getById(mealId);
                    request.setAttribute("meal", meal);
                    request.getRequestDispatcher("/mealedit.jsp").forward(request, response);
                    break;
                default:
                    List<MealTo> mealsWithExcess = getFilteredWithExcess(dao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
                    request.setAttribute("meals", mealsWithExcess);
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);
                    break;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Integer mealid;

        if (!request.getParameter("id").equals("")) {
            mealid = Integer.parseInt(request.getParameter("id"));
        } else mealid = null;

        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date"), DateTimeFormatter.ISO_DATE_TIME);

        String description = request.getParameter("description");

        int calories = Integer.parseInt(request.getParameter("calories"));

        Meal meal = new Meal(dateTime, description, calories, mealid);
        dao.createOrUpdate(meal);
        List<MealTo> mealsWithExcess = getFilteredWithExcess(dao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("meals", mealsWithExcess);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
