package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface.ListRecipe;

public class Craft {
    private ListRecipe listRecipe ;
    private Inventaire inventaire;
    private ListObjet listObjet;

    public Craft(Inventaire inventaire){
        this.listRecipe= new ListRecipe();
        this.listObjet =new ListObjet();
        this.inventaire = inventaire;
    }

    public void craft(int clef){
        if(verif(clef)){
            for (int i = 0; i != this.listRecipe.getList(clef).getResulat().length ; i++) {
                for (int j = 0; j !=this.listRecipe.getList(clef).getResulat()[i][1] ; j++) {
                    this.inventaire.ajouterObjet2(ListObjet.getitem(this.listRecipe.getList(clef).getResulat()[i][0])){
                }
            }

            }
        }

    }

    private boolean verif(int i){
        int valid=0;
        int nbingredient = listRecipe.getList(i).getRecette().length;

        for(int j=0;j<nbingredient;j++){
            for(Objet obj: this.inventaire.getInventaire()){
                if((listRecipe.getList(i).getRecette()[j][0] == obj.getIdObjet()) && (listRecipe.getList(i).getRecette()[j][1] < obj.getNb())){
                valid++;
                }
            }
        }
        if (valid==nbingredient) {
            return true;
        }
        return false;
    }
}
