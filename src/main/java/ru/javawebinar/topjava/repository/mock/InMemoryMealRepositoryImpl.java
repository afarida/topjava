package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(1);
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id) {
        Meal meal = repository.values()
                .stream()
                .filter(f -> f.getUserId() == AuthorizedUser.id() && id == f.getId())
                .findFirst().get();
        if (meal != null)
            return repository.remove(id) != null;
        return false;
    }

    @Override
    public Meal get(int id) {
        return repository.values()
                .stream()
                .filter(f -> f.getUserId() == AuthorizedUser.id() && id == f.getId())
                .findFirst().get();
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values().stream()
                .filter(f -> f.getUserId() == AuthorizedUser.id())
                .sorted((u1, u2) -> u2.getDate().compareTo(u1.getDate()))
                .collect(Collectors.toList());
    }
}

