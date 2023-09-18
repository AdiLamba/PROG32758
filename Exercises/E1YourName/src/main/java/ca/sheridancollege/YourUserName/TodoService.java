package ca.sheridancollege.YourUserName;

import java.util.List;

// External Service - Lets say this comes from WunderList

public interface TodoService {

    public List<String> retrieveTodos();

    void deleteTodo(String todo);

}