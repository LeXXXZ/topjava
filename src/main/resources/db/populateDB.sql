DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id) VALUES
( make_timestamp(2015, 05, 30, 10, 0, 0), 'Завтрак', 500, 100000),
( make_timestamp(2015, 05, 30, 13, 0, 0), 'Обед', 1000, 100000),
( make_timestamp(2015, 05, 30, 20, 0, 0), 'Ужин', 500, 100000),
( make_timestamp(2015, 05, 31, 09, 0, 0), 'Завтрак', 1000, 100000),
( make_timestamp(2015, 05, 31, 12, 0, 0), 'Обед', 500, 100000),
( make_timestamp(2015, 05, 31, 21, 0, 0), 'Ужин', 510, 100000),
( make_timestamp(2015, 05, 30, 10, 0, 0), 'Завтрак', 500, 100001),
( make_timestamp(2015, 05, 30, 13, 0, 0), 'Обед', 2001, 100001),
( make_timestamp(2015, 05, 30, 20, 0, 0), 'Ужин', 500, 100001),
( make_timestamp(2015, 05, 31, 10, 0, 0), 'Завтрак', 500, 100001);