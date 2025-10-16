package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc;

import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Inventaire;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class BlocInv extends Objet {
    public int nbBloc;

    public BlocInv(int id, int nb){
        super(id,nb);
    }

    @Override
    public ImageView getimage() {
        return new ImageView("default.png");
    }

    @Override
    public void agit(Player player,double sourisX, double sourisY) {
        // Vérifier qu'on a au moins un bloc à placer
        if (this.getNb() > 0) {
            int x=Math.toIntExact(Math.round(sourisX))/ Param.scale;
            int y=Math.toIntExact(Math.round(sourisY))/ Param.scale;
            
            // Placer le bloc dans le terrain
            player.getEnv().changeBlock(x,y,getIdObjet());
            player.getVueTerrain().changementTuileMinage(x,y,getIdObjet());
            
            // Retirer un bloc de l'inventaire
            Inventaire.getInstance().supprimerObjet(this.getIdObjet(), 1);
            System.out.println("Bloc placé et retiré de l'inventaire. Quantité restante: " + (this.getNb() - 1));
        } else {
            System.out.println("Aucun bloc disponible pour le placement!");
        }
    }
}
