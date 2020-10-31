package com.recruitment.repository;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.recruitment.entity.Offer;
import com.recruitment.service.OfferService;
import junit.framework.Assert;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OfferRepositoryTest {

	@Mock
	private OfferRepository offerRepository;

	@Mock
	private OfferService offerService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testRetrieveAllJobs() {
		List<Offer> offres = Stream.of(new Offer("Backend Developer", "Required two years of experinec"),
				new Offer("Backend Developer", "Required two years of experinec")).collect(Collectors.toList());
		Mockito.when(offerRepository.findAll()).thenReturn(offres);
		List<Offer> offersList = offerRepository.findAll();
		Assert.assertEquals(offres, offersList);
	}

	@Test
	public void testSaveOffer() {
		Offer offer = new Offer("Backend developer", "Required 2 years of experience");
		Mockito.when(offerRepository.save(offer)).thenReturn(offer);
		Offer offExp = offerRepository.save(offer);
		Assert.assertEquals(offer, offExp);
	}

	@Test
	public void testRetrieveJobOnBasisOfTitle() {
		Offer offer = new Offer("Backend developer", "Required 2 years of experience");
		Mockito.when(offerRepository.findByJobTitle(offer.getJobTitle())).thenReturn(offer);
		Offer offerExp = offerRepository.findByJobTitle(offer.getJobTitle());
		Assert.assertEquals(offer, offerExp);
	}
	

}
