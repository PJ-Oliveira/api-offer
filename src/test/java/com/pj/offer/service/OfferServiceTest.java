package com.pj.offer.service;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
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
    void testFindAll_WhenFindEachOffer_ExpectedSuccess() {
        when(this.offerRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.offerService.findAll(null).isEmpty());
        verify(this.offerRepository, times(1)).findAll((org.springframework.data.domain.Pageable) any());}


    @Test
    void testFindAll_WhenFindEachOfferWithPageable_ExpectedSuccess() {
        var pageable = TestTemplet.newPageable();
        var page = TestTemplet.newPage();
        when(offerRepository.findAll(pageable)).thenReturn(page);
        offerService.findAll(pageable);
        verify(this.offerRepository, times(1)).findAll(pageable);}

    @Test
    public void deleteOffer_WhenDeleteOfferById_ExpectedSuccess() {
        Offer offer = new Offer();
        doNothing().when(this.offerRepository).deleteById((Long) any());
        when(this.offerRepository.getById((Long) any())).thenReturn(offer);
        this.offerService.deleteOffer(1L);
        verify(this.offerRepository).deleteById((Long) any());
        verify(this.offerRepository).getById((Long) any());}

    @Test
    public void getOfferByValidId_WhenFindOfferUnexpired_ExpectedSuccess() {
        Offer offer = new Offer();
        Optional<Offer> ofResult = Optional.<Offer>of(offer);
        when(this.offerRepository.getOnlyUnexpiredOfferById((Long) any())).thenReturn(ofResult);
        this.offerService.getOfferByValidId((Long) any());
        verify(this.offerRepository).getOnlyUnexpiredOfferById((Long) any());}

    @Test
    public void getOfferByValidId_WhenFindOfferUnexpiredAndThrowingException_ExpectedSuccess() {
        Offer offer = new Offer();
        when(offerRepository.getOnlyUnexpiredOfferById(eq(offer.getId()))).thenReturn(Optional.empty());
        assertThatThrownBy(()-> offerService.getOfferByValidId(any()))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Id " + offer.getId() + " Not Found");
        verify(this.offerRepository, times(1)).getOnlyUnexpiredOfferById(any());}


    @Test
    void listenToDeleteOfferByIdProduct_WhenDeleteOfferByIdProduct_ExpectedSuccess() {
        doNothing().when(this.offerRepository).deleteOfferByProduct((Long) any());
        this.offerService.listenDeleteOfferByIdProduct(new DeleteOfferDto());
        verify(this.offerRepository, times(1)).deleteOfferByProduct((Long) any());}

    @Test
    void save_WhenSaveOffer_ExpectedSuccess() {
        Offer offer = new Offer();
        OfferForm offerForm = new OfferForm();
        when(this.offerRepository.save((any()))).thenReturn(offer);
        this.offerService.save(offerForm);
        verify(this.offerRepository, times(1)).save(any());}

}