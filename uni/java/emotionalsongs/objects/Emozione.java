package emotionalsongs.objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Emozione {
	private final StringProperty Nome = new SimpleStringProperty();
	private final StringProperty Note = new SimpleStringProperty();
	private final IntegerProperty Valutazione = new SimpleIntegerProperty();
	
	public String getNome() {
		return Nome.get();
	}
	
	public void setNome(String nome) {
		Nome.set(nome);
	}
	
	public String getNote() {
		return Note.get();
	}
	
	public void setNote(String note) {
		Note.set(note);
	}
	
	public int getValutazione() {
		return Valutazione.get();
	}
	
	public void setValutazione(Integer valutazione) {
		Valutazione.set(valutazione);
	}
}