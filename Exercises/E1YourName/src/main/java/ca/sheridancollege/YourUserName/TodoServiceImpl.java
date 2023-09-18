package ca.sheridancollege.YourUserName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.random.RandomGenerator;

public class TodoServiceImpl implements TodoService{

    List<String> list = new CopyOnWriteArrayList<>();

    @Override
    public List<String> retrieveTodos() {

       list = populateTodos();

        return list;
    }

    @Override
    public void deleteTodo(String todo) {

        list.remove(todo);
    }


    private List<String> populateTodos(){
        list.add("Spring ToDO");
        list.add("any value");
        list.add("any value 2");
        list.add("any value 3");
        list.add("any value 4");

        return list;
    }
}
