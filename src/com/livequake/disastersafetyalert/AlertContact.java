package com.livequake.disastersafetyalert;

public class AlertContact {
	private String name;
	private String number;
	private Integer safe;
	private String lastContact;
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setNumber(String num) {
		this.number = num;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setSafe(Integer safety) {
		this.safe = safety;
	}
	
	public Integer getSafe() {
		return safe;
	}
	
	public void setLastContact(String last) {
		this.lastContact = last;
	}
	
	public String getLastContact() {
		return lastContact;
	}

	@Override
	public String toString() {
		return this.name + ", " + this.number;
	}
}
