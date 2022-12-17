package com.bcnc.exercice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bcnc.exercice.controller.model.PriceRequest;
import com.bcnc.exercice.controller.model.PriceResponse;

import com.bcnc.model.Brand;
import com.bcnc.model.Price;
import com.bcnc.repository.ExerciceRepository;
import com.bcnc.service.ExerciceService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ExerciceServiceTest {
    
    @Mock
    private ExerciceRepository priceRepository;

    @InjectMocks
    private ExerciceService priceService;

    private List<Price> prices = new ArrayList<Price>();
    private LocalDateTime appDate = LocalDateTime.now();

    @BeforeEach
    public void setup() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("ZARA");

        //Llenamos una lista oara las pruebas
        for (int i = 0; i < 2; i++) {
            int valor = i+1;
            Price price = new Price();
            price.setId(Long.valueOf(valor));
            price.setPriority(valor);
            price.setPriceList(Long.valueOf(valor));
            price.setPrice(Double.valueOf(20*valor));
            price.setProductId(35455L);
            price.setBrand(brand);
            prices.add(price);
        }
    }

    //One Test
    @Test
    public void givenPriceRequest_whenGettingPrice_thenReturnPriceResponse() {
        
    	given(priceRepository.findByBrandProductAndDate(1L, 35455L, appDate)).willReturn(prices);

        PriceRequest priceRequest = PriceRequest.builder().appDate(appDate).brandId(1L).productId(35455L).build();
        PriceResponse priceResponse = priceService.getExerciceResult(priceRequest);
        
        assertEquals(1L, priceResponse.getBrandId());
        assertEquals(2L, priceResponse.getPrice());
        assertEquals(appDate, priceResponse.getAppDate());
        assertEquals(40L, priceResponse.getFinalPrice());
        assertEquals(35455L, priceResponse.getProductId());
    }

}