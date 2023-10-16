CREATE TABLE BOOK (
    id long PRIMARY KEY AUTO_INCREMENT,
    isbnNo INT,
    bookName VARCHAR(50),
    authorName VARCHAR(50),
    role VARCHAR(50),
    creationDate TIMESTAMP,
    actions VARCHAR (10)
);