package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;

public class KeyReleased implements EventHandler<KeyEvent> {
    private Player player;

    public KeyReleased(Player p) {
        player = p;
    }

    public void handle(KeyEvent event) {
        if(event.getCode() == KeyCode.LEFT ) {
            this.player.deactiveGauche();
        }
        else if(event.getCode() == KeyCode.RIGHT ) {
            this.player.deactiveDroite();
        }

    }
}
