package com.pj.offer.service;

import com.pj.offer.domain.Product;
import com.pj.offer.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void deleteProductByIdProduct(Long idProduct) {
         productRepository.deleteById(idProduct);
    }

    public void findProductByIdProduct(Long idProduct) {
        Optional<Product> product = productRepository.getProductByIdProduct(idProduct);
    }
}
