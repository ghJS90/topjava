package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ANOTHER_USER = 13;
    public static final int id;

    public static Meal user_meal_1 = new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 1, 14, 0), "Обед Юзера", 1200);
    public static Meal user_meal_2 = new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 1, 20, 0), "Ужин Юзера", 810);

    static {
        id = user_meal_1.getId();
    }

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2020, Month.JANUARY, 1, 7, 0), "Новая еда", 500);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(user_meal_1);
        updated.setDescription("Обновленный Обед Юзера");
        updated.setDateTime(LocalDateTime.of(2020, Month.JANUARY, 1, 14, 30));
        updated.setCalories(1000);
        return updated;
    }
}
