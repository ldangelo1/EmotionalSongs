module uni.emotionalsong {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens emotionalsongs to javafx.fxml;
	
	opens emotionalsongs.account to javafx.fxml;
	
	exports emotionalsongs;
	exports emotionalsongs.account;
}