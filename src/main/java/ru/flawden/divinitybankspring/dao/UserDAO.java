package ru.flawden.divinitybankspring.dao;

import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.entity.User;

import java.util.*;

@Component
public class UserDAO {

    private static int PEOPLE_COUNT = 0;
    private List<User> users;
    public Set<String> cardNumbers;

    public User authUser;

    {
        users = new ArrayList<>();
        cardNumbers = new HashSet<>();

        users.add(new User(++PEOPLE_COUNT, "Daniil", "Topchii", "topchiidv1998@gmail.com", "password"));
        users.add(new User(++PEOPLE_COUNT, "Fefo", "Fefo", "Fefofefo@gmail.com", "password"));
        System.out.println(users);
    }

    public List<User> index() {
        System.out.println("Я в индексе ДАО");
        System.out.println(users);
        return users;
    }

    public User show(int id) {
        System.out.println("Мы в SHOW");
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(++PEOPLE_COUNT);
        users.add(user);
    }

    public void update(int it, User user) {

    }

}
