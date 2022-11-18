package ru.javarush.quest.features;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Question {
    private long id;
    private String text;
    private List<Answer> answers;
    private long nextQuestionId;
    private Boolean isEnd;
}
