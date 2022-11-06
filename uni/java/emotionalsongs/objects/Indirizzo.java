package emotionalsongs.objects;

public record Indirizzo(String qualif, String addr, Integer civic, String city, String prov, Integer cap) {
	@Override
	public String toString() {
		return qualif + " " + addr + " " + civic + ", " + city + " (" + prov + ") " + cap;
	}
}