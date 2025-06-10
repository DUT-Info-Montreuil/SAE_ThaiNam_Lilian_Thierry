package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

import java.util.HashMap;

public class Recipe {
    private int[][] recette ;
    private int[][] resulat;

    public Recipe(int[][] h1, int[][] h2){
        this.recette = h1;
        this.resulat = h2;
    }

    public int[][] getRecette() {
        return this.recette;
    }

    public int[][] getResulat() {
        return this.resulat;
    }
}
