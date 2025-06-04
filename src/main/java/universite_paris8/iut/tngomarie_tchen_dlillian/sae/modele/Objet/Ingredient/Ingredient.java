package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient;

import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Ingredient extends Objet {
    private int nbObjet;

    public Ingredient(int id,int nb){
        super(id);
        this.nbObjet = nb;
    }

    @Override
    public ImageView getimage() {
        return null;
    }

    public void ajouterIngredient(){
        this.nbObjet++;
    }

    public void decrementIngredient(int quantite){
        this.nbObjet= this.nbObjet-quantite;
    }

    public int getNbObjet(){
        return this.nbObjet;
    }
}
