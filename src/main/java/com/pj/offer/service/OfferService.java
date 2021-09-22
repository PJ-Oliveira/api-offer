package com.pj.offer.service;

import com.pj.offer.advice.exception.NotFoundException;
import com.pj.offer.domain.dto.DeleteOfferDto;
import com.pj.offer.domain.Offer;
import com.pj.offer.domain.dto.OfferDto;
import com.pj.offer.domain.form.OfferForm;
import com.pj.offer.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OfferValidation offerValidation;

    public OfferDto save(OfferForm offerFORM) {
        Offer offer = modelMapper.map(offerFORM, Offer.class);
        offerValidation.validateDate(offer);
        offerRepository.save(offer);
        return modelMapper.map(offer, OfferDto.class);
    }

    public List<OfferDto> findAll(Pageable pageable){
        Page<Offer> offerPage = offerRepository.findAll(pageable);
        List<Offer> offer = offerPage.getContent();
        return offer.stream().map(x -> modelMapper.map(x, OfferDto.class)).collect(Collectors.toList());
    }

    public void deleteOffer(Long id){
        Offer offer = offerRepository.getById(id);
        this.offerRepository.deleteById(id);
    }

    public Optional<Offer> getOptionalOfferByValidId(Long id) {
        return offerRepository.getOnlyUnexpiredOfferById(id);
    }

    public OfferDto findOfferByValidId(Long id) {
        Offer offer = offerRepository.getOnlyUnexpiredOfferById(id).orElseThrow(()-> new NotFoundException("Id " + id + " Not Found"));
        return modelMapper.map(offer, OfferDto.class);
    }


    public void offerDeActivation(DeleteOfferDto deleteOfferDTO){
        offerRepository.toggleOfferActivation(deleteOfferDTO.getIdProduct(), false);
    }

    public void offerActivation(Long idProduct, Boolean status){
        offerRepository.toggleOfferActivation(idProduct, status);
    }



}
