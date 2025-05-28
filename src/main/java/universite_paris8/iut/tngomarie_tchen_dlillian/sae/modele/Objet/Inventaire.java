package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.BlocInv;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Bois;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Baton;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Fils;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Ingredient;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Tissu;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Arc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Sac;

import java.util.ArrayList;

public class Inventaire {
    private ArrayList<Objet> Inventaire;
    private int enMain;
    private int caseVide;


    //different boolean pour le craft
    private boolean craftArc = false;
    private boolean craftEpee = false;
    private boolean craftBaton = false;
    private boolean craftTissu = false;
    private boolean craftSac = false;

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
    //la fonction craft est appeler avec un bouton dans l'inventaire selon le booleen qui est active aussi dans l'inventaire selon le craft selectionner
    public void craft() {
        if (craftArc) {
            System.out.println("debut craft");
            craftArc = false;
            Baton batonTrouve = null;
            Fils filsTrouve = null;
            // Recherche dans l'inventaire
            for (Objet obj : Inventaire) {
                System.out.println("debut for");
                if (obj instanceof Baton && ((Baton) obj).getNbObjet() >= 3) {
                    System.out.println("baton trouver");
                    batonTrouve = (Baton) obj;

                }
                if (obj instanceof Fils && ((Fils) obj).getNbObjet() >= 3) {
                    System.out.println("fils trouve");
                    filsTrouve = (Fils) obj;
                }
            }
            // Si les deux ingrédients sont disponibles on supprime les ingredient et ajoute l'objet
            if (batonTrouve != null && filsTrouve != null) {
                supprimerIngredient(batonTrouve, 3);
                supprimerIngredient(filsTrouve, 3);
                System.out.println("arc craft");
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
                if (obj instanceof Bois && ((Bois) obj).getNbBloc() >= 2) {
                    boisTrouve = (Bois) obj;
                }
            }
            if(batonTrouve != null && boisTrouve != null){
                supprimerIngredient(batonTrouve,1);
                supprimerBloc(boisTrouve,2);
                this.ajoutObjet(new Epee(10,20,50,"Bois"));
            }
        }
        else if (craftBaton) {
            craftBaton = false;
            Bois boisTrouve = null;
            for (Objet obj : Inventaire) {
                if (obj instanceof Bois && ((Bois) obj).getNbBloc() >= 2) {
                    boisTrouve = (Bois) obj;
                }
            }
            if (boisTrouve != null) {
                supprimerBloc(boisTrouve, 2);
                boolean batonExiste = false;
                for (Objet obj : Inventaire) {
                    if (obj instanceof Baton) {
                        ((Baton) obj).ajouterIngredient();
                        batonExiste = true;
                    }
                }
                if (!batonExiste) {
                    this.ajoutObjet(new Baton());
                }
            }
        }
        else if (craftTissu) {
            craftTissu = false;
            Fils filsTrouve = null;
            for (Objet obj : Inventaire) {
                if (obj instanceof Fils && ((Fils) obj).getNbObjet() >= 2) {
                    filsTrouve = (Fils) obj;
                }
            }
            if (filsTrouve != null) {
                supprimerIngredient(filsTrouve, 2);
                boolean filsExiste = false;
                for (Objet obj : Inventaire) {
                    if (obj instanceof Fils) {
                        ((Fils) obj).ajouterIngredient();
                        filsExiste = true;
                    }
                }
                if (!filsExiste) {
                    this.ajoutObjet(new Fils());
                }
            }
        }
        else if (craftSac) {
            craftSac=false;
            Tissu tissuTrouve = null;
            for(Objet obj : Inventaire){
                if(obj instanceof Tissu && ((Tissu) obj).getNbObjet() >= 5) {
                    tissuTrouve = (Tissu) obj;
                }
            }
            if(tissuTrouve != null){
                supprimerIngredient(tissuTrouve,5);
                this.ajoutObjet(new Sac());
            }
        }

    }


    //differente fonction booleen;
    public void setCraftArc(){
        this.craftArc=true;
        System.out.println("setcraftarc reussi");
    }
    public void setCraftEpee(){
        this.craftEpee=true;
    }
    public void setCraftBaton(){this.craftBaton=true;}
    public void setCraftTissu(){this.craftTissu=true;}
    public void setCraftSac(){this.craftSac=true;}
}


