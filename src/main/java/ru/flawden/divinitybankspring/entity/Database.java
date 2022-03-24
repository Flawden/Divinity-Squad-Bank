package ru.flawden.divinitybankspring.entity;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Database implements Serializable {

    private static final String path = "users.dat";
    private static final String pathCard = "debit_card_list.dat";
    private static Database instance;
    public List<User> users = new ArrayList<>();
    public Set<String> cardNumbers = new HashSet<>();
    private User authUser; //Что он тут забыл?

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

    public void serializeDatabase() {

        serializeUsers(users);
        serializeCards(cardNumbers);

    }

    public void deserializeDatabase() {

        deserializeUsers();
        deserializeCards();
    }

    private void serializeUsers(List<User> users) {
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path))) {
            os.writeObject(users);
            System.out.println("Serialize succes");
        } catch (IOException e) {
            System.out.println("Error."); // To replace
        }
    }

    private void serializeCards(Set<String> cardNumbers) {
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(pathCard))) {
            os.writeObject(cardNumbers);
            System.out.println("Serialize succes");
        } catch (IOException e) {
            System.out.println("Error."); // To replace
        }
    }

    private void deserializeUsers() {
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

    private void deserializeCards() {
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(pathCard))) {
            this.cardNumbers = (Set<String>) os.readObject();
        } catch (FileNotFoundException e) {
            this.cardNumbers = new HashSet<String>();
            serializeCards(cardNumbers);
        } catch (IOException e) {
            System.out.println("Access error");
        } catch (Exception e) {
            System.out.println("Server error");
        }

    }

}
