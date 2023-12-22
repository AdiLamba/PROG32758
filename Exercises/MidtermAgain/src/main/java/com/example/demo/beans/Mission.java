package com.example.demo.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter @Setter
public class Mission {

    private long id;

    private String agent;
    private String title;
    private String gadget1;
    private String gadget2;


}
