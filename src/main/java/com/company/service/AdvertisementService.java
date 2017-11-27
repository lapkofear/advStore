package com.company.service;

import com.company.domain.Advertisement;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class AdvertisementService {
	private static final int DELAY_RANGE_START = 20;
	private static final int DELAY_RANGE_END = 2000;

	private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
	private Random random = new Random();

	public CompletableFuture<Advertisement> getAdvertisement() {
		CompletableFuture<Advertisement> future = new CompletableFuture<>();
		this.executorService.schedule(() -> future.complete(new Advertisement()), getDelay(), TimeUnit.MILLISECONDS);
		return future;
	}

	private long getDelay() {
		return random.nextInt(DELAY_RANGE_END - DELAY_RANGE_START) + DELAY_RANGE_START;
	}
}
