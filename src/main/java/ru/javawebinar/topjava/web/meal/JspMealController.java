package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController{

    @GetMapping("")
    public String meals(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        int id = getId(request);
        delete(id);
        return "redirect:/meals";
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request) {
        Meal newMeal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "Новая еда", 1000);
        request.setAttribute("meal", newMeal);
        return "mealForm";
    }


    @GetMapping("/update")
    public String update(HttpServletRequest request) {
        int id = getId(request);
        request.setAttribute("meal", get(id));
        return "mealForm";

    }

    @GetMapping("/filter")
    public String filter(HttpServletRequest request) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        request.setAttribute("meals", getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }


    @PostMapping("/add")
    public String addMeal(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (StringUtils.isEmpty(request.getParameter("id"))) {
           create(meal);
        } else {
            int id = getId(request);
            update(meal, id);

        }
        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
