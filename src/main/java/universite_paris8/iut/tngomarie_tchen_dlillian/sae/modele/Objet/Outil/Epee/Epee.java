package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee;

import javafx.scene.image.Image;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Inventaire;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Outil;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;


public class Epee extends Outil {
    private int degat;
    private int durabilité;
    private String type;

    public Epee(int id, int d, int dur, String type) {
        super(id,1);
        this.degat = d;
        this.durabilité = dur;
        this.type = type;
    }

    public ImageView getimage(){
        Image image = new Image("EpeeBois.png");
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    @Override
    public void agit(Player player ,double SourisX, double SourisY) {
        boolean attaqueReussie = false;
        
        // Attaquer les entités proches
        for(Entity mob : player.getEnv().getEntities()){
            if(mob != player && Math.abs(mob.getX() - player.getX()) < 30 && Math.abs(mob.getY() - player.getY()) < 30){
                mob.decrementerPv(this.degat);
                attaqueReussie = true;
                System.out.println("Attaque réussie ! Dégâts : " + this.degat);
            }
        }
        
        // Réduire la durabilité à chaque utilisation
        if (attaqueReussie) {
            baisseDurabilite();
            System.out.println("Durabilité restante : " + this.durabilité);
            
            // Si la durabilité atteint zéro, retirer l'épée de l'inventaire
            if (this.durabilité <= 0) {
                Inventaire.getInstance().supprimerObjet(this.getIdObjet(), 1);
                System.out.println("Épée cassée et retirée de l'inventaire !");
            } else {
                // Forcer la mise à jour de l'interface
                Inventaire.getInstance().forceUpdate();
            }
        }
    }

    public void baisseDurabilite(){
        this.durabilité = Math.max(0, this.durabilité - 1);
    }

    public int getDurabilité(Epee epee){
        return this.durabilité;
    }

    public int getDegat(Epee epee){
        return this.degat;
    }

    public String getType(Epee epee){
        return this.type;
    }

    public String toString(Epee epee){
        return "Epee en " + this.type;
    }


}
