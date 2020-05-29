package ormliteapplication.sncf.com.sqliteapplication.model;

/**
 * Created by PZII11871 on 07/02/2017.
 */

public class User {

    public static final String NOM_TABLE = "Utilisateur";

    public static final String NOM_COLONNE_ID = "id";
    public static final String NOM_COLONNE_NUM_CP = "numCp";
    public static final String NOM_COLONNE_NOM = "nom";
    public static final String NOM_COLONNE_PRENOM = "prenom";
    public static final String NOM_COLONNE_MOT_DE_PASSE = "motDePasse";
    public static final String NOM_COLONNE_PROFIL = "profil";
    public static final String NOM_COLONNE_ACTIF = "actif";
    public static final String NOM_COLONNE_RESPONSABLE = "responsable";
    public static final String NOM_COLONNE_SECTEUR = "secteur";
    public static final String NOM_COLONNE_VILLE = "ville";
    public static final String NOM_COLONNE_REGION = "region";
    public static final String NOM_COLONNE_DATE_CONNEXION = "dateConnexion";
    public static final String NOM_COLONNE_EQUIPE = "equipe";

    private long id;

    private String numCp;

    private String nom;

    private String prenom;

    private String motDePasse;

    private String profil;

    private String actif;

    private String responsable;

    private String secteur;

    private String ville;

    private String region;

    private String dateConnexion;

    private String equipe;

    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumCp() {
        return numCp;
    }

    public void setNumCp(String numCp) {
        this.numCp = numCp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String isActif() {
        return actif;
    }

    public void setActif(String actif) {
        this.actif = actif;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDateConnexion() {
        return dateConnexion;
    }

    public void setDateConnexion(String dateConnexion) {
        this.dateConnexion = dateConnexion;
    }
}
