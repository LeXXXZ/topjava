package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Integer save(Meal meal) {
        service.save(meal, authUserId());
        return meal.getId();
    }

    public void delete(int id) throws NotFoundException {
       service.delete(id, authUserId());
    }

    public Meal get(int id) throws NotFoundException {
        return service.get(id, authUserId());
    }

    public List<MealTo> getAll() {
        return new ArrayList<>(service.getAll(authUserId(), authUserCaloriesPerDay()));
    }
}