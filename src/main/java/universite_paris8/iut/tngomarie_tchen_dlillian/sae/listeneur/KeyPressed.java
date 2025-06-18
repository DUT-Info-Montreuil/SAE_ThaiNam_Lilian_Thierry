package universite_paris8.iut.tngomarie_tchen_dlillian.sae.listeneur;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.Controleur;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Pierre;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Baton;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Fils;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Arc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee.Epee;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.FlecheObjet;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue.VueObjet;


public class KeyPressed implements EventHandler<KeyEvent>{
    private Player player;
    private Controleur c;
    private VueObjet vueObjet;
    private boolean activerPane = false;

    public KeyPressed(Player player, Controleur c, VueObjet vue) {
        this.player = player;
        this.c = c;
        this.vueObjet = vue;
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
                activerPane = !activerPane;
                if(!activerPane){
                    vueObjet.dissimilerInv();
                    c.dissimilerCraft();
                }
                else{

                    vueObjet.afficherInv();
                    c.afficherCraft();

                }
                Platform.runLater(() -> c.getPanneauEntity().requestFocus() );
                break;
            case R:
                vueObjet.getFullImage();
                break;
            case N:
                player.getInventaire().ajoutObjet(new Arc());
                player.getInventaire().ajoutObjet(new Pierre(1));
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
                    player.getInventaire().ajoutObjet(new FlecheObjet(1));
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
                    player.getInventaire().ajoutObjet(new Baton(1));
                }
                vueObjet.getFullImage();
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
                    player.getInventaire().ajoutObjet(new Fils(1));
                }
                break;

            case C:
                this.player.degatjoueur(10);
                System.out.println("degat au joueur"+this.player.getpvPropProperty()+"::::"+this.player.getPv());
                break;
            case V:
//                this.player.getInventaire().craft();
                break;
            case NUMPAD1:
                System.out.println("1");
                vueObjet.getIndexPane(1);
                vueObjet.afficherCaseInv(vueObjet.getSlotDepuisIndice(1));
                player.getInventaire().changerObjet(0);
                break;
            case NUMPAD2:
                System.out.println("2");
                vueObjet.getIndexPane(2);
                vueObjet.afficherCaseInv(vueObjet.getSlotDepuisIndice(2));
                player.getInventaire().changerObjet(1);
                break;
            case NUMPAD3:
                System.out.println("3");
                vueObjet.getIndexPane(3);
                vueObjet.afficherCaseInv(vueObjet.getSlotDepuisIndice(3));
                player.getInventaire().changerObjet(2);
                break;
            case NUMPAD4:
                System.out.println("4");
                vueObjet.getIndexPane(4);
                vueObjet.afficherCaseInv(vueObjet.getSlotDepuisIndice(4));
                player.getInventaire().changerObjet(3);
                break;
            case NUMPAD5:
                System.out.println("5");
                vueObjet.getIndexPane(5);
                vueObjet.afficherCaseInv(vueObjet.getSlotDepuisIndice(5));
                player.getInventaire().changerObjet(4);
                break;
            case NUMPAD6:
                System.out.println("6");
                vueObjet.getIndexPane(6);
                vueObjet.afficherCaseInv(vueObjet.getSlotDepuisIndice(6));
                player.getInventaire().changerObjet(5);
                break;
            case NUMPAD7:
                System.out.println("7");
                vueObjet.getIndexPane(7);
                vueObjet.afficherCaseInv(vueObjet.getSlotDepuisIndice(7));
                player.getInventaire().changerObjet(6);
                break;
        }
    }

}