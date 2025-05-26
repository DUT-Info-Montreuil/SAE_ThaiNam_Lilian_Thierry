package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

public abstract class Objet{

    private int idObjet;

    public Objet(int id) {
        this.idObjet=id;
    }

    public int getIdObjet() {
        return idObjet;
    }
}