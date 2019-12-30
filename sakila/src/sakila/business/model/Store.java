package sakila.business.model;

import sakila.customer.model.Address;

public class Store {
	private int storeId;
	private int managerStaffId;
	private Address address;
	private String lastUpdate;
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getManagerStaffId() {
		return managerStaffId;
	}
	public void setManagerStaffId(int managerStaffId) {
		this.managerStaffId = managerStaffId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", managerStaffId=" + managerStaffId + ", address=" + address
				+ ", lastUpdate=" + lastUpdate + "]";
	}
	
	
}
