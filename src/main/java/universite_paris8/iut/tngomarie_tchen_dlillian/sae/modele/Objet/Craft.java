package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface.ListRecipe;

public class Craft {
    private ListRecipe listRecipe ;
    private Inventaire inventaire;

    public Craft(Inventaire inventaire){
        this.listRecipe= new ListRecipe();
        this.inventaire = inventaire;
    }

    public void crafting(int clef){
        if(verif(clef)){
            int[][] resultatRecette = this.listRecipe.getList(clef).getResulat();
            for (int i = 0; i != resultatRecette.length ; i++) {
                for (int j = 0; j != resultatRecette[i][1] ; j++) {
                    this.inventaire.ajoutObjet(this.inventaire.getListObjet().getItem(resultatRecette[i][0]));
                }
            }
        }
    }
    private boolean verif(int i) {
        int valid = 0;
        int[][] listeDeRecette = listRecipe.getList(i).getRecette();
        int nbingredient = listeDeRecette.length / 2;
        for (int j = 0; j < nbingredient; j++) {
            for (Objet obj : this.inventaire.getInventaire()) {
                if ((listeDeRecette[j][0] == obj.getIdObjet())
                        && (listeDeRecette[1][j] < obj.getNb())) {
                    valid++;
                }
            }
        }
        if (valid == nbingredient) {
            return true;
        }
        return false;
    }

}
