package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;


import javafx.scene.image.ImageView;

public abstract class Objet{

    private int idObjet;

    public Objet(int id) {this.idObjet=id;
    }

    public int getIdObjet() {
        return idObjet;
    }

    abstract public ImageView getimage();
}