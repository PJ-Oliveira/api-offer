package com.pj.offer.service;

import com.pj.offer.config.rabbitmq.cancelarofertadto.DeleteOfferDTO;
import com.pj.offer.config.modelmapper.ModelMapperConfig;
import com.pj.offer.domain.Offer;
import com.pj.offer.domain.dto.OfferDTO;
import com.pj.offer.domain.form.OfferFORM;
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
    private ModelMapperConfig modelMapperConfig;
    @Autowired
    private ModelMapper modelMapper;

    public OfferDTO save(OfferFORM offerFORM){
        Offer offer = modelMapper.map(offerFORM, Offer.class);
        offerRepository.save(offer);
        return modelMapper.map(offer, OfferDTO.class);
    }

    public List<OfferDTO> findAll(Pageable pageable){
        Page<Offer> offerPage = offerRepository.findAll(pageable);
        List<Offer> offer = offerPage.getContent();
        return offer.stream()
                .map(x -> modelMapper.map(x, OfferDTO.class))
                .collect(Collectors.toList());
    }

    public OfferDTO updateOffer(Long id, OfferFORM offerFORM){
        Offer offer = offerRepository.findOfferById(id)
                .orElseThrow(()-> new RuntimeException());
        offer = modelMapper.map(offerFORM, Offer.class);
        this.offerRepository.save(offer);
        return modelMapper.map(offer, OfferDTO.class);
    }

    public void deleteOffer(Long id){
        Offer offer = offerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException());
        this.offerRepository.delete(offer);
    }

    public Optional<Offer> findOfferById(Long id){
        //Offer offer1 = modelMapper.map(id, Offer.class);
        return offerRepository.findOfferById(id);
    }

    public OfferDTO getById(Long id){
        Offer offer = offerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException());
        return modelMapper.map(offer, OfferDTO.class);
    }

    public void deleteOfferByDeleteOfferDTO(DeleteOfferDTO deleteOfferDTO){
        offerRepository.deleteOfferByProduct(deleteOfferDTO.getId_Product());

    }
}
