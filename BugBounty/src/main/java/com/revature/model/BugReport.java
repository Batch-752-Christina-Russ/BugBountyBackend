package com.revature.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="bug_reports")
public class BugReport {

	@Id
	@SequenceGenerator(name="bug_reports_bug_id_seq", allocationSize = 1)
	@GeneratedValue(generator="bug_reports_bug_id_seq", strategy = GenerationType.AUTO)
	@Column(name="bug_id")
	private int id;
	@ManyToOne
	@JoinColumn(name="reporter_id")
	private User reporter;
	@ManyToOne
	@JoinColumn(name="resolver_id")
	private User resolver;
	@Column(name="application_name")
	private String application;
	@Column(name="location_of_bug")
	private String location;
	@Column(name="description")
	private String description;
	@Column(name="steps")
	private String steps;
	@Column(name="severity")
	private String severity;
	@Column(name="submission_date")
	private Date date;
	@Column(name="status")
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getReporter() {
		return reporter;
	}
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}
	public User getResolver() {
		return resolver;
	}
	public void setResolver(User resolver) {
		this.resolver = resolver;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSteps() {
		return steps;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	/**
	* Converts BugReport object values into Hashcode
	* <p>
	* This method is used to convert the values of the selected BugReport into Hash Code and returns those values as an int.
	*
	* @return returns int value from hashcode
	* 
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((application == null) ? 0 : application.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((reporter == null) ? 0 : reporter.hashCode());
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + ((severity == null) ? 0 : severity.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((steps == null) ? 0 : steps.hashCode());
		return result;
	}
	/**
	* Returns a boolean if this BugReport equals in value to the parameter entered
	* <p>
	* This method is used to check if the BugReport is equal to object and checking if the obj is null.
	* 
	* @param  obj  object that is to be compared to instance of BugReport class
	* @return true if BugReport equals parameter, obj is not null, and value of BugReport equals value of parameter,
	* 
	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BugReport other = (BugReport) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (reporter == null) {
			if (other.reporter != null)
				return false;
		} else if (!reporter.equals(other.reporter))
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (severity == null) {
			if (other.severity != null)
				return false;
		} else if (!severity.equals(other.severity))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (steps == null) {
			if (other.steps != null)
				return false;
		} else if (!steps.equals(other.steps))
			return false;
		return true;
	}
	
	/**
	* Converts BugReport object into a String
	* <p>
	* This method overrides the toString method to output proper format for webpages.
	*
	* @return BugReport instance as a String.
	* 
	*/
	@Override
	public String toString() {
		return "BugReport [id=" + id + ", reporter=" + reporter + ", resolver=" + resolver + ", application="
				+ application + ", location=" + location + ", description=" + description + ", steps=" + steps
				+ ", severity=" + severity + ", date=" + date + ", status=" + status + "]";
	}
	
	/**
	* Constructs BugReport with a resolver.
	* <p>
	* This constructor creates a BugReport with both a resolver and reporter, this is used for when a BugReport is resolved by a user.
	* 
	*/
	public BugReport(int id, User reporter, User resolver, String application, String location, String description,
			String steps, String severity, Date date, String status) {
		super();
		this.id = id;
		this.reporter = reporter;
		this.resolver = resolver;
		this.application = application;
		this.location = location;
		this.description = description;
		this.steps = steps;
		this.severity = severity;
		this.date = date;
		this.status = status;
	}
	
	/**
	* Constructs BugReport without a resolver.
	* <p>
	* This constructor creates a BugReport without a resolver and reporter, this is used for creating a completely new BugReport for submission.
	* 
	*/
	public BugReport(int id, User reporter, String application, String location, String description,
			String steps, String severity, Date date, String status) {
		super();
		this.id = id;
		this.reporter = reporter;
		this.application = application;
		this.location = location;
		this.description = description;
		this.steps = steps;
		this.severity = severity;
		this.date = date;
		this.status = status;
	}
	public BugReport() {
		super();
		// TODO Auto-generated constructor stub
	}
		
}
