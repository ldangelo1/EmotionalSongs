module uni.emotionalsong {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires org.postgresql.jdbc;
	
	opens emotionalsongs to javafx.fxml;
	opens emotionalsongs.objects to javafx.fxml;
	opens emotionalsongs.account to javafx.fxml;
	opens database to javafx.fxml;
	
	exports emotionalsongs;
	exports emotionalsongs.objects;
	exports emotionalsongs.account;
	exports database;
}