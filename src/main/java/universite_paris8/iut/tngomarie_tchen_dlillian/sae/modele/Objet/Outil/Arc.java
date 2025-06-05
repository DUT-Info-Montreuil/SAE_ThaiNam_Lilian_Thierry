package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Fleche;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Arc extends Outil {

    public Arc(){
        super(8,1);
    }

    public ImageView getimage(){
        Image image = new Image("Arc Bois.png");
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    public void agit(Player player){
        for(int i = 0 ; i < player.getInventaire().getInventaire().size(); i++) {
            if (player.getInventaire().getInventaire().get(i) instanceof FlecheObjet && ((FlecheObjet) player.getInventaire().getInventaire().get(i)).getNbObjet() >= 1) {
                Fleche f = new Fleche(player.getX(), player.getY(), (int) (20 * player.getDirection()), player.getEnv(), 1,player.getDirection());
                player.getEnv().addentities(f);
                ((FlecheObjet) player.getInventaire().getInventaire().get(i)).decrementIngredient(1);
            }
        }
    }
}
