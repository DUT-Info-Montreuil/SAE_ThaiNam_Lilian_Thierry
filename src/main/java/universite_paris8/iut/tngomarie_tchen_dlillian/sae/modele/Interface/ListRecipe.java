package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface;

import java.util.HashMap;

public class ListRecipe {
    HashMap<Integer, Recipe> list = new HashMap<Integer, Recipe>();
    public ListRecipe(){
        créeRecipe();
    }

    public Recipe getList(int i) {
        return list.get(i);
    }

    private void créeRecipe() {
    //Arc (3 batons + 3 fils = 1 arc)
        int[][] n1 = new int[][] {{28,32},{3,3}};
        int[][] o1 = new int[][] {{8},{1}};
        list.put(0, new Recipe(n1, o1));
    //Fleche (2 batons + 1 pierre = 3 fleches)
        int[][] n2 = new int[][] {{28,1},{2,1}};
        int[][] o2 = new int[][] {{9},{3}};
        list.put(1, new Recipe(n2, o2));
    //Epee Bois (1 baton + 2 bois = 1 épée bois)
        int[][] n3 = new int[][] {{28,0},{1,2}};
        int[][] o3 = new int[][] {{10},{1}};
        list.put(2, new Recipe(n3, o3));

    //Pioche Bois (2 batons + 3 bois = 1 pioche bois)
        int[][] n4 = new int[][] {{28,0},{2,3}};
        int[][] o4 = new int[][] {{14},{1}};
        list.put(3, new Recipe(n4, o4));
    //Hache Bois (2 batons + 3 bois = 1 hache bois) - ID 18 n'existe pas dans ListObjet
        // int[][] n5 = new int[][] {{28,0},{2,3}};
        // int[][] o5 = new int[][] {{18},{1}};
        // list.put(4, new Recipe(n5, o5));
    //Baton (1 bois = 4 batons)
        int[][] n6 = new int[][] {{0},{1}};
        int[][] o6 = new int[][]{{28},{4}};
        list.put(5, new Recipe(n6, o6));
    //Tissu (2 fils = 1 tissu)
        int[][] n7 = new int[][] {{32},{2}};
        int[][] o7 = new int[][]{{29},{1}};
        list.put(6,new Recipe(n7,o7));
    }

    public HashMap<Integer, Recipe> getList() {
        return list;
    }
}
