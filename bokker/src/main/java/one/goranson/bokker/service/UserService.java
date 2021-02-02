package one.goranson.bokker.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import lombok.extern.slf4j.Slf4j;
import one.goranson.bokker.dto.User;
import one.goranson.bokker.persistence.UserRepository;
import one.goranson.bokker.persistence.entities.UserEntity;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        this.userRepository.save(UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build());
    }

    public List<User> findAll() {
        return StreamSupport
            .stream(this.userRepository.findAll().spliterator(), false)
                .map(entity -> User.builder()
                        .id(entity.getId())
                        .firstName(entity.getFirstName())
                        .lastName(entity.getLastName())
                        .email(entity.getEmail())
                        .build())
            .collect(Collectors.toList());
    }
}
