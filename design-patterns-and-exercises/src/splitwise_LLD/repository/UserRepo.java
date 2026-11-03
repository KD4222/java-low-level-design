package repository;

import models.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepo {
    private final Map<String, User> users = new HashMap<>();

    public synchronized void addUser(User user) {
        users.put(user.getId(), user);
    }

    public synchronized User getUserById(String id) {
        return users.get(id);
    }
}
