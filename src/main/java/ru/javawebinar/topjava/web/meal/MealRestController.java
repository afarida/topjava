package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public Meal save(Meal meal) {
        return service.save(meal);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public Meal get(int id) {
        return service.get(id);
    }

    public Collection<Meal> getAll() {
        return service.getAll();
    }

    public Collection<MealWithExceed> getFilteredWithExceed(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime) {
        List<MealWithExceed> mealWithExceeds = null;
        if (fromTime != null && toTime != null)
            mealWithExceeds = MealsUtil.getFilteredWithExceeded(getAll(), fromTime, toTime, MealsUtil.DEFAULT_CALORIES_PER_DAY);
        else mealWithExceeds = MealsUtil.getWithExceeded(getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY);

        if (fromDate != null && toDate != null)
            mealWithExceeds = mealWithExceeds.stream()
                    .filter(m -> m.getDateTime().toLocalDate().compareTo(fromDate) >= 0 &&
                            m.getDateTime().toLocalDate().compareTo(toDate) <= 0)
                    .collect(Collectors.toList());

        return mealWithExceeds;
    }
}
