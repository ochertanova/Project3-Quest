package ru.javarush.quest.features;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {
    private String name;
    private int point;
    private Integer currentQuestionId;
    private Integer bestResult;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", point=" + point +
                ", currentQuestionId=" + currentQuestionId +
                ", bestResult=" + bestResult +
                '}';
    }
}
