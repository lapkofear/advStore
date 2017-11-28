package com.company.controller;

import com.company.domain.Advertisement;
import com.company.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@RestController
public class AdvertisementController {
	private final AdvertisementService advertisementService;

	@Autowired
	public AdvertisementController(AdvertisementService advertisementService) {
		this.advertisementService = advertisementService;
	}

	@GetMapping(value = "/advertisementStore", produces = MediaType.APPLICATION_JSON_VALUE)
	public CompletableFuture<Advertisement> advertisement() {
		return this.advertisementService.getAdvertisement();
	}
}
