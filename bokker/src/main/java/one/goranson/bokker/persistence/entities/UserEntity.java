package one.goranson.bokker.persistence.entities;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
@RedisHash("User")
public class UserEntity implements Serializable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
