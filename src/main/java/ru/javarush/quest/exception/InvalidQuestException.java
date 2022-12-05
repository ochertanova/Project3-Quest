package ru.javarush.quest.exception;

public class InvalidQuestException extends RuntimeException {

    public InvalidQuestException() {
        super();
    }

    public InvalidQuestException(String message) {
        super(message);
    }

    public InvalidQuestException(String message, Throwable cause) {
        super(message, cause);
    }
}
