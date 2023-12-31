package com.esg.main.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esg.main.service.CsvToJsonService;

@RestController
@RequestMapping("/upload")
public class CsvFileToJsonController {

	private final CsvToJsonService csvToJsonService;

	@Autowired
	public CsvFileToJsonController(CsvToJsonService csvToJsonService) {
		this.csvToJsonService = csvToJsonService;
	}

	@PostMapping
	public ResponseEntity<String> handleFileUpload(@RequestParam("filePath") String filePath) {
		try {

			String jsonResult = csvToJsonService.parseCsvToJsonAndSave(filePath);

			
			return new ResponseEntity<>(jsonResult, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to process the CSV file.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
