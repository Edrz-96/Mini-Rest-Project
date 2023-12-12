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

	private String removeLeadingBomAndQuotes(String value) {
		String result = value;
		if (result.startsWith("\ufeff")) {
			result = result.substring(1);
		}
		result = result.replace("\"", "");
		return result.trim();
	}

	private List<CsvFile> readCsvFile(String filePath) throws IOException {
		List<CsvFile> csvFiles = new ArrayList<>();

		CSVFormat format = CSVFormat.DEFAULT.builder().setTrim(true).setIgnoreEmptyLines(true)
				.setIgnoreSurroundingSpaces(true).setQuote('/').build();

		try (CSVParser parser = CSVParser.parse(Path.of(filePath), StandardCharsets.UTF_8, format)) {
			for (CSVRecord record : parser) {
				CsvFile csvFile = new CsvFile();
				csvFile.setCustomerName(removeLeadingBomAndQuotes(record.get(0)).trim());
				csvFile.setAddressLineOne(record.get(1));
				csvFile.setAddressLineTwo(record.get(2));
				csvFile.setTown(record.get(3));
				csvFile.setCounty(record.get(4));
				csvFile.setCountry(record.get(5));
				csvFile.setPostcode(removeLeadingBomAndQuotes(record.get(6)));
				csvFiles.add(csvFile);
			}
		}

		return csvFiles;
	}

	public String parseCsvToJsonAndSave(String filePath) throws IOException {
		List<CsvFile> csvFiles = readCsvFile(filePath);
		repo.saveAll(csvFiles);
		return "CSV file processed successfully!";
	}

}
