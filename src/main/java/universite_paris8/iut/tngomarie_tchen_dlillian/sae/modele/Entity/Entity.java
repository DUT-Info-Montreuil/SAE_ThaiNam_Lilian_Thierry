package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.traversable;

import java.util.Random;


public abstract class Entity {

	private DoubleProperty x,y;
	private double v; // vitesse de deplacement
	protected Environnement env;
	public static int compteur=0;
	private String id;
	private int pv;
	private double gravite;
	private boolean asaptrite;
	public Entity(double x, double y, double v, Environnement env,int pv) {
		this.pv=pv;
		this.x= new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		this.v = v;
		this.env=env;	
		this.id="A"+compteur;
		this.asaptrite=false;
		this.gravite=0;
		compteur++;
	}

	/*public Entity( double v, Environnement env, int pv) {
		this.pv=pv;
		Random random=new Random();
		int x = random.nextInt(env.getWidth()-1);
		int y=random.nextInt(env.getHeight()-1);
		this.x=new SimpleDoubleProperty(x);
		this.y =new SimpleDoubleProperty(y);
		this.v = v;
		this.env=env;
		this.id="A"+compteur;
		this.asaptrite=false;
		this.gravite=0;
		compteur++;
		//System.out.println("y" + y + "x" +x);
	}*/
	public void gotasprite(){this.asaptrite=true;}
	public boolean getasprite(){return this.asaptrite;}

	public  double getX() {
		return x.getValue();
	}
	public DoubleProperty getXProperty() {
		return x;
	}
	public  void setX(double n){
		x.setValue(n);
	}

	public  double getY() {
		return y.getValue();
	}
	public DoubleProperty getYProperty() {
		return y;
	}
	public  void setY(double n){
		y.setValue(n);
	}

	public String getId() {
		return id;
	}
	public void decrementerPv(int n) {
		this.pv-=n;	
	}
	public void incrementerPv(int n) {
		this.pv+=n;	
	}
	public double getV() {return this.v;}
	public void setV(double n){this.v=n;}
	public double getGravite() {return gravite;}
	public void setGravité(double n){this.gravite=n;}

	public boolean estVivant() {
		return this.pv>0;
	}
	public void meurt(){
		this.pv=0;
	}


	//permet de savoir si une action probabiliste se réalise 
	public static boolean reussitProba(double pourcent){
		double x= Math.random();
		double pp=pourcent/100;
		return (x<=pp);
	}
	public void gravité(){
		double lastY=0;
		if(this.y.getValue() == lastY){
			this.gravite=0;
		} else if (gravite>2) {
			gravite=2;
		} else
			this.gravite+=0.1;
		lastY=this.y.getValue();
	}
	public void deceleration(){
	this.v=this.v*0.99;
	}
//	public void colision() {
//		int futureX= Math.toIntExact(Math.round(this.getX() + this.getV()));
//		int futureY= Math.toIntExact(Math.round(this.getX() + this.getV()));
//		try {(traversable.valueOf(this.env.getString(this.env.getMap1()[futureX][futureY])))
//		}
//		catch ()
//	}

	public abstract void seDeplace();


    public abstract void agit();

	@Override
	public String toString() {
		return "x=" + x + ", y=" + y + ", id=" + id ;
	}

}
