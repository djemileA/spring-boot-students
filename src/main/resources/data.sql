-- Очищаем таблицы
DELETE FROM student_hobbies;
DELETE FROM students;

-- Добавляем студентов
INSERT INTO students (first_name, last_name, age, email, created_at) VALUES
('Иван', 'Иванов', 25, 'ivan@example.com', '2024-01-15 10:00:00'),
('Петр', 'Петров', 29, 'petr@example.com', '2024-01-16 11:00:00'),
('Василий', 'Смирнов', 29, 'vasiliy@example.com', '2024-01-17 12:00:00'),
('Анна', 'Сидорова', 22, 'anna@example.com', '2024-01-18 13:00:00'),
('Мария', 'Кузнецова', 24, 'maria@example.com', '2024-01-19 14:00:00');

-- Добавляем хобби
INSERT INTO student_hobbies (student_id, hobby) VALUES
(1, 'Хоккей'),
(1, 'Футбол'),
(2, 'Программирование'),
(2, 'Пейнтбол'),
(2, 'Лего'),
(3, 'Видеоигры'),
(4, 'Чтение'),
(4, 'Рисование'),
(5, 'Танцы'),
(5, 'Пение'),
(5, 'Фотография');
