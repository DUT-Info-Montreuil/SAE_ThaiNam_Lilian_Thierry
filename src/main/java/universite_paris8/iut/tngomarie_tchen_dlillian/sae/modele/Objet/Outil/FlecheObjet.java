package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Ingredient;


public class FlecheObjet extends Ingredient {
    public FlecheObjet(int nb){
        super(9,nb);
    }

    public ImageView getimage(){
        Image image = new Image("Fleche.png");
        ImageView imageView = new ImageView(image);
        return  imageView;
    }

    @Override
    public void agit(Player player,double SourisX, double SourisY) {

    }

}
