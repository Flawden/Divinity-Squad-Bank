package ru.flawden.divinitybankspring.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.flawden.divinitybankspring.entity.DebitCard;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DebitCardMapper implements RowMapper<DebitCard> {
    @Override
    public DebitCard mapRow(ResultSet rs, int rowNum) throws SQLException {
        DebitCard debitCard = new DebitCard();

                debitCard.setCreatedDate(rs.getDate("createddate"));
                debitCard.setBalance(rs.getDouble("balance"));
                debitCard.setCardNumber(rs.getString("cardnumber"));
                debitCard.setExpirationDate(rs.getDate("expirationdate"));
                debitCard.setCvv(rs.getInt("cvv"));

                return debitCard;
    }
}
