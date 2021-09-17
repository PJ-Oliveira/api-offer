package com.pj.offer.repository;

import com.pj.offer.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Modifying
    @Query("update Offer x SET x.active=:status where x.idProduct=:idProduct")
    void toggleOfferActivation(@Param("idProduct") Long idProduct, @Param("status") Boolean status);

    @Query("select offer from Offer offer where offer.fim >= CURRENT_TIMESTAMP and offer.active = true and offer.id=:id")
    Optional<Offer> getOnlyUnexpiredOfferById(@Param("id") Long id);

    @Modifying
    @Query("delete from Offer x where x.idProduct=:idProduct")
    void deleteOfferByProduct(@Param("idProduct") Long idProduct);
}
