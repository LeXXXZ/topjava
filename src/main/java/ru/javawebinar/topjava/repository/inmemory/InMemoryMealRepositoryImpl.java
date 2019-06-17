package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = getLogger(InMemoryMealRepositoryImpl.class);

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal MEAL : MealsUtil.MEALS) {
            save(MEAL, MEAL.getUserId());
        }
    }

    @Override
    public Meal save(Meal meal, Integer userId) {
        log.info("save {} for user: {}", meal, userId);

            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
                meal.setUserId(userId);
                repository.put(meal.getId(), meal);
                return meal;
            }
            // treat case: update, but absent in storage
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, Integer userId) {
        log.info("delete {} for user: {}", id, userId);
        Meal meal = repository.get(id);
        if (Objects.nonNull(meal) && meal.getUserId().equals(userId)) {
            return repository.remove(id) != null;
        } else return false;
    }

    @Override
    public Meal get(int id, Integer userId) {
        log.info("get {} for user: {}", id, userId);
        Meal meal = repository.get(id);
        if (Objects.nonNull(meal) && meal.getUserId().equals(userId)) {
            return meal;
        } else return null;
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {
        log.info("getAll for user: {}", userId);
        return repository.values().stream()
                .filter(meal -> meal.getUserId().equals(userId))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(toList());
    }
}

