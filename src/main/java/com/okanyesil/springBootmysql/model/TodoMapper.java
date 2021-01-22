package com.okanyesil.springBootmysql.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface TodoMapper {
    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);
    TodoDTO todoToTodoDto(Todo todo);

}
