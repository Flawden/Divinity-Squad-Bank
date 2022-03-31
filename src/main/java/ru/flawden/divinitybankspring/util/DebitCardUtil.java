package ru.flawden.divinitybankspring.util;

import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.entity.DebitCard;

import java.util.Random;


public class DebitCardUtil {

    //В утилитарных классах не должно быть зависимостей.
    UserDAO userDAO;

    public DebitCardUtil(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public int createCVV() {
        Random rnd = new Random();
        int cvv = rnd.nextInt(111,999);

        return cvv;
    }

    public String createCardNumber() {
        Random rnd = new Random();
        int part = 0;
        String num = "";

        while (true) {
            for (int i = 0; i < 4; i++) {
                part = rnd.nextInt(1111,9999);
                num += part + " ";
            }
            if (!userDAO.cardNumbers.contains(num)) { //Проверять содержание в БД и если нету - добавить
                userDAO.cardNumbers.add(num);
                break;
            }
        }

        return num;

    }


    public DebitCard doDebitCard() {
        String num = createCardNumber();
        int cvv = createCVV();
        return new DebitCard(num, cvv);
    }
}

//Утилитарные классы хранят набор полезных методов. Не зависимости.
//Папку Util выкинуть в корень