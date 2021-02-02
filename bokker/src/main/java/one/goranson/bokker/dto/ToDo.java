package one.goranson.bokker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ToDo {
    private String title;
    private boolean completed;

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public static ToDo of(String title, boolean completed) {
        return new ToDo(title, completed);
    }
}
