package ru.javarush.quest.init;

import ru.javarush.quest.features.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private final Map<String, User> users = new HashMap<>();

    public void saveUser(User user) {
        users.put(user.getName(), user);
    }

    public User getUser(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым. \nПожалуйста, введите корректное имя пользователя");
        }
        return users.get(name);
    }

    public boolean isExist(String name) {
        return users.containsKey(name);
    }


}
