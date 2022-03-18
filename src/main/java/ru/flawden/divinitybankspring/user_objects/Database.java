package ru.flawden.divinitybankspring.user_objects;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {

    private static Database instance;
    private final String path = "users.dat";
    public List<User> users = new ArrayList<>();

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
            System.out.println("Serialize succes");
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }

    public void deserializeUsers() {
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(path))) {
            this.users = (ArrayList<User>) os.readObject();
        } catch (FileNotFoundException e) {
            this.users = new ArrayList<User>();
            serializeUsers(users);
        } catch (IOException e) {
            System.out.println("Access error");
        } catch (Exception e) {
            System.out.println("Server error");
        }

    }

}
