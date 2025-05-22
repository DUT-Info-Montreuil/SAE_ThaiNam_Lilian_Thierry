package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Bois;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Baton;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Fils;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Ingredient;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Arc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee;

import java.util.ArrayList;

public class Inventaire {
    private ArrayList<Objet> Inventaire;
    private int enMain;
    private int caseVide;
    private Ingredient Objet1;
    private Ingredient Objet2;

    //different boolean pour le craft
    private boolean craftArc = false;
    private boolean craftEpee = false;

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
    //la fonction craft est appeler avec un bouton dans l'inventaire selon le booleen qui est active aussi dans l'inventaire selon le craft selectionner
    public void craft() {
        if (craftArc) {
            craftArc = false;
            Baton batonTrouve = null;
            Fils filsTrouve = null;
            // Recherche dans l'inventaire
            for (Objet obj : Inventaire) {
                if (obj instanceof Baton && ((Baton) obj).getNbObjet() >= 3) {
                    batonTrouve = (Baton) obj;
                }
                if (obj instanceof Fils && ((Fils) obj).getNbObjet() >= 3) {
                    filsTrouve = (Fils) obj;
                }
            }
            // Si les deux ingrédients sont disponibles on supprime les ingredient et ajoute l'objet
            if (batonTrouve != null && filsTrouve != null) {
                supprimerIngredient(batonTrouve, 3);
                supprimerIngredient(filsTrouve, 3);
                this.ajoutObjet(new Arc(8));
            }}
        else if (craftEpee) {
            craftEpee=false;
            Baton batonTrouve = null;
            Bois boisTrouve = null;
            for(Objet obj : Inventaire){
                if(obj instanceof Baton && ((Baton) obj).getNbObjet() >= 1) {
                    batonTrouve = (Baton) obj;
                }
                if (obj instanceof Bois && ((Bois) obj).getNbObjet() >= 2) {
                    boisTrouve = (Bois) obj;
                }
            }
            if(batonTrouve != null && boisTrouve != null){
                supprimerIngredient(batonTrouve,1);
                supprimerIngredient(boisTrouve,2);
                this.ajoutObjet(new Epee(10,20,50,"Bois"));
            }
        }
    }


    //differente fonction booleen;
    public void setCraftArc(){
        this.craftArc=true;
    }
    public void setCraftEpee(){
        this.craftEpee=true;
    }
}


