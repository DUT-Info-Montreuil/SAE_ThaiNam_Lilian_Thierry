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

    public Inventaire() {
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

    public void supprimerObjet(Objet objet){
        this.Inventaire.remove(objet);
    }

    public void supprimerIngredient(Ingredient ingredient,int quantité){
        for(int i = 0 ; i<this.Inventaire.size() ; i++){
            if(this.Inventaire.get(i).getIdObjet()==ingredient.getIdObjet()){
                if(ingredient.getNbObjet()>quantité){
                    ingredient.decrementIngredient(quantité);
                }
                else{
                    ingredient.decrementIngredient(ingredient.getNbObjet());
                    supprimerObjet(ingredient);
                }
            }
        }
    }
}


