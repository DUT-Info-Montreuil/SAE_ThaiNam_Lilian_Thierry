package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface.Recipe;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Bois;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Météorite;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Obsidienne;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Pierre;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Arc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.FlecheObjet;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Pioche.Pioche;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Pioche.PiocheEnBois;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Sac;

import java.util.HashMap;

public class ListObjet {
    HashMap<Integer, Objet> list = new HashMap<Integer, Objet>();
    HashMap<Integer, ImageView> listVue = new HashMap<Integer, ImageView>();
    public ListObjet(){
        créeRecipe();
    }

    private void créeRecipe() {
        list.put(0, new Bois(1));
        listVue.put(0,new ImageView("Bois.png"));

        list.put(1, new Pierre(1));
        listVue.put(1,new ImageView("Texture de pierre.png"));

        list.put(2, new Fer(1));
        listVue.put(2,new ImageView("Fer.png"));

        list.put(3, new Or(1));
        listVue.put(3,new ImageView("Or.png"));

        list.put(4, new Charbon(1));
        listVue.put(4,new ImageView("Charbon.png"));

        list.put(5, new Météorite(1));
        listVue.put(5,new ImageView("Météorite.png"));

        list.put(6, new Obsidienne(1));
        listVue.put(6,new ImageView("Bois.png"));

        list.put(7, new Plante(1));
        listVue.put(7,new ImageView("Bois.png"));

        list.put(8, new Arc());
        listVue.put(8,new ImageView("Arc Bois.png"));

        list.put(9, new FlecheObjet(1));
        listVue.put(9,new ImageView("Fleche.png"));

        list.put(10, new EpeeBois());
        listVue.put(10,new ImageView("EpeeBois.png"));

        list.put(11, new EpeeFer());
        list.put(12, new EpeeMeteorite());
        list.put(13, new EpeeOr());

        list.put(14, new PiocheEnBois());
        listVue.put(14,new ImageView("Pioche en bois.png"));

        list.put(27, new Sac());

        list.put(28, new Baton(1));
        listVue.put(28,new ImageView("Baton.png"));

        list.put(29, new Tissu(1));
        listVue.put(29,new ImageView("Tissu.png"));

        list.put(32, new Fils(1));
        listVue.put(32,new ImageView("Fils.png"));
    }

    public Objet getItem(int i) {
        return list.get(i);
    }
}
