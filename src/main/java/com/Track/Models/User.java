package com.Track.Models;

import java.sql.Timestamp;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.Data;

@Data
@Entity
public class User implements UserDetails {
@Id
@GeneratedValue(strategy=GenerationType.SEQUENCE)
 private int id;
@OneToOne(cascade = CascadeType.DETACH)
@JoinColumn(name = "ROLE_ID")
private Role role;
@Column(name = "FIRST_NAME")
private String firstName;
@Column(name = "LAST_NAME")
private String lastName;
private String email;
@Column(name = "MOBILE_NUMBER")
private String mobileNumber;
private String password;
@Column(name = "IS_LOGGED_IN")
private byte isLoggedIn;
@Column(name = "LAST_LOGGED_IN_TIMESTAMP")
private Timestamp lastLoggedInTimestamp;
@Column(name = "LAST_LOGGED_OUT_TIMESTAMP")
private Timestamp lastLoggedOutTimestamp;
@Column(name = "DEVICE_ID")
private String deviceId;
@Column(name = "DEVICE_TYPE")
private String deviceType;
@Column(name = "DEVICE_TOKEN")
private String deviceToken;
@Column(name = "CREATION_TIMESTAMP")
private Timestamp creationTimestamp;
@Column(name = "MODIFICATION_TIMESTAMP")
private Timestamp modificationTimestamp;
@Column(name = "CREATED_BY")
private String createdBy;
@Column(name = "MODIFIED_BY")
private String modifiedBy;
private byte active;
@Transient
private String otp;
private String token;

 public void setUsrname(String email) {
	 this.email=email;
 }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(int id, Role role, String firstName, String lastName, String email, String mobileNumber,
			String password, byte isLoggedIn, Timestamp lastLoggedInTimestamp, Timestamp lastLoggedOutTimestamp,
			String deviceId, String deviceType, String deviceToken, Timestamp creationTimestamp,
			Timestamp modificationTimestamp, String createdBy, String modifiedBy, byte active, String otp,
			String token) {
		super();
		this.id = id;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.isLoggedIn = isLoggedIn;
		this.lastLoggedInTimestamp = lastLoggedInTimestamp;
		this.lastLoggedOutTimestamp = lastLoggedOutTimestamp;
		this.deviceId = deviceId;
		this.deviceType = deviceType;
		this.deviceToken = deviceToken;
		this.creationTimestamp = creationTimestamp;
		this.modificationTimestamp = modificationTimestamp;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.active = active;
		this.otp = otp;
		this.token = token;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public byte getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(byte isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public Timestamp getLastLoggedInTimestamp() {
		return lastLoggedInTimestamp;
	}

	public void setLastLoggedInTimestamp(Timestamp lastLoggedInTimestamp) {
		this.lastLoggedInTimestamp = lastLoggedInTimestamp;
	}

	public Timestamp getLastLoggedOutTimestamp() {
		return lastLoggedOutTimestamp;
	}

	public void setLastLoggedOutTimestamp(Timestamp lastLoggedOutTimestamp) {
		this.lastLoggedOutTimestamp = lastLoggedOutTimestamp;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	

}
