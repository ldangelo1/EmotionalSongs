package emotionalsongs.objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Playlist {
	private final StringProperty Nome = new SimpleStringProperty();
	
	public String getNome() {
		return Nome.get();
	}
	
	public void setNome(String nome) {
		Nome.set(nome);
	}
}