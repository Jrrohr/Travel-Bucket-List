package com.codingdojo.travelBucketList.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="destinations")
public class Destination {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Name is required")
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;
	
	@NotEmpty(message="Description is required")
	@Size(min=5, message="Description must be at least 5 characters long")
	private String description;
	
	@NotEmpty(message="Picture link is required")
	private String pic;
	
	@NotEmpty(message="Best time of year to visit is required")
	private String timeOfYear;
	
	@NotEmpty(message="How expensive is required")
	private String expensive;
	
	@NotEmpty(message="Visa status is required")
	@Size(min=3, message="Visa status must be at least 3 characters")
	private String visaStatus;
	
	@NotEmpty(message="Currency type is required")
	@Size(min=3, message="Currency type must be at least 3 characters")
	private String currency;
	
	@OneToMany(mappedBy="destination", fetch = FetchType.LAZY)
    private List<BucketList> bucketLists;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	
	public Destination() {
	}
	
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
	
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTimeOfYear() {
		return timeOfYear;
	}

	public void setTimeOfYear(String timeOfYear) {
		this.timeOfYear = timeOfYear;
	}

	public String getExpensive() {
		return expensive;
	}

	public void setExpensive(String expensive) {
		this.expensive = expensive;
	}

	public String getVisaStatus() {
		return visaStatus;
	}

	public void setVisaStatus(String visaStatus) {
		this.visaStatus = visaStatus;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<BucketList> getBucketLists() {
		return bucketLists;
	}

	public void setBucketLists(List<BucketList> bucketLists) {
		this.bucketLists = bucketLists;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
