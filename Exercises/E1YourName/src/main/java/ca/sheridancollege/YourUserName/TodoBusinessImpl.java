package ca.sheridancollege.YourUserName;

import java.util.ArrayList;
import java.util.List;

public class TodoBusinessImpl {

    private TodoService todoService;

    // constructor based injection technique
    TodoBusinessImpl(TodoService todoService) {

        this.todoService = todoService;
    }

    public List<String> retrieveTodosRelatedToSpring() {
        List<String> filteredTodos = new ArrayList<String>();
        List<String> allTodos = todoService.retrieveTodos();
        for (String todo : allTodos) {
            if (todo.contains("Spring")) {
                filteredTodos.add(todo);
            }
        }
        System.out.println("Filtered todos"+filteredTodos);
        return filteredTodos;
    }

    public void deleteTodosNotRelatedToSpring() {
        List<String> allTodos = todoService.retrieveTodos();
        for (String todo : allTodos) {
            if (!todo.contains("Spring")) {
                todoService.deleteTodo(todo);
            }
        }
    }

}