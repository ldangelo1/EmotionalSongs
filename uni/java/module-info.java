module uni.emotionalsong {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires org.postgresql.jdbc;
	
	opens database to javafx.fxml;
	opens emotionalsongs to javafx.fxml;
	opens emotionalsongs.account to javafx.fxml;
	opens emotionalsongs.objects to javafx.fxml;
	opens emotionalsongs.recensione to javafx.fxml;
	
	exports database;
	exports emotionalsongs;
	exports emotionalsongs.account;
	exports emotionalsongs.objects;
	exports emotionalsongs.recensione;
}