package org.cap.model;

public class Address {

	private String doorNo;
	private String city;
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Address [doorNo=" + doorNo + ", city=" + city + "]";
	}
	public Address(String doorNo, String city) {
		super();
		this.doorNo = doorNo;
		this.city = city;
	}
	public Address() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((doorNo == null) ? 0 : doorNo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (doorNo == null) {
			if (other.doorNo != null)
				return false;
		} else if (!doorNo.equals(other.doorNo))
			return false;
		return true;
	}
	
}
