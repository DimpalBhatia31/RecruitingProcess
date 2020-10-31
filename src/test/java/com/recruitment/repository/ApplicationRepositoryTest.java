package com.recruitment.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.recruitment.entity.Application;
import junit.framework.Assert;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationRepositoryTest {

	@Mock
	ApplicationRepository applicationRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveApplication() {
		Application application = new Application(1, "Backend developer", "dimpalbhatia31@gmail.com");
		Mockito.when(applicationRepository.save(application)).thenReturn(application);
		Application applicationExp = applicationRepository.save(application);
		Assert.assertEquals(application, applicationExp);
	}

	@Test
	public void testRetrieveApplicationByOffer() {
		List<Application> applications = Stream.of(new Application(1, "Frontend Developer", "nikitaAggarwal@gmail.com"),
				new Application(2, "Frontend Developer", "dimpal@gmail.com")).collect(Collectors.toList());
		Mockito.when(applicationRepository.findByOffer("Frontend Developer")).thenReturn(applications);
		List<Application> applicationList = applicationRepository.findByOffer("Frontend Developer");
		Assert.assertEquals(applications, applicationList);
	}

	@Test
	public void testRetrieveAllApplications() {
		List<Application> applications = Stream.of(new Application(1, "Backend Developer", "dimpalbhatia31@gmail.com"),
				new Application(2, "Backend Developer", "dimpal@gmail.com")).collect(Collectors.toList());
		Mockito.when(applicationRepository.findAll()).thenReturn(applications);
		List<Application> applicationsList = applicationRepository.findAll();
		Assert.assertEquals(applications, applicationsList);
	}

}
