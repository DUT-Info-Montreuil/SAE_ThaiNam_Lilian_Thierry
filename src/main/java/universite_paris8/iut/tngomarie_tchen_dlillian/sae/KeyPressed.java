package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Arc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Epee;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;


public class KeyPressed implements EventHandler<KeyEvent>{
    private Player player;


    public KeyPressed(Player player) {
        this.player = player;
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
            case N:
                player.getInventaire().ajoutObjet(new Arc(0));

                player.getInventaire().ajoutObjet(new Epee(0,20,50,"bois"));
                System.out.println("keypresed objet");
                break;
            case P:
                player.getInventaire().changerObjet(player.getInventaire().getEnMain()+1);
                System.out.println("changer objet ++");
                break;
            case O:
                player.getInventaire().changerObjet(player.getInventaire().getEnMain()-1);
                System.out.println("changer objet --");
                break;
        }
    }

}