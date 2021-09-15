package com.pj.offer.repository;

import com.pj.offer.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Modifying
    @Query("delete from Offer x where x.idProduct=:idProduct")
    void deleteOfferByProduct(@Param("idProduct") Long idProduct);

    @Query(value = "select * from Offer x where x.fim >= CURRENT_TIMESTAMP and x.id_offer=:id", nativeQuery = true)
    Optional<Offer> getOnlyUnexpiredOfferById(@Param("id") Long id);

}
