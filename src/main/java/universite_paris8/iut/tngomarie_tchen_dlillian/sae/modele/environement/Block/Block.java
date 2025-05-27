package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Block;

public class Block {
    private boolean traversabale;
    private boolean breakable;
    private String name;
    public Block(boolean traversabale, boolean breakable,  String name) {
        this.traversabale = traversabale;
        this.breakable = breakable;
        this.name = name;
    }
    public boolean isTraversable() {
        return traversabale;
    }

    public boolean isBreakable() {
        return breakable;
    }
    public String getName() {
        return name;
    }
}
