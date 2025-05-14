package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;


public class KeyPressed implements EventHandler<KeyEvent>{
    private Player player;
    Controleur controleur = new Controleur();

    public KeyPressed(Player player) {
        this.player = player;
    }

    public void handle(KeyEvent event) {
        switch(event.getCode()) {
            case RIGHT:
                System.out.println("droite");
                controleur.aDroite();
                player.aDroite();
                break;
            case LEFT:
                System.out.println("gauche");
                controleur.aGauche();
                player.aGauche();
                break;
        }
    }

}
