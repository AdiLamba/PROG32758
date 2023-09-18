package ca.sheridancollege.YourUserName;

public class Main {

    public static void main(String[] args) {

        // preferred
        TodoService todoService = new TodoServiceImpl();

        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);

        todoBusiness.retrieveTodosRelatedToSpring();
        todoBusiness.deleteTodosNotRelatedToSpring();
    }
}
