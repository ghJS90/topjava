package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int ID_1 = START_SEQ + 3;
    public static final int ID_2 = START_SEQ + 4;
    public static final int ID_3 = START_SEQ + 5;
    public static final int ID_4 = START_SEQ + 6;
    public static final int ID_5 = START_SEQ + 7;
    public static final int ID_6 = START_SEQ + 8;

    public static Meal userMeal1 = new Meal(ID_1, LocalDateTime.of(2020, Month.JANUARY, 1, 14, 0), "Еда юзера 1", 1200);
    public static Meal userMeal2 = new Meal(ID_2, LocalDateTime.of(2020, Month.JANUARY, 1, 20, 0), "Еда юзера 2", 810);
    public static Meal userMeal3 = new Meal(ID_3, LocalDateTime.of(2020, Month.JANUARY, 2, 14, 0), "Еда юзера 3", 1000);
    public static Meal userMeal4 = new Meal(ID_4, LocalDateTime.of(2020, Month.JANUARY, 2, 20, 0), "Еда юзера 4", 810);
    public static Meal userMeal5 = new Meal(ID_5, LocalDateTime.of(2021, Month.JANUARY, 3, 21, 0), "Еда юзера 5", 2100);
    public static Meal adminMeal1 = new Meal(ID_6, LocalDateTime.of(2020, Month.JANUARY, 1, 20, 0), "Еда админа 1", 1300);

    public static Meal getNewMeal() {
        return new Meal(LocalDateTime.of(2020, Month.JANUARY, 1, 7, 0), "Новая еда", 500);
    }

    public static Meal getUpdatedMeal() {
        Meal updated = new Meal(userMeal1);
        updated.setDescription("Обновленный Обед Юзера");
        updated.setDateTime(LocalDateTime.of(2020, Month.JANUARY, 1, 14, 30));
        updated.setCalories(1000);
        return updated;
    }
}
