module uni.emotionalsong {
	requires javafx.controls;
	requires javafx.fxml;
	
	requires com.dlsc.formsfx;
	requires java.sql;
	requires org.controlsfx.controls;
	requires org.kordamp.bootstrapfx.core;
	
	opens uni.emotionalsongs to javafx.fxml;
	
	opens uni.emotionalsongs.signin to javafx.fxml;
	opens uni.emotionalsongs.signup to javafx.fxml;
	
	exports uni.emotionalsongs;
	exports uni.emotionalsongs.signin;
	exports uni.emotionalsongs.signup;
}