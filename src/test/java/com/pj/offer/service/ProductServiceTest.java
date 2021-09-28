package com.pj.offer.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pj.offer.domain.model.Product;
import com.pj.offer.repository.ProductRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    void deleteProduct_WhenDeleteProductByIdProduct_ExpectedSuccess() {
        var product = ScenarioFactory.newProduct();
        doNothing().when(this.productRepository).deleteById((Long) product.getIdProduct());
        this.productService.deleteProductByIdProduct(17L);
        verify(this.productRepository).deleteById((Long) product.getIdProduct());
    }

    @Test
    void findProductByIdProduct_WhenFindProductByIdProduct_ExpectedSuccess() {
        var product = ScenarioFactory.newProduct();
        Optional<Product> ofResult = Optional.<Product>of(product);
        when(this.productRepository.getProductByIdProduct((Long) product.getIdProduct())).thenReturn(ofResult);
        this.productService.findProductByIdProduct(product.getIdProduct());
        verify(this.productRepository, atLeast(1)).getProductByIdProduct((Long) product.getIdProduct());
    }

}

