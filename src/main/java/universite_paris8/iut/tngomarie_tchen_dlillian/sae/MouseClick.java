package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;

public class MouseClick implements EventHandler<MouseEvent> {
    private Player player;

    public MouseClick(Player p) {
        this.player = p;
    }

    public void handle(MouseEvent mouseEvent) {
        double sourisX = mouseEvent.getX();
        System.out.println("sourisX = " + sourisX + ", playerX = " + player.getX());


        if(mouseEvent.getButton() == MouseButton.PRIMARY){
            if (sourisX < player.getX()) {
                System.out.println("clic à gauche");
                player.setDirection(-1); // gauche
            } else if (sourisX > player.getX()) {
                System.out.println("clic à droite");
                player.setDirection(1); // droite
            }
            player.agit();
        }
        else if(mouseEvent.getButton() == MouseButton.SECONDARY){
            System.out.println("click gauche");

        }
    }
}
