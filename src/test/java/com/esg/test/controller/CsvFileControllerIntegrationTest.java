package com.esg.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.esg.main.Runner;
import com.esg.main.controller.CsvFileController;
import com.esg.main.domain.CsvFile;
import com.esg.main.service.CsvFileService;

@SpringBootTest(classes = Runner.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class CsvFileControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CsvFileService service;

	@Test
	public void testRead() throws Exception {
		CsvFile csvFile = new CsvFile(1L, "John Doe");

		when(service.readAll()).thenReturn(Collections.singletonList(csvFile));

		mockMvc.perform(MockMvcRequestBuilders.get("/csv/read")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].customerName").value("John Doe"));
	}

	@Test
	public void testReadById() throws Exception {
		CsvFile csvFile = new CsvFile(1L, "John Doe");
		when(service.readById(1L)).thenReturn(Optional.of(csvFile));

		mockMvc.perform(MockMvcRequestBuilders.get("/csv/read/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.customerName").value("John Doe"));
	}

	@Test
	public void testCreate() throws Exception {
		CsvFile csvFile = new CsvFile(1L, "John Doe");
		when(service.create(csvFile)).thenReturn(csvFile);
		mockMvc.perform(MockMvcRequestBuilders.post("/csv/create").contentType(MediaType.APPLICATION_JSON)
				.content("{\"customerRef\": 1, \"customerName\": \"John Doe\"}")).andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.customerName").value("John Doe"));
	

}

	@Test
	public void testUpdate() throws Exception {
		CsvFile csvFile = new CsvFile(1L, "Bob Doe");

		when(service.update(1L, csvFile)).thenReturn(csvFile);

		mockMvc.perform(MockMvcRequestBuilders.put("/csv/update/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"customerRef\":1,\"customerName\":\"John Doe\"}")).andExpect(status().isAccepted())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.customerName").value("John Doe"));
	}

	@Test
	void testDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/csv/delete/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
}
