package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Main1 extends Application {
    FXMLLoader fxmlLoader = new FXMLLoader(Main1.class.getResource("vue2.fxml"));
    public Scene scene;

    {
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("execption");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("jeu");
        stage.sizeToScene();
        stage.setScene(scene);
        stage.show();

    }


}
