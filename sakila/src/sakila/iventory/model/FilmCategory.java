package sakila.iventory.model;

public class FilmCategory {
	private Film film;
	private Category category;
	private String lastUpdate;
	
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "FilmCategory [film=" + film + ", category=" + category + ", lastUpdate=" + lastUpdate + "]";
	}
}
