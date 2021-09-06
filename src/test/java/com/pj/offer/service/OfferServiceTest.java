package com.pj.offer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pj.offer.config.advice.exception.OfferException;
import com.pj.offer.config.modelmapper.ModelMapperConfig;
import com.pj.offer.domain.dto.DeleteOfferDto;
import com.pj.offer.domain.Offer;
import com.pj.offer.domain.dto.OfferDto;
import com.pj.offer.domain.form.OfferForm;
import com.pj.offer.repository.OfferRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;



@ContextConfiguration(classes = {OfferService.class, ModelMapper.class, ModelMapperConfig.class})
@ExtendWith(MockitoExtension.class)
class OfferServiceTest {

    @MockBean
    private ModelMapperConfig modelMapperConfig;

    @InjectMocks
    private OfferService offerService;
    @Mock
    private OfferRepository offerRepository;
    @Mock
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OfferForm offerFORM;
    @Mock
    private Offer offer;

    @Test
    void testFindAll() {
        when(this.offerRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.offerService.findAll(null).isEmpty());
        verify(this.offerRepository, times(1)).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    public void testDeleteOffer() {
        Offer offer = new Offer();
        offer.setIdProduct(1L);
        offer.setFim(LocalDate.ofEpochDay(1L));
        offer.setId(1L);
        offer.setInicio(LocalDate.ofEpochDay(1L));
        offer.setDesconto(BigDecimal.valueOf(1L));
        offer.setDescricao("Descricao");
        Optional<Offer> ofResult = Optional.<Offer>of(offer);
        doNothing().when(this.offerRepository).deleteById((Long) any());
        when(this.offerRepository.findById((Long) any())).thenReturn(ofResult);
        this.offerService.deleteOffer(1L);
        verify(this.offerRepository).deleteById((Long) any());
        verify(this.offerRepository).findById((Long) any());
    }

    @Test
    void testGetById() {
        when(this.offerRepository.findById((Long) any())).thenReturn(Optional.<Offer>empty());
        assertThrows(RuntimeException.class, () -> this.offerService.getById(1L));
        verify(this.offerRepository, times(1)).findById((Long) any());
    }


    @Test
    void testDeleteOfferByDeleteOfferDTO() {
        doNothing().when(this.offerRepository).deleteOfferByProduct((Long) any());
        this.offerService.deleteOfferByDeleteOfferDTO(new DeleteOfferDto());
        verify(this.offerRepository, times(1)).deleteOfferByProduct((Long) any());
    }

    @Test
    public void save() {
        when(this.offerRepository.save((Offer) any())).thenReturn(offer);
        this.offerService.save(offerFORM);
        verify(this.offerRepository, times(1)).save((Offer) any());
    }



}