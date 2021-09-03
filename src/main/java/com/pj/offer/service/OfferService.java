package com.pj.offer.service;

import com.pj.offer.config.advice.exception.OfferException;
import com.pj.offer.config.rabbitmq.cancelarofertadto.DeleteOfferDto;
import com.pj.offer.config.modelmapper.ModelMapperConfig;
import com.pj.offer.domain.Offer;
import com.pj.offer.domain.dto.OfferDto;
import com.pj.offer.domain.form.OfferForm;
import com.pj.offer.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ModelMapperConfig modelMapperConfig;
    @Autowired
    private ModelMapper modelMapper;


    public OfferDto save(OfferForm offerFORM){
        Offer offer = modelMapper.map(offerFORM, Offer.class);
        offerRepository.save(offer);
        return modelMapper.map(offer, OfferDto.class);
    }


    public List<OfferDto> findAll(Pageable pageable){
        Page<Offer> offerPage = offerRepository.findAll(pageable);
        List<Offer> offer = offerPage.getContent();
        return offer.stream()
                .map(x -> modelMapper.map(x, OfferDto.class))
                .collect(Collectors.toList());
    }

    public OfferDto updateOffer(Long id, OfferForm offerFORM){
        Offer offer1 = offerRepository.findOfferById(id)
                .orElseThrow(()-> new OfferException("Resource with id: " + id + "not found"));
        Offer offer = modelMapper.map(offerFORM, Offer.class);
        this.offerRepository.save(offer);
        return modelMapper.map(offer, OfferDto.class);
    }

    public void deleteOffer(Long id){
        Offer offer = offerRepository.findById(id)
                .orElseThrow(()-> new OfferException("Resource with id: " + id + "not found"));
        this.offerRepository.deleteById(id);
    }

    public Optional<Offer> findOfferById(Long id){
        return offerRepository.findOfferById(id);
    }


    public OfferDto getById(Long id){
        Offer offer = offerRepository.findById(id)
                .orElseThrow(()-> new OfferException("Resource with id: " + id + "not found"));
        if(offer.getFim().isBefore(LocalDate.now())) {

        }
        return modelMapper.map(offer, OfferDto.class);
    }


    public void deleteOfferByDeleteOfferDTO(DeleteOfferDto deleteOfferDTO){
        offerRepository.deleteOfferByProduct(deleteOfferDTO.getIdProduct());
    }



}
