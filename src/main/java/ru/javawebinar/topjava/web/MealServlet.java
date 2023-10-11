package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.LocalStorage;
import ru.javawebinar.topjava.Storage;
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
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    final static int CALORIES_LIMIT = 2000;

    private Storage storage;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = new LocalStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to meals");

        String forward = "";
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("formatter", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            request.setAttribute("meals", MealsUtil.filteredByStreams(storage.getStorage(), LocalTime.MIN, LocalTime.MAX, CALORIES_LIMIT));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
            return;
        }

        switch (action) {
            case "delete": {
                Integer id = Integer.valueOf(request.getParameter("id"));
                log.debug("Delete id: " + id);
                storage.delete(id);
//                forward = "/meals.jsp";
//                request.setAttribute("formatter", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//                request.setAttribute("meals", MealsUtil.filteredByStreams(storage.getStorage(), LocalTime.MIN, LocalTime.MAX, CALORIES_LIMIT));
                response.sendRedirect("/topjava/meals");
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
                Meal meal = storage.getMealByID(id);
                log.debug("Update: id - " + meal.getId() + ", description - " + meal.getDescription());
                request.setAttribute("id", id);
                request.setAttribute("meal", meal);
                break;
            }
            case "meals":
                forward = "/meals.jsp";
                request.setAttribute("formatter", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                request.setAttribute("meals", MealsUtil.filteredByStreams(storage.getStorage(), LocalTime.MIN, LocalTime.MAX, CALORIES_LIMIT));
                break;
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
            storage.addMeal(meal);
        } else {
            meal.setId(Integer.valueOf(id));
            storage.updateMeal(meal);
        }

        request.setAttribute("formatter", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        request.setAttribute("meals", MealsUtil.filteredByStreams(storage.getStorage(), LocalTime.MIN, LocalTime.MAX, CALORIES_LIMIT));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}






















