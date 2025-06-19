package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Block;

public class Block {
    private boolean traversabale;
    private boolean breakable;
    private String name;
    private int item;
    public Block(boolean traversabale, boolean breakable,  String name,int item) {
        this.traversabale = traversabale;
        this.breakable = breakable;
        this.name = name;
        this.item = item;
    }
    public boolean isTraversable() {
        return traversabale;
    }

    public int getItem() {
        return item;
    }

    public boolean isBreakable() {
        return breakable;
    }
    public String getName() {
        return name;
    }
}
