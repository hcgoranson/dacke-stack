package one.goranson.bokker.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import one.goranson.bokker.persistence.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
