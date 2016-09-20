package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

public interface MealDao {
    void insert(Meal meal);

    void update(Meal meal);

    void delete(int id);

    Meal findOneById(int id);

    List<MealWithExceed> getAll();
}
