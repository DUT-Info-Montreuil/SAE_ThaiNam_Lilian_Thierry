package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Application extends javafx.application.Application{
    Stage stage = new Stage();
    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("vue1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),608,384);
        Controleur controleur = fxmlLoader.getController();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent keyEvent) {

                switch(keyEvent.getCode()) {
                    case D:
                        System.out.println("droite");
                        controleur.aDroite();
                        break;
                    case Q:
                        System.out.println("gauche");
                        controleur.aGauche();
                        break;

                    case E:
                        System.out.println("Inventaire");
                        break;

                }
            }
        });
    }
}
