package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Tissu extends Ingredient {

    public Tissu(int nb){
        super(29,nb);
    }

    @Override
    public ImageView getimage() {
        Image image = new Image("Tissu.png");
        ImageView imageView = new ImageView(image);
        return imageView;
    }

}
