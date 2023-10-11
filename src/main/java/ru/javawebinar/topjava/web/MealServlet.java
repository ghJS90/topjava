package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.storage.LocalMealStorage;
import ru.javawebinar.topjava.storage.MealStorage;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private final static Logger log = getLogger(MealServlet.class);
    final static int CALORIES_LIMIT = 2000;
    private MealStorage mealStorage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mealStorage = new LocalMealStorage();
        mealStorage.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        mealStorage.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        mealStorage.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        mealStorage.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        mealStorage.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        mealStorage.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        mealStorage.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        String forward;
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("formatter", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            request.setAttribute("meals", MealsUtil.filteredByStreams(mealStorage.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_LIMIT));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
            return;
        }

        switch (action) {
            case "delete": {
                Integer id = Integer.valueOf(request.getParameter("id"));
                log.debug("Delete id: " + id);
                mealStorage.delete(id);
                response.sendRedirect("meals");
                return;
            }
            case "add": {
                log.debug("Add new meal");
                forward = "/addMeal.jsp";
                Meal meal = new Meal();
                request.setAttribute("meal", meal);
                break;
            }
            case "update": {
                forward = "/addMeal.jsp";
                Integer id = Integer.valueOf(request.getParameter("id"));
                Meal meal = mealStorage.getByID(id);
                log.debug("Update: id - " + meal.getId() + ", description - " + meal.getDescription());
                request.setAttribute("meal", meal);
                break;
            }
            default: {
                response.sendRedirect("meals");
                return;
            }
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Meal meal = new Meal();
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        String id = request.getParameter("id");

        log.debug("request.getParam - " + id);

        meal.setDateTime(dateTime);
        meal.setDescription(description);
        meal.setCalories(calories);


        if (id.equals("")) {
            mealStorage.add(meal);
        } else {
            meal.setId(Integer.valueOf(id));
            mealStorage.update(meal);
        }

        response.sendRedirect("meals");
    }
}






















