package universite_paris8.iut.tngomarie_tchen_dlillian.sae.listeneur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;

public class MouseClick implements EventHandler<MouseEvent> {
    private Player player;

    public MouseClick(Player p) {
        this.player = p;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        double sourisX = mouseEvent.getX();
        double joueurX = player.getX();

        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if (sourisX > joueurX) {
                player.setDirection(1);
            } else {
                player.setDirection(-1);
            }
            player.agit();
            System.out.println("click gauche");
            System.out.println("Joueur X : " + joueurX + ", Souris X : " + sourisX);
        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            System.out.println("click droit");
        }
    }
}
