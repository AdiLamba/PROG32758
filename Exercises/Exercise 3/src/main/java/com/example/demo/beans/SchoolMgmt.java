package com.example.demo.beans;

import lombok.Data;
import lombok.Getter;

@Data
public class SchoolMgmt {

    private String name;
    private String city;
    private int numOfStudents;

    @Getter
    private final String[] cities = {"Oakville", "Mississauga", "Toronto", "Hamilton"};
}
