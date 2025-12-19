CREATE TABLE students (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    age INT,
    email VARCHAR(255),
    created_at TIMESTAMP
);

CREATE TABLE student_hobbies (
    student_id BIGINT,
    hobby VARCHAR(255),
    FOREIGN KEY (student_id) REFERENCES students(id)
);
