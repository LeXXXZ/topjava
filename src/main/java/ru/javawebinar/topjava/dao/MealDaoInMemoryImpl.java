package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoInMemoryImpl implements MealDao {

    Map<Integer, Meal> dao = new ConcurrentHashMap<>();
    AtomicInteger counter = new AtomicInteger();

    private static final Logger log = getLogger(MealDaoInMemoryImpl.class);

    {
        createOrUpdate(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        createOrUpdate(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        createOrUpdate(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        createOrUpdate(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        createOrUpdate(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        createOrUpdate(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void createOrUpdate(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(counter.incrementAndGet());
            dao.put(meal.getId(), meal);
            log.debug("meal created");
        } else {
            dao.put(meal.getId(), meal);
            log.debug("meal updated");
        }
    }

    @Override
    public Meal getById(int id) {
        log.debug("return meal");
        return dao.getOrDefault(id, new Meal(LocalDateTime.now(), "description", 1000));
    }

    @Override
    public void delete(int id) {
        dao.remove(id);
        log.debug("meal deleted");
    }

    @Override
    public List<Meal> getAll() {
        List<Meal> mealList = new ArrayList<>(dao.values());
        log.debug("return meallist");
        return mealList;
    }
}
