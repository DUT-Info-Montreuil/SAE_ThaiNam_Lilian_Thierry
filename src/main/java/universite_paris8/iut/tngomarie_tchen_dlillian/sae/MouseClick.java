package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;

public class MouseClick implements EventHandler<MouseEvent> {
    private Player player;

    public MouseClick(Player p) {
        this.player = p;
    }

    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.isPrimaryButtonDown()){
            player.agit();
        }
        else if(mouseEvent.isSecondaryButtonDown()){

        }
    }
}
