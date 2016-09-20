package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private static final String INSERT_OR_UPDATE = "/meal.jsp";
    private static final String LIST_MEALS = "/mealList.jsp";

    private MealDao model = new MealDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String forward = null;
        String action = request.getParameter("action");

        if ("edit".equalsIgnoreCase(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = model.findOneById(id);
            request.setAttribute("meal", meal);
            forward = INSERT_OR_UPDATE;

            LOG.debug("forward to edit meal, id=" + id);
        } else if ("insert".equalsIgnoreCase(action)) {
            forward = INSERT_OR_UPDATE;
            request.setAttribute("meal", new Meal());
        }
        else {
            if ("delete".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                model.delete(id);
                LOG.debug("forward to delete meal, id=" + id);
            } else LOG.debug("forward to mealList");

            forward = LIST_MEALS;

            request.setAttribute("meals", model.getAll());
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.getRequestDispatcher(forward).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int calories = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime date = LocalDateTime.parse(request.getParameter("datepicker"), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        String description = new String(request.getParameter("description").getBytes(response.getCharacterEncoding()), "UTF-8");

        Meal meal = null;
        if (!"0".equalsIgnoreCase(id)) {
            meal = model.findOneById(Integer.parseInt(id));
            meal.setCalories(calories);
            meal.setDateTime(date);
            meal.setDescription(description);
            model.update(meal);
        }
        else
        {
            meal = new Meal(date, description, calories);
            model.insert(meal);
        }

        request.setAttribute("meals", model.getAll());
        request.getRequestDispatcher(LIST_MEALS).forward(request, response);
    }
}
