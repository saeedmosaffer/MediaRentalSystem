package application;

import java.util.ArrayList;
import java.util.List;

public class Customer implements Comparable {

	private String ID;
	private String Name;
	private String Address;
	private String Plan;
	private String MobileNumber;
	protected List<String> intrested_in_received;
	protected List<String> rented;

	public Customer() {
		super();
		ID = "";
		Name = "";
		Address = "";
		Plan = "";
		MobileNumber = "";
		intrested_in_received = new ArrayList<String>();
		rented = new ArrayList<String>();
	}

	public Customer(String iD, String name, String address, String plan, String mobileNumber) {
		super();
		ID = iD;
		Name = name;
		Address = address;
		Plan = plan;
		MobileNumber = mobileNumber;
		intrested_in_received = new ArrayList<String>();
		rented = new ArrayList<String>();
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPlan() {
		return Plan;
	}

	public void setPlan(String plan) {
		Plan = plan;
	}

	public List<String> getIntrested_in_received() {
		return intrested_in_received;
	}

	public void setIntrested_in_received(List<String> intrested_in_received) {
		this.intrested_in_received = intrested_in_received;
	}

	public List<String> getRented() {
		return rented;
	}

	public void setRented(List<String> rented) {
		this.rented = rented;
	}

	@Override
	public String toString() {
		return "Customer [ID=" + ID + ", Name=" + Name + ", Address=" + Address + ", Plan=" + Plan + ", MobileNumber="
				+ MobileNumber + ", intrested_in_received=" + intrested_in_received + ", rented=" + rented + "]";
	}

	@Override
	public int compare(Object media) {
		// TODO Auto-generated method stub

		Customer customer = (Customer) media;
		return this.getName().compareTo(customer.getName());
	}

}