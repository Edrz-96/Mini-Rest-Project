package com.esg.main.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CsvFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerRef;

	public Long getCustomerRef() {
		return customerRef;
	}

	public void setCustomerRef(Long customerRef) {
		this.customerRef = customerRef;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	private String customerName;
	private String addressLineOne;
	private String addressLineTwo;
	private String town;
	private String county;
	private String country;
	private String postcode;

	@Override
	public int hashCode() {
		return Objects.hash(addressLineOne, addressLineTwo, country, county, customerName, customerRef, postcode, town);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CsvFile other = (CsvFile) obj;
		return Objects.equals(addressLineOne, other.addressLineOne)
				&& Objects.equals(addressLineTwo, other.addressLineTwo) && Objects.equals(country, other.country)
				&& Objects.equals(county, other.county) && Objects.equals(customerName, other.customerName)
				&& Objects.equals(customerRef, other.customerRef) && Objects.equals(postcode, other.postcode)
				&& Objects.equals(town, other.town);
	}

	public CsvFile() {
		super();
	}

	public CsvFile(Long customerRef) {
		this(customerRef, null, null, null, null, null, null, null);
	}

	public CsvFile(Long customerRef, String customerName) {
		this(customerRef, customerName, null, null, null, null, null, null);
	}

	public CsvFile(Long customerRef, String customerName, String addressLineOne) {
		this(customerRef, customerName, addressLineOne, null, null, null, null, null);
	}

	public CsvFile(Long customerRef, String customerName, String addressLineOne, String addressLineTwo) {
		this(customerRef, customerName, addressLineOne, addressLineTwo, null, null, null, null);
	}

	public CsvFile(Long customerRef, String customerName, String addressLineOne, String addressLineTwo, String town) {
		this(customerRef, customerName, addressLineOne, addressLineTwo, town, null, null, null);
	}

	public CsvFile(Long customerRef, String customerName, String addressLineOne, String addressLineTwo, String town,
			String county) {
		this(customerRef, customerName, addressLineOne, addressLineTwo, town, county, null, null);
	}

	public CsvFile(Long customerRef, String customerName, String addressLineOne, String addressLineTwo, String town,
			String county, String country) {
		this(customerRef, customerName, addressLineOne, addressLineTwo, town, county, country, null);
	}

	public CsvFile(Long customerRef, String customerName, String addressLineOne, String addressLineTwo, String town,
			String county, String country, String postcode) {
		this.customerRef = customerRef;
		this.customerName = customerName;
		this.addressLineOne = addressLineOne;
		this.addressLineTwo = addressLineTwo;
		this.town = town;
		this.county = county;
		this.country = country;
		this.postcode = postcode;
	}

}