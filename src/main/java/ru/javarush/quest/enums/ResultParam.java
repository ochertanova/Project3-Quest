package ru.javarush.quest.enums;

public enum ResultParam {
    GREATE_RESULT(0, "Отличный результат"),
    MIDDLE_RESULT(1, "Средний результат"),
    BAD_RESULT(2, "Плохой результат");

    private String param;
    private int id;

    ResultParam(int id, String param) {
        this.id = id;
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public int getId() {
        return id;
    }
}
