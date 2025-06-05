package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Ingredient;

import javax.swing.text.html.ImageView;

public class FlecheObjet extends Ingredient {
    public FlecheObjet(int nb){
        super(9,nb);
    }

//    public ImageView getimage(){
//        return new ImageView();
//    }

    @Override
    public void agit(Player player) {

    }

}
