package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UserUtil {
    public static final List<User> USERS = Arrays.asList(
            new User("Admin", "admin@gmail.com", "123", Role.ROLE_ADMIN, Role.ROLE_USER),
            new User("User", "user@gmail.com", "123", Role.ROLE_USER, Role.ROLE_ADMIN)
    );
}
