CREATE TABLE Person (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    full_name VARCHAR(100) UNIQUE NOT NULL,
    birth_year INT NOT NULL
);

CREATE TABLE Book (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    year INT NOT NULL,
    person_id INT REFERENCES Person(id) ON DELETE SET NULL
);
