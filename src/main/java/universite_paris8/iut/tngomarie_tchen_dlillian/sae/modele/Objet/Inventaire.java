package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface.ListRecipe;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.BlocInv;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Bois;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Météorite;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Météorite;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Arc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Outil;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Sac;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.*;

import java.util.ArrayList;

public class Inventaire {

    private ArrayList<Objet> Inventaire;
    private int enMain;
    private int caseVide;


    //different boolean pour le craft
    private boolean craftArc = false;
    private boolean craftEpeeBois = false;
    private boolean craftEpeeFer = false;
    private boolean craftEpeeOr =false;
    private boolean craftEpeeMeteore = false;
    private boolean craftBaton = false;
    private boolean craftTissu = false;
    private boolean craftSac = false;
    private boolean craftFils = false;

    public Inventaire(){
        this.Inventaire = new ArrayList<Objet>();
        this.enMain = 0;
        this.caseVide =0;
    }

    public Objet objetEnMain(){
        return Inventaire.get(enMain);
    }

    public ArrayList<Objet> getInventaire(){
        return this.Inventaire;
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

    public void ajouterObjet2(Objet objet){
        for(int i = 0; i < this.Inventaire.size(); i++){
            if(objet == this.Inventaire.get(i) && !(objet instanceof Outil)){
                this.Inventaire.get(i).addNb(objet.getNb());
            }
        }
        int i = 0;
        while(this.Inventaire.get(i) != null){
            if(this.Inventaire.get(i) == null){
                this.Inventaire.get(i).addNb(objet.getNb());
            }
            i++;
        }
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
    public void supprimerBloc(BlocInv bloc, int quantité){
        for(int i = 0 ; i<this.Inventaire.size() ; i++){
            if(this.Inventaire.get(i).getIdObjet()==bloc.getIdObjet()){
                if(bloc.getNbBloc()>quantité){
                    bloc.decrementBloc(quantité);
                }
                else{
                    bloc.decrementBloc(bloc.getNbBloc());
                    supprimerObjet(bloc);
                }
            }
        }

    }
//    //la fonction craft est appeler avec un bouton dans l'inventaire selon le booleen qui est active aussi dans l'inventaire selon le craft selectionner
//    public void craft() {
//    }
//
//
//    //differente fonction booleen;
//    public void setCraftArc(){
//        this.craftArc=true;
//        System.out.println("setcraftarc reussi");
//    }
//    public void setCraftEpeeBois(){
//        this.craftEpeeBois=true;
//    }
//    public void setCraftEpeeFer(){this.craftEpeeFer=true;}
//    public void setCraftEpeeOr(){this.craftEpeeOr=true;}
//    public void setCraftEpeeMeteore(){this.craftEpeeMeteore=true;}
//    public void setCraftBaton(){this.craftBaton=true;}
//    public void setCraftTissu(){this.craftTissu=true;}
//    public void setCraftSac(){this.craftSac=true;}
//    public void setCraftFils(){this.craftFils=true;}

    public void affiche(){System.out.println(this.Inventaire);}
}


