package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Plante extends Ingredient{

    public Plante(int nb){
        super(7,nb);
    }

    @Override
    public ImageView getimage() {
        Image image = new Image("Plante.png");
        ImageView imageView = new ImageView(image);
        return imageView;
    }
}
