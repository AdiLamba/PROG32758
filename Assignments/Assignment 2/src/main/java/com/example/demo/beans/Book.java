package com.example.demo.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
public class Book {

    private long id;
    private int isbnNo;
    private String bookName;
    private String authorName;

    @Getter @Setter
    private String role;

    @Getter @Setter
    private Date creationDate;

}
