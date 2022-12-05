package ru.javarush.quest.features;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Answer {
    private int id;
    private String text;
    private boolean right;
    private int weight;
}
