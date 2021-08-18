package com.pj.oferta.service;

import com.pj.oferta.domain.Oferta;
import com.pj.oferta.domain.dto.OfertaDTO;
import com.pj.oferta.repository.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfertaService {

    private final OfertaRepository OR;

    @Autowired
    public OfertaService(OfertaRepository or) {
        this.OR = or;
    }

    public Oferta addOferta(Oferta oferta){
        return OR.save(oferta);
    }


    public List<Oferta> findAllOferta(){
        return OR.findAll();
    }

    public Oferta updateOferta(Oferta oferta){
        return OR.save(oferta);
    }

    public void deleteOferta(Long id){
        OR.deleteOfertaById(id);
    }

    public Optional<Oferta> findOfertaById(Long id){
        return OR.findOfertaById(id);
    }

    public Oferta getById(Long id){
        return OR.getById(id);
    }
}
