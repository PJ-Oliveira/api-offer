package com.pj.offer.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.pj.offer.config.rabbitmq.cancelarofertadto.DeleteOfferDTO;
import com.pj.offer.domain.Offer;
import com.pj.offer.domain.form.OfferFORM;
import com.pj.offer.enums.OfertaStatus;
import com.pj.offer.repository.OfferRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;


@ExtendWith(MockitoExtension.class)
class OfferServiceTest{

    @InjectMocks
    private OfferService offerService;
    @Mock
    private OfferRepository offerRepository;
    @Mock
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OfferFORM offerFORM;

    @Test
    void testSave() {
        Offer offer = new Offer();
        Optional<Offer> ofResult = Optional.<Offer>of(offer);
        Offer offerMapper = modelMapper.map(offerFORM, Offer.class);
        when(this.offerRepository.save((Offer) any())).thenReturn(offerMapper);
        when(this.offerRepository.findOfferById((Long) any())).thenReturn(ofResult);
        this.offerService.save(offerFORM);
        this.offerService.findOfferById(1L);
        verify(this.offerRepository, times(1)).findOfferById((Long) any());
        verify(this.offerRepository, times(1)).save((Offer) any());
    }

    @Test
    void testFindAll() {
        when(this.offerRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.offerService.findAll(null).isEmpty());
        verify(this.offerRepository, times(1)).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testUpdateOffer() {
        Offer offer = new Offer();
        Offer offerMapper = modelMapper.map(offerFORM, Offer.class);
        Optional<Offer> ofResult = Optional.<Offer>of(offer);
        when(this.offerRepository.save((Offer) any())).thenReturn(offerMapper);
        when(this.offerRepository.findOfferById((Long) any())).thenReturn(ofResult);
        this.offerService.updateOffer(1L, offerFORM);
        verify(this.offerRepository, times(1)).findOfferById((Long) any());
        verify(this.offerRepository, times(1)).save((Offer) any());
    }

    @Test
    void testDeleteOffer() {
        Offer offer = new Offer();
        Offer offerMapper = modelMapper.map(offerFORM, Offer.class);
        Optional<Offer> ofResult = Optional.<Offer>of(offer);
        doNothing().when(this.offerRepository).delete((Offer) any());
        when(this.offerRepository.findById((Long) any())).thenReturn(ofResult);
        this.offerService.deleteOffer(1L);
        verify(this.offerRepository, times(1)).delete((Offer) any());
        verify(this.offerRepository, times(1)).findById((Long) any());
    }

    @Test
    void testFindOfferById() {
        Offer offer = new Offer();
        Offer offerMapper = modelMapper.map(offerFORM, Offer.class);
        Optional<Offer> ofResult = Optional.<Offer>of(offer);
        when(this.offerRepository.findOfferById((Long) any())).thenReturn(ofResult);
        Optional<Offer> actualFindOfferByIdResult = this.offerService.findOfferById(1L);
        verify(this.offerRepository).findOfferById((Long) any());
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
        this.offerService.deleteOfferByDeleteOfferDTO(new DeleteOfferDTO());
        verify(this.offerRepository, times(1)).deleteOfferByProduct((Long) any());
    }
}