package emotionalsongs.objects;

public class Canzone {
	private final Integer year;
	private final String ID;
	private final String author;
	private final String title;
	private final String album;
	
	public Canzone(Integer year, String ID, String author, String title, String album) {
		this.year = year;
		this.ID = ID;
		this.author = author;
		this.title = title;
		this.album = album;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public String getID() {
		return ID;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAlbum() {
		return album;
	}
}
