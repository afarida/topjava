package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/meals")
public class MealController {

    @Autowired
    MealService service;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        int userId = AuthorizedUser.id();
        model.addAttribute("mealList", MealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id) {
        int userId = AuthorizedUser.id();
        service.delete(id, userId);
        return "meals";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("meal", new Meal(LocalDateTime.now(), "", 0));
        return "meal";
    }

    @ModelAttribute("meal")
    public Meal newMeal() {
        return new Meal();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, Model model) {
        int userId = AuthorizedUser.id();
        model.addAttribute("meal", service.get(id, userId));
        return "meal";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("meal") Meal meal) {
        int userId = AuthorizedUser.id();
        service.save(meal, userId);
        return "redirect:/meals";
    }
}
