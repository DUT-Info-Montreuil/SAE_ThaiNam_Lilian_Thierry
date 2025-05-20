package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;


public class KeyPressed implements EventHandler<KeyEvent>{
    private Player player;
    private Controleur c;
    private boolean activerInv = false;

    public KeyPressed(Player player,Controleur c) {
        this.player = player;
        this.c = c;
    }

    public void handle(KeyEvent event) {
        switch(event.getCode()) {
            case RIGHT:
                player.activeDroite();
                break;
            case LEFT:
                player.activeGauche();
                break;
            case UP:
                player.saute();
                break;
            case E:
                activerInv = !activerInv;
                if(!activerInv){
                    c.deactiverInv();
                }
                else{
                    c.afficherInv();
                }
                break;
        }
    }

}