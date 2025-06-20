package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Bois;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Météorite;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Obsidienne;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.Pierre;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Arc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.FlecheObjet;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Pioche.PiocheEnBois;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Sac;

import java.util.HashMap;

public class ListObjet {
    HashMap<Integer, Objet> list = new HashMap<Integer, Objet>();
    public ListObjet(){
        créeRecipe();
    }

    private void créeRecipe() {
        list.put(0, new Bois(1));
        list.put(1, new Pierre(1));
        list.put(2, new Fer(1));
        list.put(3, new Or(1));
        list.put(4, new Charbon(0));
        list.put(5, new Météorite(1));
        list.put(6, new Obsidienne(1));
        list.put(7, new Plante(1));
        list.put(8, new Arc());
        list.put(9, new FlecheObjet(1));
        list.put(10, new EpeeBois());
        list.put(11, new EpeeFer());
        list.put(12, new EpeeMeteorite());
        list.put(13, new EpeeOr());
        list.put(14, new PiocheEnBois());
        list.put(17, new Arc());

        list.put(27, new Sac());

        list.put(28, new Baton(1));
        list.put(29, new Tissu(1));
        list.put(32, new Fils(1));
    }

    public Objet getItem(int i) {
        return list.get(i);
    }
}
