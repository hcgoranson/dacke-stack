package one.goranson.bokker.persistence.entities;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="todo")
public class ToDoEntity {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String title;
    private boolean completed;

    /**
     * No args constructor, needed for deserialization
     */
    public ToDoEntity() {
    }

    public ToDoEntity(String title) {
        this.title = title;
    }

    public ToDoEntity(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public ToDoEntity(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoEntity todo = (ToDoEntity) o;
        return completed == todo.completed &&
                Objects.equals(id, todo.id) &&
                Objects.equals(title, todo.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, completed);
    }

    public Long getId() {
        return id;
    }
}