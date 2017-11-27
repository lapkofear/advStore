package com.company.service;

import com.company.domain.Advertisement;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class AdvertisementServiceTest {

	@Autowired
	private AdvertisementService advertisementService;

	@Test
	public void delayTimeRangeEndTest() throws InterruptedException, ExecutionException, TimeoutException {
		CompletableFuture<Advertisement> completableFuture = advertisementService.getAdvertisement();
		Advertisement advertisement = completableFuture.get(2000, TimeUnit.MILLISECONDS);
		Assert.assertThat(advertisement, IsNull.notNullValue());
	}

	@Test(expected = TimeoutException.class)
	public void delayTimeRangeStartTest() throws InterruptedException, ExecutionException, TimeoutException {
		CompletableFuture<Advertisement> completableFuture = advertisementService.getAdvertisement();
		completableFuture.get(10, TimeUnit.MILLISECONDS);
	}

}