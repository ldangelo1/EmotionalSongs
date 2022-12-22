package emotionalsongs;

import database.serverES;
import emotionalsongs.objects.Playlist;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;


public class addSong extends Application {

    TableView<Playlist> lista = new TableView<>();

   // ListView<String> lista = new ListView<>();

    public Playlist nomePlay;

    @Override
    public void start(Stage primaryStage) throws SQLException {
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Table View Sample");
        primaryStage.setWidth(260);
        primaryStage.setHeight(470);
        primaryStage.setResizable(false);

        Button add = new Button("Aggiungi Canzone");

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                nomePlay = selectPlaylist();
            }
        });

        queryPList();

        final VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().addAll(lista, add);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void queryPList() throws SQLException {
        ResultSet rset = serverES.select("Playlist", "WHERE \"CF\"='" + clientESController.getCF() + "'");
        ObservableList<Playlist> data = FXCollections.observableArrayList();

        assert rset != null;
        while (rset.next()) {
            Playlist playlist = new Playlist();
            playlist.setNome(rset.getString("Nome"));
            data.add(playlist);
        }
        lista.setItems(data);
        rset.close();
    }

    private Playlist selectPlaylist(){
        return lista.getSelectionModel().getSelectedItem();
    }

    private void initPListTable(TableColumn<Playlist, String> colPList) {
        colPList.setCellValueFactory(new PropertyValueFactory<>("Nome"));
    }

    @Override
    public void init() throws Exception {
        super.init();
        TableColumn<Playlist, String> colPList = new TableColumn<>("Nome");
        lista.getColumns().add(colPList);
        initPListTable(colPList);
        queryPList();
    }
}
