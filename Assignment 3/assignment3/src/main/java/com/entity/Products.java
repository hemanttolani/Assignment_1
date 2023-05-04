package com.entity;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Version;


@Entity
@Table(name="product")
public class Products {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private String size;
	private String quantity;
	@Version
	private int version;
	@Lob 
	@Column(name = "image", columnDefinition = "MEDIUMBLOB")
	private byte[] image;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getVersion() {
		return version;
		}
	public void setVersion(int version) {
		this.version = version;
		}






	public Products( String title, String size, String quantity, byte[] image) {
		super();
		this.title = title;
		this.size = size;
		this.quantity = quantity;
		this.image = image;
	}
	@Override
	public String toString() {
		return "Products [id=" + id + ", title=" + title + ", size=" + size + ", quantity=" + quantity + "]";
	}
	

}
