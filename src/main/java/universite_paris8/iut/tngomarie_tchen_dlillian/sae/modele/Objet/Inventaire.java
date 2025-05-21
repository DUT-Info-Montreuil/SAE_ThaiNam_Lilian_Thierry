package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Ingredient;

import java.util.ArrayList;

public class Inventaire {
    private ArrayList<Objet> Inventaire;
    private int enMain;
    private int caseVide;
    private Ingredient Objet1;
    private Ingredient Objet2;

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

    public void craft(Ingredient I1,Ingredient I2){
        //enorme else if pour les different craft possible en fonction des id
    }
}


