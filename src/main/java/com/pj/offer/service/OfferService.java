package com.pj.offer.service;

import com.pj.offer.configuration.rabbit.cancelarofertadto.DeleteOfferDTO;
import com.pj.offer.domain.Offer;
import com.pj.offer.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private final OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offer addOferta(Offer offer){
        return offerRepository.save(offer);
    }

    public List<Offer> findAllOferta(){
        return offerRepository.findAll();
    }

    public Offer updateOferta(Offer offer){
        return offerRepository.save(offer);
    }

    public void deleteOffer(Long id){
        offerRepository.deleteOfertaById(id);
    }

    public Optional<Offer> findOfferById(Long id){
        return offerRepository.findOfferById(id);
    }

    public Offer getById(Long id){
        return offerRepository.getById(id);
    }

    @Transactional
    public void deleteOfferByDeleteOfferDTO(DeleteOfferDTO deleteOfferDTO){
        offerRepository.deleteOfferByProduct(deleteOfferDTO.getId_Product());
    }
}
