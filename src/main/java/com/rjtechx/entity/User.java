package com.rjtechx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
	private String role;
	private String name;
	private String address;
	private String pin;
	private String mobile;
	private String elctricityCustomerID;
	private String city;
	private boolean status;
	private String verification;
	public String getVerification() {
		return verification;
	}
	public void setVerification(String verification) {
		this.verification = verification;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getElctricityCustomerID() {
		return elctricityCustomerID;
	}
	public void setElctricityCustomerID(String elctricityCustomerID) {
		this.elctricityCustomerID = elctricityCustomerID;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + ", name=" + name
				+ ", address=" + address + ", pin=" + pin + ", mobile=" + mobile + ", elctricityCustomerID="
				+ elctricityCustomerID + ", city=" + city + ", status=" + status + ", verification=" + verification
				+ "]";
	}
	
	
	
	
}
