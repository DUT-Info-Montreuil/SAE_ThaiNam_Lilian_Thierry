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

        // Itérer sur les clés de la HashMap pour éviter les recettes null
        for (Integer key : listRecipe.getList().keySet()) {
            Recipe recipe = listRecipe.getList(key);
            
            // Vérification de sécurité pour éviter les recettes null
            if (recipe == null) {
                System.out.println("Attention: Recette null détectée pour la clé " + key);
                continue;
            }
            
            int idObjetCree = recipe.getResulat()[0][0];

            if (listObjet.getItem(idObjetCree) == null) {
                System.out.println("Objet cible non trouvé pour la recette " + key + " (ID: " + idObjetCree + ")");
                continue;
            }

            HBox hBox = creeHbox(recipe, listObjet, hauteurNbCraft, key);
            vBox.getChildren().add(hBox);
        }
    }

    public HBox creeHbox (Recipe recipe, ListObjet listObjet, double hauteur, int indexCraft ) {
        // Vérification de sécurité pour éviter les recettes null
        if (recipe == null) {
            System.err.println("Erreur: Tentative de création d'HBox avec une recette null (index: " + indexCraft + ")");
            return new HBox(); // Retourner une HBox vide pour éviter le crash
        }
        
        HBox hBox = new HBox(10);
        hBox.setPrefHeight(hauteur);
        hBox.setStyle("-fx-border-color: gray;");
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5));

        Button boutonCraft = new Button("Craft");
        boutonCraft.setPrefHeight(60);

        StringBuilder contenuTooltip = new StringBuilder("Besoin :\n");
        int[][] input = recipe.getRecette();
        
        // Vérifier que les données de la recette sont valides
        if (input == null || input.length == 0) {
            System.err.println("Erreur: Données de recette invalides (input null ou vide) pour l'index " + indexCraft);
            contenuTooltip.append("- Recette invalide\n");
        } else {
            for (int j = 0; j < input.length; j++) {
                if (input[j] == null || input[j].length < 2) {
                    System.err.println("Erreur: Données d'ingrédient invalides à l'index " + j + " pour la recette " + indexCraft);
                    continue;
                }
                
                int idObjet = input[j][0];
                int quantite = input[j][1];
                Objet objet = listObjet.getItem(idObjet);

                String nomObjet = (objet != null) ? objet.getClass().getSimpleName() : "Objet inconnu (ID: " + idObjet + ")";

                contenuTooltip.append("- ")
                        .append(nomObjet)
                        .append(" x")
                        .append(quantite)
                        .append("\n");
            }
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

        // Vérifier que les données de résultat sont valides
        int[][] resultat = recipe.getResulat();
        if (resultat == null || resultat.length == 0 || resultat[0] == null || resultat[0].length == 0) {
            System.err.println("Erreur: Données de résultat invalides pour la recette " + indexCraft);
            // Ajouter une image par défaut en cas d'erreur
            ImageView imageView = new ImageView(new Image("default.png"));
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            hBox.getChildren().addAll(boutonCraft, imageView);
            return hBox;
        }
        
        int idObjetCree = resultat[0][0];
        Objet objetCree = listObjet.getItem(idObjetCree);

        ImageView imageView;
        if (objetCree != null) {
            imageView = objetCree.getimage();
            if (imageView == null) {
                imageView = new ImageView(new Image("default.png")); // pour ceux qui ont pas d'image
            }
        } else {
            System.err.println("Attention: Objet résultat non trouvé pour l'ID " + idObjetCree + " (recette " + indexCraft + ")");
            imageView = new ImageView(new Image("default.png")); // Image par défaut
        }
        
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        hBox.getChildren().addAll(boutonCraft, imageView);

        return hBox;
    }

    public void afficherCraft(){scrollPane.setVisible(true);}
    public void dissimilerCraft(){scrollPane.setVisible(false);}

}
