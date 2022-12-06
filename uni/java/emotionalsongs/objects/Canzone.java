package emotionalsongs.objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Canzone {
	private final StringProperty Titolo = new SimpleStringProperty();
	private final StringProperty Artista = new SimpleStringProperty();
	private final IntegerProperty Anno = new SimpleIntegerProperty();
	
	public String getTitolo() {
		return Titolo.get();
	}
	
	public void setTitolo(String titolo) {
		this.Titolo.set(titolo);
	}
	
	public String getArtista() {
		return Artista.get();
	}
	
	public void setArtista(String artista) {
		this.Artista.set(artista);
	}
	
	public Integer getAnno() {
		return Anno.get();
	}
	
	public void setAnno(Integer anno) {
		this.Anno.set(anno);
	}
}