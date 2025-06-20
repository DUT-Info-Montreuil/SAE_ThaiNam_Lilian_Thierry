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
        // Obtenir les dimensions de l'écran pour ajuster les paramètres
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        
        // Ajuster les paramètres en fonction de la taille de l'écran
        universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param.updateForScreen(screenBounds);

        stage.setTitle("jeu");
        stage.setScene(scene);
        
        // Forcer le plein écran
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("Appuyez sur ÉCHAP pour quitter le plein écran");
        
        stage.show();
        
        // CORRECTION 3: S'assurer que la scène a le focus pour les inputs
        scene.getRoot().requestFocus();
    }


}
