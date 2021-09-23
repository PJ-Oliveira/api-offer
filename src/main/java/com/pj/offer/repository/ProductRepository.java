package com.pj.offer.repository;

import com.pj.offer.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> getProductByIdProduct(Long idProduct);
}
