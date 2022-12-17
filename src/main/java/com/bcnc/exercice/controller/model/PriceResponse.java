package com.bcnc.exercice.controller.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PriceResponse {
	  private Long price;
	  private Long brandId;
	  private Long productId;
	  private LocalDateTime appDate;
	  private Double finalPrice;


}
