package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
        start();
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(MEAL_ID, USER_ID);
        MATCHER.assertEquals(MEALS.get(USER_ID).get(MEAL_ID), meal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(MEAL_ID, ADMIN_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL_ID, USER_ID);
        MEALS.get(USER_ID).remove(MEAL_ID);
        MATCHER.assertCollectionEquals(MEALS.get(USER_ID).values()
                        .stream()
                        .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                        .collect(Collectors.toList()),
                service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(MEAL_ID, ADMIN_ID);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        LocalDate startDate = LocalDate.of(2015, 5, 30);
        LocalDate endDate = LocalDate.of(2015, 5, 30);
        MATCHER.assertCollectionEquals(service.getBetweenDates(startDate, endDate, USER_ID),
                MEALS.get(USER_ID).values().stream().filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalDate(), startDate, endDate))
                        .collect(Collectors.toList()));
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(MEALS.get(USER_ID).values()
                        .stream()
                        .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                        .collect(Collectors.toList()),
                service.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal meal = MEALS.get(USER_ID).get(MEAL_ID);
        meal.setDescription("Update description");
        meal.setCalories(50);
        service.save(meal, USER_ID);
        MATCHER.assertEquals(meal, service.get(MEAL_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        Meal meal = MEALS.get(USER_ID).get(MEAL_ID);
        service.update(meal, ADMIN_ID);
    }

    @Test
    public void save() throws Exception {
        Meal meal = new Meal(LocalDateTime.now(), "Save", 120);
        service.save(meal, USER_ID);
        MATCHER.assertEquals(meal, service.get(meal.getId(), USER_ID));
    }
}