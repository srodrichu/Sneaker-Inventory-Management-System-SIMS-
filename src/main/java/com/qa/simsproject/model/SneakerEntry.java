package com.qa.simsproject.model;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SneakerEntry {
	
//	Creating the schema. ID, Name, Size, Cost Price, Listed Price, Sold
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 150)
	private String name;
	
	@Column(nullable = false)
	private float size;
	
	@Column(nullable = false)
	private float costPrice;
	
	@Column(nullable = false)
	private float listedPrice;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean isSold = false;

	public SneakerEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SneakerEntry(long id, String name, float size, float costPrice, float listedPrice, boolean isSold) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.costPrice = costPrice;
		this.listedPrice = listedPrice;
		this.isSold = isSold;
	}
	

	public SneakerEntry(String name, float size, float costPrice, float listedPrice, boolean isSold) {
		super();
		this.name = name;
		this.size = size;
		this.costPrice = costPrice;
		this.listedPrice = listedPrice;
		this.isSold = isSold;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public float getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(float costPrice) {
		this.costPrice = costPrice;
	}

	public float getListedPrice() {
		return listedPrice;
	}

	public void setListedPrice(float listedPrice) {
		this.listedPrice = listedPrice;
	}

	public boolean isSold() {
		return isSold;
	}

	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}

	@Override
	public String toString() {
		return "SneakerEntry [id=" + id + ", name=" + name + ", size=" + size + ", costPrice=" + costPrice
				+ ", listedPrice=" + listedPrice + ", isSold=" + isSold + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(costPrice, id, isSold, listedPrice, name, size);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SneakerEntry other = (SneakerEntry) obj;
		return Float.floatToIntBits(costPrice) == Float.floatToIntBits(other.costPrice) && id == other.id
				&& isSold == other.isSold
				&& Float.floatToIntBits(listedPrice) == Float.floatToIntBits(other.listedPrice)
				&& Objects.equals(name, other.name) && Float.floatToIntBits(size) == Float.floatToIntBits(other.size);
	}
}
