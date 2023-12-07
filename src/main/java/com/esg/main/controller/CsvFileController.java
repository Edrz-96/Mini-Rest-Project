package com.esg.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esg.main.domain.CsvFile;
import com.esg.main.service.CsvFileService;

@RequestMapping("/csv")
@RestController
public class CsvFileController {

	@Autowired
	private CsvFileService service;
	

	// create
	@PostMapping("/create")
	public ResponseEntity<CsvFile> create(@RequestBody CsvFile p) {
		return new ResponseEntity<CsvFile>(this.service.create(p), HttpStatus.CREATED);
	}

	// read
	@GetMapping("/read")
	public ResponseEntity<List<CsvFile>> read() {
		return new ResponseEntity<List<CsvFile>>(this.service.readAll(), HttpStatus.OK);

	}

	// Read by ID
	@GetMapping("/read/{id}")
	public ResponseEntity<CsvFile> readById(@PathVariable Long id) {
		Optional<CsvFile> csvFile = this.service.readById(id);
		return csvFile.map(file -> new ResponseEntity<>(file, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// update
	@PutMapping("/update/{id}")
	public ResponseEntity<CsvFile> update(@PathVariable Long id, @RequestBody CsvFile p) {
		return new ResponseEntity<CsvFile>(this.service.update(id, p), HttpStatus.ACCEPTED);

	}

	// delete

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		return new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT);

	}

}
