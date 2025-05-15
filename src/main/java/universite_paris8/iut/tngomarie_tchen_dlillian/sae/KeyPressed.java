package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;


public class KeyPressed implements EventHandler<KeyEvent>{
    private Player player;

    public KeyPressed(Player player) {
        this.player = player;
    }

    public void handle(KeyEvent event) {
        switch(event.getCode()) {
            case RIGHT:
                player.aDroite();
                break;
            case LEFT:
                player.aGauche();
                break;
            case UP:
                player.saute();
                break;
        }
    }

}