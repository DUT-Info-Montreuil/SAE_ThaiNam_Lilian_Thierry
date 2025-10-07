package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.projectile.Fleche;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Inventaire;

public class Arc extends Outil {

    public Arc(){
        super(8,1);
    }

    public ImageView getimage(){
        Image image = new Image("Arc Bois.png");
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    public void agit(Player player,double SourisX, double SourisY){
        for(int i = 0; i < Inventaire.getInstance().getInventaire().size(); i++) {
            if (Inventaire.getInstance().getInventaire().get(i) instanceof FlecheObjet && ((FlecheObjet) Inventaire.getInstance().getInventaire().get(i)).getNbObjet() >= 1) {
                Fleche f = new Fleche(player.getX(), player.getY(), (int) (20 * player.getDirection()), player.getEnv(), 1,player.getDirection());
                player.getEnv().addentities(f);
                ((FlecheObjet) Inventaire.getInstance().getInventaire().get(i)).decrementIngredient(1);
            }
        }
    }
}
