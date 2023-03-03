package com.Track.Models;

import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private String name;
	@Column(name = "CREATION_TIMESTAMP")
	private Timestamp creationTimestamp;
	@Column(name = "MODIFICATION_TIMESTAMP")
	private Timestamp modificationTimestamp;
	@Column(name = "CREATED_BY")
	private String createby;
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;
	private byte active;
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(int id, String name, Timestamp creationTimestamp, Timestamp modificationTimestamp, String createby,
			String modifiedBy, byte active) {
		super();
		this.id = id;
		this.name = name;
		this.creationTimestamp = creationTimestamp;
		this.modificationTimestamp = modificationTimestamp;
		this.createby = createby;
		this.modifiedBy = modifiedBy;
		this.active = active;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreationTimestamp() {
		return creationTimestamp;
	}
	public void setCreationTimestamp(Timestamp creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	public Timestamp getModificationTimestamp() {
		return modificationTimestamp;
	}
	public void setModificationTimestamp(Timestamp modificationTimestamp) {
		this.modificationTimestamp = modificationTimestamp;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public byte getActive() {
		return active;
	}
	public void setActive(byte active) {
		this.active = active;
	}
	
}
