package com.okanyesil.springBootmysql.services;

import com.okanyesil.springBootmysql.model.NewTodoDTO;
import com.okanyesil.springBootmysql.model.Todo;
import com.okanyesil.springBootmysql.model.TodoDTO;
import com.okanyesil.springBootmysql.model.TodoMapper;
import com.okanyesil.springBootmysql.persistance.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper = TodoMapper.INSTANCE;

    @Override
    public TodoDTO addTodo(NewTodoDTO newTodoDTO) {
        Todo todo = new Todo(null, newTodoDTO.getTitle(), newTodoDTO.getDescription(), newTodoDTO.getStatus());
        final Todo savedTodo = todoRepository.save(todo);
        return todoMapper.todoToTodoDto(savedTodo);
    }
    @Transactional(readOnly = true)
    @Override
    public List<TodoDTO> getTodos() {
        return todoRepository.findAll().stream().map(mapToTodoDTO).collect(Collectors.toList());
    }

    @Override
    public TodoDTO updateTodo(TodoDTO todoDTO) {
        Todo todo = todoRepository.findById(todoDTO.getId()).orElseThrow(() -> new EntityNotFoundException("No todo can be found"));
        todo.setDescription(todoDTO.getDescription());
        todo.setTitle(todoDTO.getTitle());
        todo.setStatus(todoDTO.getStatus());
        final Todo savedTodo = todoRepository.save(todo);
        return todoMapper.todoToTodoDto(savedTodo);
    }

    @Override
    public TodoDTO deleteTodo(TodoDTO todoDTO) {
        Todo todo = todoRepository.findById(todoDTO.getId()).orElseThrow(() -> new EntityNotFoundException("No todo can be found"));
        todoRepository.delete(todo);
        return todoMapper.todoToTodoDto(todo);
    }
    private final Function<Todo, TodoDTO> mapToTodoDTO = (t) -> new TodoDTO(t.getId(), t.getTitle(), t.getDescription(), t.getStatus());
}
