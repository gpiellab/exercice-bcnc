package com.bcnc.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcnc.exercice.controller.model.PriceRequest;
import com.bcnc.exercice.controller.model.PriceResponse;
import com.bcnc.model.Price;
import com.bcnc.repository.ExerciceRepository;

@Service
public class ExerciceService {
	//All the project logic
	
    @Autowired
    private ExerciceRepository priceRepository;


    private PriceResponse resultPrice(PriceRequest priceRequest, Price price) {
    	
        PriceResponse priceResponse = new PriceResponse();
        
        priceResponse.setAppDate(priceRequest.getAppDate());
        priceResponse.setBrandId(price.getBrand().getId());
        priceResponse.setFinalPrice(price.getPrice());
        priceResponse.setPrice(price.getPriceList());
        priceResponse.setProductId(price.getProductId());
        
        return priceResponse;
    }
    
    
    public PriceResponse getExerciceResult(PriceRequest priceRequest) {

        List<Price> exerciceResults = priceRepository.findByBrandProductAndDate(priceRequest.getBrandId(), priceRequest.getProductId(), priceRequest.getAppDate());
        
        PriceResponse priceResponse = null;
        
        if (!exerciceResults.isEmpty()) {
            Price price = exerciceResults.stream().max(Comparator.comparing(p -> p.getPriority())).get();
        
            priceResponse = resultPrice(priceRequest, price);
        }
        return priceResponse;
    }
    
}
