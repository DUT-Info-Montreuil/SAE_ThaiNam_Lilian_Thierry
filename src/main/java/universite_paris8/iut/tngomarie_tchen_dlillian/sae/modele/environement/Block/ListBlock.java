package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Block;


import java.util.HashMap;

public class ListBlock {
    HashMap<Integer, Block> list = new HashMap<Integer, Block>();
    public ListBlock(){
        créeRecipe();
    }

    private void créeRecipe() {
            list.put(0,new Block(true,false,"AIR",0));
            list.put(1,new Block(false,true,"DIRT",0));
            list.put(2,new Block(false,true,"STONE",1));
            list.put(3,new Block(false,true,"GRASS",7));
            list.put(4,new Block(false,true,"WOOD",0));
    }
        public Block getBlock(int id) {
            return list.get(id);
        }

}
