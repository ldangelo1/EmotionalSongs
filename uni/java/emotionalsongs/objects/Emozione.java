package emotionalsongs.objects;

import javafx.beans.property.*;

public class Emozione {
	private final IntegerProperty Count = new SimpleIntegerProperty();
	private final StringProperty Nome = new SimpleStringProperty();
	private final StringProperty Note = new SimpleStringProperty();
	private final DoubleProperty Valutazione = new SimpleDoubleProperty();
	
	public int getCount() {
		return Count.get();
	}
	
	public void setCount(Integer count) {
		Count.set(count);
	}
	
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
	
	public double getValutazione() {
		return Valutazione.get();
	}
	
	public void setValutazione(Integer valutazione) {
		Valutazione.set(valutazione);
	}
}