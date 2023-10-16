package com.example.demo.database;

import com.example.demo.beans.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DatabaseAccess {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        String query = "SELECT * FROM BOOK";
        List<Book> books = jdbcTemplate.query(query, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setIsbnNo(rs.getInt("isbnNo"));
            book.setBookName(rs.getString("bookName"));
            book.setAuthorName(rs.getString("authorName"));
            book.setRole(rs.getString("role"));
            book.setCreationDate(rs.getTimestamp("creationDate"));
            return book;
        });
        return books;
    }

    public int deleteBook(long id) {
        //MapSqlParameterSource namedParameters = new MapSqlParameterSource();
       // namedParameters.addValue("id", id);
        String query = "DELETE FROM BOOK WHERE id = ?";
        int returnValue = jdbcTemplate.update(query, id);
        return returnValue;
    }

    public void insertBook(Book book) {
        String query = "INSERT INTO BOOK (isbnNo, bookName, authorName, role, creationDate) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, book.getIsbnNo(), book.getBookName(), book.getAuthorName(),
                book.getRole(), book.getCreationDate());
    }

    public int bookCount() {
        String query = "SELECT COUNT(*) FROM BOOK";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
