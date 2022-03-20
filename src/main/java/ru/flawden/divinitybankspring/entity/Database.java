package ru.flawden.divinitybankspring.entity;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Database implements Serializable {

    private static Database instance;
    private final String path = "users.dat";
    public List<User> users = new ArrayList<>();
    public HashSet<String> cardNumbers = new HashSet<String>();
    private transient User authUser;

    public User getAuthUser() {
        return authUser;
    }

    public void setAuthUser(User authUser) {
        this.authUser = authUser;
    }

    private Database() {

    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void serializeUsers(List<User> users) {
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path))) {
            os.writeObject(users);
            os.writeObject(cardNumbers);
            System.out.println("Serialize succes");
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }

    public void deserializeUsers() {
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(path))) {
            this.users = (ArrayList<User>) os.readObject();
            this.cardNumbers = (HashSet<String>) os.readObject();
        } catch (FileNotFoundException e) {
            this.users = new ArrayList<User>();
            this.cardNumbers = new HashSet<String>();
            serializeUsers(users);
        } catch (IOException e) {
            System.out.println("Access error");
        } catch (Exception e) {
            System.out.println("Server error");
        }

    }

}
