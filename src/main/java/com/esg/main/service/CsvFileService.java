package com.esg.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esg.main.domain.CsvFile;
import com.esg.main.repo.CsvFileRepo;

@Service
public class CsvFileService {

	private final CsvFileRepo repo;

	@Autowired
	public CsvFileService(CsvFileRepo repo) {
		this.repo = repo;
	}

	// Create
	public CsvFile create(CsvFile csvFile) {
		return repo.saveAndFlush(csvFile);
	}

	// Read all
	public List<CsvFile> readAll() {
		return repo.findAll();
	}

	// Read by ID
	public Optional<CsvFile> readById(Long id) {
		return repo.findById(id);
	}

	// Update
	public CsvFile update(Long id, CsvFile updatedCsvFile) {
		Optional<CsvFile> existingCsvFile = repo.findById(id);

		if (existingCsvFile.isPresent()) {

			CsvFile csvFile = existingCsvFile.get();

			csvFile.setCustomerRef(updatedCsvFile.getCustomerRef());
			csvFile.setCustomerName(updatedCsvFile.getCustomerName());
			csvFile.setAddressLineOne(updatedCsvFile.getAddressLineOne());
			csvFile.setAddressLineTwo(updatedCsvFile.getAddressLineTwo());
			csvFile.setTown(updatedCsvFile.getTown());
			csvFile.setCounty(updatedCsvFile.getCounty());
			csvFile.setCountry(updatedCsvFile.getCountry());
			csvFile.setPostcode(updatedCsvFile.getPostcode());
			return repo.saveAndFlush(csvFile);
		} else {
			// Handle not found case
			throw new RuntimeException("CsvFile not found with id: " + id);
		}
	}

	// Delete
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
