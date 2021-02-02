package one.goranson.bokker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import one.goranson.bokker.dto.ToDo;
import one.goranson.bokker.persistence.entities.ToDoEntity;
import one.goranson.bokker.persistence.ToDoRepository;

@Service
public class ToDoService {

    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    public void save(ToDo newTodo) {
        repository.save(new ToDoEntity(newTodo.getTitle(), newTodo.isCompleted()));
    }

    public List<ToDo> getAll() {
        return repository.findAll().stream()
                .map(toDoEntity -> ToDo.of(toDoEntity.getTitle(), toDoEntity.isCompleted()))
                .collect(Collectors.toList());
    }
}
