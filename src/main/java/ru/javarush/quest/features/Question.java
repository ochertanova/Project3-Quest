package ru.javarush.quest.features;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Question {
    private int id;
    private String text;
    private List<Answer> answers;
    private int nextQuestionId;
    private boolean isEnd;

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", answers=" + answers +
                ", nextQuestionId=" + nextQuestionId +
                ", isEnd=" + isEnd +
                '}';
    }
}
