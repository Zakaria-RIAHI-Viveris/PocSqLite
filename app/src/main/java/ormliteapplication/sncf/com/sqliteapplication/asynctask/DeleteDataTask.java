package ormliteapplication.sncf.com.sqliteapplication.asynctask;

import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import ormliteapplication.sncf.com.sqliteapplication.db.dao.UserDao;

/**
 * Created by PZII11871 on 09/02/2017.
 */
public class DeleteDataTask extends AsyncTask<Void, Integer, Void> {

    private final ProgressDialog progressDialog;
    private final Application application;

    private static final String TAG = "Supprimer";

    public DeleteDataTask(ProgressDialog progressDialog, Application application) {
        this.progressDialog = progressDialog;
        this.application = application;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        supprimerUtilisateurs();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        afficherResultat();
    }

    private void supprimerUtilisateurs() {
        UserDao userDao = new UserDao(application.getApplicationContext());
        userDao.open();
        long before = System.currentTimeMillis();
        userDao.suprimerTousLesUtilisteurs();
        long after = System.currentTimeMillis();
        long duration = after - before;
        Log.i(TAG, "SupprimerToutesDonnesTask duration " + duration);
        userDao.close();
    }

    private void afficherResultat() {
        Toast.makeText(application, "SupprimerToutesDonnesTask Success", Toast.LENGTH_SHORT).show();
    }
}
