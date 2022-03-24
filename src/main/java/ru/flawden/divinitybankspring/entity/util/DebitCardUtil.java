package ru.flawden.divinitybankspring.entity.util;

import ru.flawden.divinitybankspring.entity.Database;
import ru.flawden.divinitybankspring.entity.DebitCard;

import javax.xml.crypto.Data;
import java.util.Random;


public class DebitCardUtil {
    public int createCVV() {
        Random rnd = new Random();
        int cvv = rnd.nextInt(111,999);

        return cvv;
    }

    public String createCardNumber() {
        Database database = Database.getInstance();
        Random rnd = new Random();
        int part = 0;
        String num = "";

        while (true) {
            for (int i = 0; i < 4; i++) {
                part = rnd.nextInt(1111,9999);
                num += part + " ";
            }
            if (!database.cardNumbers.contains(num)) {
                database.cardNumbers.add(num);
                break;
            }
        }

        return num;

    }


    public void doDebitCard() {
        Database database = Database.getInstance();
        String num = createCardNumber();
        int cvv = createCVV();
        database.getAuthUser().getDebitCardList().add(new DebitCard(num, cvv));
    }
}
