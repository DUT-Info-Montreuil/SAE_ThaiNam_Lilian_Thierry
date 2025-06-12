package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface.Recipe;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Bois;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Météorite;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Obsidienne;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Pierre;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Arc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.FlecheObjet;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Pioche.Pioche;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Sac;

import java.util.HashMap;

public class ListObjet {
    HashMap<Integer, Objet> list = new HashMap<Integer, Objet>();
    public ListObjet(){
        créeList();
    }

    public Objet getitem(int i) {
        return list.get(i);
    }

    public void créeList() {
        //Arc

        list.put(0, new Bois(1));
        //Fleche

        list.put(1, new Pierre(1));
        //Epee Bois

        list.put(2, new Fer(1));
        //Pioche Bois

        list.put(3, new Or(1));

//A CHANGER
        list.put(4, new Bois(0));

        list.put(5, new Météorite(1));
        //Tissu

        list.put(6, new Obsidienne(1));
        //
        list.put(7, new Baton(1));
        list.put(8, new Fer(1));
        list.put(9, new Fils(1));
        list.put(10, new Plante(1));
        list.put(11, new Tissu(1));
        list.put(12, new EpeeBois());
        list.put(13, new EpeeFer());
        list.put(14, new EpeeMeteorite());
        list.put(15, new EpeeOr());

        list.put(17, new Arc());
        list.put(18, new FlecheObjet(1));
        list.put(19, new Sac());
    }
}
