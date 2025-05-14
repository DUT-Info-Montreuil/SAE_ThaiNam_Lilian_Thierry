package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.Controleur;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;

import static universite_paris8.iut.tngomarie_tchen_dlillian.sae.Controleur.env;
//BoucleDeJeux.run();
public class BoucleDeJeux {
    static final int FRAMES=60;
        static Thread gameThread = new Thread();


    public static void run() {
        gameThread.start();
        double drawInterval = 1000000000.0 / FRAMES;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                delta--;
            }
        }
    }

    public static void update() {
        for(Entity e :env.entities) {
            e.seDeplace();
            System.out.println(e.getX()+":"+e.getY());
        }
    }
}
