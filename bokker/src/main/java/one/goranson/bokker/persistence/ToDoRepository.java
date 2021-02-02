package one.goranson.bokker.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.goranson.bokker.persistence.entities.ToDoEntity;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoEntity, Long> {
    ToDoEntity findByTitle(String title);
}