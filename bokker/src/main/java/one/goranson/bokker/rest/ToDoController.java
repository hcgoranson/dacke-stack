package one.goranson.bokker.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import one.goranson.bokker.dto.ToDo;
import one.goranson.bokker.service.ToDoService;

@RestController
@RequestMapping("rest/todo")
@Slf4j
public class ToDoController {

    private final ToDoService todoService;

    public ToDoController(ToDoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<ToDo> getAll() {
        return todoService.getAll();
    }

    @PostMapping
    public void create(@RequestBody ToDo todo) {
        todoService.save(todo);
    }
}
