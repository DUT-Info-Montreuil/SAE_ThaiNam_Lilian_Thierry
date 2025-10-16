package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Pioche;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Inventaire;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Outil;

public class Pioche extends Outil {
    private int durabilité;
    private String type;

    public Pioche(int id, int dur, String type) {
        super(id, 1);
        this.durabilité = dur;
        this.type = type;
    }

    public ImageView getimage(){
        Image image = new Image("Pioche en bois.png");
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    @Override
    public void agit(Player player, double SourisX, double SourisY) {
        int x=Math.toIntExact(Math.round(SourisX)/ Param.scale);
        int y=Math.toIntExact(Math.round(SourisY)/ Param.scale);
        
        // Miner le bloc
        player.getEnv().changeBlock(y, x, 0);
        player.getVueTerrain().changementTuileMinage(x, y, 0);
        
        // Réduire la durabilité à chaque utilisation
        baisseDurabilite();
        System.out.println("Bloc miné ! Durabilité restante : " + this.durabilité);
        
        // Si la durabilité atteint zéro, retirer la pioche de l'inventaire
        if (this.durabilité <= 0) {
            Inventaire.getInstance().supprimerObjet(this.getIdObjet(), 1);
            System.out.println("Pioche cassée et retirée de l'inventaire !");
        } else {
            // Forcer la mise à jour de l'interface
            Inventaire.getInstance().forceUpdate();
        }
    }
    
    public void baisseDurabilite(){
        this.durabilité = Math.max(0, this.durabilité - 1);
    }
    
    public int getDurabilite() {
        return this.durabilité;
    }

}
