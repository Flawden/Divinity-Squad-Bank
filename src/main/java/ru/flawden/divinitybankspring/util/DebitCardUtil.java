package ru.flawden.divinitybankspring.util;

import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.entity.DebitCardEntity;

import java.util.Random;


public class DebitCardUtil {

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
            break;
            //Написать логику по проверке номеров карт на уникальность
        }

        return num;

    }

    public DebitCardEntity doDebitCard(UserDAO userDAO) {
        String num = null;
        while (true) {
            num = createCardNumber();
            if (num != null) {
                break;
            }
        }
        int cvv = createCVV();
        return new DebitCardEntity(num, cvv);
    }
}