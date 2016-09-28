package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int MEAL_ID = START_SEQ + 2;

    public static final Map<Integer, Map<Integer, Meal>> MEALS = new HashMap<>();

    public static void start()
    {
        Map<Integer, Meal> userMeal = new HashMap<>();
        userMeal.put(MEAL_ID, new Meal(MEAL_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        userMeal.put(MEAL_ID + 1, new Meal(MEAL_ID + 1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        userMeal.put(MEAL_ID + 5, new Meal(MEAL_ID + 5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        Map<Integer, Meal> adminMeal = new HashMap<>();
        adminMeal.put(MEAL_ID + 2, new Meal(MEAL_ID + 2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        adminMeal.put(MEAL_ID + 3, new Meal(MEAL_ID + 3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        adminMeal.put(MEAL_ID + 4, new Meal(MEAL_ID + 4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        MEALS.put(USER_ID, userMeal);
        MEALS.put(ADMIN_ID, adminMeal);
    }

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual)-> Objects.equals(expected.toString(), actual.toString()));
}
