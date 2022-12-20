package emotionalsongs.objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Canzone {
	private final StringProperty Titolo = new SimpleStringProperty();
	private final StringProperty Artista = new SimpleStringProperty();
	private final IntegerProperty Anno = new SimpleIntegerProperty();
	private final StringProperty ID = new SimpleStringProperty();
	
	public String getTitolo() {
		return Titolo.get();
	}
	
	public void setTitolo(String titolo) {
		Titolo.set(titolo);
	}
	
	public String getArtista() {
		return Artista.get();
	}
	
	public void setArtista(String artista) {
		Artista.set(artista);
	}
	
	public Integer getAnno() {
		return Anno.get();
	}
	
	public void setAnno(Integer anno) {
		Anno.set(anno);
	}
	
	public String getID() {
		return ID.get();
	}
	
	public void setID(String id) {
		ID.set(id);
	}
}