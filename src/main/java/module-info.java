module uni.emotionalsong {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens uni.emotionalsongs.search to javafx.fxml;
    opens uni.emotionalsongs.signin to javafx.fxml;
    opens uni.emotionalsongs.signup to javafx.fxml;

    exports uni.emotionalsongs.search;
    exports uni.emotionalsongs.signin;
    exports uni.emotionalsongs.signup;
}