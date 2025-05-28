package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Bois;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Baton;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Fils;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Arc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.FlecheObjet;


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
            case B:
                boolean fleche=false;
                for(int i=0 ; i<player.getInventaire().getInventaire().size() ; i++){
                    if(player.getInventaire().getInventaire().get(i) instanceof FlecheObjet){
                        for(int j = 0;j<20;j++){  //pour avoir 20 flech d'un coup
                            ((FlecheObjet) player.getInventaire().getInventaire().get(i)).ajouterIngredient();
                        }
                        System.out.println("20 fleches ajouté");
                        fleche=true;
                    }
                }
                if(!fleche){
                    System.out.println("fleche ajouté");
                    player.getInventaire().ajoutObjet(new FlecheObjet());
                }
                break;
            case W:
                boolean baton=false;
                for(int i=0 ; i<player.getInventaire().getInventaire().size() ; i++){
                    if(player.getInventaire().getInventaire().get(i) instanceof Baton){
                        for(int j = 0;j<20;j++){  //pour avoir 20 bois d'un coup
                            ((Baton) player.getInventaire().getInventaire().get(i)).ajouterIngredient();
                        }
                        System.out.println("20 baton ajouté");
                        baton=true;
                    }
                }
                if(!baton){
                    System.out.println("baton ajouté");
                    player.getInventaire().ajoutObjet(new Baton());
                }
                break;
            case X:
                boolean fils=false;
                for(int i=0 ; i<player.getInventaire().getInventaire().size() ; i++){
                    if(player.getInventaire().getInventaire().get(i) instanceof Fils){
                        for(int j = 0;j<20;j++){  //pour avoir 20 bois d'un coup
                            ((Fils) player.getInventaire().getInventaire().get(i)).ajouterIngredient();
                        }
                        System.out.println("20 fils ajouté");
                        fils=true;
                    }
                }
                if(!fils){
                    System.out.println("fils ajouté");
                    player.getInventaire().ajoutObjet(new Fils());
                }
                break;

            case C:
                this.player.getInventaire().setCraftArc();
                System.out.println("setcraftarc");
                break;
            case V:
                this.player.getInventaire().craft();
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