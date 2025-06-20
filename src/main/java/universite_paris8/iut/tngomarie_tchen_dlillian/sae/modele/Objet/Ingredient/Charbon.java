package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Charbon extends Ingredient {

    public Charbon(int nb){
        super(4,nb);
    }

    @Override
    public ImageView getimage() {
        Image image = new Image("Charbon.png");
        ImageView imageView = new ImageView(image);
        return imageView;
    }
}
