package com.pj.offer.service;

import com.pj.offer.advice.exception.NotFoundException;
import com.pj.offer.domain.Product;
import com.pj.offer.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void deleteProductByIdProduct(Long idProduct) {
        productRepository.deleteById(idProduct);
    }

    public void findProductByIdProduct(Long idProduct) {
        Product product = productRepository.getProductByIdProduct(idProduct).
                orElseThrow(()-> new NotFoundException("Id " + idProduct + " Not Found"));

    }
}
