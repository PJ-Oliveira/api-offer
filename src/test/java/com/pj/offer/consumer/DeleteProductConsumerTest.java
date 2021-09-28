package com.pj.offer.consumer;

import com.pj.offer.service.ProductService;
import com.pj.offer.service.ScenarioFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;


@ContextConfiguration(classes = {DeleteProductConsumer.class})
@ExtendWith(MockitoExtension.class)
class DeleteProductConsumerTest {

    @InjectMocks
    private DeleteProductConsumer deleteProductConsumer;

    @Mock
    private ProductService productServiceMock;

    @Test
    void listener_WhenListenToMsProductSucess_ExpectedSuccess() {
        var product = ScenarioFactory.newProduct();
        doNothing().when(productServiceMock).deleteProductByIdProduct(product.getIdProduct());
        deleteProductConsumer.listener("{\"idProduct\":\" 17 \"}");
    }

    @Test
    void listener_WhenListenToMsProductFail_ExpectedSuccess()  {
        var product = ScenarioFactory.newProduct();
        doThrow(new RuntimeException()).when(productServiceMock).deleteProductByIdProduct(product.getIdProduct());
        deleteProductConsumer.listener("{\"idProduct\":\" 17 \"}");
    }

}









