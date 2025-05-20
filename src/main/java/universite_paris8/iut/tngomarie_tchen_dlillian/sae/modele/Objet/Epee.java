package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

public class Epee extends Objet{
    private int degat;
    private int durabilité;
    private String type;

    public Epee(int id, int d, int dur, String type) {
        super(id);
        this.degat = d;
        this.durabilité = dur;
        this.type = type;
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
