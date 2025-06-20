package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Fer extends Ingredient {
    public Fer(int nb){
        super(2,nb);
    }
    @Override
    public ImageView getimage() {
        Image image = new Image("Fer.png");
        ImageView imageView = new ImageView(image);

        return imageView;
    }

}
