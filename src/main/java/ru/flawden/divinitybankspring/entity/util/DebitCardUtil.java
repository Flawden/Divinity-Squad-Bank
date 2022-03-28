package ru.flawden.divinitybankspring.entity.util;

import org.springframework.beans.factory.annotation.Autowired;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.entity.DebitCard;

import javax.xml.crypto.Data;
import java.util.Random;


public class DebitCardUtil {

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
            if (!userDAO.cardNumbers.contains(num)) {
                userDAO.cardNumbers.add(num);
                break;
            }
        }

        return num;

    }


    public void doDebitCard() {
        String num = createCardNumber();
        int cvv = createCVV();
        userDAO.authUser.getDebitCardList().add(new DebitCard(num, cvv));
    }
}
