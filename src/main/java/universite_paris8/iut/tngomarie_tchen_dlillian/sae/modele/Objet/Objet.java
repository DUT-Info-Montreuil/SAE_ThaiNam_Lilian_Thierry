package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;


import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;

public abstract class  Objet{

    private int idObjet;
    public int nb;

    public Objet(int id, int nb) {
        this.idObjet=id;
        this.nb = nb;
    }

    public int getIdObjet() {
        return idObjet;
    }

    public int getNb() {
        return nb;
    }

    public void addNb(int nb) {
        this.nb += nb;
    }

    abstract public ImageView getimage();

    abstract public void agit(Player player,double sourisX,double sourisY);
}