package com.pj.offer.service;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.pj.offer.advice.exception.NotFoundException;
import com.pj.offer.config.modelmapper.ModelMapperConfig;
import com.pj.offer.domain.dto.DeleteOfferDto;
import com.pj.offer.domain.Offer;
import com.pj.offer.domain.form.OfferForm;
import com.pj.offer.repository.OfferRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;



@ContextConfiguration(classes = {OfferService.class, ModelMapper.class, ModelMapperConfig.class})
@ExtendWith(MockitoExtension.class)
class OfferServiceTest {

    @InjectMocks
    private OfferService offerService;
    @Mock
    private OfferRepository offerRepository;
    @Mock
    private ModelMapper modelMapper;

    @Test
    void save_WhenSaveOffer_ExpectedSuccess() {
        var offer = TestTemplet.newOffer();
        OfferForm offerForm = new OfferForm();
        when(this.offerRepository.save((any()))).thenReturn(offer);
        this.offerService.save(offerForm);
        verify(this.offerRepository, times(1)).save(any());
    }

    @Test
    void testFindAll_WhenFindEachOffer_ExpectedSuccess() {
        when(this.offerRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.offerService.findAll(null).isEmpty());
        verify(this.offerRepository, times(1)).findAll((org.springframework.data.domain.Pageable) any());
    }


    @Test
    void testFindAll_WhenFindEachOfferWithPageable_ExpectedSuccess() {
        var pageable = TestTemplet.newPageable();
        var page = TestTemplet.newPage();
        when(offerRepository.findAll(pageable)).thenReturn(page);
        offerService.findAll(pageable);
        verify(this.offerRepository, times(1)).findAll(pageable);
    }

    @Test
    void deleteOffer_WhenDeleteOfferById_ExpectedSuccess() {
        var offer = TestTemplet.newOffer();
        doNothing().when(this.offerRepository).deleteById((Long) any());
        when(this.offerRepository.getById((Long) any())).thenReturn(offer);
        this.offerService.deleteOffer(1l);
        verify(this.offerRepository).deleteById((Long) any());
        verify(this.offerRepository).getById((Long) any());
    }


    @Test
    void getOptionalOfferByValidId_WhenFindOfferUnexpired_ExpectedSuccess() {
        var offer = TestTemplet.newOffer();
        var optionalOffer = TestTemplet.newOptionalOffer();
        when(this.offerRepository.getOnlyUnexpiredOfferById((Long) any())).thenReturn(optionalOffer);
        Optional<Offer> actualOptionalOfferByValidId = this.offerService.getOptionalOfferByValidId(1L);
        assertSame(optionalOffer, actualOptionalOfferByValidId);
        assertTrue(actualOptionalOfferByValidId.isPresent());
        assertEquals("1", actualOptionalOfferByValidId.get().getDesconto().toString());
        verify(this.offerRepository).getOnlyUnexpiredOfferById((Long) any());
    }

    @Test
    void findOfferByValidId_WhenFindOfferByValidId_ExpectedSuccess() {
        var offer = TestTemplet.newOffer();
        var optionalOffer = TestTemplet.newOptionalOffer();
        when(this.offerRepository.getOnlyUnexpiredOfferById((Long) any())).thenReturn(optionalOffer);
        when(this.modelMapper.map((Object) any(), (Class<Object>) any()))
                .thenThrow(new NotFoundException("Id " + offer.getId() + " Not Found"));
        assertThrows(NotFoundException.class, () -> this.offerService.findOfferByValidId(1l));
        verify(this.offerRepository).getOnlyUnexpiredOfferById((Long) any());
        verify(this.modelMapper).map((Object) any(), (Class<Object>) any());
    }

    @Test
    void getOfferByValidId_WhenFindOfferUnexpiredAndThrowingException_ExpectedSuccess1() {
        var offer = TestTemplet.emptyOffer();
        when(offerRepository.getOnlyUnexpiredOfferById(eq(offer.getId()))).thenReturn(Optional.empty());
        assertThatThrownBy(()-> offerService.findOfferByValidId(any()))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Id " + offer.getId() + " Not Found");
        verify(this.offerRepository, times(1)).getOnlyUnexpiredOfferById(any());
    }

    @Test
    void getOfferByValidId_WhenFindOfferByValidId_ExpectedSuccess() {
        var optionalOffer = TestTemplet.newOptionalOffer();
        when(this.offerRepository.getOnlyUnexpiredOfferById((Long) any())).thenReturn(optionalOffer);
        when(this.modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn(null);
        assertNull(this.offerService.findOfferByValidId(1L));
        verify(this.modelMapper).map((Object) any(), (Class<Object>) any());
    }

    @Test
    void offerDeActivation_WhenDeActiveOffer_ExpectedSuccess() {
        doNothing().when(this.offerRepository).toggleOfferActivation((Long) any(), (Boolean) any());
        this.offerService.offerDeActivation(new DeleteOfferDto());
        verify(this.offerRepository).toggleOfferActivation((Long) any(), (Boolean) any());
    }

    @Test
    void offerDeActivation_WhenDeActiveOffer_ExpectedSuccess2() {
        doNothing().when(this.offerRepository).toggleOfferActivation((Long) any(), (Boolean) any());
        DeleteOfferDto deleteOfferDto = mock(DeleteOfferDto.class);
        when(deleteOfferDto.getIdProduct()).thenReturn(1L);
        this.offerService.offerDeActivation(deleteOfferDto);
        verify(this.offerRepository).toggleOfferActivation((Long) any(), (Boolean) any());
        verify(deleteOfferDto).getIdProduct();
    }

    @Test
    void offerActivation_WhenActiveOffer_ExpectedSuccess() {
        doNothing().when(this.offerRepository).toggleOfferActivation((Long) any(), (Boolean) any());
        this.offerService.offerActivation(1L, true);
        verify(this.offerRepository).toggleOfferActivation((Long) any(), (Boolean) any());
    }

}