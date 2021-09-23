package com.pj.offer.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.pj.offer.advice.exception.OfferException;
import com.pj.offer.domain.Offer;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OfferValidation.class})
@ExtendWith(MockitoExtension.class)
class OfferValidationTest {

    @InjectMocks
    private OfferValidation offerValidation;

    @Test
    void validateDate_WhenFimIsEqualToInicio_ExpectedSuccess() {
        var offer = ScenarioFactory.fimIsEqualToInicio();
        assertThrows(OfferException.class, () -> this.offerValidation.validateDate(offer));
    }

    @Test
    void validateDate_WhenFimIsBeforeToInicio_ExpectedSuccess() {
        var offer = ScenarioFactory.fimIsBeforeToInicio();
        assertThrows(OfferException.class, () -> this.offerValidation.validateDate(offer));
    }

    @Test
    void validateDate_WhenFimIsBeforeToNow_ExpectedSuccess() {
        var offer = ScenarioFactory.fimIsBeforeToNow();
        assertThrows(OfferException.class, () -> this.offerValidation.validateDate(offer));
    }
    @Test
    void validateDate_WhenInicioIsBeforeToNow_ExpectedSuccess() {
        var offer = ScenarioFactory.inicioIsBeforeToNow();
        assertThrows(OfferException.class, () -> this.offerValidation.validateDate(offer));
    }

    @Test
    void validateDate_WhenSendValidDateRange_ExpectedSuccess() {
        var offer = ScenarioFactory.validDateRange();
        offerValidation.validateDate(offer);
    }


}

