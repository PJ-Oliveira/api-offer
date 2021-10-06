package com.pj.offer.config.batch;

import com.pj.offer.domain.model.Offer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OfferRowMapper implements RowMapper<Offer> {
    @Override
    public Offer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Offer offer = new Offer();
            offer.setId(resultSet.getLong("id_offer"));
            offer.setInicio(LocalDate.parse(resultSet.getString("inicio")));
            offer.setFim(LocalDate.parse(resultSet.getString("fim")));
            offer.setDescricao(resultSet.getString("descricao"));
            offer.setDesconto(resultSet.getBigDecimal("desconto"));
            offer.setActive(resultSet.getBoolean("active"));
            return offer;
    }
}
