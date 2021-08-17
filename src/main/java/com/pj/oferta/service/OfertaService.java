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

    /*public OfertaDTO update(Long id, OfertaFORM ofertaFORM){
        Oferta oferta = OR.getById(id);
        oferta.setId_Produto(ofertaFORM.getId_Produto());
        oferta.setDescricao(ofertaFORM.getDescricao());
        oferta.setInicio(ofertaFORM.getInicio());
        oferta.setFim(ofertaFORM.getFim());
        oferta.setDesconto(ofertaFORM.getDesconto());
        oferta.setStatus(ofertaFORM.getStatus());
        this.OR.save(oferta);
        return new OfertaDTO();
    }*/

    /*public Oferta update(Long id, OfertaFORM ofertaFORM){
        Oferta oferta = OR.findOfertaById(id).orElseThrow();
        //oferta.setId(ofertaFORM.getId());
        oferta.setDesconto(ofertaFORM.getDesconto());
        oferta.setStatus(ofertaFORM.getStatus());
        oferta.setFim(ofertaFORM.getFim());
        oferta.setInicio(ofertaFORM.getInicio());
        oferta.setDescricao(ofertaFORM.getDescricao());
        oferta.setId_Produto(ofertaFORM.getId_Produto());
        return OR.save(oferta);

    }*/




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
