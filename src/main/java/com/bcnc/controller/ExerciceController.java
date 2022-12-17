package com.bcnc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcnc.exercice.controller.model.PriceRequest;
import com.bcnc.exercice.controller.model.PriceResponse;
import com.bcnc.service.ExerciceService;

@Controller
public class ExerciceController {
	
	@Autowired
    private ExerciceService exerciceService;

	//EndPoint to get the result
	@RequestMapping(value = "/exercice" ,method = RequestMethod.POST)
    public ResponseEntity<PriceResponse> getExerciceResult(@RequestBody PriceRequest priceRequest) {
        
		PriceResponse priceResponse = exerciceService.getExerciceResult(priceRequest);
    
        if (priceResponse != null) {
            return ResponseEntity.ok(priceResponse);
        
        } else {
        
        	return new ResponseEntity<PriceResponse>(HttpStatus.NO_CONTENT);
        
        }
    }
}
