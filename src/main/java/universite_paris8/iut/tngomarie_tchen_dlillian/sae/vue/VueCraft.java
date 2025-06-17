package universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class VueCraft {

    private ScrollPane scrollPane;
    private AnchorPane anchorPane;

    public VueCraft(ScrollPane scPane, AnchorPane aPane){
        this.scrollPane = scPane;
        this.anchorPane = aPane;
    }

    public void ajouteListe(VBox vBox){

        int nbCraft = 7;
        double hauteur = 500;
        double hauteurNbCraft = hauteur/nbCraft;
        for(int i = 0; i < 7; i++){
            HBox hBox = new HBox(10);
            hBox.setPrefHeight(hauteurNbCraft);
            hBox.setStyle("-fx-border-color: gray;");
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPadding(new Insets(1));



            Button boutonCraft = new Button("Craft");
            boutonCraft.setPrefWidth(90);
            boutonCraft.setPrefHeight(10);
            boutonCraft.setOnAction(e -> {
                System.out.println("Craft bouton");
            });


            hBox.getChildren().add(boutonCraft);
            vBox.getChildren().add(hBox);
        }
    }

//    public HBox craftBloc(Objet ){
//
//    }

}
