package com.pj.oferta.repository;

import com.pj.oferta.domain.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OfertaRepository extends JpaRepository<Oferta, Long> {


    void deleteOfertaById(Long id);




    Optional<Oferta> findOfertaById(Long id);
}
