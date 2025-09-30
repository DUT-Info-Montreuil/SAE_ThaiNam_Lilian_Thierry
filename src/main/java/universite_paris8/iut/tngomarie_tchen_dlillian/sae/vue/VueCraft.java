package universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue;


import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.Controleur;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface.ListRecipe;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface.Recipe;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Craft;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.ListObjet;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class VueCraft {

    private ScrollPane scrollPane;
    private AnchorPane anchorPane;
    private Craft craft;
    private Controleur c;

    public VueCraft(ScrollPane scPane, AnchorPane aPane, Controleur c, Craft craft){
        this.scrollPane = scPane;
        this.anchorPane = aPane;
        this.c = c;
        this.craft = craft;
    }

    public void ajoutListe(VBox vBox, ListRecipe listRecipe, ListObjet listObjet){
        int nbCraft = 3;
        double hauteur = 200;
        double hauteurNbCraft = hauteur / nbCraft;

        for (int i = 0; i < listRecipe.getList().size() ; i++) {
            Recipe recipe = listRecipe.getList(i);
            int idObjetCree = recipe.getResulat()[0][0];

            if (listObjet.getItem(idObjetCree) == null) {
                continue;
            }

            HBox hBox = creeHbox(recipe, listObjet, hauteurNbCraft, i);
            vBox.getChildren().add(hBox);
        }
    }

    public HBox creeHbox (Recipe recipe, ListObjet listObjet, double hauteur, int indexCraft ) {
        HBox hBox = new HBox(10);
        hBox.setPrefHeight(hauteur);
        hBox.setStyle("-fx-border-color: gray;");
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5));

        Button boutonCraft = new Button("Craft");
        boutonCraft.setPrefHeight(60);

        StringBuilder contenuTooltip = new StringBuilder("Besoin :\n");
        int[][] input = recipe.getRecette();

        for (int j = 0; j < input[0].length; j++) {
            int idObjet = input[0][j];
            int quantite = input[1][j];
            Objet objet = listObjet.getItem(idObjet);

            String nomObjet = (objet != null) ? objet.getClass().getSimpleName() : "Objet inconnu";

            contenuTooltip.append("- ")
                    .append(nomObjet)
                    .append(" x")
                    .append(quantite)
                    .append("\n");
        }

        Tooltip tooltip = new Tooltip(contenuTooltip.toString());
        tooltip.setShowDelay(Duration.millis(200));
        tooltip.setHideDelay(Duration.millis(100));

        boutonCraft.setTooltip(tooltip);
        boutonCraft.setFocusTraversable(false);
        boutonCraft.setOnAction(e -> {
            Platform.runLater(() -> c.getPanneauEntity().requestFocus());
            System.out.println(indexCraft + ": ");
            this.craft.crafting(indexCraft);
        });

        int idObjetCree = recipe.getResulat()[0][0];
        Objet objetCree = listObjet.getItem(idObjetCree);

        ImageView imageView = objetCree.getimage();
        if (imageView == null) {
            imageView = new ImageView(new Image("default.png")); // pour ceux qui ont pas d'image
        }
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        hBox.getChildren().addAll(boutonCraft, imageView);

        return hBox;

    }

    public void afficherCraft(){scrollPane.setVisible(true);}
    public void dissimilerCraft(){scrollPane.setVisible(false);}

}
