package com.esg.main.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.esg.main.domain.CsvFile;
import com.esg.main.repo.CsvFileRepo;

@Service
public class CsvToJsonService {

	private final CsvFileRepo repo;

	public CsvToJsonService(CsvFileRepo repo) {
		this.repo = repo;
	}

	private List<CsvFile> readCsvFile(String filePath) throws IOException {
		List<CsvFile> csvFiles = new ArrayList<>();

		try (CSVParser parser = CSVParser.parse(Path.of(filePath), StandardCharsets.UTF_8, CSVFormat.DEFAULT)) {
			for (CSVRecord record : parser) {
				CsvFile csvFile = new CsvFile();
				csvFile.setCustomerName(record.get(0));
				csvFile.setAddressLineOne(record.get(1));
				csvFile.setAddressLineTwo(record.get(2));
				csvFile.setTown(record.get(3));
				csvFile.setCounty(record.get(4));
				csvFile.setPostcode(record.get(6));

				csvFiles.add(csvFile);
			}
		}

		return csvFiles;
	}

	public String parseCsvToJsonAndSave(String filePath) throws IOException {
		List<CsvFile> csvFiles = readCsvFile(filePath);
		// Perform any additional processing or validation if needed
		repo.saveAll(csvFiles);
		return "CSV file processed successfully!";
	}

}
