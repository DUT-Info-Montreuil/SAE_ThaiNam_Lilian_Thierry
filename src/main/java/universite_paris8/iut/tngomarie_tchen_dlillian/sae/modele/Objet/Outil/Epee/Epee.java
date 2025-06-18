package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee;

import javafx.scene.image.Image;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
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
        for(Entity mob : player.getEnv().getEntities()){
            if(mob.getX() - player.getX() < 20 && mob.getY() - player.getX() <20){
                mob.decrementerPv(this.degat);
            }
        }
    }

    public void baisseDurabilite(Epee epee){
        this.durabilité = this.durabilité-1;
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
