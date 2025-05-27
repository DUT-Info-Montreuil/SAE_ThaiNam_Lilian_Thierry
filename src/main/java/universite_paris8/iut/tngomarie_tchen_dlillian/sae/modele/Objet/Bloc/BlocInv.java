package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class BlocInv extends Objet {
    public int nbBloc;

    public BlocInv(int id, int nb){
        super(id);
        this.nbBloc = nb;
    }

    public void ajouterBloc(){
        this.nbBloc++;
    }

    public void decrementBloc(int quantite){
        this.nbBloc= this.nbBloc-quantite;
    }

    public int getNbBloc(){
        return this.nbBloc;
    }
}
