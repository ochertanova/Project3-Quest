package ru.javarush.quest.enums;

public enum UserReaction {

    YES(0, "YES", "ДА"),
    NO(1, "NO", "НЕТ");

    private int id;
    private String nameParam;
    private String description;

    UserReaction(int id, String nameParam, String description) {
        this.id = id;
        this.nameParam = nameParam;
        this.description = description;
    }

    public String getNameParam() {
        return nameParam;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}
