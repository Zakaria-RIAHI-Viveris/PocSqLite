package ormliteapplication.sncf.com.sqliteapplication.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ormliteapplication.sncf.com.sqliteapplication.db.DatabaseHelper;
import ormliteapplication.sncf.com.sqliteapplication.model.User;

/**
 * Created by PZII11871 on 09/02/2017.
 */

public class UserDao {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { User.NOM_COLONNE_ID, User.NOM_COLONNE_NOM, User.NOM_COLONNE_PRENOM,
            User.NOM_COLONNE_NUM_CP, User.NOM_COLONNE_MOT_DE_PASSE, User.NOM_COLONNE_ACTIF,
            User.NOM_COLONNE_PROFIL, User.NOM_COLONNE_DATE_CONNEXION, User.NOM_COLONNE_REGION,
            User.NOM_COLONNE_RESPONSABLE, User.NOM_COLONNE_SECTEUR, User.NOM_COLONNE_VILLE,
            User.NOM_COLONNE_EQUIPE};

    public UserDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void initDatabase(int nbEntree) {
        database.beginTransaction();
        for (int i = 0; i < nbEntree; i++) {
            User user = new User();
            user.setNom("nom" + i);
            user.setPrenom("prenom" + i);
            user.setNumCp("num cp" + i);
            user.setMotDePasse("mot de passe" + i);
            user.setActif("true");
            user.setProfil("profil " + i);
            user.setDateConnexion("date connexion ");
            user.setRegion("region " + i);
            user.setResponsable("responsable " + i);
            user.setSecteur("secteur " + i);
            user.setVille("ville " + i);
            user.setEquipe("1");
            create(user);
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    private void create(User user) {
        ContentValues values = new ContentValues();
        values.put(User.NOM_COLONNE_NUM_CP, user.getNumCp());
        values.put(User.NOM_COLONNE_NOM, user.getNom());
        values.put(User.NOM_COLONNE_PRENOM, user.getPrenom());
        values.put(User.NOM_COLONNE_MOT_DE_PASSE, user.getMotDePasse());
        values.put(User.NOM_COLONNE_ACTIF, user.isActif());
        values.put(User.NOM_COLONNE_PROFIL, user.getProfil());
        values.put(User.NOM_COLONNE_RESPONSABLE, user.getResponsable());
        values.put(User.NOM_COLONNE_SECTEUR, user.getSecteur());
        values.put(User.NOM_COLONNE_REGION, user.getRegion());
        values.put(User.NOM_COLONNE_VILLE, user.getVille());
        values.put(User.NOM_COLONNE_DATE_CONNEXION, user.getDateConnexion());
        values.put(User.NOM_COLONNE_EQUIPE, user.getEquipe());
        long insertId = database.insert(User.NOM_TABLE, null, values);
        Cursor cursor = database.query(User.NOM_TABLE,
                allColumns, User.NOM_COLONNE_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        cursor.close();
    }

    public List<User> getUtilisateursParNumCp(String condition) {
        List<User> users = new ArrayList<>();

        String [] utilsateurProjection = {
                User.NOM_COLONNE_ID,
                User.NOM_COLONNE_NUM_CP,
                User.NOM_COLONNE_NOM,
                User.NOM_COLONNE_PRENOM,
                User.NOM_COLONNE_MOT_DE_PASSE,
                User.NOM_COLONNE_ACTIF,
                User.NOM_COLONNE_PROFIL,
                User.NOM_COLONNE_RESPONSABLE,
                User.NOM_COLONNE_SECTEUR,
                User.NOM_COLONNE_REGION,
                User.NOM_COLONNE_VILLE,
                User.NOM_COLONNE_DATE_CONNEXION,
                User.NOM_COLONNE_EQUIPE
        };

        String likeClause = User.NOM_COLONNE_NUM_CP + " LIKE ?";
        String [] likeArgs = {"%" + condition + "%"};

        Cursor cursor = database.query(User.NOM_TABLE, utilsateurProjection, likeClause, likeArgs, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User comment = cursorToUtilisateur(cursor);
            users.add(comment);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return users;
    }

    public List<User> queryForAll() {
        List<User> users = new ArrayList<>();

        Cursor cursor = database.query(User.NOM_TABLE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User comment = cursorToUtilisateur(cursor);
            users.add(comment);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return users;
    }

    public void suprimerTousLesUtilisteurs() {
        database.delete(User.NOM_TABLE, null, null);
    }

    private User cursorToUtilisateur(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(0));
        user.setNumCp(cursor.getString(1));
        user.setNom(cursor.getString(2));
        user.setPrenom(cursor.getString(3));
        user.setMotDePasse(cursor.getString(4));
        user.setActif(cursor.getString(5));
        user.setProfil(cursor.getString(6));
        user.setResponsable(cursor.getString(7));
        user.setSecteur(cursor.getString(8));
        user.setRegion(cursor.getString(9));
        user.setVille(cursor.getString(10));
        user.setDateConnexion(cursor.getString(11));
        user.setEquipe(cursor.getString(12));
        return user;
    }
}
