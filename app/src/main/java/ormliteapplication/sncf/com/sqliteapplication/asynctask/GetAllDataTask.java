package ormliteapplication.sncf.com.sqliteapplication.asynctask;

import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import ormliteapplication.sncf.com.sqliteapplication.db.dao.UserDao;
import ormliteapplication.sncf.com.sqliteapplication.model.User;


/**
 * Created by PZII11871 on 09/02/2017.
 */

public class GetAllDataTask extends AsyncTask<Void, Integer, List<User>> {

    private static final String TAG = "RecupererToutes";

    private ProgressDialog progressDialog;
    private Application application;

    public GetAllDataTask(ProgressDialog progressDialog, Application application) {
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
    protected List<User> doInBackground(Void... params) {
        return getTousUtilieurs();
    }

    @Override
    protected void onPostExecute(List<User> result) {
        super.onPostExecute(result);
        progressDialog.dismiss();
        afficherResultat();
    }

    private List<User> getTousUtilieurs() {
        List<User> res;
        UserDao userDao = new UserDao(application.getApplicationContext());
        userDao.open();
        long before = System.currentTimeMillis();
        res = userDao.queryForAll();
        long after = System.currentTimeMillis();
        long duration = after - before;
        Log.i(TAG, "RecupererToutesDonnesTask duration " + duration);
        userDao.close();
        return res;
    }

    private void afficherResultat() {
        Toast.makeText(application, "RecupererToutesDonnesTask Success", Toast.LENGTH_SHORT).show();
    }
}
