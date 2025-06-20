package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient;

import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Ingredient extends Objet {
    private int nbObjet;

    public Ingredient(int id,int nb){
        super(id,nb);
    }

    @Override
    public ImageView getimage() {
        return new ImageView("default.png");
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

    public void agit(Player player,double SourisX, double SourisY){for(Entity mob : player.getEnv().getEntities()){
        if(mob.getX() - player.getX() < 20 && mob.getY() - player.getX() <20){
            mob.decrementerPv(1);
        }
    }}
}
