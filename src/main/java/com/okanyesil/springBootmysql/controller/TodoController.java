package com.okanyesil.springBootmysql.controller;

import com.okanyesil.springBootmysql.model.NewTodoDTO;
import com.okanyesil.springBootmysql.model.TodoDTO;
import com.okanyesil.springBootmysql.services.TodoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    @ApiOperation(value = "return all products", response = List.class)
    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TodoDTO>> getTodos() {
        return ResponseEntity.ok(this.todoService.getTodos());
    }


    @ApiOperation(value = "Create a todo", response = TodoDTO.class)
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TodoDTO> createNewTodo(@RequestBody NewTodoDTO newTodoDTO) {
        return new ResponseEntity<>(this.todoService.addTodo(newTodoDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update todo", response = TodoDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Todo updated successfully"),
            @ApiResponse(code = 404, message = "Not existing Todo"),
            @ApiResponse(code = 400, message = "Todo not valid")
    })
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TodoDTO> updateTodo(@RequestBody TodoDTO todoDTO) {
        return ResponseEntity.ok(todoService.updateTodo(todoDTO));
    }

    @ApiOperation(value = "Delete todo", response = TodoDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Todo deleted successfully"),
            @ApiResponse(code = 404, message = "Not existing Todo"),
            @ApiResponse(code = 400, message = "Todo not valid")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<TodoDTO> deleteTodo(@RequestBody TodoDTO todoDTO) {
        return ResponseEntity.ok(todoService.deleteTodo(todoDTO));
    }

}
