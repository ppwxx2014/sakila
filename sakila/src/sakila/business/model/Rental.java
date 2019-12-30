package sakila.business.model;

import sakila.customer.model.Customer;
import sakila.iventory.model.Inventory;

public class Rental {
	private int rentalId;
	private String rentalDate;
	private Inventory inventory;
	private Customer customer;
	private String returnDate;
	private Staff staff;
	private String lastUpdate;
	
	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	public String getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}
	public Inventory getIventory() {
		return inventory;
	}
	public void setIventory(Inventory iventory) {
		this.inventory = iventory;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "Rental [rentalId=" + rentalId + ", rentalDate=" + rentalDate + ", iventory=" + inventory + ", customer="
				+ customer + ", returnDate=" + returnDate + ", staff=" + staff + ", lastUpdate=" + lastUpdate + "]";
	}
}
