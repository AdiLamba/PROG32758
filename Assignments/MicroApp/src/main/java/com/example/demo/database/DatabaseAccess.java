package com.example.demo.database;

import com.example.demo.beans.Book;
import com.example.demo.beans.Review;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class DatabaseAccess {

    private NamedParameterJdbcTemplate jdbc;

    public List<Book> getBooks() {

        String query = "SELECT * FROM BOOKS";

        BeanPropertyRowMapper<Book> bookMapper =
                new BeanPropertyRowMapper<>(Book.class);

        List<Book> books = jdbc.query(query, bookMapper);

        return books;
    }

    public Book getBookById(Long id) {

        String query = "SELECT * FROM BOOKS WHERE id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("id", id);

        return jdbc.queryForObject(query, parameters, new BeanPropertyRowMapper<>(Book.class));
    }

    public int addBook (Book book) {

        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource();

        String query = "INSERT INTO BOOKS (title, author) VALUES (:title, :author)";

        namedParameters.addValue("title", book.getTitle());
        namedParameters.addValue("author", book.getAuthor());

        return jdbc.update(query, namedParameters);

    }

    public int addReview(Review review) {
        String query = "INSERT INTO REVIEWS (bookId, text) VALUES (:bookId, :text)";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("bookId", review.getBookId()); // Assuming you have a bookId in your Review class
        namedParameters.addValue("text", review.getText());

        return jdbc.update(query, namedParameters);
    }

    public List<Review> getReviewsByBookId(Long bookId) {
        String query = "SELECT * FROM REVIEWS WHERE bookId = :bookId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("bookId", bookId);
        BeanPropertyRowMapper<Review> reviewMapper = new BeanPropertyRowMapper<>(Review.class);
        return jdbc.query(query, parameters, reviewMapper);
    }

}
