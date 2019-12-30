package sakila.iventory.model;

public class FilmActor {
	private Actor actor;
	private Film film;
	private String lastUpdate;
	
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "FilmActor [actor=" + actor + ", film=" + film + ", lastUpdate=" + lastUpdate + "]";
	}
	
	
}
