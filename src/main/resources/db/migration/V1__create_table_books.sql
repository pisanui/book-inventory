CREATE TABLE books (
    id int NOT NULL AUTO_INCREMENT,
    title varchar(255) NOT NULL,
    author varchar(255) NOT NULL,
    published_date date,
    PRIMARY KEY (id)
);