package com.pj.offer.repository;

import com.pj.offer.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Modifying
    @Query("delete from Offer x where x.id_Product=:id_Product")
    void deleteOfferByProduct(@Param("id_Product") Long id_Product);

    void deleteOfertaById(Long id);

    Optional<Offer> findOfferById(Long id);

    Offer getById(Long id);
}