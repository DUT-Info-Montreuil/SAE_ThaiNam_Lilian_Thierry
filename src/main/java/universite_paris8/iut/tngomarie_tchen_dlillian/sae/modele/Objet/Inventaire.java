package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue.VueObjet;

public class Inventaire {

    private ObservableList<Objet> Inventaire;
    private int enMain;
    private int caseVide;
    private ListObjet listObjet;
    private static Inventaire inv=new Inventaire();

    private Inventaire() {
        this.enMain = 0;
        this.caseVide =0;
        this.Inventaire = FXCollections.observableArrayList();
        this.listObjet = new ListObjet();
        this.Inventaire.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change change) {
            }
        });
    }
    public static Inventaire getInstance() {
        if (inv==null){
            inv = new Inventaire();
        }
        return inv;
    }

    public Objet objetEnMain(){
        return Inventaire.get(enMain);
    }

    public ObservableList<Objet> getInventaire(){
        return this.Inventaire;
    }
    public ListObjet getListObjet(){return this.listObjet;}

    public void changerObjet(int num){
        this.enMain = num;
    }

    public int getEnMain(){
        return this.enMain;
    }

    public void ajoutObjet(Objet objet){
        for (Objet item : this.Inventaire) {
            if (objet.equals(item)) {
                item.addNb(objet.getNb());
                return; // Fusion terminée, on sort
            }
        }

        // Si pas trouvé, on ajoute le nouvel objet
        this.Inventaire.add(objet);
    }
    public void supprimerObjet(int idObjet, int quantite) {
        for (Objet obj : this.Inventaire) {
            if (obj.getIdObjet() == idObjet) {
                obj.supnb(quantite);
                if (obj.getNb() <= 0) {
                    this.Inventaire.remove(obj);
                }
                break;
            }
        }
    }
}


