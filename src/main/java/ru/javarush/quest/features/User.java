package ru.javarush.quest.features;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {
    private long id;
    private String name;
    private int point;
}
