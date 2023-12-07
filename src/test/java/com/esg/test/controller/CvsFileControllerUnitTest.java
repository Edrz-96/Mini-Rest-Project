package com.esg.test.controller;

import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.esg.main.Runner;
import com.esg.main.controller.CsvFileController;
import com.esg.main.domain.CsvFile;
import com.esg.main.service.CsvFileService;

@SpringBootTest(classes = Runner.class, webEnvironment = WebEnvironment.RANDOM_PORT)

public class CvsFileControllerUnitTest {

	@Mock
	private CsvFileService service;

	@InjectMocks
	private CsvFileController controller;

	@Test
	public void testRead() {
		CsvFile csvFile = new CsvFile(1L, "John Doe");

		when(service.readAll()).thenReturn(Collections.singletonList(csvFile));

		ResponseEntity<List<CsvFile>> response = controller.read();

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(1, response.getBody().size());
		Assertions.assertEquals("John Doe", response.getBody().get(0).getCustomerName());
	}

	@Test
	public void testCreate() {
		CsvFile csvFile = new CsvFile(1L, "John Doe");

		when(service.create(csvFile)).thenReturn(csvFile);

		ResponseEntity<CsvFile> response = controller.create(csvFile);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertEquals("John Doe", response.getBody().getCustomerName());
	}

	@Test
	public void testUpdate() {
		CsvFile csvFile = new CsvFile(1L, "John Doe");

		when(service.update(1L, csvFile)).thenReturn(csvFile);

		ResponseEntity<CsvFile> response = controller.update(1L, csvFile);

		Assertions.assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		Assertions.assertEquals("John Doe", response.getBody().getCustomerName());
	}

	@Test
	public void testDelete() {
		ResponseEntity<Boolean> response = controller.delete(1L);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

}
