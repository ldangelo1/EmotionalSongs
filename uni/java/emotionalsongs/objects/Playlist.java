package emotionalsongs.objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Playlist {
	private final StringProperty Nome = new SimpleStringProperty();
	private final IntegerProperty Cont = new SimpleIntegerProperty();
	
	public String getNome() {
		return Nome.get();
	}
	
	public void setNome(String nome) {
		Nome.set(nome);
	}
	
	public int getCont() {
		return Cont.get();
	}
	
	public void setCont(Integer cont) {
		Cont.set(cont);
	}
}