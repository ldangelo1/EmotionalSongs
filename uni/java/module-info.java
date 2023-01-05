module uni.emotionalsong {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens database;
	opens emotionalsongs;
	opens emotionalsongs.account;
	opens emotionalsongs.objects;
	opens emotionalsongs.recensione;
	
	exports database;
	exports emotionalsongs;
	exports emotionalsongs.account;
	exports emotionalsongs.objects;
	exports emotionalsongs.recensione;
}