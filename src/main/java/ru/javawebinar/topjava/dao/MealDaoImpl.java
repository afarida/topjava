package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealDaoImpl implements MealDao {
    private static List<Meal> meals = new ArrayList<>();

    {
        for (int i = 0; i < 100; i++) {
            int j = i % 3;
            String description = "Завтрак";
            if (j == 1)
                description = "Обед";
            else if (j == 2)
                description = "Ужин";
            meals.add(new Meal(i + 1, LocalDateTime.of(2016, Month.SEPTEMBER, (int) (Math.random() * 29) + 1, (int) (Math.random() * 23), 0),
                    description, (int) (Math.random() * 400) + 100));
        }
    }

    @Override
    public void insert(Meal meal) {
        meals.add(meal);
    }

    @Override
    public void update(Meal meal) {
        for (Meal m : meals)
            if (m.getId() == meal.getId()) {
                break;
            }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < meals.size(); i++)
            if (meals.get(i).getId() == id) {
                meals.remove(meals.get(i));
                break;
            }
    }

    @Override
    public Meal findOneById(int id) {
        for (Meal meal : meals)
            if (meal.getId() == id)
                return meal;
        return null;
    }

    @Override
    public List<MealWithExceed> getAll() {
        Map<LocalDate, Integer> caloriesByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .map(meal -> new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        caloriesByDate.get(meal.getDateTime().toLocalDate()) > 2000))
                .collect(Collectors.toList());
    }
}
