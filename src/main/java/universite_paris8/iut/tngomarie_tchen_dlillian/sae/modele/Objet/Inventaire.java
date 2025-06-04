package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.BlocInv;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Bois;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Météorite;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Météorite;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Arc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee.*;
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
        else if (craftEpeeBois) {
            craftEpeeBois=false;
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
                this.ajoutObjet(new EpeeBois());
            }
        }
        else if (craftEpeeFer) {
            craftEpeeFer=false;
            Baton batonTrouve = null;
            Fer ferTrouve = null;
            for(Objet obj : Inventaire){
                if(obj instanceof Baton && ((Baton) obj).getNbObjet() >= 1) {
                    batonTrouve = (Baton) obj;
                }
                if (obj instanceof Fer && ((Fer) obj).getNbObjet() >= 2) {
                    ferTrouve = (Fer) obj;
                }
            }
            if(batonTrouve != null && ferTrouve != null){
                supprimerIngredient(batonTrouve,1);
                supprimerIngredient(ferTrouve,2);
                this.ajoutObjet(new EpeeFer());
            }
        }
        else if (craftEpeeOr) {
            craftEpeeOr=false;
            Baton batonTrouve = null;
            Or orTrouve = null;
            for(Objet obj : Inventaire){
                if(obj instanceof Baton && ((Baton) obj).getNbObjet() >= 1) {
                    batonTrouve = (Baton) obj;
                }
                if (obj instanceof Or && ((Or) obj).getNbObjet() >= 2) {
                    orTrouve = (Or) obj;
                }
            }
            if(batonTrouve != null && orTrouve != null){
                supprimerIngredient(batonTrouve,1);
                supprimerIngredient(orTrouve,2);
                this.ajoutObjet(new EpeeOr());
            }
        }
        else if (craftEpeeMeteore) {
            craftEpeeMeteore=false;
            Baton batonTrouve = null;
            Météorite meteoriteTrouve = null;
            for(Objet obj : Inventaire){
                if(obj instanceof Baton && ((Baton) obj).getNbObjet() >= 1) {
                    batonTrouve = (Baton) obj;
                }
                if (obj instanceof Météorite && ((Météorite) obj).getNbBloc() >= 2) {
                    meteoriteTrouve = (Météorite) obj;
                }
            }
            if(batonTrouve != null && meteoriteTrouve != null){
                supprimerIngredient(batonTrouve,1);
                supprimerBloc(meteoriteTrouve,2);
                this.ajoutObjet(new EpeeMeteorite());
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
        else if (craftFils) {
            craftFils=false;
            Plante planteTrouve = null;
            for(Objet obj : Inventaire){
                if(obj instanceof Plante && ((Plante) obj).getNbObjet()>=2){
                    planteTrouve = (Plante) obj;
                }
            }
            if(planteTrouve != null){
                supprimerIngredient(planteTrouve,2);
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

    }


    //differente fonction booleen;
    public void setCraftArc(){
        this.craftArc=true;
        System.out.println("setcraftarc reussi");
    }
    public void setCraftEpeeBois(){
        this.craftEpeeBois=true;
    }
    public void setCraftEpeeFer(){this.craftEpeeFer=true;}
    public void setCraftEpeeOr(){this.craftEpeeOr=true;}
    public void setCraftEpeeMeteore(){this.craftEpeeMeteore=true;}
    public void setCraftBaton(){this.craftBaton=true;}
    public void setCraftTissu(){this.craftTissu=true;}
    public void setCraftSac(){this.craftSac=true;}
    public void setCraftFils(){this.craftFils=true;}

    public void affiche(){System.out.println(this.Inventaire);}
}


