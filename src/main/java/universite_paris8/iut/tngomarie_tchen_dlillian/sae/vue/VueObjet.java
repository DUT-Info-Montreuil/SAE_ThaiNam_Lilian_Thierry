package universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.spreadsheet.Grid;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Inventaire;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VueObjet {
    
    private GridPane gridPane;
    
    private Inventaire inventaire;
    private Player player;

    private List<Pane> slotsInventairePrimaire;
    private List<Pane> slotsInvSecondaire;

    public VueObjet(GridPane pane, Player p){
        this.player = p;
        this.gridPane = pane;
        this.slotsInventairePrimaire = new ArrayList<Pane>();
        this.slotsInvSecondaire = new ArrayList<Pane>();
    }
    public void afficherInv(){this.gridPane.setVisible(true);}
    public void dissimilerInv() {this.gridPane.setVisible(false);}

    public void afficherCaseInv(Pane paneau){
        for (Pane slot : slotsInventairePrimaire) {
            if (slot == paneau) {
                slot.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-background-color: gray;");
            } else {
                slot.setStyle("-fx-border-color: transparent; -fx-border-width: 1; -fx-background-color: gray;");
            }
        }
    }

    public void mettreObjetVue(Pane slot, Objet objet) {
        ImageView imageView = objet.getimage();
        imageView.setLayoutX(12);
        imageView.setLayoutY(5);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        slot.getChildren().clear();
        slot.getChildren().add(imageView);
    }

    public Pane getSlotDepuisIndice(int indice) {
        if (indice >= 1 && indice <= 7) {
            return slotsInventairePrimaire.get(indice - 1);
        } else if (indice >= 8 && indice <= 21) {
            return slotsInvSecondaire.get(indice - 8);
        } else {
            return null;
        }
    }

    public Pane getIndexPane(int index){
        System.out.println(this.gridPane.getChildren().get(index-1));
        return (Pane) this.gridPane.getChildren().get(index-1);
    }

    public void getFullImage(){
        List<Objet> objets = this.player.getInventaire().getInventaire();

        for (int i = 0; i < this.slotsInventairePrimaire.size(); i++) {
            if(i < objets.size()) {
                Objet objet = objets.get(i);
                if (objet != null) {
                    mettreObjetVue(slotsInventairePrimaire.get(i), objet);
                }else{
                    slotsInventairePrimaire.get(i).getChildren().clear();
                }
            }
        }

        for (int i = 0; i < this.slotsInvSecondaire.size(); i++) {
            int indexInventaire = i + slotsInventairePrimaire.size();
            if(indexInventaire < objets.size()) {
                Objet objet = objets.get(indexInventaire);
                if (objet != null) {
                    mettreObjetVue(slotsInvSecondaire.get(i), objet);
                }else{
                    slotsInvSecondaire.get(i).getChildren().clear();
                }
            }
        }
    }

    public List<Pane> getSlotsInventairePrimaire() {return slotsInventairePrimaire;}
    public void setSlotsInventairePrimaire(List<Pane> slotsInventairePrimaire) {this.slotsInventairePrimaire = slotsInventairePrimaire;}

    public List<Pane> getSlotsInvSecondaire() {return slotsInvSecondaire;}
    public void setSlotsInvSecondaire(List<Pane> slotsInvSecondaire) {this.slotsInvSecondaire = slotsInvSecondaire;}

    public GridPane getPane() {
        return gridPane;
    }
}
