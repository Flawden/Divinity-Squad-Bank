package ru.flawden.divinitybankspring.util;

import org.springframework.security.core.userdetails.User;
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
        UserDAO userDAO;
        Random rnd = new Random();
        int part = 0;
        String num = "";

        while (true) {
            for (int i = 0; i < 4; i++) {
                part = rnd.nextInt(1111,9999);
                num += part + " ";
            }
            break;
        }

        return num;

    }

    public DebitCardEntity doDebitCard(UserDAO userDAO) {
        String num = null;
        while (true) {
            num = createCardNumber();
            if (!userDAO.checkCardNumExist(num)) {
                break;
            }
        }
        int cvv = createCVV();
        return new DebitCardEntity(num, cvv);
    }
}