package com.bcnc.exercice;

import java.time.LocalDateTime;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bcnc.controller.ExerciceController;
import com.bcnc.exercice.controller.model.PriceRequest;

//Leer CSV para los datos del Tests
@SpringBootTest
class ExerciceApplicationTest {
	
	//@Autowired
	private ExerciceController exerciceController;

	@ParameterizedTest
    @CsvFileSource(resources = "/prices.csv", numLinesToSkip = 1)
	void restIntegrationTest(ArgumentsAccessor argumentsAccessor) {
	LocalDateTime appDate = LocalDateTime.of(
		argumentsAccessor.getInteger(2),
		argumentsAccessor.getInteger(3),
		argumentsAccessor.getInteger(4),
		argumentsAccessor.getInteger(5),
		0);
	
		PriceRequest priceRequest = PriceRequest.builder()
			.brandId(argumentsAccessor.getLong(0))
			.productId(argumentsAccessor.getLong(1))
			.appDate(appDate).build();
		
		//ResponseEntity<PriceResponse> priceResponse = exerciceController.getExerciceResult(priceRequest);
		/*if (priceResponse.getStatusCode() == HttpStatus.OK) {
			PriceResponse price = priceResponse.getBody();
			if (price != null) {
				//assertEquals(price.getBrandId(), argumentsAccessor.getLong(0)); 
				assertEquals(price.getProductId(), argumentsAccessor.getLong(1)); 
				assertEquals(price.getAppDate(), appDate);
				assertEquals(price.getFinalPrice(), argumentsAccessor.getDouble(6));
				assertEquals(price.getPrice(), argumentsAccessor.getLong(7));
			}
		} else {
		}*/
	}

}