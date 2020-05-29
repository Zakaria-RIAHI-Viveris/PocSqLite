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

public class LoadDataTask extends AsyncTask<Void, Integer, Void> {

    private static final String TAG = "ChargerDonnesTask";

    private int nbEntree;
    private ProgressDialog progressDialog;
    private Application application;

    public LoadDataTask(int nbEntree, ProgressDialog progressDialog, Application application) {
        this.nbEntree = nbEntree;
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
        initDb(nbEntree);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        afficherResultat();
    }

    private void initDb(int nbEntree) {
        UserDao userDao = new UserDao(application.getApplicationContext());
        userDao.open();
        long before = System.currentTimeMillis();
        userDao.initDatabase(nbEntree);
        long after = System.currentTimeMillis();
        long duration = after - before;
        Log.i(TAG, "ChargerDonnesTask duration " + duration);
        userDao.close();
    }

    private void afficherResultat() {
        Toast.makeText(application, "ChargerDonnesTask Success", Toast.LENGTH_SHORT).show();
    }
}
