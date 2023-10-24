DELETE
FROM user_role;
DELETE
FROM users;
DELETE
FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);


INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2020-01-01 14:00:00', 'Еда юзера 1', 1200),
       (100000, '2020-01-01 20:00:00', 'Еда юзера 2', 810),
       (100000, '2020-01-02 14:00:00', 'Еда юзера 3', 1000),
       (100000, '2020-01-02 20:00:00', 'Еда юзера 4', 810),
       (100000, '2021-01-03 21:00:00', 'Еда юзера 5', 2100),
       (100001, '2020-01-01 20:00:00', 'Еда админа 1', 1300);
