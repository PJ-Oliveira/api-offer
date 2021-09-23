package com.pj.offer.consumer;

import com.pj.offer.advice.exception.NotFoundException;
import com.pj.offer.advice.exception.OfferException;
import com.pj.offer.service.ProductService;

import com.pj.offer.service.ScenarioFactory;
import com.pj.offer.consumer.DeleteProductConsumer;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ContextConfiguration(classes = {DeleteProductConsumer.class})
@ExtendWith(MockitoExtension.class)
class DeleteProductConsumerTest {

    @InjectMocks
    private DeleteProductConsumer deleteProductConsumer;

    @Mock
    private ProductService productServiceMock;

    @Test
    void testListener_WhenListenToMsProduct_ExpectedSuccess()
    {
        var product = ScenarioFactory.newProduct();
        doNothing().when(productServiceMock).deleteProductByIdProduct(product.getIdProduct());
        deleteProductConsumer.listener("{\"idProduct\":\" 17 \"}");
    }


    //TODO falta capturar o catch
    //@Test
//    void testListener_WhenListenToMsProductFail_ExpectedSuccess() throws Exception
//    {
//        var product = ScenarioFactory.newProduct();
//        doThrow(Exception.class).when(productServiceMock).deleteProductByIdProduct(product.getIdProduct());
//        assertThrows(Exception.class, (() -> deleteProductConsumer.listener("{\"idProduct\":\" 17 \"}")));
//
//        given(when(productServiceMock.deleteProductByIdProduct(product.getIdProduct()));
//
//    }

}

