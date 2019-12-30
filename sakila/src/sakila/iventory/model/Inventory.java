package sakila.iventory.model;

import sakila.business.model.Store;

public class Inventory {
	private int iventoryId;
	private Film film;
	private Store store;
	private String lastUpdate;
	
	public int getIventoryId() {
		return iventoryId;
	}
	public void setIventoryId(int iventoryId) {
		this.iventoryId = iventoryId;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "Iventory [iventoryId=" + iventoryId + ", film=" + film + ", lastUpdate=" + lastUpdate + "]";
	}
	
	
}
