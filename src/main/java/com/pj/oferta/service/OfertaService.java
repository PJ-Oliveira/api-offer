package com.pj.oferta.service;

import com.pj.oferta.configuration.rabbit.cancelarofertadto.CancelarOfertaDTO;
import com.pj.oferta.domain.Oferta;
import com.pj.oferta.repository.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OfertaService {

    @Autowired
    private final OfertaRepository ofertaRepository;

    @Autowired
    public OfertaService(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    public Oferta addOferta(Oferta oferta){
        return ofertaRepository.save(oferta);
    }

    public List<Oferta> findAllOferta(){
        return ofertaRepository.findAll();
    }

    public Oferta updateOferta(Oferta oferta){
        return ofertaRepository.save(oferta);
    }

    public void deleteOferta(Long id){
        ofertaRepository.deleteOfertaById(id);
    }

    public Optional<Oferta> findOfertaById(Long id){
        return ofertaRepository.findOfertaById(id);
    }

    public Oferta getById(Long id){
        return ofertaRepository.getById(id);
    }

    @Transactional
    public void cancelaOfertaById_Product(CancelarOfertaDTO cancelarOfertaDTO){
        ofertaRepository.deleteOfertaByProduct(cancelarOfertaDTO.getProduct());
    }
}
