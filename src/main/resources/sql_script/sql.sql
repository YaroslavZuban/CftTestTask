CREATE TABLE gender
(
    id   serial PRIMARY KEY,
    name varchar(30) NOT NULL
);

CREATE TABLE authors
(
    id          serial PRIMARY KEY,
    name        varchar(60) NOT NULL,
    middle_name varchar(60),
    surname     varchar(60) NOT NULL
);

CREATE TABLE books
(
    id               serial PRIMARY KEY,
    title            varchar(60)                  NOT NULL,
    publication_year integer,
    count            integer CHECK ( count >= 0 ) NOT NULL
);

CREATE SEQUENCE books_seq START 4;

CREATE TABLE books_authors
(
    id        serial PRIMARY KEY,
    book_id   integer REFERENCES books (id)   NOT NULL,
    author_id integer REFERENCES authors (id) NOT NULL
);

CREATE TABLE readers
(
    id          serial PRIMARY KEY,
    name        varchar(60)                    NOT NULL,
    middle_name varchar(60),
    surname     varchar(60)                    NOT NULL,
    gender_id   integer REFERENCES gender (id) NOT NULL
);

CREATE TABLE issuance
(
    id           serial PRIMARY KEY,
    book_id      integer REFERENCES books (id)   NOT NULL,
    readers_id   integer REFERENCES readers (id) NOT NULL,
    receipt_date date                            NOT NULL,
    return_date  date                            NOT NULL
);

CREATE TABLE book_return
(
    id          serial PRIMARY KEY,
    issuance_id integer REFERENCES issuance (id) NOT NULL,
    return_date date                             NOT NULL
);


INSERT INTO gender (name)
VALUES ('Male'),
       ('Female');

INSERT INTO authors (name, middle_name, surname)
VALUES ('John', 'Michael', 'Doe'),
       ('Alice', 'Marie', 'Smith');

INSERT INTO books (title, publication_year, count)
VALUES ('Book Title 1', 2020, 10),
       ('Book Title 2', 2018, 15);

INSERT INTO books_authors (book_id, author_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO readers (name, middle_name, surname, gender_id)
VALUES ('Emma', 'Rose', 'Johnson', 2),
       ('William', 'Alexander', 'Brown', 1);

INSERT INTO issuance (book_id, readers_id, receipt_date, return_date)
VALUES (1, 1, '2022-01-01', '2022-02-01'),
       (2, 2, '2022-02-01', '2022-03-01');

INSERT INTO book_return (issuance_id, return_date)
VALUES (1, '2022-02-01'),
       (2, '2022-03-01');

DROP TABLE gender,authors,books,books_authors,readers,issuance,book_return;