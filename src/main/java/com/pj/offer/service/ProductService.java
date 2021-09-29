package com.pj.offer.service;

import com.pj.offer.domain.model.Product;
import com.pj.offer.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void deleteProductByIdProduct(Long idProduct) {
        log.info("Such Product will be deleted: {}!", idProduct);
        log.warn("If Id {} does not exist, then a exception will be thrown!", idProduct);
        productRepository.deleteById(idProduct);
    }

    public void findProductByIdProduct(Long idProduct) {
        log.info("Id {} will be search for!", idProduct);
        log.warn("If {} does not exist, then a exception will be thrown!", idProduct);
        Optional<Product> product = productRepository.getProductByIdProduct(idProduct);
        log.info("Printing offerDto value: {}!" + product);
    }
}
