package com.okanyesil.springBootmysql.services;

import com.okanyesil.springBootmysql.model.NewTodoDTO;
import com.okanyesil.springBootmysql.model.Todo;
import com.okanyesil.springBootmysql.model.TodoDTO;

import java.util.List;

public interface TodoService {
    TodoDTO addTodo(NewTodoDTO newTodoDTO);
    List<TodoDTO> getTodos();
    TodoDTO updateTodo(TodoDTO todoDTO);
    TodoDTO deleteTodo(TodoDTO todoDTO);
}
