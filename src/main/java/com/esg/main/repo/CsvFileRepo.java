package com.esg.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esg.main.domain.CsvFile;

@Repository
public interface CsvFileRepo extends JpaRepository<CsvFile, Long> {

}
