package com.pj.offer.service;

import com.pj.offer.advice.exception.NotFoundException;
import com.pj.offer.domain.model.Offer;
import com.pj.offer.domain.dto.OfferDto;
import com.pj.offer.domain.form.OfferForm;
import com.pj.offer.repository.OfferRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OfferValidation offerValidation;

    public OfferDto save(OfferForm offerFORM) {
        Offer offer = modelMapper.map(offerFORM, Offer.class);
        log.info("Will be saved: {} ", offer);
        offerValidation.validateDate(offer);
        log.info("{} will be validate!", offer.getId());
        log.warn("If data integrity is affected, then an exception will be thrown.");
        offerRepository.save(offer);
        log.info("{} successfully saved!}", offer.getId());
        return modelMapper.map(offer, OfferDto.class);
    }

    public List<OfferDto> findAll(Pageable pageable){
        Page<Offer> offerPage = offerRepository.findAll(pageable);
        log.info("All offer will be listed in a pageable way");
        log.warn("If offers does not exist at all, then a exception will be thrown");
        List<Offer> offer = offerPage.getContent();
        log.info("{}", offer);
        return offer.stream().map(x -> modelMapper.map(x, OfferDto.class)).collect(Collectors.toList());
    }

    public void deleteOffer(Long id){
        Offer offer = offerRepository.getById(id);
        log.info("Such offer will be deleted: {}!", id);
        log.warn("If {} does not exist, then a exception will be thrown!", id);
        this.offerRepository.deleteById(id);
        log.info("Offer {} successfully deleted", id);
    }

    public Optional<Offer> getOptionalOfferByValidId(Long id) {
        log.info("Id {} will be search for!", id);
        log.warn("If {} does not exist, then a exception will be thrown!", id);
        return offerRepository.getOnlyUnexpiredOfferById(id);
    }

    public OfferDto findOfferByValidId(Long id) {
        log.info("Id {} will be search for!", id);
        log.warn("If {} does not exist, will throw exception!", id);
        Offer offer = offerRepository.getOnlyUnexpiredOfferById(id).orElseThrow(()-> new NotFoundException("Id " + id + " Not Found"));
        log.info("Printing offerDto value: {}!" + offer);
        return modelMapper.map(offer, OfferDto.class);
    }

    public void offerActivation(Long id, Boolean status){
        offerRepository.toggleOfferActivation(id, status);
    }

}
