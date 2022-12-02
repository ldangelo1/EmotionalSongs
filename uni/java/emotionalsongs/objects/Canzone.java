package emotionalsongs.objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Canzone {
	private final IntegerProperty Anno = new SimpleIntegerProperty();
	private final StringProperty ID = new SimpleStringProperty();
	private final StringProperty Autore = new SimpleStringProperty();
	private final StringProperty Titolo = new SimpleStringProperty();
	private final StringProperty Album = new SimpleStringProperty();
	
	public int getAnno() {
		return Anno.get();
	}
	
	public void setAnno(Integer anno) {
		this.Anno.set(anno);
	}
	
	public String getID() {
		return ID.get();
	}
	
	public void setID(String id) {
		this.ID.set(id);
	}
	
	public String getAutore() {
		return Autore.get();
	}
	
	public void setAutore(String autore) {
		this.Autore.set(autore);
	}
	
	public String getTitolo() {
		return Titolo.get();
	}
	
	public void setTitolo(String titolo) {
		this.Titolo.set(titolo);
	}
	
	public String getAlbum() {
		return Album.get();
	}
	
	public void setAlbum(String album) {
		this.Album.set(album);
	}
}