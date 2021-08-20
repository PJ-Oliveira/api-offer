package com.pj.oferta.repository;

import com.pj.oferta.domain.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springfox.documentation.spring.web.json.Json;

import java.util.List;
import java.util.Optional;


public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    void deleteOfertaById(Long id);

    @Modifying
    @Query("delete from Oferta x where x.Product=:product")
    void deleteOfertaByProduct(@Param("product") Long product);

    Optional<Oferta> findOfertaById(Long id);

    Oferta getById(Long id);

}
