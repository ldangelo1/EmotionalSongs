module uni.emotionalsong {
	requires javafx.controls;
	requires javafx.fxml;
	
	requires com.dlsc.formsfx;
	requires java.sql;
	requires org.controlsfx.controls;
	requires org.kordamp.bootstrapfx.core;
	requires org.postgresql.jdbc;
	
	opens emotionalsongs to javafx.fxml;
	
	opens emotionalsongs.account to javafx.fxml;
	opens emotionalsongs.signup to javafx.fxml;
	
	exports emotionalsongs;
	exports emotionalsongs.account;
	exports emotionalsongs.signup;
}