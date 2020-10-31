package com.recruitment.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Offer", uniqueConstraints = @UniqueConstraint(columnNames = { "jobTitle" }))
public class Offer {

	@OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Application> applications = new ArrayList<>();

	@Id
	private String jobTitle;

	private String jobDesc;

	private int noOfApplications;

	private LocalDate startDate = LocalDate.now();

	// default constructor
	public Offer() {

	}

	public Offer(String jobTitle, String jobDesc) {
		this.jobTitle = jobTitle;
		this.jobDesc = jobDesc;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public int getNoOfApplications() {
		return noOfApplications;
	}

	public void setNoOfApplications(int noOfApplications) {
		this.noOfApplications = noOfApplications;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void addApplication(Application application) {
		applications.add(application);
		application.setOffer(this.getJobTitle());
	}

}
