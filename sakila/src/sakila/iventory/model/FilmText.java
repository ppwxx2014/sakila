package sakila.iventory.model;

public class FilmText {
	private Film film;
	private String title;
	private String description;
	
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "FilmText [film=" + film + ", title=" + title + ", description=" + description + "]";
	}
}
