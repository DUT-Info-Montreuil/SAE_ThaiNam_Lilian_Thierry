package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Arc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee;


public class KeyPressed implements EventHandler<KeyEvent>{
    private Player player;
    private Controleur c;
    private boolean activerInv = false;
    @FXML
    private Pane slot1;
    @FXML
    private Pane slot2;
    @FXML
    private Pane slot3;
    @FXML
    private Pane slot4;
    @FXML
    private Pane slot5;
    @FXML
    private Pane slot6;
    @FXML
    private Pane slot7;

    public KeyPressed(Player player,Controleur c, Pane slot1, Pane slot2, Pane slot3, Pane slot4, Pane slot5, Pane slot6, Pane slot7) {
        this.player = player;
        this.c = c;
        this.slot1 = slot1;
        this.slot2 = slot2;
        this.slot3 = slot3;
        this.slot4 = slot4;
        this.slot5 = slot5;
        this.slot6 = slot6;
        this.slot7 = slot7;

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

            case NUMPAD1:
                System.out.println("& ou 1");
                c.afficherCaseInv(slot1);
                break;
            case NUMPAD2:
                System.out.println("2");
                c.afficherCaseInv(slot2);
                break;
            case NUMPAD3:
                System.out.println("3");
                c.afficherCaseInv(slot3);
                break;
            case NUMPAD4:
                System.out.println("4");
                c.afficherCaseInv(slot4);
                break;
            case NUMPAD5:
                System.out.println("5");
                c.afficherCaseInv(slot5);
                break;
            case NUMPAD6:
                System.out.println("6");
                c.afficherCaseInv(slot6);
                break;
            case NUMPAD7:
                System.out.println("7");
                c.afficherCaseInv(slot7);
                break;
        }
    }

}