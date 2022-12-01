package ru.javarush.quest.init;

import ru.javarush.quest.features.User;

import java.util.HashMap;
import java.util.Map;


public class UserRepository {

    private volatile Map<String, User> users = new HashMap<>();

    public void saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user can`t be null");
        }
        validateName(user.getName());
        users.put(user.getName(), user);
    }

    public User getUser(String name) {
        validateName(name);
        if (!isExist(name)) {
            throw new IllegalArgumentException("User with name isn`t exist in repository");
        }
        return users.get(name);
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public synchronized boolean isExist(String name) {
        validateName(name);
        return users.containsKey(name);
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("name can`t be empty");
        }
    }
}
