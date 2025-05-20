package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import java.util.ArrayList;

public class Inventaire {
    private ArrayList<Objet> Inventaire;
    private int enMain;
    private int caseVide;

    public Inventaire(){
        this.Inventaire = new ArrayList<Objet>();
        this.enMain = 0;
        this.caseVide =0;
    }

    public Objet objetEnMain(){
        return Inventaire.get(enMain);
    }

    public void changerObjet(int num){
        this.enMain = num;
    }
    public int getEnMain(){
        return this.enMain;
    }

    public void ajoutObjet(Objet objet){
        this.Inventaire.add(caseVide,objet);
        caseVide++;
    }
}
