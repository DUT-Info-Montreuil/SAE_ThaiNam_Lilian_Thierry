module universite_paris8.iut.tngomarie.loupmouton24 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens universite_paris8.iut.tngomarie_tchen_dlillian.sae to javafx.fxml;
    exports universite_paris8.iut.tngomarie_tchen_dlillian.sae;
    opens universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele to javafx.fxml;
    exports universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele;
    opens universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity to javafx.fxml;
    exports universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;
    opens universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement to javafx.fxml;
    exports universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement;
    opens universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet to javafx.fxml;
    exports universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;
    opens universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil to javafx.fxml;
    exports universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil;
    opens universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface to javafx.fxml;
    exports universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface;
    opens universite_paris8.iut.tngomarie_tchen_dlillian.sae.listeneur to javafx.fxml;
    exports universite_paris8.iut.tngomarie_tchen_dlillian.sae.listeneur;
    opens universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue to javafx.fxml;
    exports universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue;
}