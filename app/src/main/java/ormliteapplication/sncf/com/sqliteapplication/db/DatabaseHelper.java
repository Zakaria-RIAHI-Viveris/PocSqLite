package ormliteapplication.sncf.com.sqliteapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ormliteapplication.sncf.com.sqliteapplication.model.User;

/**
 * Created by PZII11871 on 09/02/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String NOM_BDD = "tablesqlite.db";
    private static final int VERSION_BDD = 1;

    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table "
            + User.NOM_TABLE + "(" + User.NOM_COLONNE_ID + " integer primary key autoincrement, "
            + User.NOM_COLONNE_NUM_CP + " text not null, "
            + User.NOM_COLONNE_NOM + " text not null, "
            + User.NOM_COLONNE_PRENOM + " text not null, "
            + User.NOM_COLONNE_MOT_DE_PASSE + " text not null, "
            + User.NOM_COLONNE_ACTIF + " text not null, "
            + User.NOM_COLONNE_PROFIL + " text not null, "
            + User.NOM_COLONNE_RESPONSABLE + " text not null, "
            + User.NOM_COLONNE_SECTEUR + " text not null, "
            + User.NOM_COLONNE_REGION + " text not null, "
            + User.NOM_COLONNE_VILLE + " text not null, "
            + User.NOM_COLONNE_DATE_CONNEXION + " text not null, "
            + User.NOM_COLONNE_EQUIPE + " text not null);";

    public DatabaseHelper(Context context) {
        super(context, NOM_BDD, null, VERSION_BDD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading version " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + User.NOM_TABLE);
        onCreate(db);
    }
}
