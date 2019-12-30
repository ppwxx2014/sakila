package sakila.customer.model;

import sakila.business.model.Store;

public class Customer {
	private Store store;
	private Address address;
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private int active;
	private String createDate;
	private String lastUpdate;
	
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int cuntomerId) {
		this.customerId = cuntomerId;
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
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "Customer [address=" + address + ", cuntomerId=" + customerId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", active=" + active + ", createDate=" + createDate
				+ ", lastUpdate=" + lastUpdate + "]";
	}
	
	
}
